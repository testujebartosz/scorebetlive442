package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.json.LeagueCreateJson;
import com.bart.scorebetlive442.model.json.LeagueResponseJson;
import org.mapstruct.Mapper;

@Mapper
public interface  LeagueMapper {

    League convertJsonToLeague(LeagueCreateJson leagueCreateJson);

    LeagueResponseJson convertLeagueToJson(League league);

    League toLeagueEntity(LeagueEntity leagueEntity);

    LeagueEntity toLeagueEntity(League league);
}
