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
        cmpshp.printData();
        cmpshp.recoverPlayers();
        
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
                    System.out.println("\n==========================");
                    System.out.println("| Number is out of range |");
                    System.out.println("==========================\n");
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("\n==============================================");
                System.out.println("| You entered something that is not expected |");
                System.out.println("==============================================\n");
            }
            
        }
        while(numberOfTournaments < 4 || numberOfTournaments > 14);
        
        System.out.println("You entered number " + numberOfTournaments);
        
        String stringTourName;
        int exitDoWhileLoop = 0;
        
        while(numberOfTournaments != 0)
        {
            System.out.println("\nEnter tournament name : ");
            stringTourName = sc.nextLine();   
            System.out.println("You entered tourName " + stringTourName);

            Tournament seasontournament = new SeasonTournament(stringTourName,"clay","Grand Slam");
            seasontournament.setContestants(cmpshp.getPlayers());
            seasontournament.play();
            cmpshp.updateAtpRanks();
            cmpshp.printCurrentStatus();
            
            numberOfTournaments --;
        }   

        cmpshp.recoverPlayers();
        AtpFinals atpfinals = new AtpFinals(new ArrayList<>(cmpshp.getPlayers().subList(0,8)));
        System.out.println("\nBuckle up, ATP Tournament is about to start ...");
        atpfinals.play();
    }    
}
