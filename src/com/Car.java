package com;

public class Car implements Comparable<Car> {

    Brand brand;
    protected String CarID;
    protected String Color;
    protected String frameID;
    protected String engineID;

    public Car() {
    }

    public Car(Car obj) {
        this.CarID = obj.CarID;
        this.frameID = obj.frameID;
        this.brand = obj.brand;
        this.engineID = obj.engineID;
        this.Color = obj.Color;
    }

    public Car(String CarID, Brand brand, String Color, String frameID, String engineID) {
        this.CarID = CarID;
        this.brand = brand;
        this.Color = Color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String CarID) {
        this.CarID = CarID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getframeID() {
        return frameID;
    }

    public void setframeID(String frameID) {
        this.frameID = frameID;
    }

    public String getengineID() {
        return engineID;
    }

    public void setengineID(String engineID) {
        this.engineID = engineID;
    }

    public void print() {
        System.out.printf("%-10s|%-10s|%-10s|%20s|%-6f\n", CarID, brand, Color, frameID, engineID);
    }

    public String toString() {
        return CarID + ',' + brand.brandID + ',' + Color + ',' + frameID + ',' + engineID;
    }

    public String screenString() {
        Car c = new Car();
        return "CarID= " + CarID + ", BrandID= " + brand.brandID + ", Color= " + Color + ", FrameID= " + frameID + ", EngineID= " + engineID;
    }

    public int compareTo(Car c) {
        int d = this.brand.brandName.compareTo(c.brand.brandName);
        if (d != 0) {
            return d;
        }
        return this.CarID.compareTo(c.CarID);
    }

}
