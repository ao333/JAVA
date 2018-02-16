package edu.gatech.seclass.sdpcryptogram.domain;

import edu.gatech.seclass.sdpcryptogram.services.Utils;

/**
 * Created by jabbasi on 7/4/17.
 */

public class MyCryptogram extends BaseDomain {

    private String uniqueID; //assigned by exteral web service
    private String solutionPhrase;
    private String matchingEncodedPhrase;

    public MyCryptogram(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public MyCryptogram(String solutionPhrase, String matchingEncodedPhrase) {
        this.solutionPhrase = solutionPhrase;
        this.matchingEncodedPhrase = matchingEncodedPhrase;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getSolutionPhrase() {
        return solutionPhrase;
    }

    public void setSolutionPhrase(String solutionPhrase) {
        this.solutionPhrase = solutionPhrase;
    }

    public String getMatchingEncodedPhrase() {
        return matchingEncodedPhrase;
    }

    public void setMatchingEncodedPhrase(String matchingEncodedPhrase) {
        this.matchingEncodedPhrase = matchingEncodedPhrase;
    }

    public boolean validate() {
        if(Utils.isNullOrEmpty(solutionPhrase)) {
            throw new IllegalStateException("solutionPhrase is null or empty");
        }

        if(Utils.isNullOrEmpty(matchingEncodedPhrase)) {
            throw new IllegalStateException("matchingEncodedPhrase is null or empty");
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyCryptogram that = (MyCryptogram) o;

        return uniqueID != null ? uniqueID.equals(that.uniqueID) : that.uniqueID == null;

    }

    @Override
    public int hashCode() {
        return uniqueID != null ? uniqueID.hashCode() : 0;
    }
}
