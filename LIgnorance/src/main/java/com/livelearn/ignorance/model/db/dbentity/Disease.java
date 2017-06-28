package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Disease {

    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String disease_id;
    private String dept_id;
    private String source;
    private String status;
    private String disease_name;

    @Generated(hash = 1604931585)
    public Disease(Long id, String disease_id, String dept_id, String source,
                   String status, String disease_name) {
        this.id = id;
        this.disease_id = disease_id;
        this.dept_id = dept_id;
        this.source = source;
        this.status = status;
        this.disease_name = disease_name;
    }

    @Generated(hash = 1596955631)
    public Disease() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease_id() {
        return this.disease_id;
    }

    public void setDisease_id(String disease_id) {
        this.disease_id = disease_id;
    }

    public String getDept_id() {
        return this.dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisease_name() {
        return this.disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }
}
