package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.TeamEntity;
import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamCreateJson;
import com.bart.scorebetlive442.model.json.TeamResponseJson;
import org.mapstruct.Mapper;

@Mapper
public interface TeamMapper {

    Team convertJsonToTeam(TeamCreateJson teamCreateJson);

    TeamResponseJson convertTeamToJson(Team team);

    Team toModel(TeamEntity teamEntity);

    TeamEntity toEntity(Team team);
}
