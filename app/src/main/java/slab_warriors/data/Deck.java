package slab_warriors.data;

import java.util.List;

public class Deck {
    private String gameId;
    private int cardId;
    public List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }
    public Deck(String gameId, int cardId) {
        this.gameId = gameId;
        this.cardId = cardId;
    }
}