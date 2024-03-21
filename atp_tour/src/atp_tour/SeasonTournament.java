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
public class SeasonTournament extends Tournament
{
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quaterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    public SeasonTournament(String tourName, String tourType, String tourSurface) {
        super(tourName, tourType, tourSurface);
    }

    @Override
    public void play()
    {
        
    }
    
}
