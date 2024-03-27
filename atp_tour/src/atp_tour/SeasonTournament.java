package atp_tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Ivan Milin
 */
public class SeasonTournament extends Tournament
{
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    public SeasonTournament(String tourName, String tourType, String tourSurface) {
        super(tourName, tourType, tourSurface);
        this.roundOf16 = this.contestants;
        this.quaterFinalists = new ArrayList<>();
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
    }

    @Override
    public void play()
    {
        System.out.println("=====================================================================");
        System.out.println("===================== ROUND OF SIXTEEN PLAYERS ======================");
        System.out.println("=====================================================================");
        
        this.roundOf16 = this.contestants;
        Collections.shuffle(this.roundOf16);
    
        for(int i = 0; i < roundOf16.size(); i += 2)
        {
            Player winner, loser;
            Player p1 = roundOf16.get(i);
            Player p2 = roundOf16.get(i + 1);
            
            if(p1.isHeInjured() == true)
            {
                winner = p2;
                System.out.println(p1.getName() + " is injured");
                System.out.println(p2.getName() + " won this match\n");
            }
            else
            {
                Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
                match.printMatchResult();
                
                if(winner.equals(p1))
                    loser = p2;
                else
                    loser = p1;
                
                if(this.tourType.equals("Grand Slam"))
                    loser.setAtpPoints(loser.getAtpPoints() + 180);
                else
                    loser.setAtpPoints(loser.getAtpPoints() + 100);
            }    
            quaterFinalists.add(winner);
        }

        System.out.println("=====================================================================");
        System.out.println("=====================      QUARTER FINALS      ======================");
        System.out.println("=====================================================================");
        
        Collections.shuffle(this.quaterFinalists);
        
        for(int i = 0; i < quaterFinalists.size(); i += 2)
        {
            Player winner, loser;
            Player p1 = quaterFinalists.get(i);
            Player p2 = quaterFinalists.get(i+1);
            
            if(p1.isHeInjured() == true)
            {
                winner = p2;
                System.out.println(p1.getName() + " is injured");
                System.out.println(p2.getName() + " won this match\n");
            }
            else
            {
                Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
                match.printMatchResult();
                
                if(winner.equals(p1))
                    loser = p2;
                else
                    loser = p1;
                
                if(this.tourType.equals("Grand Slam"))
                    loser.setAtpPoints(loser.getAtpPoints() + 360);
                else
                    loser.setAtpPoints(loser.getAtpPoints() + 200);
            }
            semiFinalists.add(winner);
        }
        
        System.out.println("=====================================================================");
        System.out.println("=====================       SEMI  FINALS       ======================");
        System.out.println("=====================================================================");
        
        Collections.shuffle(this.semiFinalists);
        
        for(int i = 0; i < semiFinalists.size(); i += 2)
        {
            Player winner, loser;
            Player p1 = semiFinalists.get(i);
            Player p2 = semiFinalists.get(i+1);
            
            if(p1.isHeInjured() == true)
            {
                winner = p2;
                System.out.println(p1.getName() + " is injured");
                System.out.println(p2.getName() + " won this match\n");
            }
            else
            {
                Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
                match.printMatchResult();
                
                if(winner.equals(p1))
                    loser = p2;
                else
                    loser = p1;
                
                
                
                if(this.tourType.equals("Grand Slam"))
                    loser.setAtpPoints(loser.getAtpPoints() + 720);
                else
                    loser.setAtpPoints(loser.getAtpPoints() + 400);
            }
            finalists.add(winner);
        }
        
        System.out.println("=====================================================================");
        System.out.println("=====================       FINAL  MATCH       ======================");
        System.out.println("=====================================================================");
            
        Player winner, firstPlace, secondPlace;
        Player p1 = finalists.get(0);
        Player p2 = finalists.get(1);
        
        if(p1.isHeInjured() == true)
        {
            firstPlace = p2;
            System.out.println(p1.getName() + " is injured");
            System.out.println(p2.getName() + " won this match\n");
        }    
        else
        {
            Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
            winner = match.playMatch();
            match.printMatchResult();
            
            if(winner.equals(p1))
            {
                firstPlace  = p1;
                secondPlace = p2;
            }    
            else
            {
                firstPlace  = p2;
                secondPlace = p1;
            }
            
            if(this.tourType.equals("Grand Slam"))
            {
                firstPlace.setAtpPoints(firstPlace.getAtpPoints() + 2000);
                secondPlace.setAtpPoints(secondPlace.getAtpPoints() + 1000);
            }
            else
            {
                firstPlace.setAtpPoints(firstPlace.getAtpPoints() + 1200);
                secondPlace.setAtpPoints(secondPlace.getAtpPoints() + 650);
            } 
        }   
    }
    
    
    
}
