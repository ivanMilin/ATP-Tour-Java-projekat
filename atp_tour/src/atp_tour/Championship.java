package atp_tour;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ivan Milin
 */
public class Championship
{
    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;
    public ArrayList<String> tourNames;

    public Championship() {
        this.players = new ArrayList<>();
        this.tournaments = new ArrayList<>();
        this.tourNames = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }
    
    public void loadFile()
    {
        String filePath_to_players_txt     = "./players.txt";
        String filePath_to_tournaments_txt = "./tournaments.txt";
        
        System.out.println("\nLoading players and tournaments from files ...");
        
        // loading data from file players.txt into ArrayList<Player> players
        try(BufferedReader players_from_file = new BufferedReader(new FileReader(filePath_to_players_txt)))
        {
            String line;
         
            while((line = players_from_file.readLine()) != null)
            {
                String[] atpRank_name_ability_surface_atpPoints = line.split(",");
                
                if(atpRank_name_ability_surface_atpPoints.length == 5)
                {
                    Player ply = new Player(Integer.parseInt(atpRank_name_ability_surface_atpPoints[0]),
                                            atpRank_name_ability_surface_atpPoints[1],
                                            atpRank_name_ability_surface_atpPoints[2],
                                            atpRank_name_ability_surface_atpPoints[3],
                                            Integer.parseInt(atpRank_name_ability_surface_atpPoints[4]),
                                            false);
                    players.add(ply);
                }
                
                else
                {
                    System.out.println("Problem with data length in file players.txt");
                }    
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Problem with loading data from file players.txt");
        }
        
        // loading data from file tournaments.txt into ArrayList<Player> tournaments
        try(BufferedReader tournaments_from_file = new BufferedReader(new FileReader(filePath_to_tournaments_txt)))
        {
            String line;

            while((line = tournaments_from_file.readLine()) != null)
            {
                String[] tourName_tourSurface_tourType = line.split(",");

                if(tourName_tourSurface_tourType.length == 3)
                {
                    Tournament trnmnt = new SeasonTournament(tourName_tourSurface_tourType[0],
                                                       tourName_tourSurface_tourType[1],
                                                       tourName_tourSurface_tourType[2]);    
                    tournaments.add(trnmnt);
                    tourNames.add(tourName_tourSurface_tourType[0]);
                }
                else
                {
                    System.out.println("Problem with data length in file tournament.txt");
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Problem with loading data from file tournaments.txt");
        } 
        System.out.println("Loaded players and tournaments from files ...\n");
    }
    
    public void printData() 
    {
        System.out.println("=====================================================================");
        // Printing players
        System.out.println("Players:");
        
        for (Player player : players) 
        {
            System.out.println("\t"+player.getAtpRank() +". "+ player.getName() +", "+ player.getAbility() +", "+ player.getPreferedSurface() +", "+ player.getAtpPoints());
        }
        
        System.out.println("=====================================================================");
        // Printing tournaments
        System.out.println("Tournaments:");
        
        for (Tournament tournament : tournaments) 
        {
            System.out.println("\t"+tournament.getTourName() +", "+ tournament.getTourSurface() +", "+ tournament.getTourType());
        }
        System.out.println("=====================================================================");
        
        /*
        System.out.println("Tournament names:");
        for (String tourname : tourNames) 
        {
            System.out.print(tourname + " ");
        }
        System.out.println("\n==================================================================");
        */
    }

    public void recoverPlayers()
    {
        for(Player player : players){
            player.setInjured(false);
        }
        System.out.println("=====================================================================");
        System.out.println("| All players are recovered and ready to play in the upcoming match |");
        System.out.println("=====================================================================");
    }
         
    public void pokusajPrinta() {
        for (Player player : players) 
        {
            System.out.printf("%-2d - %-18s - %d%n", player.getAtpRank(), player.getName(), player.getAtpPoints());
        }  
    }
}

