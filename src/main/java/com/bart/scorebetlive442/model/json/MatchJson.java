package com.bart.scorebetlive442.model.json;

import com.bart.scorebetlive442.model.Team;
import com.fasterxml.jackson.annotation.JsonView;

import static com.bart.scorebetlive442.model.json.MatchJson.View.*;

import java.util.Date;

public record MatchJson(

        @JsonView({CreateResponse.class, GetResponse.class})
        Long id,
        @JsonView({CreateRequest.class, GetResponse.class})
        Team teamHome,
        @JsonView({CreateRequest.class, GetResponse.class})
        Team teamAway,
        @JsonView({CreateRequest.class, GetResponse.class})
        Date dateTime,
        @JsonView({CreateRequest.class, GetResponse.class})
        String stadiumName,
        @JsonView({CreateRequest.class, GetResponse.class})
        String city,
        @JsonView({CreateRequest.class, GetResponse.class})
        Integer scoreHome,
        @JsonView({CreateRequest.class, GetResponse.class})
        Integer scoreAway
) {

    public record View() {
        public record CreateRequest() {}
        public record CreateResponse() {}
        public record GetResponse() {}
    }
}
