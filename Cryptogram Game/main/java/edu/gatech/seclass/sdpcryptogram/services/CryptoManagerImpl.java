package edu.gatech.seclass.sdpcryptogram.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.gatech.seclass.sdpcryptogram.MyApplication;
import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayer;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerCryptogramSolution;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerRating;
import edu.gatech.seclass.utilities.ExternalWebService;

/**
 * Created by jabbasi on 7/4/17.
 */

public class CryptoManagerImpl implements  IAdminManager, IPlayerManager {

    private static CryptoManagerImpl singleton = new CryptoManagerImpl();

    private DAO dao;

    private CryptoManagerImpl() {
        dao = new DAO("mydata", MyApplication.getAppContext());

        //create dummy cryptos
//        String username = "testuser";
//        this.addNewPlayer(new MyPlayer("testuser", "Test", "User", true));

        //dummy crypto
//        MyCryptogram myCryptogram = this.addCryptogram(new MyCryptogram("ABC", "BCD"));

        //dummy solution
//        this.solveCryptogramForPlayer("testuser", myCryptogram.getUniqueID(), "AAA");


    }

    //for unit testing only
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public static IAdminManager getAdminManager() {
        return singleton;
    }

    public static IPlayerManager getPlayerManager() {
        return singleton;
    }

    @Override
    public MyCryptogram addCryptogram(MyCryptogram cryptogram) {
        cryptogram.validate();

        try {
            String cryptogramID = ExternalWebService.getInstance().addCryptogramService(cryptogram.getMatchingEncodedPhrase(), cryptogram.getSolutionPhrase());
            cryptogram.setUniqueID(cryptogramID);
            this.dao.saveCryptogram(cryptogram);
        }catch(Exception e) {
            //cryptogram.setUniqueID(null);
        }

        if(Utils.isNotNullOrEmpty(cryptogram.getUniqueID())) {
            dao.saveCryptogram(cryptogram);
        }

        return cryptogram;
    }

    @Override
    public void addNewPlayer(MyPlayer player) {

        assert Utils.isNotNullOrEmpty(player.getUsername());

        dao.savePlayer(player);

        ExternalWebService.getInstance().updateRatingService(player.getUsername(), player.getFirstname(), player.getLastname(), 0, 0, 0);

    }

    @Override
    public List<MyPlayerCryptogramSolution> listOfCryptogramDataToShow(String username) {

        Set<MyCryptogram> cryptograms = new LinkedHashSet<MyCryptogram>();

        List<String[]> externalsCryptograms = ExternalWebService.getInstance().syncCryptogramService();
        if(externalsCryptograms != null){
            for(String[] ec:externalsCryptograms) {
                String solPh = ec[2];
                String encPh = ec[1];
                String id = ec[0];
                MyCryptogram mc = new MyCryptogram(id);
                mc.setSolutionPhrase(solPh);
                mc.setMatchingEncodedPhrase(encPh);
                cryptograms.add(mc);

                if(this.dao.findCryptogramByID(id) == null) {
                    this.dao.saveCryptogram(mc);
                }
            }
        }

        //add any locally persisted cryptograms to list
        cryptograms.addAll(this.dao.listOfCryptograms());

        List<MyPlayerCryptogramSolution> solutions = new ArrayList<MyPlayerCryptogramSolution>();

        for(MyCryptogram cryptogram:cryptograms) {
            MyPlayerCryptogramSolution solution = this.dao.findPlayerCryptogramSolution(username, cryptogram.getUniqueID());
            if(solution == null) {
                solution = new MyPlayerCryptogramSolution(cryptogram.getUniqueID(), username);
            }

            solutions.add(solution);
        }

        return solutions;
    }

    @Override
    public void solveCryptogramForPlayer(String username, String cryptogramID, String playerSolution) {

        assert username != null;
        assert cryptogramID != null;
        assert playerSolution != null;

        MyPlayer player = this.dao.findPlayerByUsername(username);
        if(player == null) {
            throw new IllegalArgumentException("Invalid username");
        }

        MyCryptogram cryptogram = this.dao.findCryptogramByID(cryptogramID);

        if(cryptogram == null) {
            throw new IllegalArgumentException("Invalid cryptogramID");
        }

        MyPlayerCryptogramSolution playerCryptogramSolution = this.dao.findPlayerCryptogramSolution(username, cryptogramID);
        Boolean newCryptoAttempted = false;
        if(playerCryptogramSolution == null) {
            playerCryptogramSolution = new MyPlayerCryptogramSolution(cryptogramID, username);
            newCryptoAttempted = true;
        }

        if(Boolean.TRUE.equals(playerCryptogramSolution.getSolved())) {
            return; //no need to solve again if solved
        }

        playerCryptogramSolution.addPlayerSolution(playerSolution);

        if(cryptogram.getSolutionPhrase().equalsIgnoreCase(playerSolution)) {
            playerCryptogramSolution.setSolved(Boolean.TRUE);
        }

        //persist Player solution
        this.dao.saveMyPlayerCryptogramSolution(playerCryptogramSolution);

        MyPlayerRating playerRating = this.dao.findPlayerRating(username);
        if(playerRating == null) {
            playerRating = new MyPlayerRating(username);
        }

        if(playerCryptogramSolution.getSolved()) {
            playerRating.incrementCryptogramsSolved();
        } else {
            playerRating.incrementIncorrectSolutions();
        }

        if(newCryptoAttempted) {
            playerRating.incrementCryptogramsStarted();
        }

        try {
            boolean success = ExternalWebService.getInstance().updateRatingService(username, player.getFirstname(),
                    player.getLastname(), playerRating.getCryptogramsSolved(),
                    playerRating.getCryptogramsStarted(), playerRating.getIncorrectSolutionSubmitted());
            System.out.println("addPlayerRating to webservice=" + success);
        } catch(Exception e) {
        }

        this.dao.saveMyPlayerRating(playerRating);

    }

    @Override
    public List<MyPlayerRating> listOfPlayerRatings() {

        List<MyPlayerRating> playerRatings = new ArrayList<MyPlayerRating>();

        List<ExternalWebService.PlayerRating> externalPRs = ExternalWebService.getInstance().syncRatingService();
        List<String> externalPRsUsernames = ExternalWebService.getInstance().playernameService();


        for(int i = 0; i < externalPRs.size(); i++) {
            ExternalWebService.PlayerRating pr = externalPRs.get(i);
            String username = externalPRsUsernames.get(i);

            MyPlayerRating pr2 = new MyPlayerRating(username);
            pr2.setDisplayName(pr.getFirstname() + " " + pr.getLastname());
            pr2.setCryptogramsSolved(pr.getSolved());
            pr2.setCryptogramsStarted(pr.getStarted());
            pr2.setIncorrectSolutionSubmitted(pr.getIncorrect());

            playerRatings.add(pr2);
        }


        List<MyPlayerRating> localPRs = this.dao.listOfPlayerRatings();

        for(MyPlayerRating pr:localPRs) {
            MyPlayer player = this.dao.findPlayerByUsername(pr.getUsername());
            pr.setDisplayName(player.getFirstname() + " " + player.getLastname());
            if(playerRatings.contains(pr) == false) {
                playerRatings.add(pr);
            }

        }


        return playerRatings;
    }

    @Override
    public MyCryptogram findCryptogramForID(String cryptogramID) {
        return this.dao.findCryptogramByID(cryptogramID);
    }

    @Override
    public boolean loginAs(String username) {

        MyPlayer player = this.dao.findPlayerByUsername(username);

        return player != null;
    }
}
