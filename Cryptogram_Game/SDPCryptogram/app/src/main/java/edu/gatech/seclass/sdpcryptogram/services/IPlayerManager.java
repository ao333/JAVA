package edu.gatech.seclass.sdpcryptogram.services;

import java.util.List;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerCryptogramSolution;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerRating;

/**
 * Created by jabbasi on 7/4/17.
 */

public interface IPlayerManager {

    /**
     * List of cryptograms which Player will use to choose a cryptogram
     * @return
     */
    List<MyPlayerCryptogramSolution> listOfCryptogramDataToShow(String username);

    /**
     * Adds/update the cryptogram solution for choosen player
     * @param username
     * @param cryptogramID
     * @param playerSolution
     */
    void solveCryptogramForPlayer(String username, String cryptogramID, String playerSolution);


    /**
     * Provides the list of Player ratings
     * @return
     */
    List<MyPlayerRating> listOfPlayerRatings();

    /**
     *
     * @param cryptogramID
     * @return
     */
    MyCryptogram findCryptogramForID(String cryptogramID);

    /**
     *
     * @param username
     * @return
     */
    boolean loginAs(String username);

}
