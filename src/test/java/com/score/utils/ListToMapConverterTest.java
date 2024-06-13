package com.score.utils;

import com.score.exception.ScoreBoardException;
import com.score.model.Match;
import com.score.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ListToMapConverterTest {

    private ListToMapConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ListToMapConverter();
    }

    @Test
    void testConvertListToMapWithIndex() throws ScoreBoardException {
        List<Match> matches = Arrays.asList(
                new Match( new Team("Team A",1),new Team("Team B",1)),
                new Match( new Team("Team C",2),new Team("Team D",3)),
                new Match( new Team("Team E",0),new Team("Team F",0))
        );
        Map<Integer, Match> result = converter.convertListToMapWithIndex(matches);

        assertNotNull(result, "The result map should not be null");
        assertEquals(matches.size(), result.size(), "The size of the map should match the size of the list");
        for (int i = 0; i < matches.size(); i++) {
            assertEquals(matches.get(i), result.get(i + 1), "The map value should match the corresponding list element");
        }
    }

    @Test
    void testConvertListToMapWithSameTeamName() throws ScoreBoardException {
        List<Match> matches = Arrays.asList(
                new Match( new Team("Team A",1),new Team("Team B",1)),
                new Match( new Team("Team A",2),new Team("Team B",3)),
                new Match( new Team("Team E",0),new Team("Team F",0))
        );
        Map<Integer, Match> result = converter.convertListToMapWithIndex(matches);

        assertNotNull(result, "The result map should not be null");
        assertEquals(matches.size(), result.size(), "The size of the map should match the size of the list");
        for (int i = 0; i < matches.size(); i++) {
            assertEquals(matches.get(i), result.get(i + 1), "The map value should match the corresponding list element");
        }
    }

    @Test
    void testConvertListToMapWhenListIsNull(){
        List<Match> matches = null;
        ScoreBoardException exceptionThrown = assertThrows(ScoreBoardException.class, () -> converter.convertListToMapWithIndex(matches), "Custom exception expected");

        assertEquals("List to be convereted should not be null", exceptionThrown.getMessage());
    }
}
