package com.livelearn.ignorance.model.db.dbhelper;


import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.DepartmentDao;
import com.livelearn.ignorance.model.db.dbentity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDBHelper {

    private volatile static DepartmentDBHelper mInstance;

    private DepartmentDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static DepartmentDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (DepartmentDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DepartmentDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 根据科室名获取二级科室id
     *
     * @param departmentName 科室名
     * @return 二级科室id
     */
    public String querySubDepartmentIdByDepartmentName(String departmentName) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Sub_dept_name.eq(departmentName)).list();
        return !departmentList.isEmpty() ? departmentList.get(0).getSub_dept_id() : "";
    }

    /**
     * 获取所有一级科室名称
     */
    public List<String> queryAllDepartmentName() {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().list();
        List<String> departmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            if (!departmentNameList.contains(department.getDept_name()))
                departmentNameList.add(department.getDept_name());
        }
        return departmentNameList;
    }

    /**
     * 获得医生类型的一级科室名称
     */
    public List<String> queryDoctorTypeDepartmentName() {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().list();
        List<String> departmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            if (!departmentNameList.contains(department.getDept_name()) && !department.getDept_id().startsWith("2"))
                departmentNameList.add(department.getDept_name());
        }
        return departmentNameList;
    }

    /**
     * 获得护士类型的一级科室名称
     */
    public List<String> queryNurseTypeDepartmentName() {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().list();
        List<String> departmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            if (!departmentNameList.contains(department.getDept_name()) && department.getDept_id().startsWith("2")) {
                departmentNameList.add(department.getDept_name());
            }
        }
        return departmentNameList;
    }

    /**
     * 根据一级科室名获取二级科室列表
     */
    public List<Department> querySubDepartmentListByDepartmentName(String departmentName) {
        return GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Dept_name.eq(departmentName)).list();
    }

    /**
     * 根据科室id获取科室
     */
    public Department queryDepartmentByDepartmentId(String departmentId) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Sub_dept_id.eq(departmentId)).list();
        return !departmentList.isEmpty() ? departmentList.get(0) : null;
    }

    /**
     * 获取所有科室
     */
    public List<Department> queryAllDepartment() {
        return GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().list();
    }

    /**
     * 获取所有二级科室名称
     */
    public List<String> queryAllSubDepartmentName() {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().list();
        List<String> subDepartmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            subDepartmentNameList.add(department.getSub_dept_name());
        }
        return subDepartmentNameList;
    }

    /**
     * 根据科室关键字模糊查询二级科室名称
     */
    public List<String> querySubDepartmentNameListByKeyword(String keyword) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Sub_dept_name.like("%" + keyword + "%")).list();
        List<String> subDepartmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            subDepartmentNameList.add(department.getSub_dept_name());
        }
        return subDepartmentNameList;
    }


    /**
     * 根据一级科室名获取二级科室名称列表
     */
    public List<String> querySubDepartmentNameListByDepartmentName(String departmentName) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Dept_name.eq(departmentName)).list();
        List<String> subDepartmentNameList = new ArrayList<>();
        for (Department department : departmentList) {
            subDepartmentNameList.add(department.getSub_dept_name());
        }
        return subDepartmentNameList;
    }

    /**
     * 根据二级科室名查询二级科室id
     */
    public String querySubDepartmentIdBySubDepartmentName(String subDepartmentName) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Sub_dept_name.eq(subDepartmentName)).list();
        return !departmentList.isEmpty() ? departmentList.get(0).getSub_dept_id() : "";
    }

    /**
     * 根据二级科室名查询一级科室名
     */
    public String queryDepartmentNameBySubDepartmentName(String subDepartmentName) {
        List<Department> departmentList = GreenDaoManager.getInstance().getSession().getDepartmentDao().queryBuilder().where(DepartmentDao.Properties.Sub_dept_name.eq(subDepartmentName)).list();
        return !departmentList.isEmpty() ? departmentList.get(0).getDept_name() : "";
    }
}
