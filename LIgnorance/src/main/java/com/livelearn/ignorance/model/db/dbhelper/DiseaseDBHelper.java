package com.livelearn.ignorance.model.db.dbhelper;


import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.DiseaseDao;
import com.livelearn.ignorance.model.db.dbentity.Department;
import com.livelearn.ignorance.model.db.dbentity.Disease;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDBHelper {

    private volatile static DiseaseDBHelper mInstance;

    private DiseaseDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static DiseaseDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (DiseaseDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DiseaseDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据疾病名查询对应的id
     *
     * @param diseaseName 疾病名
     * @return Diseaseid
     */
    public String queryDiseaseIdByDiseaseName(String diseaseName) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Disease_name.eq(diseaseName)).list();
        return !diseaseList.isEmpty() ? diseaseList.get(0).getDisease_id() : "";
    }

    /**
     * 根据疾病id获得对应疾病实体
     *
     * @param diseaseId 疾病id
     * @return 疾病实体
     */
    public Disease queryDiseaseByDiseaseId(String diseaseId) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Disease_id.eq(diseaseId)).list();
        return !diseaseList.isEmpty() ? diseaseList.get(0) : null;
    }

    /**
     * 根据科室id获取疾病实体列表
     *
     * @param departmentId 科室id
     * @return 疾病实体列表
     */
    public List<Disease> queryDiseaseListByDepartmentId(String departmentId) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Dept_id.eq(departmentId)).list();
        List<Disease> diseases = new ArrayList<>();
        //疾病名称可能会重复
        List<String> diseaseNameList = new ArrayList<>();
        for (Disease disease : diseaseList) {
            if (!diseaseNameList.contains(disease.getDisease_name())) {
                diseaseNameList.add(disease.getDisease_name());
                diseases.add(disease);
            }
        }
        return diseases;
    }

    /**
     * 获取Disease表中所有的科室id
     */
    public List<String> queryAllDepartmentId() {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().list();
        List<String> diseaseIdList = new ArrayList<>();
        for (Disease disease : diseaseList) {
            if (!diseaseIdList.contains(disease.getDept_id())) {
                diseaseIdList.add(disease.getDept_id());
            }
        }
        return diseaseIdList;
    }

    /**
     * 查询所有疾病
     */
    public List<Disease> queryAllDisease() {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().list();
        List<Disease> diseases = new ArrayList<>();
        List<String> diseaseNameList = new ArrayList<>();
        for (Disease disease : diseaseList) {
            if (!diseaseNameList.contains(disease.getDisease_name())) {
                diseaseNameList.add(disease.getDisease_name());
                diseases.add(disease);
            }
        }
        return diseases;
    }

    /**
     * 根据关键字模糊查询疾病
     */
    public List<String> queryDiseaseNameListByKeyword(String keyword) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Disease_name.like("%" + keyword + "%")).list();
        List<String> diseaseNameList = new ArrayList<>();
        for (Disease disease : diseaseList) {
            if (!diseaseNameList.contains(disease.getDisease_name())) {
                diseaseNameList.add(disease.getDisease_name());
            }
        }
        return diseaseNameList;
    }

    /**
     * 根据疾病id获取对应二级科室名称
     */
    public String querySubDepartmentNameByDiseaseId(String diseaseId) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Disease_id.eq(diseaseId)).list();
        String subDepartmentName = "";
        if (!diseaseList.isEmpty()) {
            Department department = DepartmentDBHelper.getInstance().queryDepartmentByDepartmentId(diseaseList.get(0).getDept_id());
            if (department != null) {
                return department.getSub_dept_name();
            }
        }
        return subDepartmentName;
    }

    /**
     * 根据疾病名和科室id获取疾病实体类
     */
    public Disease queryDiseaseByDiseaseNameAndDeptId(String diseaseName, String departmentId) {
        List<Disease> diseaseList = GreenDaoManager.getInstance().getSession().getDiseaseDao().queryBuilder().where(DiseaseDao.Properties.Disease_name.eq(diseaseName)).where(DiseaseDao.Properties.Dept_id.eq(departmentId)).list();
        return !diseaseList.isEmpty() ? diseaseList.get(0) : null;
    }
}
