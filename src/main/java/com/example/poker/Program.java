package com.example.poker;

import java.util.Scanner;

public class Program {

    private CardDeck cardDeck = new CardDeck();
    private BetManager betManager = new BetManager(0,0);
    private HandEvaluator handEvaluator = new HandEvaluator(cardDeck);




    public void Run(){
        cardDeck.combine();
        cardDeck.shuffleCards();
        dealOutCards();
        meny();
    }

    private void meny() {
        //todo: legg til slik at hvis de ikke vil bette så kan de gå ut av spille.
        System.out.println("welcome to poker. how much money do you want to start betting with? : ");
        Scanner scanner = new Scanner(System.in);
        long balance = scanner.nextInt();
        betManager.setBalancePlayer(balance);
        boolean folded = false;
        long dealerBet = 0;



        //todo: da du lager databasen. husk at penga skal være i dollar
        System.out.println("place a bet: ");
        long bet = scanner.nextInt();
        betManager.setTotalBetPlacedPlayer(bet);
        betManager.setBalancePlayer(balance - bet);




        for(int i = 0; i < 3; i++){
            cardDeck.drawCardsOnTable();
        }

        cardDeck.showPlayerHeldCard();

        for(int i = 0; i < 2; i++){
            dealerBet += 50;
            betManager.setTotalBetPlacedDealer(dealerBet);

            System.out.println("dealer placed 50$");
            System.out.println("raise, call or fold\n");
            String choice = scanner.next().toLowerCase();
            switch(choice){
                case "raise":
                    //todo: endre på navnet til input2
                    System.out.println("\nhow much?: ");
                    long input2 = scanner.nextInt();
                    betManager.setTotalBetPlacedPlayer(input2);
                    System.out.println("\ndealer matched the bet\n");
                    betManager.setTotalBetPlacedDealer(dealerBet += input2);
                    break;
                case "call":

                    System.out.println("matched the bet");
                    betManager.setTotalBetPlacedPlayer(dealerBet);
                    break;
                case "fold":
                    System.out.println("you folded");
                    betManager.setMoneyLostPlayer
                            (betManager.getBalancePlayer() - betManager.getTotalBetPlacedPlayer());
                    betManager.totalPot
                            (betManager.getTotalBetPlacedDealer(),betManager.getTotalBetPlacedPlayer());
                    betManager.setBalanceDealer(betManager.totalPot
                            (betManager.getTotalBetPlacedDealer(),betManager.getTotalBetPlacedPlayer()));
                    //todo: legg til senere slik at hvor mye penger en spiller har er i et annet sted.
                    betManager.setBalancePlayer(0);
                    betManager.setTotalBetPlacedPlayer(0);
                    betManager.setTotalBetPlacedDealer(0);
                    folded = true;
                    break;
                default:
                    System.out.println("input a valid value");
            }
            if (folded == true){
                break;
            }
            cardDeck.drawCardsOnTable();
            System.out.println("on the table: ");
            cardDeck.showCardsOnTable();
        }
        System.out.println("\nall cards on the table: ");
        cardDeck.showCardsOnTable();
        System.out.println();
        System.out.println("your hand: ");
        cardDeck.showPlayerHeldCard();
        System.out.println("\n total amount in the pot in dollars: ");
        System.out.println
                (betManager.totalPot
                (betManager.getTotalBetPlacedDealer(),betManager.getTotalBetPlacedPlayer())
        );
        handEvaluator.check();



    }

    private void dealOutCards(){
        /*
        for loopen funker som den skal. har ingen ting med index å gjøre.
        det er basert på selve kort stokken.
         */
        for(int i = 0; i < 2; i++){
            cardDeck.playerDraw();
        }
        for(int i = 0; i < 2; i++){
            cardDeck.dealerDraw();
        }

    }
}
