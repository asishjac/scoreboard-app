package com.score.utils;

import com.score.exception.ScoreBoardException;
import com.score.model.Match;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The type List to map converter.
 */
public class ListToMapConverter {

    /**
     * Convert list to map with index of list as key and match as value.
     *
     * @param gameSummary the game summary
     * @return the map
     * @throws ScoreBoardException the custom score board exception
     */
    public Map<Integer, Match> convertListToMapWithIndex(List<Match> gameSummary) throws ScoreBoardException {
        if (Objects.isNull(gameSummary)) {
            throw new ScoreBoardException("List to be convereted should not be null");
        }
        AtomicInteger counter = new AtomicInteger(1);
        // Convert List to Map with position as key
        return gameSummary.stream()
                .collect(Collectors.toMap(match -> counter.getAndIncrement(), match -> match));
    }
}
