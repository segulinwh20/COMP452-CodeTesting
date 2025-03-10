import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    //all the logic is in this constructor, that seems problematic ~Jackson
    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = addGuessMessage();

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        addPrompt();

        addLowerBtn(guessMessage);

        addCorrectBtn(guessMessage, cardsPanel, gameFinishedCallback);

        addHigherBtn(guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                numGuesses = 0;
                upperBound = 1000;
                lowerBound = 1;

                lastGuess = (lowerBound + upperBound + 1) / 2;
                guessMessage.setText("I guess " + lastGuess + ".");
            }
        });
    }

    private void addLowerBtn(JLabel guessMessage){
        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            //REFACTORED THIS
            guessMessage.setText(newGuess(false));
        });
        add(lowerBtn);
        lowerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0,10)));
    }

    private void addCorrectBtn(JLabel guessMessage, JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        JButton correctBtn = new JButton("Equal");
        correctBtn.addActionListener(e -> {
            guessMessage.setText("I guess ___.");

            // Send the result of the finished game to the callback
            GameResult result = new GameResult(false, lastGuess, numGuesses);
            gameFinishedCallback.accept(result);

            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
        });
        this.add(correctBtn);
        correctBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));
    }

    private void addHigherBtn(JLabel guessMessage){
        JButton higherBtn = new JButton("Higher");
        higherBtn.addActionListener(e -> {
            //REFACTORED THIS
            guessMessage.setText(newGuess(true));
        });
        this.add(higherBtn);
        higherBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addPrompt(){
        JLabel prompt = new JLabel("Your number is...");
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0,10)));
    }
    private JLabel addGuessMessage(){
        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        return guessMessage;
    }

    private String newGuess(Boolean isHigher) {
        if(isHigher){
            lowerBound = Math.max(lowerBound, lastGuess + 1) ;
        } else {
            upperBound = Math.min(upperBound, lastGuess + 1);
        }

        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;

        return("I guess " + lastGuess + ".");
    }

}
