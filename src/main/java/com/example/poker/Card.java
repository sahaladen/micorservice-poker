package com.example.poker;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
}
