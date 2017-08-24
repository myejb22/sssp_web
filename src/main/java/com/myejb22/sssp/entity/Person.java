package com.myejb22.sssp.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Andy
 * @date 2017/8/24
 */
@Entity(name = "jpa_person")
public class Person {

    private Integer id;
    private String lastName;
    private Integer age;
    private Date birthDay;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(columnDefinition = "DATE")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
