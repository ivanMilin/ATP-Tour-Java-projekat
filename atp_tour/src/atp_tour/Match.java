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
        this.p1ScorePerSets = new int[winSetNum+2];
        this.p2ScorePerSets = new int[winSetNum+2];
        this.rng = new Random();
        firstPlayerServes = false;
        setNumber = 0;
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
        System.out.println("Usao sam u playGame()");
        p1Gems = 0;
        p2Gems = 0;
        
        p1counter = 0;
        p2counter = 0;
        
        this.firstPlayerServes = !this.firstPlayerServes;
        
        while(true)
        {
            boolean probability = chanceEvent(whoServesNow().servePointChance(changeWhosHasToServe(), this.matchSurface));
            
            if (probability)
            {
                p1counter ++;
            }
            else
            {
                p2counter ++;
            }

            /*0 = 0, 1 = 15, 2 = 30, 3 = 40, 4 = gem++ */
            if(p1counter == 4 && p2counter < 4)
            {
                p1Gems ++;
                p1ScorePerSets[setNumber] = p1ScorePerSets[setNumber] + 1;
                break;
            }
            else if(p2counter == 4 && p1counter < 4)
            {
                p2Gems ++;
                p2ScorePerSets[setNumber] = p2ScorePerSets[setNumber] + 1;
                break;
            }
            else if(p1counter == 4 && p2counter == 4)
            {
                while(true)
                {
                    boolean deuceSituation = chanceEvent(whoServesNow().servePointChance(changeWhosHasToServe(), this.matchSurface));
                    if(deuceSituation == true)
                    {
                        p1counter++;
                        if(p1counter - p2counter >=2)
                        {
                            p1Gems ++;
                            p1ScorePerSets[setNumber] = p1ScorePerSets[setNumber] + 1;
                            break;
                        }
                    }
                    else
                    {
                        p2counter ++;
                        if(p2counter - p1counter >=2)
                        {
                            p2Gems ++;
                            p2ScorePerSets[setNumber] = p2ScorePerSets[setNumber] + 1;
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
        System.out.println("Usao sam u playSet()");
        while (Math.abs(p1Gems - p2Gems) < 2 || (p1Gems < 6 && p2Gems < 6)) 
        {
             playGame();
        }
        if (p1Gems == 6 && p2Gems <= 4) 
        {
            p1Sets++;
        } 
        if (p2Gems == 6 && p1Gems <= 4) 
        {
            p2Sets++;
        }
        if(p1Gems == 6 && p2Gems == 6)
        {
            playTieBreak();
        }
    }
    
    private void playTieBreak() {
        System.out.println("Usao sam u playTieBreak()");
        int p1Points = 0;
        int p2Points = 0;

        while (true) {
            boolean probability = chanceEvent(whoServesNow().servePointChance(changeWhosHasToServe(), matchSurface));
            if (probability){
                p1Points++;
            } else {
                p2Points++;
            }

            if (p1Points >= 7 || p2Points >= 7) 
            {
                if (Math.abs(p1Points - p2Points) >= 2) 
                {
                    if(p1Points > p2Points)
                    {
                        p1ScorePerSets[setNumber] = p1ScorePerSets[setNumber] + 1;
                        break;
                    }
                    else
                    {
                        p2ScorePerSets[setNumber] = p2ScorePerSets[setNumber] + 1;
                        break;
                    }
                }
            }
        }

        if (p1Points == 7 && p2Points == 6) 
        {
            p1Sets++;
        } 
        else if (p2Points == 7 && p1Points == 6) 
        {
            p2Sets++;
        }
    }

    public Player playMatch()
    {
        System.out.println("Usao sam u playMatch()");
        while(p1Sets < winSetNum && p2Sets < winSetNum)
        {
            playSet();
            setNumber ++;
        }
        printMatchResult();
        
        if(p1Sets > p2Sets)
            return p1;
        else 
            return p2;        
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
