package edu.gatech.seclass.sdpcryptogram.services;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayer;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerCryptogramSolution;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerRating;

/**
 * Created by jabbasi on 7/4/17.
 */

public class DAO {

    private final String CRYPTOGRAMS_FILENAME = "cryptograms2.dat";
    private final String PLAYERS_FILENAME = "players2.dat";
    private final String PLAYER_CRYPTOGRAM_SOLUTIONS_FILENAME = "players_solutions2.dat";
    private final String PLAYER_RATINGS_FILENAME = "players_ratings2.dat";


    private Set<MyCryptogram> cryptograms;
    private Set<MyPlayer> players;
    private Set<MyPlayerCryptogramSolution> playerCryptogramSolutions;
    private Set<MyPlayerRating> playerRatings;

    private String folder;

    private Context context;

    public DAO(String folder, Context context) {
        this.folder = folder;
        this.context = context;
        Set<MyCryptogram> savedCryptograms = (Set<MyCryptogram>)(Object)deserialize(CRYPTOGRAMS_FILENAME);
        if(savedCryptograms == null) {
            cryptograms = new LinkedHashSet<MyCryptogram>();
        } else {
            cryptograms = savedCryptograms;
        }

        Set<MyPlayer> savedPlayers = (Set<MyPlayer>)(Object)deserialize(PLAYERS_FILENAME);
        if(savedPlayers == null) {
            players = new LinkedHashSet<MyPlayer>();
        } else {
            players = savedPlayers;
        }

        Set<MyPlayerCryptogramSolution> savedPlayerSolutions = (Set<MyPlayerCryptogramSolution>)(Object)deserialize(PLAYER_CRYPTOGRAM_SOLUTIONS_FILENAME);
        if(savedPlayerSolutions == null) {
            playerCryptogramSolutions = new LinkedHashSet<MyPlayerCryptogramSolution>();
        } else {
            playerCryptogramSolutions = savedPlayerSolutions;
        }

        Set<MyPlayerRating> savedPlayerRatings = (Set<MyPlayerRating>)(Object)deserialize(PLAYER_RATINGS_FILENAME);
        if(savedPlayerRatings == null) {
            playerRatings = new LinkedHashSet<MyPlayerRating>();
        } else {
            playerRatings = savedPlayerRatings;
        }
    }

    public boolean saveCryptogram(MyCryptogram cryptogram) {
        if(Utils.isNullOrEmpty(cryptogram.getUniqueID())) {
            throw new IllegalArgumentException("Invalid cryptogram");
        }

        this.cryptograms.add(cryptogram);

        serialize(CRYPTOGRAMS_FILENAME, this.cryptograms);

        return true;
    }

    public boolean savePlayer(MyPlayer player) {
        if(Utils.isNullOrEmpty(player.getUsername())) {
            throw new IllegalArgumentException("Invalid player");
        }

        this.players.add(player);

        serialize(PLAYERS_FILENAME, players);

        return true;
    }

    public List<MyCryptogram> listOfCryptograms() {
        Set<MyCryptogram> set = new LinkedHashSet<MyCryptogram>(this.cryptograms);

        return new ArrayList<MyCryptogram>(set);
    }

    public MyPlayer findPlayerByUsername(String username) {
        if(username == null) {
            return null;
        }
        for(MyPlayer player:this.players) {
            if(player.getUsername().equals(username)) {
                return player;
            }
        }

        return null;
    }

    public MyPlayerCryptogramSolution findPlayerCryptogramSolution(String username, String cryptogramID) {
        MyPlayerCryptogramSolution temp = new MyPlayerCryptogramSolution(cryptogramID, username);

        for(MyPlayerCryptogramSolution solution:this.playerCryptogramSolutions) {
            if(solution.equals(temp)) {
                return solution;
            }
        }

        return null;
    }

    public MyCryptogram findCryptogramByID(String cryptogramID) {

        for(MyCryptogram cryptogram:this.cryptograms) {
            if(cryptogram.getUniqueID().equals(cryptogramID)) {
                return cryptogram;
            }
        }

        return null;
    }

    public MyPlayerRating findPlayerRating(String username) {

        assert Utils.isNotNullOrEmpty(username);
        for(MyPlayerRating playerRating:this.playerRatings) {
            if(playerRating.getUsername().equals(username)) {
                return playerRating;
            }
        }

        return null;
    }

    public void saveMyPlayerCryptogramSolution(MyPlayerCryptogramSolution solution) {
        if(Utils.isNullOrEmpty(solution.getCryptogramID()) || Utils.isNullOrEmpty(solution.getPlayerUsername())) {
            throw new IllegalArgumentException("Invalid solution");
        }

        this.playerCryptogramSolutions.add(solution);

        this.serialize(PLAYER_CRYPTOGRAM_SOLUTIONS_FILENAME, this.playerCryptogramSolutions);

    }

    public void saveMyPlayerRating(MyPlayerRating playerRating) {

        this.playerRatings.add(playerRating);

        this.serialize(PLAYER_RATINGS_FILENAME, this.playerRatings);
    }

    public List<MyPlayerRating> listOfPlayerRatings() {
        return new ArrayList<MyPlayerRating>(this.playerRatings);
    }

    private void serialize(String filename, Collection<? extends Object> objects){
        try {
            File file = new File(this.folder + File.pathSeparator + filename);
            FileOutputStream fo;
            if(context != null) {
                fo = context.openFileOutput(file.getName(), Context.MODE_PRIVATE);
            } else {
                fo = new FileOutputStream(file);
            }

            ObjectOutputStream ou = new ObjectOutputStream(fo);
            ou.writeObject(objects);
            ou.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Collection<Object> deserialize(String filename){
        try {
            File file = new File(this.folder + File.pathSeparator + filename);
            FileInputStream fi;
            if(context != null) {
                fi = context.openFileInput(file.getName());
            } else {
                fi = new FileInputStream(file);
            }
            ObjectInputStream oi = new ObjectInputStream(fi);
            Collection<Object> list = (Collection<Object>)oi.readObject();
            oi.close();
            fi.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
