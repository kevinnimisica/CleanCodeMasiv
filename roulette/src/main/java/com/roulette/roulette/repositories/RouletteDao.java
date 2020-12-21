package com.roulette.roulette.repositories;

import java.util.List;

import com.roulette.roulette.models.RouletteDTO;

import org.springframework.stereotype.Repository;

@Repository
public interface RouletteDao {

	boolean saveRoulette(RouletteDTO rouletteDTO);

	List<RouletteDTO> findAll();

	RouletteDTO findById(String idRoulette);

	boolean update(RouletteDTO rouletteDTO);
    
}
