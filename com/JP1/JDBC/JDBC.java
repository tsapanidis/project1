package com.JP1.JDBC;
import com.JP1.STNFO.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class JDBC {

    private Connection conn = null;

    public JDBC(String username,String password) {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/project1?useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName(JDBC_DRIVER);

            this.conn = DriverManager.getConnection(DB_URL,username,password);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

   /* private ArrayList<com.JP1.STNFO.Insurance> RetrieveExpDate(String Plate){
        String sql = "SELECT expdate FROM vehicles WHERE plate='"+Plate+"'";
        return GetInsurance(sql);
    }*/

    public HashMap<String,Insurance> RetrieveExpDates(){
        String sql = "SELECT expdate,Plate FROM vehicles";
        return GetInsurance(sql);
    }

    private HashMap<String,Insurance> GetInsurance(String query){
        HashMap<String,Insurance> ins= new HashMap<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                ins.put(rs.getString("Plate"),new Insurance());
                ins.get(rs.getString("Plate")).setExpDate(rs.getDate("expdate"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return ins;
    }

    public ArrayList<Vehicle> RetrievePlates(){
        String sql = "SELECT Plate,idVehicles,idOwner FROM vehicles";
        return GetVehicle(sql);
    }

    private ArrayList<Vehicle> RetrieveOwnedPlates(String Name) {
        String sql = "SELECT Plate FROM vehicles,owner WHERE owner.name='"+Name+"' AND owner.idOwner = vehicles.idOwner";
        return GetVehicle(sql);
    }

    private ArrayList<Vehicle> GetVehicle(String query){
        ArrayList<Vehicle> ins= new ArrayList<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                ins.add(new Vehicle());
                ins.get(ins.size()-1).setPlate(rs.getString("Plate"));
                ins.get(ins.size()-1).setIdvehicle(rs.getInt("idVehicles"));
                ins.get(ins.size()-1).setIdowner(rs.getInt("idOwner"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return ins;
    }

    public HashMap<String,Owner> RetrieveOwners(){
        String sql = "SELECT name,Plate FROM owner,vehicles WHERE owner.idOwner = vehicles.idOwner";
        return GetOwner(sql);
    }

    private HashMap<String,Owner> GetOwner(String query){
        HashMap<String,Owner> ins= new HashMap<>();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                ins.put(rs.getString("Plate"),new Owner());
                ins.get(rs.getString("Plate")).setName(rs.getString("name"));
            }
            rs.close();
            stmt.close();
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
        return ins;
    }

    public void SetVehicle(List<Vehicle> veh,List<Insurance> ins){
        Map<String, Date> exppl = new HashMap<>();
        for (Vehicle v: veh) {
        for (Insurance in : ins){
            if (v.getIdvehicle() == in.getIdvehicle()) {
                exppl.put(v.getPlate(), in.getExpDate());
                break;
            }
        }
        }
        String sql = "INSERT INTO vehicles VALUES ";
        int i=0;
        for (Vehicle v: veh){
            if (i>0){
                sql=sql.concat(",");
            }
            sql=sql.concat( "("+v.getIdvehicle()+",");
            sql =sql.concat( "'"+v.getPlate()+"',");
            sql =sql.concat( "'"+exppl.get(v.getPlate())+"',");
            sql =sql.concat( ""+v.getIdowner()+")");
            i++;
        }
        UpdateDB(sql);
    }

    public void SetOwner(List<Owner> own){
        String sql = "INSERT INTO owner VALUES ";
        int i=0;
        for (Owner ow: own){
            if (i>0){
                sql=sql.concat(",");
            }
            sql=sql.concat( "("+ow.getIdowner()+",");
            sql =sql.concat( "'"+ow.getName()+"')");
            i++;
        }
        UpdateDB(sql);
    }

    private void UpdateDB(String query){
        try {
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(query);
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }
    }
}
