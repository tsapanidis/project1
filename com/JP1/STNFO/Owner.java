package com.JP1.STNFO;

public class Owner {
    private String Name;
    private int idowner;
    private String Lname;
    private String Address;

    public int getIdowner() {
        return idowner;
    }

    public void setIdowner(int idowner) {
        this.idowner = idowner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "Name='" + Name + '\'' +
                "}\n";
    }
}
