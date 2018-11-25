package com.JP1.IO;
import com.JP1.STNFO.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileManager {
    private FileWriter fw;
    private BufferedReader br;
    private List<FileData> fd;

    public FileManager(){
        FileReader fr;
        File f = new File ("output.csv");
        this.fd = new ArrayList<>();
        try {
            this.fw = new FileWriter(f);
            fr = new FileReader(f);
            this.br= new BufferedReader(fr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileManager(String FileName){
        FileReader fr;
        File f = new File (FileName+".csv");
        this.fd = new ArrayList<>();
        try {
            this.fw = new FileWriter(f);
            fr = new FileReader(f);
            this.br= new BufferedReader(fr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void WritetoFile(String out){
        try {
            this.fw.write(out);
            this.fw.flush();
            this.fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void ReadfromFIle(){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            int i = 0;
            for (String Line; (Line = this.br.readLine()) != null; ) {
                String[] vals = Line.split(",");
                this.fd.add(new FileData());
                this.fd.get(i).setIdvehicle(Integer.parseInt(vals[0]));
                this.fd.get(i).setIdowner(Integer.parseInt(vals[1]));
                this.fd.get(i).setPlate(vals[2]);
                this.fd.get(i).setName(vals[3]);
                this.fd.get(i).setExpdate(format.parse(vals[4]));
                i++;
            }
        }catch (ParseException pe){
                pe.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Owner> getOwnerfromFile(){
        ArrayList<Owner> ao= new ArrayList<>();
        for (FileData f : this.fd){
            ao.add(new Owner());
            ao.get(ao.size()-1).setIdowner(f.getIdowner());
            ao.get(ao.size()-1).setName(f.getName());
        }
        return ao;
    }

    public ArrayList<Vehicle> getVehiclefromFile(){
        ArrayList<Vehicle> ao= new ArrayList<>();
        for (FileData f : this.fd){
            ao.add(new Vehicle());
            ao.get(ao.size()-1).setIdowner(f.getIdowner());
            ao.get(ao.size()-1).setIdvehicle(f.getIdvehicle());
            ao.get(ao.size()-1).setPlate(f.getPlate());
        }
        return ao;
    }

    public ArrayList<Insurance> getInsurancefromFile(){
        ArrayList<Insurance> ao= new ArrayList<>();
        for (FileData f : this.fd){
            ao.add(new Insurance());
            ao.get(ao.size()-1).setIdvehicle(f.getIdvehicle());
            ao.get(ao.size()-1).setExpDate(f.getExpdate());
        }
        return ao;
    }



    private class FileData{
        private int idvehicle;
        private int idowner;
        private String plate;
        private String name;
        private Date expdate;

        private int getIdvehicle() {
            return idvehicle;
        }

        private void setIdvehicle(int idvehicle) {
            this.idvehicle = idvehicle;
        }

        private int getIdowner() {
            return idowner;
        }

        private void setIdowner(int idowner) {
            this.idowner = idowner;
        }

        private String getPlate() {
            return plate;
        }

        private void setPlate(String plate) {
            this.plate = plate;
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private Date getExpdate() {
            return expdate;
        }

        private void setExpdate(Date expdate) {
            this.expdate = expdate;
        }
    }

}


