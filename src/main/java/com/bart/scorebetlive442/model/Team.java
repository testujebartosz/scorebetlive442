package com.bart.scorebetlive442.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Null(groups = {Team.Group.Create.class, Team.Group.Update.class})
    private Long id;
    @NotEmpty
    @Length(min = 5, max = 50)
    private String name;
    private String city;
    private String country;
    private String foundedYear;

    public static class Group {
        public interface Create {}
        public interface Update {}
    }
}
