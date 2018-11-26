package com.JP1.IO;
import com.JP1.STNFO.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileManager {
    private File f;
    private BufferedReader br;
    private List<FileData> fd;
    private Boolean hf;

    public FileManager(){
        this.fd = new ArrayList<>();
        bootfs("output.csv");
    }

    public FileManager(String FileName){
        this.fd = new ArrayList<>();
        bootfs(FileName+".csv");
    }

    private void bootfs(String path){
        FileReader fr;
        this.hf =true;
        this.f = new File (path);
        try {
            if (this.f.exists() && !this.f.isDirectory()) {
                fr = new FileReader(this.f);
                this.br = new BufferedReader(fr);
            }else{
                this.hf = false;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void WritetoFile(String out){
        try {
            FileWriter fw = new FileWriter(this.f);
            fw.write(out);
            fw.flush();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void ReadfromFIle(){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            int i = 0;
            for (String Line; (Line = this.br.readLine()) != null; ) {
                String[] vals = Line.split(",");
                this.fd.add(new FileData());
               // this.fd.get(i).setIdvehicle(Integer.parseInt(vals[0]));
               // this.fd.get(i).setIdowner(Integer.parseInt(vals[1]));
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

    public HashMap<String,Owner> getOwnerfromFile(){
        if (fd.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Owner> ao= new HashMap<>();
        for (FileData f : this.fd){
            ao.put(f.getPlate(),new Owner());
            ao.get(f.getPlate()).setName(f.getName());
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

    public HashMap<String,Insurance> getInsurancefromFile(){
        if (fd.size() == 0){
            ReadfromFIle();
        }
        HashMap<String,Insurance> ao= new HashMap<>();
        for (FileData f : this.fd){
            ao.put(f.getPlate(),new Insurance());
            ao.get(f.getPlate()).setExpDate(f.getExpdate());
        }
        return ao;
    }

    public Boolean HasFile() {
        return hf;
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


