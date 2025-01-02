package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.MatchEntity;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.model.json.MatchJson;
import org.mapstruct.*;

@Mapper
public interface MatchMapper {

    Match convertJsonToMatch(MatchJson matchJson);

    MatchJson convertMatchToJson(Match match);

    Match toMatchModel(MatchEntity matchEntity);

//    @Mapping(target = "teamHome", expression = "java(matchEntity.getTeamHome())")
//    @Mapping(target = "teamAway", expression = "java(matchEntity.getTeamAway())")
//    Match toMatchModelEnriched(MatchEntity matchEntity, TeamEntity homeTeam, TeamEntity awayTeam);

    MatchEntity toMatchEntity(Match match);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateMatchFromDto(Match source, @MappingTarget MatchEntity target);

}
