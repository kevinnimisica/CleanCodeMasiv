package com.roulette.roulette.controllers;

import java.util.List;

import com.roulette.roulette.models.BetDTO;
import com.roulette.roulette.models.RouletteDTO;
import com.roulette.roulette.services.RouletteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roulette")
public class RouletteController {
    
    @Autowired
    private RouletteService rouletteService;

    @PostMapping
    public ResponseEntity<String> saveRoulette(@RequestBody RouletteDTO rouletteDTO){
        String id = rouletteService.saveRoulette(rouletteDTO);
        if(id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<RouletteDTO>> fetchAllRoulettes(){
        List<RouletteDTO> roulettes;
        roulettes = rouletteService.fetchAllRoulettes();

        return new ResponseEntity<>(roulettes, HttpStatus.OK);
    }

    @PutMapping("/open/{id}")
    public ResponseEntity<Boolean> makeBet(@PathVariable("id") String idRoulette, @RequestBody BetDTO betDTO, @RequestHeader(value = "user", required = true) String userId) {
        betDTO.setUsername(userId);
        if(rouletteService.makeBet(idRoulette, betDTO)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("close/{id}")
    public ResponseEntity< List<BetDTO>> closeBets(@PathVariable("id") String idRoulette){
        List<BetDTO> winners = rouletteService.closeBets(idRoulette);
        if(winners != null){
            return new ResponseEntity<>(winners, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
