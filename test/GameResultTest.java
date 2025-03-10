import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameResultTest {
    @Test
    void testGameResultInit() {
        GameResult humanGame = new GameResult(true, 42, 5);
        assertTrue(humanGame.humanWasPlaying);
        assertEquals(42, humanGame.correctValue);
        assertEquals(5, humanGame.numGuesses);

        GameResult computerGame = new GameResult(false, 42, 5);
        assertFalse(computerGame.humanWasPlaying);
        assertEquals(42, computerGame.correctValue);
        assertEquals(5, computerGame.numGuesses);
    }
}
