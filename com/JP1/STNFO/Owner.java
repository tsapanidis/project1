package com.JP1.STNFO;

public class Owner {
    private String Name;
    private int idowner;

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

    @Override
    public String toString() {
        return "{" +
                "Name='" + Name + '\'' +
                "}\n";
    }
}
