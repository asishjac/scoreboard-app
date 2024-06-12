# scoreboard-app
    Sample implementation of the Football World Cup Score Board.This library supports starting a game, finishing a game, updating goals, and getting a summary of games.

## Requirements
- Java 17
- Spring Boot 3.2.5

# run
    mvn clean test

## Assumptions
- Two matches should not have the same teams playing simultaneously.Because the indexOf() method returns the index of the first occurrence of the match in the list. If there are multiple matches with the same teams, indexOf will always return the same index for all of them, leading to incorrect indexing in the output.

