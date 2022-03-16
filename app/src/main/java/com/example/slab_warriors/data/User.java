package com.example.slab_warriors.data;

public class User {
    private Integer id;
    private String username;
    private String email;
    private final String accountNumber;
    private int level, fighterCount, cardCount;
    private boolean admin, banned;
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
        return admin;
    }
    public boolean isBanned() {
        return banned;
    }
    public User(String username, String email, String accountNumber, int level, int fighterCount, int cardCount, boolean admin, boolean banned) {
        this.username = username;
        this.email = email;
        this.accountNumber = accountNumber;
        this.level = level;
        this.fighterCount = fighterCount;
        this.cardCount = cardCount;
        this.admin = admin;
        this.banned = banned;
    }
    public User(Integer id, String username, String email, String accountNumber, int level, int fighterCount, int cardCount, boolean admin, boolean banned) {
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
}