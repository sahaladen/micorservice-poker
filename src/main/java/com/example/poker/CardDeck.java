package com.example.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> combinedCards = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();
    private final List<Card> playerPulledCard = new ArrayList<>();
    //todo: endre array til å representere bordet.
    private final List<Card> dealerPulledCard = new ArrayList<>();
    private final List<Card> cardsOnTable = new ArrayList<>();

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

    public List<Card> playerDraw(){
        if(combinedCards.isEmpty()){
            System.out.println("no more cards to pull");
        }else {
            Card topCard = combinedCards.remove(0);
            playerPulledCard.add(topCard);
            System.out.println("player Pulled: " + topCard);

        }
        return playerPulledCard;

    }

    public List<Card> dealerDraw(){
        if(combinedCards.isEmpty()){
            System.out.println("no more cards to pull");
        }else {
            Card topCard = combinedCards.remove(0);
            dealerPulledCard.add(topCard);
            System.out.println("dealer Pulled: " + topCard);

        }
        return dealerPulledCard;
    }

    public List<Card> drawCardsOnTable(){
        if(combinedCards.isEmpty()){
            System.out.println("no more cards to pull");
        }else {
            Card topCard = combinedCards.remove(0);
            cardsOnTable.add(topCard);
            System.out.println("dealer Pulled card to table: " + topCard);

        }
        return cardsOnTable;
    }
    public void discardPlayerHeldHands(){
        if(playerPulledCard.isEmpty()){
            System.out.println("no more cards to discard");
        }else {
            Card heldCard = playerPulledCard.remove(0);
            discardPile.add(heldCard);
            System.out.println("discarded player hand: " + heldCard);
        }
    }

    public void discardDealerHeldHands(){
        if(playerPulledCard.isEmpty()){
            System.out.println("no more cards to discard");
        }else {
            Card heldCard = dealerPulledCard.remove(0);
            discardPile.add(heldCard);
            System.out.println("discarded dealer hand: " + heldCard);
        }
    }
    public List<Card> showPlayerHeldCard(){
        if(playerPulledCard.isEmpty()){
            System.out.println("no cards to show");
        }else{
            for(Card card : playerPulledCard){
                System.out.println(card);
            }
        }
        return playerPulledCard;
    }

    public List<Card> showDealerHand(){
        if(dealerPulledCard.isEmpty()){
            System.out.println("no cards to show");
        }else{
            for(Card card : dealerPulledCard){
                System.out.println(card);
            }
        }
        return dealerPulledCard;
    }
    public List<Card> showCardsOnTable(){
        if(cardsOnTable.isEmpty()){
            System.out.println("no cards to show");
        }else{
            for(Card card : cardsOnTable){
                System.out.println(card);
            }
        }
        return cardsOnTable;
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

    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }
}
