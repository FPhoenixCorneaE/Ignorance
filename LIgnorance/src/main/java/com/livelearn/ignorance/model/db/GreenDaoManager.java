package com.livelearn.ignorance.model.db;

import android.database.sqlite.SQLiteDatabase;

import com.google.gson.reflect.TypeToken;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.application.IgnoranceApplication;
import com.livelearn.ignorance.common.FilesDirectory;
import com.livelearn.ignorance.model.db.dbdao.DaoMaster;
import com.livelearn.ignorance.model.db.dbdao.DaoSession;
import com.livelearn.ignorance.model.db.dbentity.Department;
import com.livelearn.ignorance.model.db.dbentity.Disease;
import com.livelearn.ignorance.model.db.dbentity.Hospital;
import com.livelearn.ignorance.model.db.dbentity.ProvinceCity;
import com.livelearn.ignorance.model.db.dbentity.Symptom;
import com.livelearn.ignorance.utils.AssetsUtils;
import com.livelearn.ignorance.utils.GsonUtils;
import com.livelearn.ignorance.utils.ResourceUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    /**
     * 变量放在主存区上，使用该变量的每个线程，都将从主存区拷贝一份到自己的工作区上进行操作。
     * volatile, 声明这个字段易变（可能被多个线程使用），Java内存模型负责各个线程的工作区与主存区的该字段的值保持同步，即一致性。
     * static, 声明这个字段是静态的（可能被多个实例共享），在主存区上该类的所有实例的该字段为同一个变量，即唯一性。
     * volatile, 声明变量值的一致性；static,声明变量的唯一性。
     * 此外，volatile同步机制不同于synchronized, 前者是内存同步，后者不仅包含内存同步（一致性），且保证线程互斥（互斥性）。
     * static 只是声明变量在主存上的唯一性，不能保证工作区与主存区变量值的一致性；除非变量的值是不可变的，即再加上final的修饰符，否则static声明的变量，不是线程安全的。
     */
    private volatile static GreenDaoManager mInstance;

    private GreenDaoManager() {
        // 通过DaoMaster的内部类DevOpenHelper，得到一个便利的SQLiteOpenHelper对象。
        // 并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(IgnoranceApplication.getInstance().getApplicationContext(), FilesDirectory.DB_DIR_NAME, null);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个Session指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(database);
        mDaoSession = mDaoMaster.newSession();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 懒汉式单例模式
     * 这个synchronized很重要，如果没有synchronized，那么使用getInstance()是有可能得到多个GreenDaoManager实例。
     */
    public static synchronized GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public void startLoadDB() {
        //清空一次数据
        getSession().getDepartmentDao().deleteAll();
        getSession().getDiseaseDao().deleteAll();
        getSession().getHospitalDao().deleteAll();
        getSession().getProvinceCityDao().deleteAll();
        getSession().getSymptomDao().deleteAll();

        //科室
        String departmentJson = ResourceUtils.getString(R.string.DB_Department);
        ArrayList<Department> departmentList = GsonUtils.getListFromJson(departmentJson, new TypeToken<List<Department>>() {
        });
        //疾病
        String diseaseJson = AssetsUtils.readFileFromAssets(IgnoranceApplication.getInstance(), "db_disease.json");
        ArrayList<Disease> diseaseList = GsonUtils.getListFromJson(diseaseJson, new TypeToken<List<Disease>>() {
        });
        //医院
        String hospitalJson = ResourceUtils.getString(R.string.DB_Hospital);
        ArrayList<Hospital> hospitalList = GsonUtils.getListFromJson(hospitalJson, new TypeToken<List<Hospital>>() {
        });
        //省市
        String provinceJson = ResourceUtils.getString(R.string.DB_Province_City);
        ArrayList<ProvinceCity> provinceCityList = GsonUtils.getListFromJson(provinceJson, new TypeToken<List<ProvinceCity>>() {
        });
        //症状
        String symptomJson = ResourceUtils.getString(R.string.DB_Symptom);
        ArrayList<Symptom> symptomList = GsonUtils.getListFromJson(symptomJson, new TypeToken<List<Symptom>>() {
        });

        //插入数据
        getSession().getDepartmentDao().insertInTx(departmentList);
        getSession().getDiseaseDao().insertInTx(diseaseList);
        getSession().getHospitalDao().insertInTx(hospitalList);
        getSession().getProvinceCityDao().insertInTx(provinceCityList);
        getSession().getSymptomDao().insertInTx(symptomList);
    }
}
