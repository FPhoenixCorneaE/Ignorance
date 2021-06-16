package com.livelearn.ignorance.model.db.dbhelper;


import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.HospitalDao;
import com.livelearn.ignorance.model.db.dbentity.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalDBHelper {

    private volatile static HospitalDBHelper mInstance;

    private HospitalDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static HospitalDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (HospitalDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new HospitalDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 所有医院信息
     */
    public List<Hospital> queryAllHospital() {
        return GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().list();
    }

    /**
     * 所有医院名字
     */
    public List<String> queryAllHospitalName() {
        List<Hospital> hospitalList = GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().list();
        List<String> hospitalNameList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            hospitalNameList.add(hospital.getHOSPITAL_NAME());
        }
        return hospitalNameList;
    }

    /**
     * 根据医院关键字模糊查询
     *
     * @param keyword 医院关键字
     * @return 医院实体列表
     */
    public List<Hospital> queryHospitalListByKeyword(String keyword) {
        return GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().where(HospitalDao.Properties.HOSPITAL_NAME.like("%" + keyword + "%")).list();
    }

    /**
     * 根据医院名字获得对应的医院id
     *
     * @param hospitalName 医院名字
     * @return 医院id
     */
    public String queryHospitalIdByHospitalName(String hospitalName) {
        List<Hospital> hospitalList = GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().where(HospitalDao.Properties.HOSPITAL_NAME.eq(hospitalName)).list();
        return !hospitalList.isEmpty() ? hospitalList.get(0).getHOSPITAL_ID() : "";
    }

    /**
     * 根据医院id获得医院实体
     *
     * @param hospitalId 医院id
     * @return 医院实体
     */
    public Hospital queryHospitalByHospitalId(String hospitalId) {
        List<Hospital> hospitalList = GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().where(HospitalDao.Properties.HOSPITAL_ID.eq(hospitalId)).list();
        return !hospitalList.isEmpty() ? hospitalList.get(0) : null;
    }

    /**
     * 根据医院id获取医院名字
     *
     * @param hospitalId 医院id
     * @return 医院名字
     */
    public String queryHospitalNameByHospitalId(String hospitalId) {
        List<Hospital> hospitalList = GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().where(HospitalDao.Properties.HOSPITAL_ID.eq(hospitalId)).list();
        return !hospitalList.isEmpty() ? hospitalList.get(0).getHOSPITAL_NAME() : "";
    }

    /**
     * 根据医院关键字获取医院名字列表
     *
     * @param keyword 医院关键字
     * @return 医院名字列表
     */
    public List<String> queryHospitalNameListByKeyword(String keyword) {
        List<Hospital> hospitalList = GreenDaoManager.getInstance().getSession().getHospitalDao().queryBuilder().where(HospitalDao.Properties.HOSPITAL_NAME.like("%" + keyword + "%")).list();
        List<String> hospitalNameList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            if (!hospitalNameList.contains(hospital.getHOSPITAL_NAME())) {
                hospitalNameList.add(hospital.getHOSPITAL_NAME());
            }
        }
        return hospitalNameList;
    }
}
