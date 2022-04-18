package com.example.slab_warriors.data;

import com.google.gson.Gson;
import java.util.Arrays;

public class User {
    private Integer id;
    private String username, email, password, password_confirmation;
    private int level, fighterCount, cardCount, admin, banned;
    public static User loggedInUser;
    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getFighterCount() {
        return fighterCount;
    }
    public void setFighterCount(int fighterCount) {
        this.fighterCount = fighterCount;
    }
    public int getCardCount() {
        return cardCount;
    }
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }
    public boolean isAdmin() {
        return admin==1;
    }
    public boolean isBanned() {
        return banned==1;
    }
    public void setAdmin(boolean admin) {
        this.admin=admin?1:0;
    }
    public void setBanned(boolean banned){
        this.banned=banned?1:0;
    }
    public User(String username, String email, String password, String password_confirmation) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static User getUser(String in, String name){
        Gson converter = new Gson();
        User[] users = converter.fromJson(in,User[].class);
        boolean present = Arrays.stream(users).anyMatch(u -> u.getUsername().equals(name));
        if (present) {
            loggedInUser = Arrays.stream(users).filter(u -> u.getUsername().equals(name)).findFirst().get();
            return loggedInUser;
        }
        else return null;
    }
}