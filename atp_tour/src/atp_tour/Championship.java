/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        players = new ArrayList<>();
        tournaments = new ArrayList<>();
        tourNames = new ArrayList<>();
    }
    
    public void loadFile() throws FileNotFoundException
    {
        String filePath_to_players_txt     = "./players.txt";
        String filePath_to_tournaments_txt = "./tournaments.txt";
        
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
                                            Integer.parseInt(atpRank_name_ability_surface_atpPoints[4]));
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
    }
    
    public void printData() 
    {
        System.out.println("==================================================================");
        // Printing players
        System.out.println("Players:");
        
        for (Player player : players) 
        {
            System.out.println("\t"+player.getAtpRank() +". "+ player.getName() +", "+ player.getAbility() +", "+ player.getPreferedSurface() +", "+ player.getAtpPoints());
        }
        
        System.out.println("==================================================================");
        // Printing tournaments
        System.out.println("Tournaments:");
        
        for (Tournament tournament : tournaments) 
        {
            System.out.println("\t"+tournament.getTourName() +", "+ tournament.getTourSurface() +", "+ tournament.getTourType());
        }
        System.out.println("==================================================================");
        
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
        System.out.println("All players are recovered from their injuries and ready to play in the upcoming match");
    }
    
    
}
