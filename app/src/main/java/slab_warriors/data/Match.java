package slab_warriors.data;

public class Match {
    private User player;
    private Game game;
    private Fighter fighter;
    private Enemy enemy;
    private Deck deck;
    private final int placeableCards = 3;

    public Match(User player, Game game, Fighter fighter, Enemy enemy, Deck deck) {
        this.player = player;
        this.game = game;
        this.fighter = fighter;
        this.enemy = enemy;
        this.deck = deck;
    }
    public User getPlayer() {
        return player;
    }
    public void setPlayer(User player) {
        this.player = player;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Fighter getFighter() {
        return fighter;
    }
    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
    public Enemy getEnemy() {
        return enemy;
    }
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    public Deck getDeck() {
        return deck;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    /*
        create game + deck
     */
    public void matchMade() {
        boolean win = this.enemy.getHp() < 1 ^ this.fighter.getHp() < 1;
        while (win) {
            newRound();
        }
        if (win) {
            int lvl = this.player.getLevel();
            this.player.setLevel(lvl++);
        }
    }
    //one round
    private void newRound() {}
}
