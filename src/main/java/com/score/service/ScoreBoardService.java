package com.score.service;


import com.score.exception.ScoreBoardException;
import com.score.model.Match;
import com.score.utils.ListToMapConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Score board service.
 */
public class ScoreBoardService {
    private final List<Match> matches = new ArrayList<>();
    private final ListToMapConverter listToMapConverter = new ListToMapConverter();


    /**
     * Start a game.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @return the match
     * @throws ScoreBoardException the custom score board exception
     */
    public Match startGame(String homeTeam, String awayTeam) throws ScoreBoardException {
        if (StringUtils.isEmpty(homeTeam)|| StringUtils.isEmpty(awayTeam)) {
            throw new ScoreBoardException("Team name cannot be empty or null");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    /**
     * Update goals for a match.
     *
     * @param match         the match
     * @param homeTeamGoals the home team goals
     * @param awayTeamGoals the away team goals
     * @return the match
     * @throws ScoreBoardException the custom score board exception
     */
    public Match updateGoals(Match match, int homeTeamGoals, int awayTeamGoals) throws ScoreBoardException {
        if (Objects.isNull(match)) {
            throw new ScoreBoardException("Invalid match provided");
        }
        match.getHomeTeam().setGoals(homeTeamGoals);
        match.getAwayTeam().setGoals(awayTeamGoals);
        return match;
    }

    /**
     * Gets summary of all matches.
     *
     * @return the summary
     * @throws ScoreBoardException the custom score board exception
     */
    public List<Match> getSummary() throws ScoreBoardException {
        // Convert List<Match> to Map<Integer, Match>
        Map<Integer, Match> matchMap = listToMapConverter.convertListToMapWithIndex(matches);
        // Sort Map<Integer, Match> and return List<Match>
        return sortMapAndReturnSummaryList(matchMap);
    }

    /**
     * Finish a game.
     *
     * @param match the match
     * @throws ScoreBoardException the custom score board exception
     */
    public void finishGame(Match match) throws ScoreBoardException {
        if (Objects.isNull(match)) {
            throw new ScoreBoardException("Invalid match provided");
        }
        matches.remove(match);
    }

    /**
     * Sort map and return summary list.
     *
     * @param summaryMap the summary map
     * @return the list
     * @throws ScoreBoardException the custom score board exception
     */
    public List<Match> sortMapAndReturnSummaryList(Map<Integer, Match> summaryMap) throws ScoreBoardException {
        if (Objects.isNull(summaryMap)) {
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
