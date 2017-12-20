package com.qantas.api.customerapi.model;

import javax.persistence.*;

@Entity
@Table(name="OFFICEADDRESS")
@DiscriminatorValue("Office")
public class OfficeAddress extends Address {
    @Column(name="address_type", updatable = false,insertable = false)
    private String address_type;

    @Column
    String complexName;

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }
}
