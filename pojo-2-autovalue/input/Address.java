package com.edsilfer.android.domain.entity;


import java.util.Date;

public class Address {

    private String streetAddress1;
    private String streetAddress2;
    private String number;
    private String complement;
    private String neighborhood;
    private City city;
    private State state;
    private Country country;
    private String zipCode;
    private Date startLiving;
    private Date endLiving;

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getStartLiving() {
        return startLiving;
    }

    public void setStartLiving(Date startLiving) {
        this.startLiving = startLiving;
    }

    public Date getEndLiving() {
        return endLiving;
    }

    public void setEndLiving(Date endLiving) {
        this.endLiving = endLiving;
    }
}
