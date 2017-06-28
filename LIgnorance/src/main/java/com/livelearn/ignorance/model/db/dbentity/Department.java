package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Department {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String sub_dept_id;
    private String dept_id;
    private String dept_name;
    private String sub_dept_name;

    @Generated(hash = 810703747)
    public Department(Long id, String sub_dept_id, String dept_id, String dept_name,
                      String sub_dept_name) {
        this.id = id;
        this.sub_dept_id = sub_dept_id;
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.sub_dept_name = sub_dept_name;
    }

    @Generated(hash = 355406289)
    public Department() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSub_dept_id() {
        return this.sub_dept_id;
    }

    public void setSub_dept_id(String sub_dept_id) {
        this.sub_dept_id = sub_dept_id;
    }

    public String getDept_id() {
        return this.dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return this.dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getSub_dept_name() {
        return this.sub_dept_name;
    }

    public void setSub_dept_name(String sub_dept_name) {
        this.sub_dept_name = sub_dept_name;
    }
}
