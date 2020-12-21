package com.roulette.roulette.models;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BetDTO implements Serializable{
    @Min(0)
    @Max(36)
    @Nullable
    private int number;

    private double moneyBeated;
    
    @Pattern(regexp = "red|black?", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String color;

    @Pattern(regexp = "[a-z|A-Z]^[0-9]^")
    private String username;

}
