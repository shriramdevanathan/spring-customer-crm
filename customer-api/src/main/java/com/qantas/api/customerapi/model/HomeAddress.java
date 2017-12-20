package com.qantas.api.customerapi.model;

import javax.persistence.*;

@Entity
@Table(name="HOMEADDRESS")
@DiscriminatorValue("Home")
public class HomeAddress extends Address {
    @Column
    String unitNumber;

    @Column
    String apartmentNumber;

    @Column(name="address_type", updatable = false,insertable = false)
    private String address_type;

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}
