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
        Scanner atpEnter = new Scanner(System.in);
        
        
        cmpshp.loadFile();
        //cmpshp.printData();
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
        
        while(numberOfTournaments > 0)
        {
            System.out.println("\nEnter tournament name : ");
            stringTourName = sc.nextLine();   
            System.out.println("You entered tourName " + stringTourName);

            Tournament tournament = null;
            if((cmpshp.getTournamentByTourName(stringTourName))!= null)
            {
                tournament = cmpshp.getTournamentByTourName(stringTourName);
                if(tournament.isPlayable() == true)
                {
                    Tournament seasontournament = new SeasonTournament(tournament.getTourName(),tournament.getTourSurface(),tournament.getTourType());

                    seasontournament.setContestants(cmpshp.getPlayers());
                    seasontournament.play();
                    cmpshp.updateAtpRanks();
                    cmpshp.printCurrentStatus();

                    cmpshp.recoverPlayers();
                    numberOfTournaments --;
                    tournament.setPlayable(false);
                }
                else
                    System.out.println("\n" + tournament.getTourName() +  " is already played, enter something else");
            }    
            else    
                System.out.println("\nTournament does not exist");
        }   
        
        AtpFinals atpfinals = new AtpFinals(new ArrayList<>(cmpshp.getPlayers().subList(0,8)));
        
        System.out.println("Press enter so ATP Tournament can start");
        atpEnter.nextLine();
        
        System.out.println("\nBuckle up, ATP Tournament is about to start ...");
        atpfinals.play();
        
        cmpshp.updateAtpRanks();
        cmpshp.printCurrentStatus();
    }    
}


// Round robin izmeni za semifinalu 
// U playTieBreak treba da igraci da se menjaju (Ono sa uzvicnik) 
// playable postavi na false da ne moze isti turnir vise puta da se igra
// recover players stavi u while loop
