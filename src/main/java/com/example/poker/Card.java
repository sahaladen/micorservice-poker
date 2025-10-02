package com.example.poker;

import lombok.Getter;
import lombok.Setter;

//todo: lombok doesnt work. find out at a later time why.

public class Card {
    private Suit cardType;
    private Rank cardNumber;

    public Card(Suit cardType, Rank cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Card{" +
                cardType +
                ", " + cardNumber +
                '}';
    }


    public Suit getCardType() {
        return cardType;
    }

    public Rank getCardNumber() {
        return cardNumber;
    }
}
