package com.bart.scorebetlive442.model.json;

import com.fasterxml.jackson.annotation.JsonView;

import static com.bart.scorebetlive442.model.json.TeamJson.View.*;

public record TeamJson(

        @JsonView({CreateResponse.class, GetResponse.class})
        Long id,
        @JsonView({CreateRequest.class, GetResponse.class})
        String name,
        @JsonView({CreateRequest.class, GetResponse.class})
        String city,
        @JsonView({CreateRequest.class, GetResponse.class})
        String country,
        @JsonView({CreateRequest.class, GetResponse.class})
        String foundedYear
) {

    public record View() {
        public record CreateRequest() {}
        public record CreateResponse() {}
        public record GetResponse() {}
    }
}
