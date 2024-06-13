# scoreboard-app
    Sample implementation of the Football World Cup Score Board.This library supports starting a game, finishing a game, updating goals, and getting a summary of games.

## requirements
- Java 17
- Spring Boot 3.2.5

# run
    mvn clean test

## note
- Could have split the ScoreBoardService by writing startGame,updateGoals,getSummary and finishGame to different classes.Since the solution doesnot have much complexity,added all the service methods in a single class.
- Handled the assumption by providing a Map with the position as key and Match as value.Then sorted the map and extracted the list by total goals and insertion order.
- The librarry now handles simultaneous matches of same teams.