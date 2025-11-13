package com.example.poker.controller;

import com.example.poker.Card;
import com.example.poker.CardDeck;
import com.example.poker.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/microservice/poker")
public class PokerController {

    private final CardDeck cardDeck;
    private final CardDeckServiceImp cardDeckService;
    private final EventSender eventSender;
    private final HandEvaluatorImp handEvaluator;
    private final BetManagerServiceImp betManagerService;

    @PostMapping("/combine-cards")
    public List<Card> getAllCards(){
        return cardDeckService.combineCards();
    }

    @PostMapping("/player-draw")
    public List<Card> playerDraw(){
        return cardDeckService.playerDraw();
    }

    @PostMapping("/dealer-draw")
    public List<Card> dealerDraw(){
        return cardDeckService.dealerDraw();
    }

    @PostMapping("/draw-cards-on-table")
    public List<Card> drawCardsOnTable(){
        return cardDeckService.drawCardsOnTable();
    }

    @PostMapping("/discard-all-cards")
    public void discardAllCards(){
        cardDeckService.discardAllCards();
    }





    @GetMapping("/test-all")
    public String testAll(){
        log.info("sending test money...");
        //trenger ikke denne linja med koden men det gjør det mer lesbart.
        long balance = betManagerService.getPlayerBalance();


        log.info("pre-sending");
        eventSender.publishPlayerBalance();
        log.info("post-sending");

        return String.format("all tests are done:\nBalance: %d", balance);
    }
}
