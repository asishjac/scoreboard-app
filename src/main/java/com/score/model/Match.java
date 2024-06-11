package com.score.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private Team homeTeam;
    private Team awayTeam;

    public Match(String homeTeamName, String awayTeamName) {
        this.homeTeam = new Team(homeTeamName);
        this.awayTeam = new Team(awayTeamName);
    }

    public int getTotalGoals() {
        return homeTeam.getGoals() + awayTeam.getGoals();
    }
}
