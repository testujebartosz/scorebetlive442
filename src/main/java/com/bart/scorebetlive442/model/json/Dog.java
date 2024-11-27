package com.bart.scorebetlive442.model.json;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter
@EqualsAndHashCode
@ToString
public class Dog {

    private String name;

//    public Dog() {
//        name = "Reksio";
//    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
