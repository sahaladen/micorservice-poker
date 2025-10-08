package com.example.poker;

//todo: lombok doesnt work. find out at a later time why.

public class Card {
    private Suit cardSuit;
    private Rank cardRank;

    public Card(Suit cardType, Rank cardNumber) {
        this.cardSuit = cardType;
        this.cardRank = cardNumber;
    }

    @Override
    public String toString() {
        return "Card{" +
                cardSuit +
                ", " + cardRank +
                '}';
    }


    public Suit getCardSuit() {
        return cardSuit;
    }

    public Rank getCardRank() {
        return cardRank;
    }
}
