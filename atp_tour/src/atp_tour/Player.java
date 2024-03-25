package atp_tour;

import java.util.Random;

/**
 *
 * @author Ivan Milin
 */
public class Player implements Comparable<Player>
{
    private String name;
    private String ability;
    private String preferedSurface;
    private int atpRank;
    private int atpPoints;
    private int atpCounter;
    private boolean injured;
    
    public Player(int atpRank, String name, String ability, String preferedSurface, int atpPoints, boolean injured) {
        //System.out.println("Created Player");
        this.name = name;
        this.ability = ability;
        this.preferedSurface = preferedSurface;
        this.atpRank = atpRank;
        this.atpPoints = atpPoints;
        this.injured = injured;
        this.atpCounter = 0;
    }

    // ======== Getters ========
    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    public String getPreferedSurface() {
        return preferedSurface;
    }

    public int getAtpRank() {
        return atpRank;
    }

    public int getAtpPoints() {
        return atpPoints;
    }

    public boolean isInjured() {
        return injured;
    }

    public int getAtpCounter() {
        return atpCounter;
    }

    // ======== Setters ========

    public void setName(String name) {
        this.name = name;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setPreferedSurface(String preferedSurface) {
        this.preferedSurface = preferedSurface;
    }

    public void setAtpRank(int atpRank) {
        this.atpRank = atpRank;
    }

    public void setAtpPoints(int atpPoints) {
        this.atpPoints = atpPoints;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    public void setAtpCounter(int atpCounter) {
        this.atpCounter = atpCounter;
    }
    
    // ======== Additional methods ========
    public int servePointChance(Player opponent, String surface){   
        int probabilityChance = 50; 
        
        if(opponent.ability.equals("backhand"))
        {
            probabilityChance = probabilityChance - 8;
        }
        if(ability.equals("forehand"))
        {
            probabilityChance = probabilityChance - 10;
        }
        if(ability.equals("serve"))
        {
            probabilityChance = probabilityChance + 15;
        }
        if(opponent.ability.equals("serve"))
        {
            probabilityChance = probabilityChance - 5;
        }
        if(opponent.ability.equals("mentality"))
        {
            probabilityChance = probabilityChance + 10;
        }
        if(ability.equals("mentality"))
        {
            probabilityChance = probabilityChance + 5;
        }
        if(preferedSurface.equals(surface))
        {
            probabilityChance = probabilityChance + 5;
        }    

        probabilityChance = probabilityChance - Math.abs(this.atpRank - opponent.getAtpRank());
        
    return probabilityChance;
    }
    
    @Override
    public String toString(){
        return "Player: " + name + " ,ability : " + ability + " ,preferedSurface : " + 
                preferedSurface + " ,atpRank : " + atpRank + " ,atpPoints : " + atpPoints + " ,injured : " + injured + " \n";
    }

    @Override
    public int compareTo(Player o)
    {
        if(o == null)
            return 1;
        int greaterThan = 0;
        if(this.atpPoints < o.getAtpPoints())
            greaterThan = 1;
        else if(this.atpPoints > o.getAtpPoints())
            greaterThan = -1;
        return greaterThan;
    }
    
    public boolean isHeInjured()
    {
        this.injured = false;
        Random random_oneToHundred = new Random();
        int  injureProbability = random_oneToHundred.nextInt(100)+1;
        
        if(injureProbability == 1){
            this.injured = true;
        }
        return injured;
    }
}
