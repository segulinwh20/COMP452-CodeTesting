import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {

    private GuessResult result;

    @Test
    void testMakeGuessReturnsCorrectValue() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame();
        int lowGuess = -1;
        int highGuess = 1005;
        int ans = humanGuessesGame.getTargetForTest();

        assertEquals(GuessResult.LOW, humanGuessesGame.makeGuess(lowGuess));
        assertEquals(GuessResult.HIGH, humanGuessesGame.makeGuess(highGuess));
        assertEquals(GuessResult.CORRECT, humanGuessesGame.makeGuess(ans));
    }

    @Test
    void numGuessesReturnsCorrectValue() {
        int incorrectGuess = 1005;

        HumanGuessesGame gameWithNoGuesses = new HumanGuessesGame();
        assertEquals(0, gameWithNoGuesses.getNumGuesses());

        HumanGuessesGame gameWithThreeGuesses = new HumanGuessesGame();
        gameWithThreeGuesses.makeGuess(incorrectGuess);
        gameWithThreeGuesses.makeGuess(incorrectGuess);
        gameWithThreeGuesses.makeGuess(incorrectGuess);
        assertEquals(3, gameWithThreeGuesses.getNumGuesses());

        HumanGuessesGame gameWithNineGuesses = new HumanGuessesGame();
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        assertEquals(9, gameWithNineGuesses.getNumGuesses());
    }

    @Test
    void testIsDone() {

        int incorrectGuess = 1005;

        HumanGuessesGame gameWithNoGuesses = new HumanGuessesGame();

        HumanGuessesGame gameWithThreeGuesses = new HumanGuessesGame();
        gameWithThreeGuesses.makeGuess(incorrectGuess);
        gameWithThreeGuesses.makeGuess(incorrectGuess);
        gameWithThreeGuesses.makeGuess(incorrectGuess);

        HumanGuessesGame gameWithNineGuesses = new HumanGuessesGame();
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);
        gameWithNineGuesses.makeGuess(incorrectGuess);

        assertFalse(gameWithNoGuesses.isDone());
        assertFalse(gameWithThreeGuesses.isDone());
        assertFalse(gameWithNineGuesses.isDone());

        HumanGuessesGame correctGuessGame = new HumanGuessesGame();
        int ans = correctGuessGame.getTargetForTest();
        correctGuessGame.makeGuess(ans);
        assertTrue(correctGuessGame.isDone());
    }
}
