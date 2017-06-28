package com.livelearn.ignorance.model.db.dbentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Symptom {

    @Id(autoincrement = true)
    private Long id;
    private String symptom_id;
    private String part_name;
    private String part_id;
    private String sex;
    private String dept_id;
    private String symptom_desc;
    private String symptom_key_word;

    @Generated(hash = 532399712)
    public Symptom(Long id, String symptom_id, String part_name, String part_id,
                   String sex, String dept_id, String symptom_desc,
                   String symptom_key_word) {
        this.id = id;
        this.symptom_id = symptom_id;
        this.part_name = part_name;
        this.part_id = part_id;
        this.sex = sex;
        this.dept_id = dept_id;
        this.symptom_desc = symptom_desc;
        this.symptom_key_word = symptom_key_word;
    }

    @Generated(hash = 2107028981)
    public Symptom() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymptom_id() {
        return this.symptom_id;
    }

    public void setSymptom_id(String symptom_id) {
        this.symptom_id = symptom_id;
    }

    public String getPart_name() {
        return this.part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getPart_id() {
        return this.part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept_id() {
        return this.dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getSymptom_desc() {
        return this.symptom_desc;
    }

    public void setSymptom_desc(String symptom_desc) {
        this.symptom_desc = symptom_desc;
    }

    public String getSymptom_key_word() {
        return this.symptom_key_word;
    }

    public void setSymptom_key_word(String symptom_key_word) {
        this.symptom_key_word = symptom_key_word;
    }
}
