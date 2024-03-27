package atp_tour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Ivan Milin
 */
public class AtpFinals extends Tournament
{
    private ArrayList<Player> groupA;
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;
    private ArrayList<Player> winnersFromGroupA;
    private ArrayList<Player> winnersFromGroupB;

    private int[] totalWinsGroupA;
    private int[] totalWinsGroupB;

    public AtpFinals(ArrayList<Player> atp_players) {
        super("ATP Finals", "hard", "Grand Slam");
        this.numOfSets = 2;
        this.groupA = new ArrayList<>();
        this.groupB = new ArrayList<>();
        this.winnersFromGroupA = new ArrayList<>();
        this.winnersFromGroupB = new ArrayList<>();
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
        this.totalWinsGroupA = new int[atp_players.size()];
        this.totalWinsGroupB = new int[atp_players.size()];

        for(int i = 0; i < 8; i++)
        {
            if((i+1) % 2 == 1)
                groupA.add(atp_players.get(i));
            else
                groupB.add(atp_players.get(i));
        }
    }
    
    @Override
    public void play()
    {
        System.out.println("\nPlayers from groupA : "/* + groupA.size()*/);
        for(Player membersA : groupA)
        {
            System.out.println("    " + membersA.getAtpRank()+". " + membersA.getName());
        }        
        
        System.out.println("\nPlayers from groupB : " /*+ groupB.size()*/);
        for(Player membersB : groupB)
        {
            System.out.println("    " + membersB.getAtpRank()+". " + membersB.getName());
        }
        
        simulateGroupA_GroupB(groupA);
        simulateGroupA_GroupB(groupB);
        
        System.out.println("\nPlayers for SEMI final");
        for (Player player : semiFinalists) {
            System.out.println("    " + player.getName() + " - numberOfWins " + player.getAtpCounter() + " - atpPoints " + player.getAtpPoints());
            player.setAtpCounter(0);
        }
        
        simulateSemiFinal(semiFinalists);
        
        System.out.println("\nPlayers for FINAL match");
        for (Player player : finalists) {
            System.out.println("    " + player.getName() + " - atpPoints " + player.getAtpPoints());
            player.setAtpCounter(0);
        }
        
        simulateFinalMatch();
    }
    
    public void simulateGroupA_GroupB(ArrayList<Player> halfPlayers)
    {
        for(int i = 0; i < halfPlayers.size(); i++)
        {
            for(int j = i + 1; j < halfPlayers.size(); j++)
            {
                Player winner, loser;
                Player p1 = halfPlayers.get(i);
                Player p2 = halfPlayers.get(j);

                if(p1.isHeInjured() == true)
                {
                    winner = p2;
                    halfPlayers.get(j).setAtpCounter(halfPlayers.get(j).getAtpCounter() + 1);
                    halfPlayers.get(j).setAtpPoints(halfPlayers.get(j).getAtpPoints() + 200);
                    System.out.println(p1.getName() + " is injured");
                    System.out.println(p2.getName() + " won this match\n");
                }
                else
                {
                    Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
                    winner = match.playMatch();
                    
                    if(winner.equals(p1))
                    {
                        halfPlayers.get(i).setAtpCounter(halfPlayers.get(i).getAtpCounter() + 1);
                        halfPlayers.get(i).setAtpPoints(halfPlayers.get(i).getAtpPoints() + 200);
                        loser = p2;
                    }
                    else
                    {  
                        loser = p1;
                        halfPlayers.get(j).setAtpCounter(halfPlayers.get(j).getAtpCounter() + 1);
                        halfPlayers.get(j).setAtpPoints(halfPlayers.get(j).getAtpPoints() + 200);
                    }
                }
            }
        }
        Comparator<Player> numberOfWins = Comparator.comparingInt(Player::getAtpCounter).reversed();
        Collections.sort(halfPlayers, numberOfWins);
        
        semiFinalists.addAll(halfPlayers.subList(0, 2));
    }
    
    public void simulateSemiFinal(ArrayList<Player> semifinalists)
    {
        // First pair of semifinalists play here
        Player winner1, loser1;
        
        Player player_firstA  = semiFinalists.get(0);
        Player player_secondB = semiFinalists.get(3);

        if(player_firstA.isHeInjured() == true)
        {
            winner1 = player_secondB;
            semiFinalists.get(3).setAtpCounter(semiFinalists.get(3).getAtpCounter() + 1);
            semiFinalists.get(3).setAtpPoints(semiFinalists.get(3).getAtpPoints() + 400);
            System.out.println(player_firstA.getName() + " is injured");
            System.out.println(player_secondB.getName() + " won this match\n");
        }
        else
        {
            Match match = new Match(player_firstA, player_secondB, this.tourSurface, this.numOfSets);
            winner1 = match.playMatch();

            if(winner1.equals(player_firstA))
            {
                semiFinalists.get(0).setAtpCounter(semiFinalists.get(0).getAtpCounter() + 1);
                semiFinalists.get(0).setAtpPoints(semiFinalists.get(0).getAtpPoints() + 400);
                loser1 = player_secondB;    
            }
            else
            {
                loser1 = player_firstA;
                semiFinalists.get(3).setAtpCounter(semiFinalists.get(3).getAtpCounter() + 1);
                semiFinalists.get(3).setAtpPoints(semiFinalists.get(3).getAtpPoints() + 400);
            }
        }
        finalists.add(winner1);

        //Second pair of semifinalists play here
        Player winner2, loser2;
        Player player_firstB  = semiFinalists.get(2);
        Player player_secondA = semiFinalists.get(1);
        
        if(player_firstB.isHeInjured() == true)
        {
            winner2 = player_secondA;
            semiFinalists.get(1).setAtpCounter(semiFinalists.get(1).getAtpCounter() + 1);
            semiFinalists.get(1).setAtpPoints(semiFinalists.get(1).getAtpPoints() + 400);
            System.out.println(player_firstB.getName() + " is injured");
            System.out.println(player_secondA.getName() + " won this match\n");
        }
        else
        {
            Match match = new Match(player_firstB, player_secondA, this.tourSurface, this.numOfSets);
            winner2 = match.playMatch();

            if(winner2.equals(player_firstB))
            {
                semiFinalists.get(2).setAtpCounter(semiFinalists.get(2).getAtpCounter() + 1);
                semiFinalists.get(2).setAtpPoints(semiFinalists.get(2).getAtpPoints() + 400);
                loser2 = player_secondA;    
            }
            else
            {
                loser2 = player_firstB;
                semiFinalists.get(1).setAtpCounter(semiFinalists.get(1).getAtpCounter() + 1);
                semiFinalists.get(1).setAtpPoints(semiFinalists.get(1).getAtpPoints() + 400);
            }
        }
        
        finalists.add(winner2);
    }
    
    public void simulateFinalMatch()
    {
        Player winner;
        Player p1 = finalists.get(0);
        Player p2 = finalists.get(1);
        
        if(p1.isHeInjured() == true)
        {
            winner = p2;
            p2.setAtpPoints(p2.getAtpPoints() + 500);
            System.out.println(p1.getName() + " is injured");
            System.out.println(p2.getName() + " won this match\n");
        }
        else
        {
            Match match = new Match(p1, p2, this.tourSurface, this.numOfSets);
            winner = match.playMatch();
            
            if(winner.equals(p1))
            {
                p1.setAtpPoints(p1.getAtpPoints() + 500);
                System.out.println("\nPlayer " + p1.getName() + ", " + p1.getAtpPoints() + " won ATP Finals tournament\n");
            }
            else
            { 
                p2.setAtpPoints(p2.getAtpPoints() + 500);
                System.out.println("\nPlayer " + p2.getName() + ", " + p2.getAtpPoints() + " won ATP Finals tournament\n");
            }
        }
    }
}
