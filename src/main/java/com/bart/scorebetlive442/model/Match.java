package com.bart.scorebetlive442.model;

import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Null(groups = {Match.Group.Create.class, Match.Group.Update.class})
    private Long id;
    private Team teamHome;
    private Team teamAway;
    private LocalDateTime matchTime;
    @Length(min = 3, max = 40)
    private String stadiumName;
    private String city;
    private Integer scoreHome;
    private Integer scoreAway;

    public static class Group {
        public interface Create {}
        public interface Update {}
    }
}
