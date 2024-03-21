/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atp_tour;

import java.util.ArrayList;

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

    public AtpFinals(ArrayList<Player> groupA, ArrayList<Player> groupB, ArrayList<Player> semiFinalists, ArrayList<Player> finalists, String tourName, String tourSurface, String tourType) {
        super(tourName, tourSurface, tourType);
        this.groupA = groupA;
        this.groupB = groupB;
        this.semiFinalists = semiFinalists;
        this.finalists = finalists;
    }
    
    @Override
    public void play(){
    }
}
