package com.score.service;

import com.score.exception.ScoreBoardException;
import com.score.model.Match;
import com.score.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        scoreBoardService = new ScoreBoardService();
    }

    @Test
    void testStartGame() throws ScoreBoardException {
        Match match = scoreBoardService.startGame("Mexico", "Canada");
        assertNotNull(match);
        assertEquals("Mexico", match.getHomeTeam().getName());
        assertEquals("Canada", match.getAwayTeam().getName());
        assertEquals(0, match.getHomeTeam().getGoals());
        assertEquals(0, match.getAwayTeam().getGoals());
    }

    @Test
    void testStartGameWhenTeamIsEmptyOrNull() {
        ScoreBoardException exceptionThrown = assertThrows(ScoreBoardException.class, () -> scoreBoardService.startGame(null, ""), "Custom exception expected");
        assertEquals("Team name cannot be empty or null", exceptionThrown.getMessage());
    }

    @Test
    void testUpdateGoals() throws ScoreBoardException {
        Match match = scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.updateGoals(match, 3, 2);
        assertEquals(3, match.getHomeTeam().getGoals());
        assertEquals(2, match.getAwayTeam().getGoals());
    }

    @Test
    void testUpdateGoalsWhenMatchIsNull() {
        Match match1 = null;
        ScoreBoardException exceptionThrown = assertThrows(ScoreBoardException.class, () -> scoreBoardService.updateGoals(match1, 1, 1), "Custom exception expected");
        assertEquals("Invalid match provided", exceptionThrown.getMessage());
    }

    @Test
    void testGetSummary() throws ScoreBoardException {

        Match match1 = scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.updateGoals(match1, 0, 5);

        Match match2 = scoreBoardService.startGame("Spain", "Brazil");
        scoreBoardService.updateGoals(match2, 10, 2);

        Match match3 = scoreBoardService.startGame("Germany", "France");
        scoreBoardService.updateGoals(match3, 2, 2);

        Match match4 = scoreBoardService.startGame("Uruguay", "Italy");
        scoreBoardService.updateGoals(match4, 6, 6);

        Match match5 = scoreBoardService.startGame("Argentina", "Australia");
        scoreBoardService.updateGoals(match5, 3, 1);

        List<Match> gameSummary = scoreBoardService.getSummary();

        assertEquals(5, gameSummary.size());
        assertEquals("Uruguay", gameSummary.get(0).getHomeTeam().getName());
        assertEquals("Italy", gameSummary.get(0).getAwayTeam().getName());
        assertEquals("Spain", gameSummary.get(1).getHomeTeam().getName());
        assertEquals("Brazil", gameSummary.get(1).getAwayTeam().getName());
        assertEquals("Mexico", gameSummary.get(2).getHomeTeam().getName());
        assertEquals("Canada", gameSummary.get(2).getAwayTeam().getName());
        assertEquals("Argentina", gameSummary.get(3).getHomeTeam().getName());
        assertEquals("Australia", gameSummary.get(3).getAwayTeam().getName());
        assertEquals("Germany", gameSummary.get(4).getHomeTeam().getName());
        assertEquals("France", gameSummary.get(4).getAwayTeam().getName());
    }

    @Test
    void testFinishGame() throws ScoreBoardException {
        Match match = scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.finishGame(match);
        List<Match> summary = scoreBoardService.getSummary();
        assertFalse(summary.contains(match));
    }

    @Test
    void testFinishGameWhenMatchIsNull() {
        Match match1 = null;
        ScoreBoardException exceptionThrown = assertThrows(ScoreBoardException.class, () -> scoreBoardService.finishGame(match1), "Custom exception expected");
        assertEquals("Invalid match provided", exceptionThrown.getMessage());
    }

    @Test
    void testSortMapAndReturnSummaryList() throws ScoreBoardException {
        Map<Integer, Match> summaryMap = new HashMap<>();
        summaryMap.put(1, new Match(new Team("Team A", 1), new Team("Team B", 1)));
        summaryMap.put(2, new Match(new Team("Team C", 2), new Team("Team D", 3)));
        summaryMap.put(3, new Match(new Team("Team E", 0), new Team("Team F", 0)));

        List<Match> sortedList = scoreBoardService.sortMapAndReturnSummaryList(summaryMap);

        assertNotNull(sortedList, "The sorted list should not be null");
        assertEquals(3, sortedList.size(), "The sorted list should contain 5 matches");

        // Verify the order of matches by total goals and insertion order
        assertEquals("Team C", sortedList.get(0).getHomeTeam().getName());
        assertEquals("Team D", sortedList.get(0).getAwayTeam().getName());

        assertEquals("Team A", sortedList.get(1).getHomeTeam().getName());
        assertEquals("Team B", sortedList.get(1).getAwayTeam().getName());

        assertEquals("Team E", sortedList.get(2).getHomeTeam().getName());
        assertEquals("Team F", sortedList.get(2).getAwayTeam().getName());
    }

    @Test
    void testSortMapAndReturnSummaryListWhenMatchesAreSimultaneous() throws ScoreBoardException {
        Map<Integer, Match> summaryMap = new HashMap<>();
        summaryMap.put(1, new Match(new Team("Team A", 1), new Team("Team B", 1)));
        summaryMap.put(2, new Match(new Team("Team A", 2), new Team("Team B", 3)));
        summaryMap.put(3, new Match(new Team("Team A", 2), new Team("Team B", 3)));

        List<Match> sortedList = scoreBoardService.sortMapAndReturnSummaryList(summaryMap);

        assertNotNull(sortedList, "The sorted list should not be null");
        assertEquals(3, sortedList.size(), "The sorted list should contain 5 matches");

        // Verify the order of matches by total goals and insertion order
        assertEquals(2, sortedList.get(0).getHomeTeam().getGoals());
        assertEquals(3, sortedList.get(0).getAwayTeam().getGoals());

        assertEquals(2, sortedList.get(1).getHomeTeam().getGoals());
        assertEquals(3, sortedList.get(1).getAwayTeam().getGoals());

        assertEquals(1, sortedList.get(2).getHomeTeam().getGoals());
        assertEquals(1, sortedList.get(2).getAwayTeam().getGoals());
    }

    @Test
    void testSortMapAndReturnSummaryListWhenMapIsNull() {
        Map<Integer, Match> summaryMap = null;
        ScoreBoardException exceptionThrown = assertThrows(ScoreBoardException.class, () -> scoreBoardService.sortMapAndReturnSummaryList(summaryMap), "Custom exception expected");
        assertEquals("Map cannot be null", exceptionThrown.getMessage());
    }
}
