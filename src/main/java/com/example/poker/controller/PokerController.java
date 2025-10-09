package com.example.poker.controller;

import com.example.poker.Card;
import com.example.poker.service.CardDeckService;
import com.example.poker.service.CardDeckServiceImp;
import com.example.poker.service.EventSender;
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

    @GetMapping()
    public List<Card> getAllCards(){
        log.info("pre-sending");
        eventSender.publishTest();
        log.info("post-sending");
        return cardDeckService.getCombinedCards();
    }
}
