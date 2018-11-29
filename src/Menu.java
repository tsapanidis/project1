import java.util.Scanner;
import com.JP1.STNFO.*;
import com.JP1.IO.*;
import com.JP1.JDBC.JDBC;
import java.util.Map;

public class Menu {


    static InputManager im = new InputManager();


    private static Map<String,Owner> mo;
    private static Map<String,Insurance> mi;
    private static FileManager fm;
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
        if (file >= 0){
            fm=new FileManager(args[file+1]);
        }else {
            fm = new FileManager();
        }
        if (fm.HasFile()){
            mo = fm.getOwnerfromFile();
            mi = fm.getInsurancefromFile();
        }else{
            JDBC db = new JDBC("root","root"); //GK: Put your username and password here
            mo = db.RetrieveOwners();
            mi = db.RetrieveExpDates();
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
        int i=im.GetOption();

        System.out.println(i);


            switch (i) {
                case 1:
                    String plate;
                    do {
                        System.out.println("Please type your plate(ABC-1234) :");
                        //InputManager first = new InputManager();
                        plate = im.GetPlate();
                    }while(!mi.containsKey(plate)) ;//GK: Oops forgot ALWAYS DO SANITY CHECK
                        if(mi.get(plate).getStatus()){
                            System.out.println("Valid Insurance");
                        }else {
                            System.out.println("Invalid Insurance");
                        }

                    break;

                case 2:
                    System.out.println("Plesase select output :");
                    int choice = im.GetChoice();

                    System.out.println("Please give a number that represents a timeframe in days :");
                    //InputManager second = new InputManager();
                    int days = im.GetNum();
                    System.out.println(days);
                    String s="";
                    for (String plates : mi.keySet()) {
                        if (mi.get(plates).getStatus() && mi.get(plates).getDays() <= days) {
                            if (choice == 1) {
                                System.out.println(plates + " " + mo.get(plates).getName() + " " + mi.get(plates).getExpirationDate());
                            } else {
                                s = s.concat(plates + "," + mo.get(plates).getName() + "," + mi.get(plates).getExpirationDate() + "\n");

                            }
                        }
                    }
                    if(choice == 2){
                        fm.WritetoFile(s);
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
