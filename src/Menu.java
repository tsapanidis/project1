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
                System.out.println("go to 1");
                break;

            case 2:
                System.out.println("go to 2");
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
