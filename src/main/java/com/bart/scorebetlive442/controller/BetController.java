package com.bart.scorebetlive442.controller;

import com.bart.scorebetlive442.model.Bet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/bets")
public class BetController {

    @GetMapping(value = "/italy/seriea")
    public List<Map<String,Object>> getBets(){
        List<Bet> bets = Arrays.asList(
                new Bet("FC Juventus", "AC Milan", 2.4, 5.0, 3.0),
                new Bet("AS Roma", "SS Lazio", 3.0, 2.0, 3.2)
        );

        return bets.stream()
                .map(bet -> {
                    Map<String, Object> formattedBet = new LinkedHashMap<>();
                    formattedBet.put("teamA", bet.getTeamA());
                    formattedBet.put("teamB", bet.getTeamB());
                    formattedBet.put("1", bet.getOdds1());
                    formattedBet.put("x", bet.getOddsX());
                    formattedBet.put("2", bet.getOdds2());
                    return formattedBet;
                }).toList();
    }
}
