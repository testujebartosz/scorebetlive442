package com.bart.scorebetlive442.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class League {

    @Null(groups = {Group.Create.class, Group.Update.class})
    private Long id;
    @NotEmpty
    @Length(min = 5, max = 50)
    private String name;
    @NotEmpty
    private String country;
    private Set<Team> teams;

    public static class Group {
        public interface Create {}
        public interface Update {}
    }
}
