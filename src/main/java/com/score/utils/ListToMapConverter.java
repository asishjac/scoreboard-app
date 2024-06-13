package com.score.utils;

import com.score.exception.ScoreBoardException;
import com.score.model.Match;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ListToMapConverter {

    public Map<Integer, Match> convertListToMapWithIndex(List<Match>gameSummary) throws ScoreBoardException {
        if(gameSummary == null){
            throw new ScoreBoardException("List to be convereted should not be null");
        }
        AtomicInteger counter = new AtomicInteger(1);
        // Convert List to Map with position as key
        return gameSummary.stream()
                .collect(Collectors.toMap(match -> counter.getAndIncrement(), match -> match));
    }
}
