package com.example.poker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface HandEvaluatorService {
    void setWinner(String winner);
    String getWinner();
}
