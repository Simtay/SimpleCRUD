package com.nz.simplecrud.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * 
 * @author Emre Simtay <emre@simtay.com>
 */
@Entity
public class Address extends BaseEntity implements Serializable {
    
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
}