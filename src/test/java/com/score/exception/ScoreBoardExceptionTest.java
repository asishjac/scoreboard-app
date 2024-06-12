package com.score.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardExceptionTest {

    @Test
    void testScoreBoardExceptionMessage() {
        String errorMessage = "This is a custom error message";
        ScoreBoardException exception = new ScoreBoardException(errorMessage);

        assertNotNull(exception, "Exception instance should not be null");
        assertEquals(errorMessage, exception.getMessage(), "Exception message should match the input message");
    }

    @Test
    void testScoreBoardExceptionInheritance() {
        ScoreBoardException exception = new ScoreBoardException("Test message");

        assertTrue(exception instanceof Exception, "ScoreBoardException should be an instance of Exception");
    }
}
