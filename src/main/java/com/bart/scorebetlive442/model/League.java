package com.bart.scorebetlive442.model;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Null(groups = Group.Create.class)
    private Long id;
    @Length(min = 5, max = 50)
    private String name;
    private String country;
    //private Set<Team> teams;

    public static class Group {
        public interface Create {}
    }
}
