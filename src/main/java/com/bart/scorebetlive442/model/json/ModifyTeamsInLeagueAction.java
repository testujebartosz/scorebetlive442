package com.bart.scorebetlive442.model.json;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ModifyTeamsInLeagueAction {

    ADD("Add", "aDD"),
    REMOVE("Remove", "Delete");
    private final List<String> acceptedValues;

    ModifyTeamsInLeagueAction(String... acceptedValues) {
        this.acceptedValues = Arrays.stream(acceptedValues).map(String::toUpperCase).toList();
    }

    public static ModifyTeamsInLeagueAction fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (ModifyTeamsInLeagueAction action : ModifyTeamsInLeagueAction.values()) {
            if (action.acceptedValues.contains(value.toUpperCase())) {
                return action;
            }
        }
        return null;
    }
}
