package com.bart.scorebetlive442.model.json;

import java.time.LocalDateTime;

public record MatchJson(

//        @JsonView({CreateResponse.class, GetResponse.class})
        Long id,
//        @JsonView({CreateRequest.class, GetResponse.class})
        TeamJson teamHome,
//        @JsonView({CreateRequest.class, GetResponse.class})
        TeamJson teamAway,
//        @JsonView({CreateRequest.class, GetResponse.class})
        LocalDateTime matchTime,
//        @JsonView({CreateRequest.class, GetResponse.class})
        String stadiumName,
//        @JsonView({CreateRequest.class, GetResponse.class})
        String city,
//        @JsonView({CreateRequest.class, GetResponse.class})
        Integer scoreHome,
//        @JsonView({CreateRequest.class, GetResponse.class})
        Integer scoreAway
) {

    public record View() {
        public record CreateRequest() {}
        public record CreateResponse() {}
        public record GetResponse() {}
    }
}
