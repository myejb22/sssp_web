package com.myejb22.sssp.entity;

import javax.persistence.*;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/25
 */
@Cacheable
@Table(name = "ssp_department")
@Entity
public class Department {

    private Integer id;
    private String deptName;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
