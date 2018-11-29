import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.JP1.STNFO.*;
import com.JP1.IO.*;
import com.JP1.JDBC.JDBC;
import java.util.Map;

public class Menu {


    static InputManager input = new InputManager();

    private static Map<String,Owner> owners;
    private static Map<String,Insurance> insurances;
    private static Map<String,Vehicle> vehicles;
    private static FileManager filemanager;
    //GK: Totally NOT a rip-off from Classic Doom
    private static int checkArg(String arg, String [] args){
        if(args.length > 0){
            for (int i=0;i<args.length;i++){
                if (args[i].equals(arg)){
                    return i;
                }
            }
        }
        return -1;
    }
//GK: Call this in order to retrieve the Data ONCE
    private static void Boot(String [] args){
        int file = checkArg("-file",args);
        try {
            if (file >= 0) {
                filemanager = new FileManager(args[file + 1]);
            } else {
                filemanager = new FileManager();
            }
        }catch(FileNotFoundException e){
            System.err.println("Error:File-"+e.getMessage());
        }
        if (filemanager.HasFile()){
            try {
                owners = filemanager.getOwnerfromFile();
                insurances = filemanager.getInsurancefromFile();
                vehicles = filemanager.getVehiclefromFile();
                filemanager.close();
            }catch(IOException e){
                System.err.println("Error:File-"+e.getMessage());
            }
        }else{
            try {
                JDBC database = new JDBC("root", "root"); //GK: Put your username and password here
                owners = database.RetrieveOwners();
                insurances = database.RetrieveExpDates();
                vehicles = database.RetrieveVehicles();
                database.close();
            }catch (SQLException sqe){
                System.err.println("Error:SQL-"+sqe.getMessage());
            }catch (ClassNotFoundException e){
                System.err.println("Error:SQL-"+e.getMessage());
            }
        }

    }

    public static void main(String[] args) {

        Boot(args);
        //GK: For Testing purposes ONLY
       // System.out.println(owners+"\n"+insurances);

        System.out.println("--- Type a number to choose functionality :");
        System.out.println("1. Vehicle Insurance Status");
        System.out.println("2. Insurances that are about to expire");
        System.out.println("3. Uninsured vehicles");
        System.out.println("4. Fine calculation");
        int i=input.GetOption();

        System.out.println(i);


            switch (i) {
                case 1:
                    String plate;
                    do {
                        System.out.println("Please type your plate(ABC-1234) :");
                        //InputManager first = new InputManager();
                        plate = input.GetPlate();
                    }while(!insurances.containsKey(plate)) ;//GK: Oops forgot ALWAYS DO SANITY CHECK
                        if(insurances.get(plate).getStatus()){
                            System.out.println("Valid Insurance");
                        }else {
                            System.out.println("Invalid Insurance");
                        }

                    break;

                case 2:
                    System.out.println("Plesase select output :");
                    int choice = input.GetChoice();

                    System.out.println("Please give a number that represents a timeframe in days :");
                    //InputManager second = new InputManager();
                    int days = input.GetNum();
                    System.out.println(days);
                    String s="";
                    for (String plates : insurances.keySet()) {
                        if (insurances.get(plates).getStatus() && insurances.get(plates).getDays() <= days) {
                            if (choice == 1) {
                                System.out.println(plates + " " + owners.get(plates).getName() + " " + owners.get(plates).getLname() + " " + owners.get(plates).getAddress() + " " + insurances.get(plates).getExpirationDate() + " " + vehicles.get(plates).getType() + " " + vehicles.get(plates).getBrand() + " " + vehicles.get(plates).getModel() );
                            } else {
                                s = s.concat(plates + "," + owners.get(plates).getName() + "," + owners.get(plates).getLname() + "," + owners.get(plates).getAddress() + "," + insurances.get(plates).getExpirationDate() + "," + vehicles.get(plates).getType() + "," + vehicles.get(plates).getBrand() + "," + vehicles.get(plates).getModel() + "\n");

                            }
                        }
                    }
                    if(choice == 2){
                        filemanager.WritetoFile(s);
                    }
                    break;

                case 3:
                    System.out.println("go to 3");
                    break;

                case 4:
                    System.out.println("go to 4");
                    break;

                default:
                    System.out.println("Type a number between 1-4");
                    break;
            }






    }
}
