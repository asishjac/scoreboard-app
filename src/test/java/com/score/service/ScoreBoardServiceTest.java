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
}
