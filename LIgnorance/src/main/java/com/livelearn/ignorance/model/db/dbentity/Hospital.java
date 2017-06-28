package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 1.@Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
 * 2.@Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
 * 3.@Property：可以自定义字段名，注意外键不能使用该属性
 * 4.@NotNull：属性不能为空
 * 5.@Transient：使用该注释的属性不会被存入数据库的字段中
 * 6.@Unique：该属性值必须在数据库中是唯一值
 * 7.@Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
 */

@Entity
public class Hospital {

    //不能用int
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String HOSPITAL_ID;
    private String HOSPITAL_NAME;
    private String CITY_CODE;
    private String HOSPITAL_STS;

    @Generated(hash = 1853329061)
    public Hospital(Long id, String HOSPITAL_ID, String HOSPITAL_NAME,
                    String CITY_CODE, String HOSPITAL_STS) {
        this.id = id;
        this.HOSPITAL_ID = HOSPITAL_ID;
        this.HOSPITAL_NAME = HOSPITAL_NAME;
        this.CITY_CODE = CITY_CODE;
        this.HOSPITAL_STS = HOSPITAL_STS;
    }

    @Generated(hash = 1301721268)
    public Hospital() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHOSPITAL_ID() {
        return this.HOSPITAL_ID;
    }

    public void setHOSPITAL_ID(String HOSPITAL_ID) {
        this.HOSPITAL_ID = HOSPITAL_ID;
    }

    public String getHOSPITAL_NAME() {
        return this.HOSPITAL_NAME;
    }

    public void setHOSPITAL_NAME(String HOSPITAL_NAME) {
        this.HOSPITAL_NAME = HOSPITAL_NAME;
    }

    public String getCITY_CODE() {
        return this.CITY_CODE;
    }

    public void setCITY_CODE(String CITY_CODE) {
        this.CITY_CODE = CITY_CODE;
    }

    public String getHOSPITAL_STS() {
        return this.HOSPITAL_STS;
    }

    public void setHOSPITAL_STS(String HOSPITAL_STS) {
        this.HOSPITAL_STS = HOSPITAL_STS;
    }

}
