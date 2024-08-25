package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Score;
import com.bart.scorebetlive442.model.json.ScoreCreateJson;
import com.bart.scorebetlive442.model.json.ScoreResponseJson;
import com.bart.scorebetlive442.entity.ScoreEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ScoreMapper {

    Score convertJsonToScore(ScoreCreateJson scoreCreateJson);

    ScoreResponseJson convertScoreToJson(Score score);

    Score toScoreModel(ScoreEntity scoreEntity);

    ScoreEntity toScoreEntity(Score score);

}
