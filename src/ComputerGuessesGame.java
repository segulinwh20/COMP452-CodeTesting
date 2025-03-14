import java.util.function.Consumer;

public class ComputerGuessesGame {
    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public void finishGame(Consumer<GameResult> gameFinishedCallback){
        GameResult result = new GameResult(false, lastGuess, numGuesses);
        gameFinishedCallback.accept(result);
    }

    public String firstGuess(){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;
        lastGuess = (lowerBound + upperBound + 1) / 2;
        return ("I guess " + lastGuess + ".");
    }

    public String newGuess(Boolean isHigher) {
        if(isHigher){
            lowerBound = Math.max(lowerBound, lastGuess + 1) ;
        } else {
            upperBound = Math.min(upperBound, lastGuess);
        }
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;

        return("I guess " + lastGuess + ".");
    }

}
