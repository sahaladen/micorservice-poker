package com.example.poker.service;

import com.example.poker.BetManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BetManagerServiceImp implements BetManagerService{
    BetManager betManager = new BetManager(999999,999999);
    @Override
    public long getPlayerBalance() {
        return betManager.getBalancePlayer();
    }
}
