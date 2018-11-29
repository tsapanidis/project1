package com.JP1.IO;
import com.JP1.STNFO.*;

import java.io.*;
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

    public FileManager()throws FileNotFoundException{
        this.filedata = new ArrayList<>();
        bootFs("output.csv");
    }

    public FileManager(String FileName)throws FileNotFoundException{
        this.filedata = new ArrayList<>();
        bootFs(FileName+".csv");
    }

    private void bootFs(String path) throws FileNotFoundException {
        this.hasfile =true;
        this.file = new File (path);
            if (this.file.exists() && !this.file.isDirectory()) {
                this.buffreader = new BufferedReader(new FileReader(this.file));
            }else{
                this.hasfile = false;
            }

    }

    public void WritetoFile(String out){
        try {
            FileWriter filewriter = new FileWriter(this.file);
            filewriter.write(out);
            filewriter.flush();
            filewriter.close();
        }catch(IOException e){
            System.err.println("Error:File-"+e.getMessage());
        }
    }

    private void ReadfromFIle()throws IOException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (String Line; (Line = this.buffreader.readLine()) != null; ) {
                String[] values = Line.split(",");
                this.filedata.add(new FileData());
                this.filedata.get(this.filedata.size()-1).setPlate(values[0]);
                this.filedata.get(this.filedata.size()-1).setName(values[1]);
                this.filedata.get(this.filedata.size()-1).setLname(values[2]);
                this.filedata.get(this.filedata.size()-1).setAddress(values[3]);
                this.filedata.get(this.filedata.size()-1).setExpdate(format.parse(values[4]));
                this.filedata.get(this.filedata.size()-1).setType(Integer.parseInt(values[5]));
                this.filedata.get(this.filedata.size()-1).setBrand(values[6]);
                this.filedata.get(this.filedata.size()-1).setModel(values[7]);
            }
        }catch (ParseException pe){
            System.err.println("Error:File-"+pe.getMessage());
        }

    }

    public HashMap<String,Owner> getOwnerfromFile() throws IOException{
        if (filedata.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Owner> owners= new HashMap<>();
        for (FileData data : this.filedata){
            owners.put(data.getPlate(),new Owner());
            owners.get(data.getPlate()).setName(data.getName());
            owners.get(data.getPlate()).setLname(data.getLname());
            owners.get(data.getPlate()).setAddress(data.getAddress());
        }
        return owners;
    }

    public HashMap<String,Insurance> getInsurancefromFile() throws IOException{
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

    public HashMap<String,Vehicle> getVehiclefromFile() throws IOException{
        if (filedata.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Vehicle> vehicles= new HashMap<>();
        for (FileData data : this.filedata){
            vehicles.put(data.getPlate(),new Vehicle());
            vehicles.get(data.getPlate()).setType(data.getType());
            vehicles.get(data.getPlate()).setBrand(data.getBrand());
            vehicles.get(data.getPlate()).setModel(data.getModel());
        }
        return vehicles;
    }

    public void close() throws IOException{
            this.buffreader.close();
    }

    public Boolean HasFile() {
        return hasfile;
    }

    private class FileData{
        private String plate;
        private String name;
        private Date expdate;
        private int type;
        private String Brand;
        private String Model;
        private String Lname;
        private String Address;

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

        private int getType() { return type; }

        private void setType(int type) { this.type = type; }

        private String getBrand() { return Brand; }

        private void setBrand(String brand) { Brand = brand; }

        private String getModel() { return Model; }

        private void setModel(String model) { Model = model; }

        private String getLname() { return Lname; }

        private void setLname(String lname) { Lname = lname; }

        private String getAddress() { return Address; }

        private void setAddress(String address) { Address = address; }
    }

}


