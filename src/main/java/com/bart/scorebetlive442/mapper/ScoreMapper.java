package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Score;
import com.bart.scorebetlive442.model.json.ScoreCreateJson;
import org.springframework.stereotype.Component;

@Component
public class ScoreMapper {

    public Score convertJsonToScore(ScoreCreateJson scoreCreateJson) {
        Score score = new Score();
        score.setTeamA(scoreCreateJson.teamA());
        score.setTeamB(scoreCreateJson.teamB());
        score.setScoreA(scoreCreateJson.scoreA());
        score.setScoreB(scoreCreateJson.scoreB());
        return score;
    }
}
