package com;

import java.io.Serializable;

public class Brand implements Comparable<Brand>, Serializable {

    protected String brandID;
    protected String brandName;
    protected String soundBrand;
    protected double price;

    public Brand() {
    }

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void print() {
        System.out.printf("%-10s|%-35s|%-17s|%-6.3f\n", brandID, brandName, soundBrand, price);
    }

    public String toString() {
        return "brandID= " + brandID + ", brandName= " + brandName + ", soundBrand= " + soundBrand + ", price= " + price;
    }

    public String outputString() {
        return brandID + ',' + brandName + ',' + brandName + ':' + price;
    }

    public int compareTo(Brand o) {
        return this.brandID.compareTo(o.getBrandID());
    }

}
