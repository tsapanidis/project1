import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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
                System.out.println(plate);
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
