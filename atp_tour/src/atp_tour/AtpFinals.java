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
        System.out.println("Players from groupA : "/* + groupA.size()*/);
        for(Player membersA : groupA)
        {
            System.out.println("    " + membersA.getAtpRank()+". " + membersA.getName());
        }        
        
        System.out.println("Players from groupB : " /*+ groupB.size()*/);
        for(Player membersB : groupB)
        {
            System.out.println("    " + membersB.getAtpRank()+". " + membersB.getName());
        }
        simulateGroupA_GroupB(groupA);
        simulateGroupA_GroupB(groupB);
        
        System.out.println("==========================   SEMI  FINAL   ==========================");
        for (Player player : semiFinalists) {
            System.out.println(player.getName() + " - numberOfWins " + player.getAtpCounter() + " - atpPoints " + player.getAtpPoints());
            player.setAtpCounter(0);
        }
        System.out.println("=====================================================================\n");
        //simulateHalfOfAtpPlayers(semiFinalists);
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
        
    }
}
