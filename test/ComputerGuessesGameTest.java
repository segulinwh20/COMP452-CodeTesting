import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerGuessesGameTest {

    private ComputerGuessesGame game;
    private GameResult resultHolder;
    private int lastGuess;

    @BeforeEach
    void setUp() {
        game = new ComputerGuessesGame();
        resultHolder = null;
        lastGuess = -1;
    }

    private void saveLastGuess(String guessString) {
        lastGuess = Integer.parseInt(guessString.replace("I guess ", "").replace(".", ""));
    }

    @Test
    void firstGuessTest() {
        saveLastGuess(game.firstGuess());
        assertEquals(501, lastGuess);
    }

    @Test
    void newGuessAdjustsRangeWhenHigher() {
        game.firstGuess();
        saveLastGuess(game.newGuess(true));
        assertEquals(752, lastGuess);
    }

    @Test
    void newGuessAdjustsRangeWhenLower() {
        game.firstGuess();
        saveLastGuess(game.newGuess(false));
        assertEquals(252, lastGuess);
    }

    @Test
    void multipleGuesses() {
        game.firstGuess();
        game.newGuess(true);
        game.newGuess(false);
        saveLastGuess(game.newGuess(true));
        assertEquals(691, lastGuess);
    }

    @Test
    void numGuessesTracksCorrectly() {
        game.firstGuess();
        game.newGuess(true);
        game.newGuess(false);
        game.newGuess(false);
        game.finishGame(result -> resultHolder = result);
        assertNotNull(resultHolder);
        assertEquals(4, resultHolder.numGuesses);
    }

    @Test
    void accurateEndGameStateAfterMultipleGuesses() {
        saveLastGuess(game.firstGuess());
        assertEquals(501, lastGuess);
        saveLastGuess(game.newGuess(true));
        assertEquals(752, lastGuess);
        saveLastGuess(game.newGuess(true));
        assertEquals(877, lastGuess);
        saveLastGuess(game.newGuess(false));
        assertEquals(815, lastGuess);
        game.finishGame(result -> resultHolder = result);
        assertNotNull(resultHolder);
        assertEquals(4, resultHolder.numGuesses);
        assertEquals(lastGuess, resultHolder.correctValue);
    }

    @Test
    void targetOneAboveFirstGuess() {
        game.firstGuess();
        game.newGuess(true);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        saveLastGuess(game.newGuess(false));
        assertEquals(502, lastGuess);
    }

    @Test
    void targetOneBelowFirstGuess() {
        game.firstGuess();
        game.newGuess(false);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        saveLastGuess(game.newGuess(true));
        assertEquals(500, lastGuess);
    }

    @Test
    void minimumTargetGuessedCorrectly() {
        game.firstGuess();
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        saveLastGuess(game.newGuess(false));
        assertEquals(2, lastGuess);
    }

    @Test
    void maximumTargetGuessedCorrectly() {
        game.firstGuess();
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        saveLastGuess(game.newGuess(true));
        assertEquals(999, lastGuess);
    }

    @Test
    void lowerBoundRespected() {
        game.firstGuess();
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        game.newGuess(false);
        saveLastGuess(game.newGuess(false));
        assertTrue(lastGuess > 2);
    }

    @Test
    void upperBoundRespected() {
        game.firstGuess();
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        game.newGuess(true);
        saveLastGuess(game.newGuess(true));
        assertTrue(lastGuess < 1000);
    }
}