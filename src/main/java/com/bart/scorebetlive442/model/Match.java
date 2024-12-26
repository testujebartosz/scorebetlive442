package com.bart.scorebetlive442.model;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Null(groups = {Match.Group.Create.class, Match.Group.Update.class})
    private Long id;
    private Team teamHome;
    private Team teamAway;
    private Date dateTime;
    @Length(min = 3, max = 40)
    private String stadiumName;
    private String city;

    public static class Group {
        public interface Create {}
        public interface Update {}
    }
}
