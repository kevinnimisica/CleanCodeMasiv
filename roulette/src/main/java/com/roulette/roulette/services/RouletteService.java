package com.roulette.roulette.services;

import java.util.List;

import com.roulette.roulette.models.BetDTO;
import com.roulette.roulette.models.RouletteDTO;

public interface RouletteService {

	String saveRoulette(RouletteDTO rouletteDTO);

	List<RouletteDTO> fetchAllRoulettes();

	boolean makeBet(String idRoulette, BetDTO betDTO);

	List<BetDTO> closeBets(String idRoulette);
    
}
