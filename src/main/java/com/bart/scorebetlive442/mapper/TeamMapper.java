package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.model.Team;
import com.bart.scorebetlive442.model.json.TeamCreateJson;
import com.bart.scorebetlive442.model.json.TeamResponseJson;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team convertJsonToTeam(TeamCreateJson teamCreateJson) {
        Team team = new Team();
        team.setName(teamCreateJson.name());
        team.setCity(teamCreateJson.city());
        team.setCountry(teamCreateJson.country());
        team.setFoundedYear(teamCreateJson.foundedYear());
        return team;
    }

    public TeamResponseJson convertTeamToJson(Team team) {
        return new TeamResponseJson(
                team.getName(),
                team.getCity(),
                team.getCountry(),
                team.getId(),
                team.getFoundedYear()
        );
    }
}
