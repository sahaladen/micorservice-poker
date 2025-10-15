package com.example.poker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HandEvaluatorImp implements HandEvaluatorService{
    private String winner;

    @Override
    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String getWinner() {
        return winner;
    }
}
