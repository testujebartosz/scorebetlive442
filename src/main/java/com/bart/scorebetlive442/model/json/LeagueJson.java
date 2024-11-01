package com.bart.scorebetlive442.model.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

import static com.bart.scorebetlive442.model.json.LeagueJson.View.*;

public record LeagueJson(
        @JsonView({CreateResponse.class, GetResponse.class})
        Long id,
        @JsonView({CreateRequest.class, GetResponse.class})
        String name,
        @JsonView({CreateRequest.class, GetResponse.class})
        String country,
        @JsonView({GetResponse.class})
        Set<Long> teamIds

) {

    public record View() {
        public record CreateRequest() {}
        public record CreateResponse() {}
        public static class GetResponse extends TeamJson.View.GetResponse {}
    }
}
