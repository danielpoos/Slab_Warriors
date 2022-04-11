package com.example.slab_warriors.data;

import com.google.gson.Gson;
import java.util.Arrays;

public class User {
    private Integer id;
    private String username;
    private String email;
    private final String accountNumber;
    private int level, fighterCount, cardCount;
    private int admin, banned;
    public static User loggedInUser;
    public Integer getId() {
        return id;
    }
    public String getAccountNumber() {
        return accountNumber;
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
    public User(String username, String email, String accountNumber, int level, int fighterCount, int cardCount, int admin, int banned) {
        this.username = username;
        this.email = email;
        this.accountNumber = accountNumber;
        this.level = level;
        this.fighterCount = fighterCount;
        this.cardCount = cardCount;
        this.admin = admin;
        this.banned = banned;
    }
    public User(Integer id, String username, String email, String accountNumber, int level, int fighterCount, int cardCount, int admin, int banned) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.accountNumber = accountNumber;
        this.level = level;
        this.fighterCount = fighterCount;
        this.cardCount = cardCount;
        this.admin = admin;
        this.banned = banned;
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