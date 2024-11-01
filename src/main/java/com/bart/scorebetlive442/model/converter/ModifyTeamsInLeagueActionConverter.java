package com.bart.scorebetlive442.model.converter;

import com.bart.scorebetlive442.model.json.ModifyTeamsInLeagueAction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ModifyTeamsInLeagueActionConverter implements Converter<String, ModifyTeamsInLeagueAction> {

    @Override
    public ModifyTeamsInLeagueAction convert(@NonNull String source) {
        return ModifyTeamsInLeagueAction.fromValue(source);
    }
}
