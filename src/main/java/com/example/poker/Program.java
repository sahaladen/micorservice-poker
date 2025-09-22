package com.example.poker;

public class Program {
    //todo: se hvor jeg vil håndtere kortene som er på bordet.
    CardDeck cardDeck = new CardDeck();

    public void Run(){
        cardDeck.combine();
        cardDeck.shuffleCards();
        meny();
    }

    private void meny() {
        System.out.println("welcome to poker. place a bet: ");
        //todo: legg til en måte å få betta på.
        //todo: kan ha egen klasse for det men vi ser ann.
    }
}
