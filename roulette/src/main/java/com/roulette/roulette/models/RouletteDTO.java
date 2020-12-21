package com.roulette.roulette.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouletteDTO implements Serializable{
    private String id;

    private double maxBet;

    private boolean state;

    private List<BetDTO> bets;
}
