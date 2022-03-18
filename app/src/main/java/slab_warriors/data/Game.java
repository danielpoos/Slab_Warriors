package slab_warriors.data;

public class Game {
    private String id;
    private int userId, fighterId;

    public Game(String id, int userId, int fighterId) {
        this.id = id;
        this.userId = userId;
        this.fighterId = fighterId;
    }
    public Game(String id, int userId, int fighterId, Deck deck){
        //
    }
}