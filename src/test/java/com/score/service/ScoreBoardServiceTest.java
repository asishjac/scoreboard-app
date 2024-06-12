package com.score.service;

import com.score.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScoreBoardServiceTest {

    private ScoreBoardService scoreBoardService;

    @BeforeEach
    void setUp() {
        scoreBoardService = new ScoreBoardService();
    }

    @Test
    void testStartGame() {
        Match match = scoreBoardService.startGame("Mexico", "Canada");
        assertNotNull(match);
        assertEquals("Mexico", match.getHomeTeam().getName());
        assertEquals("Canada", match.getAwayTeam().getName());
        assertEquals(0, match.getHomeTeam().getGoals());
        assertEquals(0, match.getAwayTeam().getGoals());
    }

    @Test
    void testUpdateGoals() {
        Match match = scoreBoardService.startGame("Mexico", "Canada");
        scoreBoardService.updateGoals(match, 3, 2);
        assertEquals(3, match.getHomeTeam().getGoals());
        assertEquals(2, match.getAwayTeam().getGoals());
    }
}
