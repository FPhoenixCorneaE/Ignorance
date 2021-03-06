package com.livelearn.ignorance.model.db.dbdao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.livelearn.ignorance.model.db.dbentity.Department;
import com.livelearn.ignorance.model.db.dbentity.Disease;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.model.db.dbentity.Hospital;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.model.db.dbentity.ProvinceCity;
import com.livelearn.ignorance.model.db.dbentity.Symptom;

import com.livelearn.ignorance.model.db.dbdao.DepartmentDao;
import com.livelearn.ignorance.model.db.dbdao.DiseaseDao;
import com.livelearn.ignorance.model.db.dbdao.DouBanBookCollectionDao;
import com.livelearn.ignorance.model.db.dbdao.HospitalDao;
import com.livelearn.ignorance.model.db.dbdao.LongTimeBookCollectionDao;
import com.livelearn.ignorance.model.db.dbdao.ProvinceCityDao;
import com.livelearn.ignorance.model.db.dbdao.SymptomDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig departmentDaoConfig;
    private final DaoConfig diseaseDaoConfig;
    private final DaoConfig douBanBookCollectionDaoConfig;
    private final DaoConfig hospitalDaoConfig;
    private final DaoConfig longTimeBookCollectionDaoConfig;
    private final DaoConfig provinceCityDaoConfig;
    private final DaoConfig symptomDaoConfig;

    private final DepartmentDao departmentDao;
    private final DiseaseDao diseaseDao;
    private final DouBanBookCollectionDao douBanBookCollectionDao;
    private final HospitalDao hospitalDao;
    private final LongTimeBookCollectionDao longTimeBookCollectionDao;
    private final ProvinceCityDao provinceCityDao;
    private final SymptomDao symptomDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        departmentDaoConfig = daoConfigMap.get(DepartmentDao.class).clone();
        departmentDaoConfig.initIdentityScope(type);

        diseaseDaoConfig = daoConfigMap.get(DiseaseDao.class).clone();
        diseaseDaoConfig.initIdentityScope(type);

        douBanBookCollectionDaoConfig = daoConfigMap.get(DouBanBookCollectionDao.class).clone();
        douBanBookCollectionDaoConfig.initIdentityScope(type);

        hospitalDaoConfig = daoConfigMap.get(HospitalDao.class).clone();
        hospitalDaoConfig.initIdentityScope(type);

        longTimeBookCollectionDaoConfig = daoConfigMap.get(LongTimeBookCollectionDao.class).clone();
        longTimeBookCollectionDaoConfig.initIdentityScope(type);

        provinceCityDaoConfig = daoConfigMap.get(ProvinceCityDao.class).clone();
        provinceCityDaoConfig.initIdentityScope(type);

        symptomDaoConfig = daoConfigMap.get(SymptomDao.class).clone();
        symptomDaoConfig.initIdentityScope(type);

        departmentDao = new DepartmentDao(departmentDaoConfig, this);
        diseaseDao = new DiseaseDao(diseaseDaoConfig, this);
        douBanBookCollectionDao = new DouBanBookCollectionDao(douBanBookCollectionDaoConfig, this);
        hospitalDao = new HospitalDao(hospitalDaoConfig, this);
        longTimeBookCollectionDao = new LongTimeBookCollectionDao(longTimeBookCollectionDaoConfig, this);
        provinceCityDao = new ProvinceCityDao(provinceCityDaoConfig, this);
        symptomDao = new SymptomDao(symptomDaoConfig, this);

        registerDao(Department.class, departmentDao);
        registerDao(Disease.class, diseaseDao);
        registerDao(DouBanBookCollection.class, douBanBookCollectionDao);
        registerDao(Hospital.class, hospitalDao);
        registerDao(LongTimeBookCollection.class, longTimeBookCollectionDao);
        registerDao(ProvinceCity.class, provinceCityDao);
        registerDao(Symptom.class, symptomDao);
    }
    
    public void clear() {
        departmentDaoConfig.clearIdentityScope();
        diseaseDaoConfig.clearIdentityScope();
        douBanBookCollectionDaoConfig.clearIdentityScope();
        hospitalDaoConfig.clearIdentityScope();
        longTimeBookCollectionDaoConfig.clearIdentityScope();
        provinceCityDaoConfig.clearIdentityScope();
        symptomDaoConfig.clearIdentityScope();
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public DiseaseDao getDiseaseDao() {
        return diseaseDao;
    }

    public DouBanBookCollectionDao getDouBanBookCollectionDao() {
        return douBanBookCollectionDao;
    }

    public HospitalDao getHospitalDao() {
        return hospitalDao;
    }

    public LongTimeBookCollectionDao getLongTimeBookCollectionDao() {
        return longTimeBookCollectionDao;
    }

    public ProvinceCityDao getProvinceCityDao() {
        return provinceCityDao;
    }

    public SymptomDao getSymptomDao() {
        return symptomDao;
    }

}
