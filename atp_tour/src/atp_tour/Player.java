/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp_tour;

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
    private boolean injured = false; 
    private int probabilityChance; 
    private boolean whichTurn; 
    
    public Player(int atpRank, String name, String ability, String preferedSurface, int atpPoints) {
        //System.out.println("Created Player");
        this.name = name;
        this.ability = ability;
        this.preferedSurface = preferedSurface;
        this.atpRank = atpRank;
        this.atpPoints = atpPoints;
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

    public int getProbabilityChance() {
        return probabilityChance;
    }

    public boolean getWhichTurn() {
        return whichTurn;
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

    public void setProbabilityChance(int probabilityChance) {
        this.probabilityChance = probabilityChance;
    }

    public void setWhichTurn(boolean whichTurn) {
        this.whichTurn = whichTurn;
    }
    
    // ======== Additional methods ========
    public int ServePointChance(Player opponent, String surface){       
        if(ability.equals("backhand")){
            if(!opponent.getAbility().equals("Backhand"))
                probabilityChance = probabilityChance + 8;
            else
                probabilityChance = probabilityChance - 8;
        }
        else if(ability.equals("forehand")){
            probabilityChance = probabilityChance + 10;
        }
        else if(ability.equals("serve")){
            if (!opponent.getAbility().equals("serve"))
                probabilityChance = probabilityChance + 15; 
            else 
                probabilityChance = probabilityChance - 5;
        }
        else if(ability.equals("mentality")){
            if(opponent.getWhichTurn() == true){
                probabilityChance = probabilityChance + 10;
                opponent.setProbabilityChance(opponent.getProbabilityChance()-10);   
            }
            else
                probabilityChance = probabilityChance + 5;
        }
        
        if(preferedSurface.equals(surface)){
            probabilityChance = probabilityChance + 10;
        }    
        probabilityChance = probabilityChance - Math.abs(this.atpPoints - opponent.getAtpPoints());
        return probabilityChance;
    }
    
    @Override
    public String toString(){
        return "Player: " + name + " ability : " + ability + "preferedSurface : " + 
                preferedSurface + " atpRank : " + atpRank + "atpPoints : " + atpPoints + " injured : " + injured;
    }

    @Override
    public int compareTo(Player o)
    {
        if(o == null)
            return 1;
        int greaterThan = 0;
        if(this.atpPoints > o.getAtpPoints())
            greaterThan = 1;
        else if(this.atpPoints < o.getAtpPoints())
            greaterThan = -1;
        return greaterThan;
    }
}
