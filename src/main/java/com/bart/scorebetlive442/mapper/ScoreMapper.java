package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Score;
import com.bart.scorebetlive442.model.json.ScoreCreateJson;
import com.bart.scorebetlive442.model.json.ScoreResponseJson;
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

    public ScoreResponseJson convertScoreToJson(Score score) {
        return new ScoreResponseJson(
                score.getTeamA(),
                score.getTeamB(),
                score.getScoreA(),
                score.getScoreB()
        );
    }
}
