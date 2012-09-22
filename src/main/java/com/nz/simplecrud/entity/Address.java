package com.nz.simplecrud.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressid;
    
    @Column(length = 50)
    private String street;
    
    @Column(length = 50)
    private String suburb;
    
    @Column(length = 50)
    private String city;
    
    @Column(length = 50)
    private String country;

    public Address() {
    }

    public Integer getAddressid() {
        return this.addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return this.suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return addressid != null ? this.getClass().hashCode() + addressid.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Address)) {
            return false;
        } else if (((Address) obj).addressid.equals(
                this.addressid)) {
            return true;
        } else {
            return false;
        }
    }
}