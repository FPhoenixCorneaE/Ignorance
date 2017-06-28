package com.livelearn.ignorance.model.db.dbhelper;

import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.model.db.dbdao.ProvinceCityDao;
import com.livelearn.ignorance.model.db.dbentity.ProvinceCity;

import java.util.ArrayList;
import java.util.List;

public class ProvinceCityDBHelper {

    private volatile static ProvinceCityDBHelper mInstance;

    private ProvinceCityDBHelper() {
    }

    /**
     * 饿汉式单例模式
     */
    public static ProvinceCityDBHelper getInstance() {
        if (mInstance == null) {
            synchronized (ProvinceCityDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new ProvinceCityDBHelper();
                }
            }
        }
        return mInstance;
    }

    /**
     * 从省份id获得省名
     */
    public String queryProvinceNameByProvinceId(String provinceId) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.PROVINCE_CODE.eq(provinceId)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getPROVINCE_NAME() : "";
    }

    /**
     * 从城市id获得城市名
     */
    public String queryCityNameByCityId(String cityId) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.CITY_CODE.eq(cityId)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getCITY_NAME() : "";
    }


    /**
     * 从区id获得区名
     */
    public String queryCountyNameByCountyId(String countyId) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.COUNTY_CODE.eq(countyId)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getCOUNTY_NAME() : "";
    }

    /**
     * 获得省份名称列表
     */
    public List<String> queryAllProvinceName() {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().list();
        List<String> provinceNameList = new ArrayList<>();
        for (ProvinceCity t : provinceCityList) {
            if (!provinceNameList.contains(t.getPROVINCE_NAME())) {
                provinceNameList.add(t.getPROVINCE_NAME());
            }
        }
        return provinceNameList;
    }

    /**
     * 根据省份名称查询该省份所有城市名称列表
     */
    public List<String> queryCityNameListByProvinceName(String provinceName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.PROVINCE_NAME.eq(provinceName)).list();
        List<String> cityNameList = new ArrayList<>();
        for (ProvinceCity provinceCity : provinceCityList) {
            if (!cityNameList.contains(provinceCity.getCITY_NAME())) {
                cityNameList.add(provinceCity.getCITY_NAME());
            }
        }
        return cityNameList;
    }

    /**
     * 根据省份id查询省下的所有城市实体类
     */
    public List<ProvinceCity> queryCityListByProvinceId(String provinceId) {
        return GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.PROVINCE_CODE.eq(provinceId)).list();
    }

    /**
     * 根据省份id查询该省份所有城市名称列表
     */
    public List<String> queryCityNameListByProvinceId(String provinceId) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.PROVINCE_NAME.eq(provinceId)).list();
        List<String> cityNameList = new ArrayList<>();
        for (ProvinceCity provinceCity : provinceCityList) {
            if (!cityNameList.contains(provinceCity.getCITY_NAME())) {
                cityNameList.add(provinceCity.getCITY_NAME());
            }
        }
        return cityNameList;
    }

    /**
     * 根据城市名获得区列表
     */
    public List<String> queryCountNameListByCityName(String cityName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.CITY_NAME.eq(cityName)).list();
        List<String> countNameList = new ArrayList<>();
        for (ProvinceCity provinceCity : provinceCityList) {
            countNameList.add(provinceCity.getCOUNTY_NAME());
        }
        return countNameList;
    }

    /**
     * 根据省名获得id
     */
    public String queryProvinceIdByProvinceName(String provinceName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.PROVINCE_NAME.eq(provinceName)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getPROVINCE_CODE() : "";
    }

    /**
     * 根据城市名称获得城市id
     */
    public String queryCityIdByCityName(String cityName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.CITY_NAME.eq(cityName)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getCITY_CODE() : "";
    }

    /**
     * 根据区名+市名+省名获得信息
     */
    public ProvinceCity queryProvinceCityByCountyNameAndCityNameAndProvinceName(String countyName, String cityName, String provinceName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(
                ProvinceCityDao.Properties.COUNTY_NAME.eq(countyName),
                ProvinceCityDao.Properties.CITY_NAME.eq(cityName),
                ProvinceCityDao.Properties.PROVINCE_NAME.eq(provinceName)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0) : null;
    }

    /**
     * 根据关键字模糊搜索城市
     */
    public List<String> queryCityNameListByKeyword(String keyword) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.CITY_NAME.like("%" + keyword + "%")).list();
        List<String> cityNameList = new ArrayList<>();
        for (ProvinceCity provinceCity : provinceCityList) {
            if (!cityNameList.contains(provinceCity.getCITY_NAME())) {
                cityNameList.add(provinceCity.getCITY_NAME());
            }
        }
        return cityNameList;
    }

    /**
     * 根据城市名获得所属省
     */
    public String queryProviceNameByCityName(String cityName) {
        List<ProvinceCity> provinceCityList = GreenDaoManager.getInstance().getSession().getProvinceCityDao().queryBuilder().where(ProvinceCityDao.Properties.CITY_NAME.eq(cityName)).list();
        return !provinceCityList.isEmpty() ? provinceCityList.get(0).getPROVINCE_NAME() : "";
    }
}