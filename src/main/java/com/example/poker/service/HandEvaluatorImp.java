package com.example.poker.service;

import com.example.poker.CardDeck;
import com.example.poker.HandEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandEvaluatorImp implements HandEvaluatorService{

    private final HandEvaluator handEvaluator;



    @Override
    public String getWinner() {
        handEvaluator.check();
        return handEvaluator.getWinner();
    }


}
