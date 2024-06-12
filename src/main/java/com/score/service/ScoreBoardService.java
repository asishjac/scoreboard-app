package com.score.service;


import com.score.exception.ScoreBoardException;
import com.score.model.Match;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {
    private final List<Match> matches = new ArrayList<>();

    public Match startGame(String homeTeam, String awayTeam) throws ScoreBoardException {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new ScoreBoardException("Team name cannot be empty or null");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    public Match updateGoals(Match match, int homeTeamGoals, int awayTeamGoals) throws ScoreBoardException {
        if (match == null) {
            throw new ScoreBoardException("Invalid match provided");
        }
        match.getHomeTeam().setGoals(homeTeamGoals);
        match.getAwayTeam().setGoals(awayTeamGoals);
        return match;
    }

    public List<Match> getSummary() {
        matches.sort((m1, m2) -> {
            int scoreComparison = Integer.compare(m2.getTotalGoals(), m1.getTotalGoals());
            if (scoreComparison == 0) {
                return Long.compare(matches.indexOf(m2), matches.indexOf(m1));
            }
            return scoreComparison;
        });
        return matches;
    }

    public void finishGame(Match match) throws ScoreBoardException {
        if (match == null) {
            throw new ScoreBoardException("Invalid match provided");
        }
        matches.remove(match);
    }
}
