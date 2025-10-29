package com.example.poker.controller;

import com.example.poker.Card;
import com.example.poker.CardDeck;
import com.example.poker.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/microservice/poker")
public class PokerController {

    private final CardDeck cardDeck;
    private final CardDeckServiceImp cardDeckService;
    private final EventSender eventSender;
    private final HandEvaluatorImp handEvaluator;
    private final BetManagerServiceImp betManagerService;

    @GetMapping("/combine-cards")
    public List<Card> getAllCards(){
        log.info("pre-sending");
        eventSender.publishCardTest();
        log.info("post-sending");
        return cardDeckService.combineCards();
    }

    @GetMapping("/player-draw")
    public List<Card> playerDraw(){
        return cardDeckService.playerDraw();
    }
    @GetMapping("/dealer-draw")
    public List<Card> dealerDraw(){
        return cardDeckService.dealerDraw();
    }
    @GetMapping("/draw-cards-on-table")
    public List<Card> drawCardsOnTable(){
        return cardDeckService.drawCardsOnTable();
    }
    @GetMapping("/discard-all-cards")
    public void discardAllCards(){
        cardDeckService.discardAllCards();
    }



    @GetMapping("/player-hand")
    public List<Card> playerHand(){
        return cardDeckService.showPlayerHands();
    }

    @GetMapping("/dealer-hand")
    public List<Card> dealerHand(){
        return cardDeckService.showDealerHands();
    }
    @GetMapping("/show-cards-on-table")
    public List<Card> showCardsOnTable(){
        return cardDeckService.showCardsOnTable();
    }


    @GetMapping("/get-winner")
    public String getWinner() {
        return handEvaluator.getWinner();

    }




    @GetMapping("/test-all")
    public String testAll(){
        log.info("sending test money...");
        //trenger ikke denne linja med koden men det gjør det mer lesbart.
        long balance = betManagerService.getPlayerBalance();
        eventSender.publishPlayerBalance();

        log.info("setting test winner...");

        String winner = "playertest";
        log.info("winner: " + winner);
        eventSender.publishCardTest();

        log.info("pre-sending");
        eventSender.publishCardTest();
        log.info("post-sending");

        return String.format("all tests are done: \nWinner: %s\nBalance: %d", winner, balance);
    }
}
