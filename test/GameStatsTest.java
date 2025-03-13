import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStatsTest {
    private final GameStats mockGameStats = new GameStats() {
        @Override
        public int numGames(int numGuesses) {
            if (numGuesses == 1) return 1;
            if (numGuesses >= 2 && numGuesses <= 3) return 2;
            if (numGuesses >= 4 && numGuesses <= 5) return 3;
            if (numGuesses >= 6 && numGuesses <= 7) return 4;
            if (numGuesses >= 8 && numGuesses <= 9) return 5;
            if (numGuesses >= 10 && numGuesses <= 11) return 6;
            if (numGuesses >= 12 && numGuesses <= 13) return 7;
            if (numGuesses >= 14) return 8;
            return 0;
        }

        @Override
        public int maxNumGuesses() {
            return 14;
        }
    };

    @Test
    void testNumGames() {
        assertEquals(1, mockGameStats.numGames(1));
        assertEquals(2, mockGameStats.numGames(2));
        assertEquals(2, mockGameStats.numGames(3));
        assertEquals(3, mockGameStats.numGames(4));
        assertEquals(3, mockGameStats.numGames(5));
        assertEquals(4, mockGameStats.numGames(6));
        assertEquals(4, mockGameStats.numGames(7));
        assertEquals(5, mockGameStats.numGames(8));
        assertEquals(5, mockGameStats.numGames(9));
        assertEquals(6, mockGameStats.numGames(10));
        assertEquals(6, mockGameStats.numGames(11));
        assertEquals(7, mockGameStats.numGames(12));
        assertEquals(7, mockGameStats.numGames(13));
        assertEquals(8, mockGameStats.numGames(14));
        assertEquals(8, mockGameStats.numGames(15));
        assertEquals(0, mockGameStats.numGames(-1));
        assertEquals(0, mockGameStats.numGames(0));
    }

    @Test
    void testMaxNumGuesses() {
        int maxGuesses = mockGameStats.maxNumGuesses();
        assertEquals(14, maxGuesses);
    }

    @Test
    void testEdgeCasesForNumGamesWithGuessRanges() {
        assertEquals(0, mockGameStats.numGames(0));
        assertEquals(0, mockGameStats.numGames(-1));
        assertEquals(8, mockGameStats.numGames(14));
        assertEquals(8, mockGameStats.numGames(100));
    }

    @Test
    void testInvalidInputs() {
        assertEquals(0, mockGameStats.numGames(-1));
        assertEquals(0, mockGameStats.numGames(0));
    }
}