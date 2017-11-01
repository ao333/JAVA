package edu.gatech.seclass.sdpcryptogram.services;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayer;

/**
 * Created by jabbasi on 7/4/17.
 */

public interface IAdminManager {

    /**
     * Addlows administrators to add a new cryptogram
     * @param cryptogram
     */
    MyCryptogram addCryptogram(MyCryptogram cryptogram);


    /**
     * Add new local player
     * @param player
     */
    void addNewPlayer(MyPlayer player);
}
