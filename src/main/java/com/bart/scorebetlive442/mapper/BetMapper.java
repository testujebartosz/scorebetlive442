package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Bet;
import com.bart.scorebetlive442.model.json.BetCreateJson;
import org.springframework.stereotype.Component;

@Component
public class BetMapper {

    public Bet convertJsonToBet(BetCreateJson betCreateJson) {
        Bet bet = new Bet();
        bet.setTeamA(betCreateJson.teamA());
        bet.setTeamB(betCreateJson.teamB());
        bet.setOdds1(betCreateJson.odds1());
        bet.setOdds2(betCreateJson.odds2());
        bet.setOddsX(betCreateJson.oddsX());
        return bet;
    }
}
