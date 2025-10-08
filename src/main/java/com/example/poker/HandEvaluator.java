package com.example.poker;

import java.util.*;

public class HandEvaluator {

    private  final CardDeck cardDeck;

    public HandEvaluator(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void check(){
        if (royalFlush()) {
            System.out.println("You have a Royal Flush");
        } else if (straightFlush()) {
            System.out.println("You have a Straight Flush");
        } else if (fourOfAKind()) {
            System.out.println("You have Four of a Kind");
        } else if (fullHouse()) {
            System.out.println("You have a Full House");
        } else if (flush()) {
            System.out.println("You have a Flush");
        } else if (straight()) {
            System.out.println("You have a Straight");
        } else if (threeOfAKind()) {
            System.out.println("You have Three of a Kind");
        } else if (twoPair()) {
            System.out.println("You have Two Pair");
        } else if (onePair()) {
            System.out.println("You have One Pair");
        } else {
            System.out.println("High Card: " + highCard());
        }


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



    private Map<Suit, Integer> countSuits(){
        List<Card> combine = getCombinedHandsPlayer();
        Map<Suit, Integer> suitCount = new HashMap<>();

        for(Card card : combine){
            Suit suit = card.getCardSuit();
            if(suitCount.containsKey(suit)){
                suitCount.put(suit, suitCount.get(suit) + 1);
            }else{
                suitCount.put(suit, + 1 );
            }
        }
        return suitCount;
    }
    private Map<Rank, Integer> countRanks() {
        List<Card> combine = getCombinedHandsPlayer();
        Map<Rank, Integer> rankCount = new HashMap<>();

        for (Card card : combine) {
            Rank rank = card.getCardRank();
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }
    public Card highCard(){
        List<Card> combine = getCombinedHandsPlayer();
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
    public boolean onePair(){
        Map<Rank, Integer> rankCount = countRanks();
        for (int count : rankCount.values()) {
            if (count == 2) return true;
        }
        return false;

    }
    public boolean twoPair() {
        Map<Rank, Integer> rankCount = countRanks();
        int pairs = 0;
        for (int count : rankCount.values()) {
            if (count == 2) pairs++;
        }
        return pairs >= 2;
    }
    public boolean threeOfAKind() {
        Map<Rank, Integer> rankCount = countRanks();
        for (int count : rankCount.values()) {
            if (count == 3) return true;
        }
        return false;
    }





    public boolean straight() {
        List<Card> combine = getCombinedHandsPlayer();
        Set<Integer> uniqueRanks = new HashSet<>();

        for (Card card : combine) {
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


    public boolean flush() {
        Map<Suit, Integer> suitCount = countSuits();
        for (int count : suitCount.values()) {
            if (count >= 5) return true;
        }
        return false;
    }



    public boolean fullHouse() {
        Map<Rank, Integer> rankCount = countRanks();
        int threeCount = 0;
        int twoCount = 0;

        for (int count : rankCount.values()) {
            if (count >= 3) threeCount++;
            else if (count >= 2) twoCount++;
        }

        return threeCount >= 1 && (twoCount >= 1 || threeCount >= 2);
    }


    public boolean fourOfAKind() {
        Map<Rank, Integer> rankCount = countRanks();
        for (int count : rankCount.values()) {
            if (count == 4) return true;
        }
        return false;
    }


    public boolean straightFlush() {
        List<Card> combined = getCombinedHandsPlayer();

        Map<Suit, List<Card>> cardsBySuit = new HashMap<>();
        for (Card card : combined) {
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


    public boolean royalFlush() {
        List<Card> combine = getCombinedHandsPlayer();
        Map<Suit, List<Card>> cardsBySuit = new HashMap<>();

        for(Card card: combine){
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


}




