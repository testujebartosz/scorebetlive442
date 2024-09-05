package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.MatchEntity;
import com.bart.scorebetlive442.model.Match;
import com.bart.scorebetlive442.model.json.MatchCreateJson;
import com.bart.scorebetlive442.model.json.MatchResponseJson;
import org.mapstruct.*;

@Mapper
public interface MatchMapper {

    Match convertJsonToMatch(MatchCreateJson matchCreateJson);

    MatchResponseJson convertMatchToJson(Match match);

    Match toMatchModel(MatchEntity matchEntity);

    MatchEntity toMatchEntity(Match match);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateMatchFromDto(Match source, @MappingTarget MatchEntity target);

}
