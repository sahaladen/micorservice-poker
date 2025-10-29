package com.example.poker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class HandEvaluator {

    private  final CardDeck cardDeck;
    private String winner;
/*
    public HandEvaluator(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

 */


    public void check(){
        //legg til at den returner winner
        List<Card> playerHand = getCombinedHandsPlayer();
        List<Card> dealerHand = getCombinedHandsDealer();

        int playerRank = handValue(playerHand);
        int dealerRank = handValue(dealerHand);

        if (playerRank > dealerRank) {
            System.out.println("You win with " + handName(playerRank));
            setWinner("player");
        } else if (dealerRank > playerRank) {
            System.out.println("Dealer wins with " + handName(dealerRank));
            setWinner("dealer");
        } else {
            Card playerHigh = highCard(playerHand);
            Card dealerHigh = highCard(dealerHand);

            if (playerHigh.getCardRank().getValue() > dealerHigh.getCardRank().getValue()) {
                System.out.println("Both have " + handName(playerRank) + ", but you win with higher card: " + playerHigh);
                setWinner("player");
            } else if (dealerHigh.getCardRank().getValue() > playerHigh.getCardRank().getValue()) {
                System.out.println("Both have " + handName(playerRank) + ", but dealer wins with higher card: " + dealerHigh);
                setWinner("dealer");
            } else {
                System.out.println("It's a tie!");
                setWinner("tie");
            }
        }
    }
    private String handName(int rank) {
        switch (rank) {
            case 10: return "Royal Flush";
            case 9: return "Straight Flush";
            case 8: return "Four of a Kind";
            case 7: return "Full House";
            case 6: return "Flush";
            case 5: return "Straight";
            case 4: return "Three of a Kind";
            case 3: return "Two Pair";
            case 2: return "One Pair";
            default: return "High Card";
        }
    }

    public int handValue(List<Card> cards){
        if (royalFlush(cards)) return 10;
        if (straightFlush(cards)) return 9;
        if (fourOfAKind(cards)) return 8;
        if (fullHouse(cards)) return 7;
        if (flush(cards)) return 6;
        if (straight(cards)) return 5;
        if (threeOfAKind(cards)) return 4;
        if (twoPair(cards)) return 3;
        if (onePair(cards)) return 2;
        return 1; // High card
    }

    private List<Card> getCombinedHandsPlayer(){
        List<Card> combine = new ArrayList<>();
        combine.addAll(cardDeck.getPlayerPulledCards());
        combine.addAll(cardDeck.getCardsOnTable());

        return combine;
    }
    private List<Card> getCombinedHandsDealer(){
        List<Card> combine = new ArrayList<>();
        combine.addAll(cardDeck.getDealerPulledCard());
        combine.addAll(cardDeck.getCardsOnTable());

        return combine;
    }
    private boolean checkStraight(List<Card> cards) {
        //todo: sjekk om metoden funker som den skal. gpt sier noe annet.
        Set<Integer> values = new HashSet<>();
        for (Card card : cards) {
            values.add(card.getCardRank().getValue());
        }

        List<Integer> sorted = new ArrayList<>(values);
        Collections.sort(sorted);

        int consecutive = 1;
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) == sorted.get(i - 1) + 1) {
                consecutive++;
                if (consecutive >= 5) return true;
            } else {
                consecutive = 1;
            }
        }
        return false;
    }

    private boolean containsRoyalStraight(List<Card> cards) {
        Set<Integer> values = new HashSet<>();
        for (Card card : cards) {
            values.add(card.getCardRank().getValue());
        }
        // Royal flush is 10, J, Q, K, A
        return values.contains(10) && values.contains(11) &&
                values.contains(12) && values.contains(13) &&
                values.contains(14); // Ace should be 14 in Rank enum
    }



    private Map<Suit, Integer> countSuits(List<Card> cards){
        Map<Suit, Integer> suitCount = new HashMap<>();

        for(Card card : cards){
            Suit suit = card.getCardSuit();
            if(suitCount.containsKey(suit)){
                suitCount.put(suit, suitCount.get(suit) + 1);
            }else{
                suitCount.put(suit, + 1 );
            }
        }
        return suitCount;
    }
    private Map<Rank, Integer> countRanks(List<Card> cards) {
        Map<Rank, Integer> rankCount = new HashMap<>();

        for (Card card : cards) {
            Rank rank = card.getCardRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }
    public Card highCard(List<Card> cards){
        //todo: fiks koden slik at then bruker varaiblen cards i steden for combine.
        List<Card> combine = cards;
        if(combine.isEmpty()){
            return null;
        }else {
            Card highest = combine.get(0);
            for (Card card : combine){
                if (card.getCardRank().getValue() > highest.getCardRank().getValue()){
                    highest = card;
                }
            }
            return highest;
        }

    }
    public boolean onePair(List<Card> cards){
        Map<Rank, Integer> rankCount = countRanks(cards);
        for (int count : rankCount.values()) {
            if (count == 2) return true;
        }
        return false;

    }
    public boolean twoPair(List<Card> cards) {
        Map<Rank, Integer> rankCount = countRanks(cards);
        int pairs = 0;
        for (int count : rankCount.values()) {
            if (count == 2) pairs++;
        }
        return pairs >= 2;
    }
    public boolean threeOfAKind(List<Card> cards) {
        Map<Rank, Integer> rankCount = countRanks(cards);
        for (int count : rankCount.values()) {
            if (count == 3) return true;
        }
        return false;
    }





    public boolean straight(List<Card> cards) {

        Set<Integer> uniqueRanks = new HashSet<>();

        for (Card card : cards) {
            uniqueRanks.add(card.getCardRank().getValue());
        }

        List<Integer> sortedRanks = new ArrayList<>(uniqueRanks);
        Collections.sort(sortedRanks);

        int consecutive = 1;
        for (int i = 1; i < sortedRanks.size(); i++) {
            if (sortedRanks.get(i) == sortedRanks.get(i - 1) + 1) {
                consecutive++;
                if (consecutive >= 5) return true;
            } else {
                consecutive = 1;
            }
        }

        if (uniqueRanks.contains(14)) {
            List<Integer> aceLow = new ArrayList<>(uniqueRanks);
            aceLow.remove(Integer.valueOf(14));
            aceLow.add(1); // Ace counts as 1
            Collections.sort(aceLow);

            consecutive = 1;
            for (int i = 1; i < aceLow.size(); i++) {
                if (aceLow.get(i) == aceLow.get(i - 1) + 1) {
                    consecutive++;
                    if (consecutive >= 5) return true;
                } else {
                    consecutive = 1;
                }
            }
        }

        return false;
    }


    public boolean flush(List<Card> cards) {
        Map<Suit, Integer> suitCount = countSuits(cards);
        for (int count : suitCount.values()) {
            if (count >= 5) return true;
        }
        return false;
    }



    public boolean fullHouse(List<Card> cards) {
        Map<Rank, Integer> rankCount = countRanks(cards);
        int threeCount = 0;
        int twoCount = 0;

        for (int count : rankCount.values()) {
            if (count >= 3) threeCount++;
            else if (count >= 2) twoCount++;
        }

        return threeCount >= 1 && (twoCount >= 1 || threeCount >= 2);
    }


    public boolean fourOfAKind(List<Card> cards) {
        Map<Rank, Integer> rankCount = countRanks(cards);
        for (int count : rankCount.values()) {
            if (count == 4) return true;
        }
        return false;
    }


    public boolean straightFlush(List<Card> cards) {

        Map<Suit, List<Card>> cardsBySuit = new HashMap<>();
        for (Card card : cards) {
            Suit suit = card.getCardSuit();
            cardsBySuit.putIfAbsent(suit, new ArrayList<>());
            cardsBySuit.get(suit).add(card);
        }

        for (List<Card> suitedCards : cardsBySuit.values()) {
            if (suitedCards.size() >= 5) {
                if (checkStraight(suitedCards)) return true;
            }
        }
        return false;
    }


    public boolean royalFlush(List<Card> cards) {
        Map<Suit, List<Card>> cardsBySuit = new HashMap<>();

        for(Card card: cards){
            Suit suit = card.getCardSuit();
            cardsBySuit.putIfAbsent(suit, new ArrayList<>());
            cardsBySuit.get(suit).add(card);
        }
        for (List<Card> suitedCards : cardsBySuit.values()) {
            if (suitedCards.size() >= 5) {
                if (containsRoyalStraight(suitedCards)) {
                    return true;
                }
            }
        }
        return false;
    }


    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}




