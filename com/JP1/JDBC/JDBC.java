package com.JP1.JDBC;
import com.JP1.STNFO.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class JDBC {

    private Connection conn = null;

    public JDBC(String username,String password) {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //GK: This is getting too long
        String DB_URL = "jdbc:mysql://localhost:3306/project1?useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
        try {
            Class.forName(JDBC_DRIVER);

            this.conn = DriverManager.getConnection(DB_URL,username,password);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public HashMap<String,Insurance> RetrieveExpDates(){
        String sql = "SELECT expdate,Plate FROM vehicles";
        return GetInsurance(sql);
    }

    private HashMap<String,Insurance> GetInsurance(String query){
        HashMap<String,Insurance> insurances= new HashMap<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                insurances.put(rs.getString("Plate"),new Insurance());
                insurances.get(rs.getString("Plate")).setExpDate(rs.getDate("expdate"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return insurances;
    }

    public ArrayList<Vehicle> RetrievePlates(){
        String sql = "SELECT Plate FROM vehicles";
        return GetVehicle(sql);
    }

   /* private ArrayList<Vehicle> RetrieveOwnedPlates(String Name) {
        String sql = "SELECT Plate FROM vehicles,owner WHERE owner.name='"+Name+"' AND owner.idOwner = vehicles.idOwner";
        return GetVehicle(sql);
    }*/

    private ArrayList<Vehicle> GetVehicle(String query){
        ArrayList<Vehicle> vehicles= new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                vehicles.add(new Vehicle());
                vehicles.get(vehicles.size()-1).setPlate(rs.getString("Plate"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return vehicles;
    }

    public HashMap<String,Owner> RetrieveOwners(){
        String sql = "SELECT name,Plate FROM owner,vehicles WHERE owner.idOwner = vehicles.idOwner";
        return GetOwner(sql);
    }

    private HashMap<String,Owner> GetOwner(String query){
        HashMap<String,Owner> owners= new HashMap<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                owners.put(rs.getString("Plate"),new Owner());
                owners.get(rs.getString("Plate")).setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return owners;
    }

    public void SetVehicle(List<Vehicle> vehicles,List<Insurance> insurances){
        Map<String, Date> expirationMap = new HashMap<>();
        for (Vehicle vehicle: vehicles) {
        for (Insurance insurance : insurances){
            if (vehicle.getIdvehicle() == insurance.getIdvehicle()) {
                expirationMap.put(vehicle.getPlate(), insurance.getExpirationDate());
                break;
            }
        }
        }
        String sql = "INSERT INTO vehicles VALUES ";
        int i=0;
        for (Vehicle vehicle: vehicles){
            if (i>0){
                sql=sql.concat(",");
            }
            sql=sql.concat( "("+vehicle.getIdvehicle()+",");
            sql =sql.concat( "'"+vehicle.getPlate()+"',");
            sql =sql.concat( "'"+expirationMap.get(vehicle.getPlate())+"',");
            sql =sql.concat( ""+vehicle.getIdowner()+")");
            i++;
        }
        UpdateDB(sql);
    }

    public void SetOwner(List<Owner> owners){
        String sql = "INSERT INTO owner VALUES ";
        int i=0;
        for (Owner owner: owners){
            if (i>0){
                sql=sql.concat(",");
            }
            sql=sql.concat( "("+owner.getIdowner()+",");
            sql =sql.concat( "'"+owner.getName()+"')");
            i++;
        }
        UpdateDB(sql);
    }

    private void UpdateDB(String query){
        try {
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
    }

    public void close(){
        try {
            this.conn.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
    }
}
