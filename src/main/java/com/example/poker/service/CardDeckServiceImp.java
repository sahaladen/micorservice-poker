package com.example.poker.service;

import com.example.poker.Card;
import com.example.poker.CardDeck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardDeckServiceImp implements CardDeckService{

    private final CardDeck cardDeck = new CardDeck();

    @Override
    public List<Card> combineCards() {
        cardDeck.combine();
        cardDeck.shuffleCards();
        return cardDeck.getCombinedCards();
    }



    @Override
    public List<Card> playerDraw() {
        cardDeck.playerDraw();
        cardDeck.showPlayerHeldCard();
        return cardDeck.showPlayerHeldCard();
    }

    @Override
    public List<Card> showPlayerHands() {
        return cardDeck.showPlayerHeldCard();
    }

    @Override
    public List<Card> showDealerHands() {
        return cardDeck.showDealerHand();
    }

    @Override
    public List<Card> dealerDraw() {
        return cardDeck.dealerDraw();
    }

    @Override
    public List<Card> showCardsOnTable() {
        return cardDeck.showCardsOnTable();
    }

    @Override
    public List<Card> drawCardsOnTable() {
        return cardDeck.drawCardsOnTable();
    }
}
