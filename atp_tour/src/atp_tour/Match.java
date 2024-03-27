package atp_tour;

import java.util.Random;

/**
 *
 * @author Ivan Milin
 */
public class Match 
{
    private Player p1;
    private Player p2;
    private String matchSurface;
    private int winSetNum;
    private int p1Sets, p1Gems;
    private int p2Sets, p2Gems;
    private int p1ScorePerSets[];
    private int p2ScorePerSets[];
    private int setNumber;
    private Random rng;
    private boolean firstPlayerServes;
    
    int p1counter = 0, p2counter = 0;
    int p1points = 0, p2points = 0;

    public Match(Player p1, Player p2, String matchSurface, int winSetNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.matchSurface = matchSurface;
        this.winSetNum = winSetNum;
        this.p1Sets = 0;
        this.p1Gems = 0;
        this.p2Sets = 0;
        this.p2Gems = 0;
        this.p1ScorePerSets = new int[winSetNum*2-1];
        this.p2ScorePerSets = new int[winSetNum*2-1];
        this.rng = new Random();
        this.firstPlayerServes = false;
        this.setNumber = 0;
    }
    
    private boolean chanceEvent(int probability)
    {
        int randomNumber = rng.nextInt(100)+1; //Ovo moras da proveris da li je dobro
        if(randomNumber <= probability)
            return true;
        else
            return false;
    }

    private void playGame()
    {
        //System.out.println("Usao sam u playGame()");
        
        p1counter = 0;
        p2counter = 0;
        
        this.firstPlayerServes = !this.firstPlayerServes;
        
        while(true)
        {
            boolean probability = chanceEvent(whoServesNow().servePointChance(changeWhosHasToServe(), this.matchSurface));
            
            if (probability)
            {
                if(whoServesNow().getName().equals(p1.getName()))
                    p1counter ++;
                if(whoServesNow().getName().equals(p2.getName()))
                    p2counter ++;
            }
            else
            {
                if(changeWhosHasToServe().getName().equals(p1.getName()))
                    p1counter ++;
                if(changeWhosHasToServe().getName().equals(p2.getName()))
                    p2counter ++;
            }
            
            if(p1counter == 4 && p2counter < 3)
                break;
            
            if(p2counter == 4 && p1counter < 3)
                break;
            
            if (Math.abs(p1counter - p2counter) == 2 && (p1counter >= 3 || p2counter >= 3))
                break;
            
            //if (p2counter - p1counter == 2 && (p1counter >= 3 || p2counter >= 3))
                //break;
            
            if(p1counter == 3 && p2counter == 3)
                continue;
            
            if(p1counter >= 4 && p2counter >= 4)
            {
                if(Math.abs(p1counter - p2counter) >= 2)
                {
                    break;
                }
            }
        }
        
        /*0 = 0, 1 = 15, 2 = 30, 3 = 40, 4 = gem++ */
        if(p1counter > p2counter)
        {
            p1Gems ++;
            p1ScorePerSets[setNumber] ++;
        }   
        else
        {
            p2Gems ++;
            p2ScorePerSets[setNumber] ++;
        }       
    }

    private void playSet()
    {
        p1Gems = 0;
        p2Gems = 0;
        
        //System.out.println("Usao sam u playSet()");
        
        while (Math.abs(p1Gems - p2Gems) < 2 || (p1Gems < 6 && p2Gems < 6)) 
        {   
            playGame();   
            
            if(p1Gems == 6 && p2Gems == 6)
                break;
        }
        
        if(p1Gems == 6 && p2Gems == 6)
        {
            //System.out.println("Situacija za TieBreak()");
            playTieBreak();
        }
        
        if (p1Gems > p2Gems)
            p1Sets++;
        else
            p2Sets++;
    }

    private void playTieBreak() 
    {
        //System.out.println("Usao sam u playTieBreak()");
        int p1Points = 0;
        int p2Points = 0;

        while (true) 
        {
            this.firstPlayerServes = !this.firstPlayerServes;
            boolean probability = chanceEvent(whoServesNow().servePointChance(changeWhosHasToServe(), matchSurface));
            
            if (probability)
            {
                if(whoServesNow().getName().equals(p1.getName()))
                    p1Points ++;
                if(whoServesNow().getName().equals(p2.getName()))
                    p2Points ++;
            }
            else
            {
                if(changeWhosHasToServe().getName().equals(p1.getName()))
                    p1Points ++;
                if(changeWhosHasToServe().getName().equals(p2.getName()))
                    p2Points ++;
            }
            
            if(p1Points == 7 && p2Points < 6)
                break;
            
            if(p2Points == 7 && p1Points < 6)
                break;
            
            if(p1Points - p2Points == 2 && (p1Points >= 6 && p2Points >= 6))
                break;
            
            if(p2Points - p1Points == 2 && (p1Points >= 6 && p2Points >= 6))
                break;
        }

        if(p1Points > p2Points)
        {
            p1Gems ++;
            p1ScorePerSets[setNumber] ++;
        }   
        else
        {
            p2Gems ++;
            p2ScorePerSets[setNumber] ++;
        } 

    }

    public Player playMatch()
    {
        //System.out.println("Usao sam u playMatch()");
        while(p1Sets < winSetNum && p2Sets < winSetNum)
        {
            playSet();
            setNumber ++;
        }
        //printMatchResult();
        
        if(p1Sets > p2Sets)
            return p1;
        else 
            return p2;        
    }
    
    public void printMatchResult() 
    {
        StringBuilder toString_p1ScorePerSets = new StringBuilder();
        StringBuilder toString_p2ScorePerSets = new StringBuilder();
        
        for(int i = 0; i < setNumber; i++)
        {
            toString_p1ScorePerSets.append(p1ScorePerSets[i]).append(" ");
            toString_p2ScorePerSets.append(p2ScorePerSets[i]).append(" ");
        }
        System.out.println(String.format("%-20s %10s %2s", p1.getName(), toString_p1ScorePerSets, p1Sets));
        System.out.println(String.format("%-20s %10s %2s\n", p2.getName(), toString_p2ScorePerSets, p2Sets));
    }
    
    public Player whoServesNow(){
        if(firstPlayerServes == true)
            return p1;
        else
            return p2;
    }
    
    public Player changeWhosHasToServe(){
        if(firstPlayerServes == true)
            return p2;
        else
            return p1;
    }
    /*
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets i p2ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets i p2ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets i p2ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets i p2ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets i p2ScorePerSets ????
    */
}