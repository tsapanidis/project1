package com.JP1.IO;
import com.JP1.STNFO.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FileManager {
    private File file;
    private BufferedReader buffreader;
    private List<FileData> filedata;
    private Boolean hasfile;

    public FileManager(){
        this.filedata = new ArrayList<>();
        bootfs("output.csv");
    }

    public FileManager(String FileName){
        this.filedata = new ArrayList<>();
        bootfs(FileName+".csv");
    }

    private void bootfs(String path){
        FileReader filereader;
        this.hasfile =true;
        this.file = new File (path);
        try {
            if (this.file.exists() && !this.file.isDirectory()) {
                filereader = new FileReader(this.file);
                this.buffreader = new BufferedReader(filereader);
            }else{
                this.hasfile = false;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void WritetoFile(String out){
        try {
            FileWriter filewriter = new FileWriter(this.file);
            filewriter.write(out);
            filewriter.flush();
            filewriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void ReadfromFIle(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int i = 0;
            for (String Line; (Line = this.buffreader.readLine()) != null; ) {
                String[] values = Line.split(",");
                this.filedata.add(new FileData());
               // this.fd.get(i).setIdvehicle(Integer.parseInt(vals[0]));
               // this.fd.get(i).setIdowner(Integer.parseInt(vals[1]));
                this.filedata.get(i).setPlate(values[0]);
                this.filedata.get(i).setName(values[1]);
                this.filedata.get(i).setExpdate(format.parse(values[2]));
                i++;
            }
        }catch (ParseException pe){
                pe.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public HashMap<String,Owner> getOwnerfromFile(){
        if (filedata.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Owner> owners= new HashMap<>();
        for (FileData data : this.filedata){
            owners.put(data.getPlate(),new Owner());
            owners.get(data.getPlate()).setName(data.getName());
        }
        return owners;
    }

    public ArrayList<Vehicle> getVehiclefromFile(){
        ArrayList<Vehicle> vehicles= new ArrayList<>();
        for (FileData data : this.filedata){
            vehicles.add(new Vehicle());
            vehicles.get(vehicles.size()-1).setIdowner(data.getIdowner());
            vehicles.get(vehicles.size()-1).setIdvehicle(data.getIdvehicle());
            vehicles.get(vehicles.size()-1).setPlate(data.getPlate());
        }
        return vehicles;
    }

    public HashMap<String,Insurance> getInsurancefromFile(){
        if (filedata.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Insurance> insurances= new HashMap<>();
        for (FileData data : this.filedata){
            insurances.put(data.getPlate(),new Insurance());
            insurances.get(data.getPlate()).setExpDate(data.getExpdate());
        }
        return insurances;
    }

    public Boolean HasFile() {
        return hasfile;
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


