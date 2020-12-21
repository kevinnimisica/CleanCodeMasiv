package com.roulette.roulette.repositories;

import java.util.List;

import com.roulette.roulette.models.RouletteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RouletteDaoIml implements RouletteDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "Roulette";

    @Override
    public boolean saveRoulette(RouletteDTO rouletteDTO) {
        try {
            redisTemplate.opsForHash().put(KEY, rouletteDTO.getId().toString(), rouletteDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<RouletteDTO> findAll() {
        List<RouletteDTO> rouletteDTOs;
        rouletteDTOs = redisTemplate.opsForHash().values(KEY);
        return rouletteDTOs;
    }

    @Override
    public RouletteDTO findById(String idRoulette) {
        RouletteDTO roulette;
        roulette = (RouletteDTO) redisTemplate.opsForHash().get(KEY, idRoulette.toString());
        return roulette;
    }

    @Override
    public boolean update(RouletteDTO rouletteDTO) {
        try {
            redisTemplate.opsForHash().put(KEY, rouletteDTO.getId().toString(), rouletteDTO);
            return true;    
        } catch (Exception e) {
            return false;
        }
        
        
    }

    
    
}
