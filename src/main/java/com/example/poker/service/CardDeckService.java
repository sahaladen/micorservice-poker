package com.example.poker.service;

import com.example.poker.Card;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CardDeckService {
    List<Card> combineCards();
    List<Card> playerDraw();
    List<Card> showPlayerHands();
    List<Card> showDealerHands();
    List<Card> dealerDraw();
    List<Card> showCardsOnTable();
    List<Card> drawCardsOnTable();
    void discardAllCards();
}
