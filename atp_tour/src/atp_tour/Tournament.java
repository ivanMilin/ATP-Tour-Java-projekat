package atp_tour;

import java.util.ArrayList;

/**
 *
 * @author Ivan Milin
 */
abstract class Tournament {
    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;

    public Tournament(String tourName, String tourSurface, String tourType) {
        this.tourName = tourName;
        this.tourType = tourType;
        this.tourSurface = tourSurface;
        this.playable = true;
        
        if(tourType.equals("Grand Slam"))
            this.numOfSets = 3;
        else
            this.numOfSets = 2;        
    }
    
    // ======== Getters ========
    public String getTourName() {
        return tourName;
    }

    public String getTourType() {
        return tourType;
    }

    public String getTourSurface() {
        return tourSurface;
    }

    public boolean isPlayable() {
        return playable;
    }

    public int getNumOfSets() {
        return numOfSets;
    }

    //public ArrayList<Player> getContestants() {
    //    return contestants;
    //}

    // ======== Setters ========

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public void setTourSurface(String tourSurface) {
        this.tourSurface = tourSurface;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public void setNumOfSets(int numOfSets) {
        this.numOfSets = numOfSets;
    }

    public void setContestants(ArrayList<Player> contestants) {
        this.contestants = contestants;
    }
    
    abstract public void play();
}


