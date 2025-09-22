package com.example.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> combinedCards = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();
    private final List<Card> playerPulledCard = new ArrayList<>();
    private final List<Card> dealerPulledCard = new ArrayList<>();

    public void combine(){
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) { // skip NONE if you have it
                for (Suit suit : Suit.values()) {
                    combinedCards.add(new Card(suit, rank));
                }
            }
        }
    }

    public void shuffleCards(){
        Collections.shuffle(combinedCards);
        System.out.println("deck shuffled");
    }

    public void playerDraw(){
        if(combinedCards.isEmpty()){
            System.out.println("no more cards to pull");
        }else {
            Card topCard = combinedCards.remove(0);
            playerPulledCard.add(topCard);
            System.out.println("Pulled: " + topCard);

        }

    }

    public void dealerDrawOnTable(){
        if(combinedCards.isEmpty()){
            System.out.println("no more cards to pull");
        }else {
            Card topCard = combinedCards.remove(0);
            dealerPulledCard.add(topCard);
            System.out.println("Pulled: " + topCard);

        }
    }
    public void discardHeldHands(){
        if(playerPulledCard.isEmpty()){
            System.out.println("no more cards to discard");
        }else {
            Card heldCard = playerPulledCard.remove(0);
            discardPile.add(heldCard);
            System.out.println("discarded " + heldCard);
        }
    }
    public void showHeldCard(){
        if(playerPulledCard.isEmpty()){
            System.out.println("no cards to show");
        }else{
            for(Card card : playerPulledCard){
                System.out.println(card);
            }
        }
    }


    public List<Card> getCombinedCards(){
        System.out.println("Cards left in deck: " + combinedCards.size());
        return combinedCards;
    }
    public List<Card> getPlayerPulledCards() {
        return playerPulledCard;
    }
    public List<Card> getDealerPulledCard(){
        return dealerPulledCard;
    }
}
