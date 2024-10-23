package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamJson;
import org.mapstruct.*;

@Mapper
public interface TeamMapper {

    Team convertJsonToTeam(TeamJson teamJson);

    TeamJson convertTeamToJson(Team team);

    Team toTeamModel(TeamEntity teamEntity);

    TeamEntity toTeamEntity(Team team);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateTeamFromDto(Team source, @MappingTarget TeamEntity target);
}

