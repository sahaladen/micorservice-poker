package com.example.poker;

import java.util.Scanner;

public class Program {
    //todo: se hvor jeg vil håndtere kortene som er på bordet.
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
        int balance = scanner.nextInt();
        betManager.setBalancePlayer(balance);
        boolean folded = false;
        int dealerBet = 0;



        //todo: da du lager databasen. husk at penga skal være i dollar
        System.out.println("place a bet: ");
        int bet = scanner.nextInt();
        betManager.setTotalBetPlacedPlayer(bet);
        betManager.setBalancePlayer(balance - bet);

        //todo: legg til en måte å få betta på.


        for(int i = 0; i < 3; i++){
            cardDeck.dealerDrawOnTable();
        }


        for(int i = 0; i < 2; i++){
            cardDeck.showPlayerHeldCard();
            dealerBet += 50;
            System.out.println("dealer placed 50$");
            System.out.println("raise, call or fold");
            String choice = scanner.next().toLowerCase();
            switch(choice){
                case "raise":
                    //todo: endre på navnet til input2
                    int input2 = scanner.nextInt();
                    betManager.setTotalBetPlacedPlayer(input2);
                    //matcher player hver gang
                    dealerBet += input2;
                    break;
                case "call":
                    //todo: finn en måte å se hvor mye penger som er på bordet eller
                    //todo: gjør slik at spillern spiller mot dealer.
                    System.out.println("matched the bet");
                    break;
                case "fold":
                    System.out.println("you folded");
                    betManager.setMoneyLostPlayer(betManager.getTotalBetPlacedPlayer());
                    betManager.setTotalBetPlacedPlayer(0);
                    folded = true;
                    break;
                default:
                    System.out.println("input a valid value");
            }
            if (folded == true){
                break;
            }
            cardDeck.dealerDrawOnTable();
            cardDeck.showCardsOnTable();
        }
        betManager.setTotalBetPlacedDealer(dealerBet);
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
