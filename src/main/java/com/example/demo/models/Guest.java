package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//* guest model
@Entity
public class Guest {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String idType;
    private String idNumber;
    private String mobileNumber;

    public Guest(Long id, String name, String idType, String idNumber, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.mobileNumber = mobileNumber;
    }

    public Guest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
