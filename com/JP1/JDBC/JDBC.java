package com.JP1.JDBC;
import com.JP1.STNFO.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class JDBC {

    private Connection conn = null;

    public JDBC(String username,String password) throws SQLException,ClassNotFoundException{
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //GK: This is getting too long
        String DB_URL = "jdbc:mysql://localhost:3306/project1?useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(DB_URL,username,password);
    }

    public HashMap<String,Vehicle> RetrieveVehicles() throws SQLException{
        String sql = "SELECT Plate,type,Brand,model FROM vehicles";
        return GetVehicle(sql);
    }

    private HashMap<String,Vehicle> GetVehicle(String query) throws SQLException{
        HashMap<String,Vehicle> vehichles= new HashMap<>();
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                vehichles.put(rs.getString("Plate"),new Vehicle());
                vehichles.get(rs.getString("Plate")).setType(rs.getInt("type"));
                vehichles.get(rs.getString("Plate")).setBrand(rs.getString("Brand"));
                vehichles.get(rs.getString("Plate")).setModel(rs.getString("model"));
            }
            rs.close();
            stmt.close();
        return vehichles;
    }

    public HashMap<String,Insurance> RetrieveExpDates() throws SQLException{
        String sql = "SELECT expdate,Plate FROM vehicles";
        return GetInsurance(sql);
    }

    private HashMap<String,Insurance> GetInsurance(String query)throws SQLException{
        HashMap<String,Insurance> insurances= new HashMap<>();
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                insurances.put(rs.getString("Plate"),new Insurance());
                insurances.get(rs.getString("Plate")).setExpDate(rs.getDate("expdate"));
            }
            rs.close();
            stmt.close();
        return insurances;
    }

    public HashMap<String,Owner> RetrieveOwners() throws SQLException{
        String sql = "SELECT name,Plate,Lname,Address FROM owner,vehicles WHERE owner.idOwner = vehicles.idOwner";
        return GetOwner(sql);
    }

    private HashMap<String,Owner> GetOwner(String query) throws SQLException{
        HashMap<String,Owner> owners= new HashMap<>();
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                owners.put(rs.getString("Plate"),new Owner());
                owners.get(rs.getString("Plate")).setName(rs.getString("name"));
                owners.get(rs.getString("Plate")).setLname(rs.getString("Lname"));
                owners.get(rs.getString("Plate")).setAddress(rs.getString("Address"));
            }
            rs.close();
            stmt.close();
        return owners;
    }

    public void SetVehicle(List<Vehicle> vehicles,List<Insurance> insurances)throws SQLException{
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
            sql =sql.concat( "'"+new java.sql.Date(expirationMap.get(vehicle.getPlate()).getTime())+"',");
            sql =sql.concat( ""+vehicle.getIdowner()+",");
            sql =sql.concat( ""+vehicle.getType()+",");
            sql =sql.concat( "'"+vehicle.getBrand()+"',");
            sql =sql.concat( "'"+vehicle.getModel()+"')");
            i++;
        }
        UpdateDB(sql);
    }

    public void SetOwner(List<Owner> owners) throws SQLException{
        String sql = "INSERT INTO owner VALUES ";
        int i=0;
        for (Owner owner: owners){
            if (i>0){
                sql=sql.concat(",");
            }
            sql=sql.concat( "("+owner.getIdowner()+",");
            sql =sql.concat( "'"+owner.getName()+"',");
            sql =sql.concat( "'"+owner.getLname()+"',");
            sql =sql.concat( "'"+owner.getAddress()+"')");
            i++;
        }
        UpdateDB(sql);
    }

    private void UpdateDB(String query) throws SQLException{
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
    }

    public void close() throws SQLException{
            this.conn.close();
    }
}
