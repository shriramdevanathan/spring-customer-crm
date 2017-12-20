package com.qantas.api.customerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customer_id")
    @ApiModelProperty(notes = "The database generated customer ID")
    private Long id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private Date dob;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "customer_id")

    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Customer(String firstName, String lastName, Date dob, List<Address> addresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.addresses = addresses;
    }
    public Customer(){}
}
