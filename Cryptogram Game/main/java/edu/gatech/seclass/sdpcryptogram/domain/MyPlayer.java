package edu.gatech.seclass.sdpcryptogram.domain;

/**
 * Created by jabbasi on 7/4/17.
 */

public class MyPlayer extends BaseDomain {

    private String username; //must be unique
    private String firstname;
    private String lastname;
    private String playerRating;
    private Boolean isAdmin;

    public MyPlayer(String username) {
        this.username = username;
    }

    public MyPlayer(String username, String firstname, String lastname, Boolean isAdmin) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPlayerRating() {
        return playerRating;
    }

    public void setPlayerRating(String playerRating) {
        this.playerRating = playerRating;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPlayer player = (MyPlayer) o;

        return username != null ? username.equals(player.username) : player.username == null;

    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

}
