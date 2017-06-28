package com.livelearn.ignorance.model.db.dbhelper;


import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.SymptomDao;
import com.livelearn.ignorance.model.db.dbentity.Symptom;

import java.util.ArrayList;
import java.util.List;

public class SymptomDBHelper {

    private volatile static SymptomDBHelper mInstance;

    private SymptomDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static SymptomDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (SymptomDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new SymptomDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取部位列表
     */
    public List<String> queryPartList() {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().list();
        List<String> partList = new ArrayList<>();
        for (Symptom symptom : symptomList) {
            if (!partList.contains(symptom.getPart_name())) {
                partList.add(symptom.getPart_name());
            }
        }
        return partList;
    }

    /**
     * 根据部位名称获取部位id
     */
    public String queryPartIdByPartName(String partName) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Part_name.eq(partName)).list();
        return symptomList.size() != 0 ? symptomList.get(0).getPart_id() : "";
    }

    /**
     * 根据部位名称获取症状列表
     */
    public List<Symptom> querySymptomListByPartName(String partName) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Part_name.eq(partName)).list();
        //症状名称可能会重复
        List<String> symptomNameList = new ArrayList<>();
        List<Symptom> symptoms = new ArrayList<>();
        for (Symptom symptom : symptomList) {
            if (!symptomNameList.contains(symptom.getSymptom_key_word())) {
                symptomNameList.add(symptom.getSymptom_key_word());
                symptoms.add(symptom);
            }
        }
        return symptoms;
    }

    /**
     * 根据症状名获取对应症状实体类
     */
    public Symptom querySymptomBySymptomKeyword(String symptomKeyword) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Symptom_key_word.eq(symptomKeyword)).list();
        return !symptomList.isEmpty() ? symptomList.get(0) : null;
    }

    /**
     * 根据症状id获取对应症状实体类
     */
    public Symptom querySymptomBySymptomId(String symptomId) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Symptom_id.eq(symptomId)).list();
        return !symptomList.isEmpty() ? symptomList.get(0) : null;

    }

    /**
     * 根据症状名取所属科室id列表
     */
    public List<String> queryDepartmentIdListBySymptomKeyword(String symptomKeyword) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Symptom_key_word.eq(symptomKeyword)).list();
        List<String> departmentIdList = new ArrayList<>();
        for (Symptom symptom : symptomList) {
            if (!departmentIdList.contains(symptom.getDept_id())) {
                departmentIdList.add(symptom.getDept_id());
            }
        }
        return departmentIdList;
    }

    /**
     * 查询所有症状列表
     */
    public List<Symptom> queryAllSymptom() {
        return GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().list();
    }

    /**
     * 搜索模糊查询症状名称列表
     */
    public List<String> querySymptomNameListByKeyword(String keyword) {
        List<Symptom> symptomList = GreenDaoManager.getInstance().getSession().getSymptomDao().queryBuilder().where(SymptomDao.Properties.Symptom_key_word.like("%" + keyword + "%")).list();
        List<String> symptomNameList = new ArrayList<>();
        for (Symptom symptom : symptomList) {
            if (!symptomNameList.contains(symptom.getSymptom_key_word())) {
                symptomNameList.add(symptom.getSymptom_key_word());
            }
        }
        return symptomNameList;
    }
}
