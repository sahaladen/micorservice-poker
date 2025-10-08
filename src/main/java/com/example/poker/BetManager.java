package com.example.poker;

public class BetManager {
    //todo: legg til slik at balance er 2 styk. en for dealer og player
    private int totalBetPlacedPlayer;
    private int totalBetPlacedDealer;
    private int balancePlayer;
    private int balanceDealer = 999999999;
    private int moneyLostPlayer;
    private int moneyLostDealer;

    public BetManager(int totalBetPlacedPlayer, int totalBetPlacedDealer) {
        this.totalBetPlacedPlayer = totalBetPlacedPlayer;
        this.totalBetPlacedDealer = totalBetPlacedDealer;

    }

    private int totalPot(int totalBetPlacedDealer, int totalBetPlacedPlayer){
        int total = totalBetPlacedDealer + totalBetPlacedPlayer;
        return total;
    }
    public void winner(){

    }

    public int getTotalBetPlacedPlayer() {
        return totalBetPlacedPlayer;
    }

    public void setTotalBetPlacedPlayer(int totalBetPlacedPlayer) {
        this.totalBetPlacedPlayer = totalBetPlacedPlayer;
    }

    public int getBalancePlayer() {
        return balancePlayer;
    }

    public void setBalancePlayer(int balancePlayer) {
        this.balancePlayer = balancePlayer;
    }

    public int getMoneyLostPlayer() {
        return moneyLostPlayer;
    }

    public void setMoneyLostPlayer(int moneyLostPlayer) {
        this.moneyLostPlayer = moneyLostPlayer;
    }

    public int getTotalBetPlacedDealer() {
        return totalBetPlacedDealer;
    }

    public void setTotalBetPlacedDealer(int totalBetPlacedDealer) {
        this.totalBetPlacedDealer = totalBetPlacedDealer;
    }

    public int getMoneyLostDealer() {
        return moneyLostDealer;
    }

    public void setMoneyLostDealer(int moneyLostDealer) {
        this.moneyLostDealer = moneyLostDealer;
    }

    public int getBalanceDealer() {
        return balanceDealer;
    }

    public void setBalanceDealer(int balanceDealer) {
        this.balanceDealer = balanceDealer;
    }
}
