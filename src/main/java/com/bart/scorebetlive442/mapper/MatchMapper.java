package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.model.json.MatchCreateJson;
import com.bart.scorebetlive442.model.json.MatchResponseJson;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    public Match convertJsonToMatch(MatchCreateJson matchCreateJson) {
        Match match = new Match();
        match.setId(matchCreateJson.id());
        match.setTeamHome(matchCreateJson.teamHome());
        match.setTeamAway(matchCreateJson.teamAway());
        match.setDateTime(matchCreateJson.dateTime());
        match.setStadiumName(matchCreateJson.stadiumName());
        match.setCity(matchCreateJson.city());
        match.setScoreHome(matchCreateJson.scoreHome());
        match.setScoreAway(matchCreateJson.scoreAway());
        return match;
    }

    public MatchResponseJson convertMatchToJson(Match match) {
        return new MatchResponseJson(
                match.getId(),
                match.getTeamHome(),
                match.getTeamAway(),
                match.getDateTime(),
                match.getStadiumName(),
                match.getCity(),
                match.getScoreHome(),
                match.getScoreAway()
        );
    }
}
