package com.bart.scorebetlive442.mapper;

import com.bart.scorebetlive442.entity.LeagueEntity;
import com.bart.scorebetlive442.model.League;
import com.bart.scorebetlive442.model.json.LeagueJson;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {TeamMapper.class})
public interface LeagueMapper {

    @Mapping(target = "teams", ignore = true)
    League convertJsonToLeague(LeagueJson leagueJson);

    @Mapping(target = "teamCount", expression = "java(league.getTeams() != null ? league.getTeams().size() : null)")
    @Mapping(target = "teamDetails", source = "teams")
    LeagueJson convertLeagueToJson(League league);

    List<LeagueJson> convertLeagueToJson(List<League> leagues);

    League toLeagueModel(LeagueEntity leagueEntity);

    List<League> toLeagueModel(List<LeagueEntity> leagueEntities);

    LeagueEntity toLeagueEntity(League league);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateLeagueFromDto(League source, @MappingTarget LeagueEntity target);

}
