package com.score.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private String name;
    private int goals;

    public Team(String name) {
        this.name = name;
        this.goals = 0;
    }

}
