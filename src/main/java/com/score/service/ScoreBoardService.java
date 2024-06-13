package com.score.service;


import com.score.exception.ScoreBoardException;
import com.score.model.Match;
import com.score.utils.ListToMapConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoardService {
    private final List<Match> matches = new ArrayList<>();
    private final ListToMapConverter listToMapConverter = new ListToMapConverter();

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

    public List<Match> getSummary() throws ScoreBoardException {
        // Convert List<Match> to Map<Integer, Match>
        Map<Integer, Match> matchMap = listToMapConverter.convertListToMapWithIndex(matches);
        // Sort Map<Integer, Match> and return List<Match>
        return sortMapAndReturnSummaryList(matchMap);
    }

    public void finishGame(Match match) throws ScoreBoardException {
        if (match == null) {
            throw new ScoreBoardException("Invalid match provided");
        }
        matches.remove(match);
    }

    public List<Match> sortMapAndReturnSummaryList(Map<Integer, Match> summaryMap) throws ScoreBoardException {
        if (summaryMap == null) {
            throw new ScoreBoardException("Map cannot be null");
        }
        // Sort the Map by total goals and insertion order
        return summaryMap.entrySet().stream().sorted((entry1, entry2) -> {
            int scoreComparison = Integer.compare(entry2.getValue().getTotalGoals(), entry1.getValue().getTotalGoals());
            if (scoreComparison == 0) {
                return Integer.compare(entry2.getKey(), entry1.getKey());
            }
            return scoreComparison;
        }).map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
