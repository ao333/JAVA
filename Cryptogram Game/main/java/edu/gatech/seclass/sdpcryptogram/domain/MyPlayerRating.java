package edu.gatech.seclass.sdpcryptogram.domain;

/**
 * Created by jabbasi on 7/4/17.
 */

public class MyPlayerRating extends  BaseDomain {

    private String username;
    private String displayName;
    private int cryptogramsSolved;
    private int cryptogramsStarted;
    private int incorrectSolutionSubmitted;

    public MyPlayerRating(String username) {
        this.username = username;
        this.cryptogramsSolved = 0;
        this.cryptogramsStarted = 0;
        this.incorrectSolutionSubmitted = 0;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getCryptogramsSolved() {
        return cryptogramsSolved;
    }

    public void setCryptogramsSolved(int cryptogramsSolved) {
        this.cryptogramsSolved = cryptogramsSolved;
    }

    public int getCryptogramsStarted() {
        return cryptogramsStarted;
    }

    public void setCryptogramsStarted(int cryptogramsStarted) {
        this.cryptogramsStarted = cryptogramsStarted;
    }

    public int getIncorrectSolutionSubmitted() {
        return incorrectSolutionSubmitted;
    }

    public void setIncorrectSolutionSubmitted(int incorrectSolutionSubmitted) {
        this.incorrectSolutionSubmitted = incorrectSolutionSubmitted;
    }

    public String getUsername() {
        return username;
    }

    public void incrementCryptogramsSolved() {
        this.cryptogramsSolved += 1;
    }

    public void incrementCryptogramsStarted() {
        this.cryptogramsStarted += 1;
    }

    public void incrementIncorrectSolutions() {
        this.incorrectSolutionSubmitted += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPlayerRating that = (MyPlayerRating) o;

        return username != null ? username.equals(that.username) : that.username == null;

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
