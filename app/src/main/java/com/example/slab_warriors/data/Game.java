package com.example.slab_warriors.data;

public class Game {
    private int id,userId,fighterId;
    public int getId() {return id;}
    public int getUserId() {return userId;}
    public int getFighterId() {return fighterId;}
    public static Game currentGame;
    public Game(int id, int userId, int fighterId) {
        this.id = id;
        this.userId = userId;
        this.fighterId = fighterId;
    }
    public Game(int userId, int fighterId) {
        this.userId = userId;
        this.fighterId = fighterId;
    }
}