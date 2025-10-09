package com.example.poker;

public class BetManager {
    //todo: legg til slik at balance er 2 styk. en for dealer og player
    private long totalBetPlacedPlayer;
    private long totalBetPlacedDealer;
    private long balancePlayer;
    private long balanceDealer = 999999999;
    private long moneyLostPlayer;
    private long moneyLostDealer;

    public BetManager(int totalBetPlacedPlayer, int totalBetPlacedDealer) {
        this.totalBetPlacedPlayer = totalBetPlacedPlayer;
        this.totalBetPlacedDealer = totalBetPlacedDealer;

    }

    public long totalPot(long totalBetPlacedDealer, long totalBetPlacedPlayer){
        long total = totalBetPlacedDealer + totalBetPlacedPlayer;
        return total;
    }
    public void winner(){

    }


    public long getTotalBetPlacedPlayer() {
        return totalBetPlacedPlayer;
    }

    public void setTotalBetPlacedPlayer(long totalBetPlacedPlayer) {
        this.totalBetPlacedPlayer = totalBetPlacedPlayer;
    }

    public long getTotalBetPlacedDealer() {
        return totalBetPlacedDealer;
    }

    public void setTotalBetPlacedDealer(long totalBetPlacedDealer) {
        this.totalBetPlacedDealer = totalBetPlacedDealer;
    }

    public long getBalancePlayer() {
        return balancePlayer;
    }

    public void setBalancePlayer(long balancePlayer) {
        this.balancePlayer = balancePlayer;
    }

    public long getBalanceDealer() {
        return balanceDealer;
    }

    public void setBalanceDealer(long balanceDealer) {
        this.balanceDealer = balanceDealer;
    }

    public long getMoneyLostPlayer() {
        return moneyLostPlayer;
    }

    public void setMoneyLostPlayer(long moneyLostPlayer) {
        this.moneyLostPlayer = moneyLostPlayer;
    }

    public long getMoneyLostDealer() {
        return moneyLostDealer;
    }

    public void setMoneyLostDealer(long moneyLostDealer) {
        this.moneyLostDealer = moneyLostDealer;
    }
}
