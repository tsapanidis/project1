package com.JP1.IO;

import java.util.Scanner;
public class InputManager {
    //GK: Create The scanner object
    private Scanner i;
    //GK: Use the default constructor to intialize the Scanner object
    public InputManager() {
        this.i=new Scanner(System.in);
    }

    //GK: hasNext is taking as an argument either a specific string or a regular expression (how the string should be written)
    //GK: The while loop is constantly asking the user to give input, test it and if it is what we want, then return it
    public String GetPlate(){
        while (!this.i.hasNext("[A-Z]{3}-[0-9]{4}")){
            System.out.println("Invalid Plate");
            this.i.next();
        }
        return this.i.next();
    }

    public double GetFine(){
        while (!this.i.hasNextDouble()){
            System.out.println("Invalid Fine");
            this.i.next();
        }
        return this.i.nextDouble();
    }

    public int GetNum(){
        while (!this.i.hasNextInt()){
            System.out.println("Invalid Value");
            this.i.next();
        }
        return this.i.nextInt();
    }

    public String GetRaw(){
        return this.i.next();
    }
}
