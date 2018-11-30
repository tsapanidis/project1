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

    //GK: For Better convinience in F4
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (Name != null ? !Name.equals(owner.Name) : owner.Name != null) return false;
        if (Lname != null ? !Lname.equals(owner.Lname) : owner.Lname != null) return false;
        return Address != null ? Address.equals(owner.Address) : owner.Address == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
