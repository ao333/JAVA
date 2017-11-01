package edu.gatech.seclass.sdpcryptogram.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.sdpcryptogram.services.Utils;

/**
 * Created by jabbasi on 7/4/17.
 */

public class MyPlayerCryptogramSolution extends  BaseDomain {

    private String cryptogramID;
    private String playerUsername;

    private List<String> playerSolutions;
    private Boolean solved;


    public MyPlayerCryptogramSolution(String cryptogramID, String playerUsername) {
        assert Utils.isNotNullOrEmpty(cryptogramID);
        assert Utils.isNotNullOrEmpty(playerUsername);

        this.cryptogramID = cryptogramID;
        this.playerUsername = playerUsername;
        this.solved = false;
    }

    public String getCryptogramID() {
        return cryptogramID;
    }

    public void setCryptogramID(String cryptogramID) {
        this.cryptogramID = cryptogramID;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public void addPlayerSolution(String playerSolution) {
        if(playerSolutions == null) {
            playerSolutions = new ArrayList<String>();
        }

        playerSolutions.add(playerSolution);
    }

    public List<String> getPlayerSolutions() {
        return playerSolutions;
    }

    public int playerIncorrectSubmissions() {
        if(playerSolutions == null) {
            return 0;
        }

        int submissions = playerSolutions.size();
        int incorrectSubmissions = getSolved() ? submissions - 1 : submissions;
        if(incorrectSubmissions < 0) {
            incorrectSubmissions = 0;
        }

        return incorrectSubmissions;
    }

    public String latestPlayerSolution() {
        if(playerSolutions == null || playerSolutions.isEmpty()) {
            return "";
        }

        return playerSolutions.get(playerSolutions.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPlayerCryptogramSolution that = (MyPlayerCryptogramSolution) o;

        if (cryptogramID != null ? !cryptogramID.equals(that.cryptogramID) : that.cryptogramID != null)
            return false;
        return playerUsername != null ? playerUsername.equals(that.playerUsername) : that.playerUsername == null;

    }

    @Override
    public int hashCode() {
        int result = cryptogramID != null ? cryptogramID.hashCode() : 0;
        result = 31 * result + (playerUsername != null ? playerUsername.hashCode() : 0);
        return result;
    }
}
