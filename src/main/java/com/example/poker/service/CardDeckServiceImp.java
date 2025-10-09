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
    public List<Card> getCombinedCards() {
        cardDeck.combine();
        return cardDeck.getCombinedCards();
    }
}
