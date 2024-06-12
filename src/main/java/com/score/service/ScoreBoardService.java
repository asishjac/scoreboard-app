package com.score.service;


import com.score.model.Match;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {
    private final List<Match> matches = new ArrayList<>();

    public Match startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }
}
