package com.example.poker.service;

import com.example.poker.CardDeck;
import com.example.poker.HandEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandEvaluatorImp implements HandEvaluatorService{
    private String winner;
    private final CardDeck cardDeck = new CardDeck();
    //todo: finn ut hvordan do vil håndtere all dette med å få lagra kortenen.
    private final HandEvaluator handEvaluator = new HandEvaluator(cardDeck);

    @Override
    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String getWinner() {
        return winner;
    }
}
