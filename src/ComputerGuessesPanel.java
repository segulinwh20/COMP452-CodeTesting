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

    private ComputerGuessesGame game;

    //all the logic is in this constructor, that seems problematic ~Jackson
    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        game = new ComputerGuessesGame();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = addGuessMessage();

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        addPrompt();

        addLowerBtn(guessMessage);

        addCorrectBtn(guessMessage, cardsPanel, gameFinishedCallback);

        addHigherBtn(guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                guessMessage.setText(game.firstGuess());
            }
        });
    }

    private void addLowerBtn(JLabel guessMessage){
        JButton lowerBtn = new JButton("Lower");
        lowerBtn.addActionListener(e -> {
            //REFACTORED THIS
            guessMessage.setText(game.newGuess(false));
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
            game.finishGame(gameFinishedCallback);

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
            guessMessage.setText(game.newGuess(true));
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
}
