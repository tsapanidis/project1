import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import com.JP1.STNFO.*;
import com.JP1.IO.*;
import com.JP1.JDBC.JDBC;
import java.util.Map;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

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
        int i = scanner.nextInt();
        System.out.println(i);


        switch (i){
            case 1:
                System.out.println("Please type your plate(ABC-1234) :");
                InputManager first = new InputManager();
                String plate = first.GetPlate();
                if (insurances.containsKey(plate)) { //GK: Oops forgot ALWAYS DO SANITY CHECK
                    System.out.println(insurances.get(plate).getStatus()); //GK: F1 Done???
                }
                break;

            case 2:
                System.out.println("Please give a number that represents a timeframe in days :");
                InputManager second = new InputManager();
                int days = second.GetNum();
                System.out.println(days);
                break;

            case 3:
                System.out.println("go to 3");
                break;

            case 4:
                System.out.println("go to 4");
                break;

            default:
                System.out.println("Type a number between 1-4");

        }





    }
}
