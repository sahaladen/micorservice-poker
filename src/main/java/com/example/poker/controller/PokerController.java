package com.example.poker.controller;

import com.example.poker.Card;
import com.example.poker.service.CardDeckService;
import com.example.poker.service.CardDeckServiceImp;
import com.example.poker.service.EventSender;
import com.example.poker.service.HandEvaluatorImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/microservice/poker")
public class PokerController {

    private final CardDeckServiceImp cardDeckService;
    private final EventSender eventSender;
    private final HandEvaluatorImp handEvaluator;

    @GetMapping()
    public List<Card> getAllCards(){
        log.info("pre-sending");
        eventSender.publishCardTest();
        log.info("post-sending");
        return cardDeckService.getCombinedCards();
    }
    @GetMapping("/winner")
    public String testWinner(){
        log.info("setting test winner...");
        handEvaluator.setWinner("player");
        String winner = handEvaluator.getWinner();
        log.info("winner: " + winner);
        eventSender.publishCardTest();

        return "winner: " + winner;
    }
}
