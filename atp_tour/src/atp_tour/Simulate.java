/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atp_tour;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ivan Milin
 */
public class Simulate 
{
    private static ArrayList<String> desiredTourNames = new ArrayList<>(); 

    public static void main(String[] args) throws FileNotFoundException {
        Championship cmpshp = new Championship();
        Scanner sc = new Scanner(System.in);
        
        
        cmpshp.loadFile();
        //cmpshp.printData();
        //cmpshp.recoverPlayers();
        
        String readString;
        int numberOfTournaments = 0;
        do
        {
            System.out.println("How many tournament you want (min 4 - max 13) : ");
            readString = sc.nextLine();
            try
            {
                numberOfTournaments = Integer.parseInt(readString);
             
                if(numberOfTournaments > 14 || numberOfTournaments < 4)
                {
                    System.out.println("==========================");
                    System.out.println("| Number is out of range |");
                    System.out.println("==========================");
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("==============================================");
                System.out.println("| You entered something that is not expected |");
                System.out.println("==============================================");
            }
            
        }
        while(numberOfTournaments < 4 || numberOfTournaments > 14);
        
        System.out.println("You entered number " + numberOfTournaments);
        
        String stringTourName;
        int exitDoWhileLoop = 0;
        
        do
        {
            System.out.println("Enter tournament name : ");
            stringTourName = sc.nextLine();   
            if(!isDifferentTourName(stringTourName,numberOfTournaments))
            {
                desiredTourNames.add(stringTourName);
                System.out.println("You entered tourName " + stringTourName);
                exitDoWhileLoop = 1;
            } 
            else
            {
                System.out.println("=====================================================");
                System.out.println("| Enter tourName that you have not already entered  |");
                System.out.println("=====================================================");
            }
        }
        while(exitDoWhileLoop == 0);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }

    public static boolean isDifferentTourName(String input, int numberOfTournaments)
    {
        for(String desiredTourName : desiredTourNames)
        {
            if(input.equals(desiredTourName))
                return true;
        }
        return false;
    }
    
}
