package com.JP1.STNFO;

public class Vehicle {
    private String Plate;
    private int idvehicle;
    private int idowner;
    private int type;
    private String Brand;
    private String Model;

    public boolean equals(Object o) {
        if (!(o instanceof Vehicle))
            return false;
        Vehicle n = (Vehicle) o;
        return n.Plate.equals(Plate);
    }

    public int compareTo(Vehicle n) {
        int lastCmp = Plate.compareTo(n.Plate);
        return (lastCmp != 0 ? lastCmp : Plate.compareTo(n.Plate));
    }

    public int getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(int idvehicle) {
        this.idvehicle = idvehicle;
    }

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public String getPlate() {
        return Plate;
    }

    public void setPlate(String plate) {
        Plate = plate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
