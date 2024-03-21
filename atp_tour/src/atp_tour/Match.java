/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    private Random rng;
    
    int p1counter = 0, p2counter = 0;
    int p1points = 0, p2points = 0;

    public Match(Player p1, Player p2, String matchSurface, int winSetNum) {
        this.p1 = p1;
        this.p2 = p2;
        this.matchSurface = matchSurface;
        this.winSetNum = winSetNum;
        this.p1ScorePerSets = new int[winSetNum];
        this.p2ScorePerSets = new int[winSetNum];
        this.rng = new Random();
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
        while(true)
        {
            boolean playerProbability = chanceEvent(p1.ServePointChance(p2, matchSurface));

            if(playerProbability == true)
                p1counter ++;
            else
                p2counter ++;


            /*0 = 0, 1 = 15, 2 = 30, 3 = 40, 4 = gem++ */
            if(p1counter == 4 && p2counter < 4)
            {
                p1Gems ++;
                break;
            }
            else if(p2counter == 4 && p1counter < 4)
            {
                p1Gems ++;
                break;
            }
            else if(p1counter == 4 && p2counter == 4)
            {
                while(true)
                {
                    boolean deuceSituation = chanceEvent(p1.ServePointChance(p2, matchSurface));
                    if(deuceSituation == true)
                    {
                        p1counter++;
                        if(p1counter - p2counter >=2)
                        {
                            p1Gems ++;
                            break;
                        }
                    }
                    else
                    {
                        p2counter ++;
                        if(p2counter - p1counter >=2)
                        {
                            p2Gems ++;
                            break;
                        }
                    }
                }
                break;
            }
        }        
    }

    private void playSet()
    {
        while (Math.abs(p1Gems - p2Gems) < 2 || (p1Gems < 6 && p2Gems < 6)) 
        {
             playGame();
        }
        if(p1Gems == 6 && p2Gems == 6)
        {
            playTieBreak();
        }
    }
    
    private void playTieBreak() {
        int p1Points = 0;
        int p2Points = 0;

        while (true) {
            boolean probability = chanceEvent(p1.ServePointChance(p2, matchSurface));
            if (probability) {
                p1Points++; // Domaćin osvaja poen
            } else {
                p2Points++; // Gost osvaja poen
            }

            // Provera da li je neki od igrača osvojio 7 poena
            if (p1Points >= 7 || p2Points >= 7) {
                if (Math.abs(p1Points - p2Points) >= 2) {
                    break;
                }
            }
        }

        // Provera rezultata tie-breaka
        if (p1Points == 7 && p2Points == 6) {
            p1Sets++;
        } else if (p2Points == 7 && p1Points == 6) {
            p2Sets++;
        }
    }

    public void playMatch()
    {
        int randomNumber = rng.nextInt(100)+1; //Ovo moras da proveris da li je dobro
        if(randomNumber == 1)
        {
            p1.setInjured(true);
        }
        else
        {
            if(winSetNum == 3)
            {
                playSet();
                playSet();
                playSet();
            }        
            else
            {
                playSet();
                playSet();
            }
        }
    }
    
    public void printMatchResult()
    {
        if(winSetNum == 2)
        {
            System.out.println(p1.getName() +"   "+ p1ScorePerSets[0] +" "+ p1ScorePerSets[1] +"     "+ p1Sets);
            System.out.println(p2.getName() +"   "+ p2ScorePerSets[0] +" "+ p2ScorePerSets[1] +"     "+ p2Sets);
        }
        else if(winSetNum == 3)
        {
            System.out.println(p1.getName() +"   "+ p1ScorePerSets[0] +" "+ p1ScorePerSets[1] +" "+ p1ScorePerSets[2] +"     "+ p1Sets);
            System.out.println(p2.getName() +"   "+ p2ScorePerSets[0] +" "+ p2ScorePerSets[1] +" "+ p2ScorePerSets[2] +"     "+ p2Sets);
        }
        else
            System.out.println("Probleme u ispis metode printMatchResult()");
    }
    /*
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets ????
        GDE DA STAVIM DODELU VREDNOSTI PROMENLJIVIM p1ScorePerSets ????
    */

}
