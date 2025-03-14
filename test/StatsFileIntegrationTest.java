import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatsFileIntegrationTest {

    @Test
    void testNumGames() {
        StatsFile stats = new StatsFile("test-stats.csv");
        assertEquals(2, stats.numGames(1));
        assertEquals(3, stats.numGames(2));
        assertEquals(2, stats.numGames(3));
        assertEquals(4, stats.numGames(4));
        assertEquals(1, stats.numGames(5));
        assertEquals(1, stats.numGames(6));
        assertEquals(1, stats.numGames(7));
        assertEquals(3, stats.numGames(10));
        assertEquals(2, stats.numGames(11));
    }

    @Test
    void testMaxGuesses() {
        StatsFile stats = new StatsFile("test-stats.csv");
        assertEquals(11, stats.maxNumGuesses());
    }

    @Test
    void testNumGamesEmptyList() {
        StatsFile stats = new StatsFile("test-stats-empty.csv");
        assertEquals(0, stats.numGames(1));
        assertEquals(0, stats.numGames(2));
        assertEquals(0, stats.numGames(3));
    }

    @Test
    void testMaxGuessEmptyList() {
        StatsFile stats = new StatsFile("test-stats-empty.csv");
        assertEquals(0, stats.maxNumGuesses());
    }




}
