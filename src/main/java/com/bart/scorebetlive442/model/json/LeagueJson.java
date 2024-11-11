package com.bart.scorebetlive442.model.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

import static com.bart.scorebetlive442.model.json.LeagueJson.View.*;

public record LeagueJson(
        @JsonView({CreateRequest.class, GetResponse.class, GetResponseShort.class})
        Long id,
        @JsonView({CreateRequest.class, GetResponse.class, GetResponseShort.class})
        String name,
        @JsonView({CreateRequest.class, GetResponse.class, GetResponseShort.class})
        String country,
        @JsonView({GetResponse.class})
        Set<TeamJson> teamDetails

) {

    public record View() {
        public record CreateRequest() {}
        public record CreateResponse() {}
        public static class GetResponse extends TeamJson.View.GetResponse {}
        public static class GetResponseShort {}
    }
}
