package com.clark.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer gender;
    private Date birth;
    private Date hired;
    private String SIN;
    private Integer departmentid;
    private Integer positionid;

    public Employee(Integer id, String firstName, String lastName, String email,
                    String phone, String address, Integer gender, String SIN,
                    Integer departmentid, Integer positionid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.birth = new Date();
        this.hired = new Date();
        this.SIN = SIN;
        this.departmentid = departmentid;
        this.positionid = positionid;
    }
}
