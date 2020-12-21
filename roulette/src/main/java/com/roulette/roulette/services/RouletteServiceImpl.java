package com.roulette.roulette.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.roulette.roulette.models.BetDTO;
import com.roulette.roulette.models.RouletteDTO;
import com.roulette.roulette.repositories.RouletteDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouletteServiceImpl implements RouletteService {

    @Autowired
    private RouletteDao rouletteDao;

    @Override
    public String saveRoulette(RouletteDTO rouletteDTO) {
        if (rouletteDao.saveRoulette(rouletteDTO)) {
            return rouletteDTO.getId();
        }
        return null;
    }

    @Override
    public List<RouletteDTO> fetchAllRoulettes() {
        return rouletteDao.findAll();
    }

    @Override
    public boolean makeBet(String idRoulette, BetDTO betDTO) {
        RouletteDTO rouletteDTO = rouletteDao.findById(idRoulette);
        List<BetDTO> bets = rouletteDTO.getBets();
        if(bets == null){
            bets = new ArrayList<>();
        }
        bets.add(betDTO);
        rouletteDTO.setBets(bets);
        rouletteDTO.setState(true);
        if(rouletteDao.update(rouletteDTO)){
            return true;
        }

        return false;
    }

    @Override
    public List<BetDTO> closeBets(String idRoulette) {
        RouletteDTO rouletteDTO = rouletteDao.findById(idRoulette);
        Random rand = new Random(); 
        int winNumber = rand.nextInt(36);
        if(rouletteDTO != null && rouletteDTO.isState()){
            rouletteDTO.setState(false);
            rouletteDao.update(rouletteDTO);
            
            return getWinnners(winNumber, rouletteDTO);
        }
        
        return null;
    }

    private List<BetDTO> getWinnners(int winNumber, RouletteDTO rouletteDTO){
        List<BetDTO> winners = new ArrayList<>();
        String color = winNumber % 2 == 0? "red": "back";
        for(BetDTO bet : rouletteDTO.getBets()){
            if(bet.getNumber() == winNumber){
                bet.setMoneyBeated(bet.getMoneyBeated()*5);
                winners.add(bet);
            }
            else if(bet.getColor().toLowerCase().equals(color)){
                bet.setMoneyBeated(bet.getMoneyBeated()*1.8);
                winners.add(bet);
            }
        }

        return winners;

    }
    
}
