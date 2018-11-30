import com.JP1.STNFO.*;
import com.JP1.JDBC.*;

import java.util.ArrayList;
import java.util.List;

public class Gen {
    public static void main(String[] args) {
        Hello h= new Hello();
        RandomDates rd = new RandomDates();
        RandomNames rm = new RandomNames();
        RandomOwnerId roid = new RandomOwnerId();
        RandomVehicleId rvid = new RandomVehicleId();
        List<Owner> owners = new ArrayList<>();
        List<Insurance> insurances = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        JDBC db = new JDBC("root","root");

        for (int i=0;i<50;i++){
            owners.add(new Owner());
            owners.get(owners.size()-1).setIdowner(i+1);
            owners.get(owners.size()-1).setName(rm.GenName());
        }
        db.SetOwner(owners);
        for (int i=0;i<150;i++){
            vehicles.add(new Vehicle());
            insurances.add(new Insurance());
            vehicles.get(vehicles.size()-1).setIdvehicle(i+1);
            vehicles.get(vehicles.size()-1).setPlate(h.genPlates());
            vehicles.get(vehicles.size()-1).setIdowner(owners.get((int)(Math.random()*50)).getIdowner());
            insurances.get(insurances.size()-1).setIdvehicle(vehicles.get(vehicles.size()-1).getIdvehicle());
            insurances.get(insurances.size()-1).setExpDate(rd.GenDate());
        }
        db.SetVehicle(vehicles,insurances);
        db.close();
        System.out.println("Done");
    }
}
