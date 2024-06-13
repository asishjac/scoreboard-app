# scoreboard-app
    Sample implementation of the Football World Cup Score Board.This library supports starting a game, finishing a game, updating goals, and getting a summary of games.

## requirements
- Java 17
- Spring Boot 3.2.5

# run
    mvn clean test

## assumptions
- Two matches should not have the same teams playing simultaneously.Because the indexOf() method returns the index of the first occurrence of the match in the list. If there are multiple matches with the same teams, indexOf will always return the same index for all of them, leading to incorrect indexing in the output.

## note
- Could have split the ScoreBoardService by writing startGame,updateGoals,getSummary and finishGame to different classes.Since the solution doesnot have much complexity,added all the service methods in a single class.