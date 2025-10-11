package com.example.poker.controller;

import com.example.poker.Card;
import com.example.poker.service.CardDeckService;
import com.example.poker.service.CardDeckServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/microservice/poker")
public class PokerController {
    //todo: funker på nettsiden men ikke på terminal
    //todo: så enten lag en branch med source kode for å fikse spille
    //todo: eller bare tukle med master branch
    public PokerController(CardDeckService cardDeckService) {
        this.cardDeckService = cardDeckService;
    }



    private final CardDeckService cardDeckService;

    @GetMapping()
    public List<Card> getAllCards(){
        return cardDeckService.getCombinedCards();
    }
}
