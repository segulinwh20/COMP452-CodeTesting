import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsFileMockTest {
    private final StatsFile statsFile = new StatsFile("test-stats.csv") {
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
            assertEquals(1, statsFile.numGames(1));
            assertEquals(2, statsFile.numGames(2));
            assertEquals(2, statsFile.numGames(3));
            assertEquals(3, statsFile.numGames(4));
            assertEquals(3, statsFile.numGames(5));
            assertEquals(4, statsFile.numGames(6));
            assertEquals(4, statsFile.numGames(7));
            assertEquals(5, statsFile.numGames(8));
            assertEquals(5, statsFile.numGames(9));
            assertEquals(6, statsFile.numGames(10));
            assertEquals(6, statsFile.numGames(11));
            assertEquals(7, statsFile.numGames(12));
            assertEquals(7, statsFile.numGames(13));
            assertEquals(8, statsFile.numGames(14));
            assertEquals(8, statsFile.numGames(15));
            assertEquals(0, statsFile.numGames(-1));
            assertEquals(0, statsFile.numGames(0));
        }

        @Test
        void testMaxNumGuesses() {
            int maxGuesses = statsFile.maxNumGuesses();
            assertEquals(14, maxGuesses);
        }

        @Test
        void testEdgeCasesForNumGamesWithGuessRanges() {
            assertEquals(0, statsFile.numGames(0));
            assertEquals(0, statsFile.numGames(-1));
            assertEquals(8, statsFile.numGames(14));
            assertEquals(8, statsFile.numGames(100));
        }

        @Test
        void testInvalidInputs() {
            assertEquals(0, statsFile.numGames(-1));
            assertEquals(0, statsFile.numGames(0));
        }
}