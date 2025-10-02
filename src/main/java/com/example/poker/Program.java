package com.example.poker;

import java.util.Scanner;

public class Program {
    //todo: se hvor jeg vil håndtere kortene som er på bordet.
    CardDeck cardDeck = new CardDeck();

    public void Run(){
        cardDeck.combine();
        cardDeck.shuffleCards();
        meny();
        start();
    }

    private void meny() {
        System.out.println("welcome to poker. place a bet: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        //todo: da du lager databasen. husk at penga skal være i dollar
        System.out.println("bet placed: " + input + "$");
        //todo: legg til en måte å få betta på.
        //todo: kan ha egen klasse for det men vi ser ann.
    }

    private void start(){
        /*
        for loopen funker som den skal. har ingen ting med index å gjøre.
        det er basert på selve kort stokken.
         */
        HandEvaluator handEvaluator = new HandEvaluator(cardDeck);
        for(int i = 0; i < 5; i++){
            cardDeck.dealerDrawOnTable();
        }
        cardDeck.showDealerHand();

        for(int i = 0; i < 2; i++){
            cardDeck.playerDraw();
        }
        cardDeck.showHeldCard();

        System.out.println("handle class: ");
        handEvaluator.check();

    }
}
