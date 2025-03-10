import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Displays statistics about how many guesses the person took during past games
 * Loads data from the file and displays in a JPanel
 *
 * TODO: refactor this class
 */
public class StatsPanel extends JPanel {

    private final JPanel resultsPanel;

    // Stats will display the number of games in each "bin"
    // A bin goes from BIN_EDGES[i] through BIN_EDGES[i+1]-1, inclusive
    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};
    private ArrayList<JLabel> resultsLabels = new ArrayList<>();

    public StatsPanel(JPanel cardsPanel) {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        addTitle();

        this.add(Box.createRigidArea(new Dimension(0,40)));

        //THIS WAS REFACTORED
        resultsPanel = addResultsPanel();
        this.add(resultsPanel);
        updateResultsPanel();

        this.add(Box.createVerticalGlue());

        //THIS WAS REFACTORED
        JButton quit = addQuitButton(cardsPanel);

        this.add(quit);
        quit.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,20)));

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                updateResultsPanel();
            }
        });
    }

    private JPanel addResultsPanel(){
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Guesses"));
        panel.add(new JLabel("Games"));
        addBinLabels(panel);
        panel.setMinimumSize(new Dimension(120, 120));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private void addTitle(){
        JLabel title = new JLabel("Your Stats");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        JLabel subtitle = new JLabel("(Past 30 Days)");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(subtitle);
    }

    private JButton addQuitButton(JPanel cardsPanel){
        JButton quit = new JButton("Back to Home");
        quit.addActionListener(e -> {
            // See itemStateChanged in https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java
            CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
            cardLayout.show(cardsPanel, ScreenID.HOME.name());
        });
        return quit;
    }

    private void addBinLabels(JPanel panel) {
        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            String binName;
            if(binIndex == BIN_EDGES.length-1){
                // last bin
                binName = BIN_EDGES[binIndex] + " or more";
            }
            else{
                int upperBound = BIN_EDGES[binIndex+1] - 1;
                if(upperBound > BIN_EDGES[binIndex]){
                    binName = BIN_EDGES[binIndex] + "-" + upperBound;
                }
                else{
                    binName = Integer.toString(BIN_EDGES[binIndex]);
                }
            }
            panel.add(new JLabel(binName));
            JLabel result = new JLabel("--");
            resultsLabels.add(result);
            panel.add(result);
        }
    }


    private void clearResults(){
        for(JLabel lbl : resultsLabels){
            lbl.setText("--");
        }
    }

    private void updateResultsPanel(){
        clearResults();

        GameStats stats = new StatsFile();

        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            final int lowerBound = BIN_EDGES[binIndex];
            int numGames = 0;

            if(binIndex == BIN_EDGES.length-1){
                // last bin
                // Sum all the results from lowerBound on up
                for(int numGuesses=lowerBound; numGuesses<stats.maxNumGuesses(); numGuesses++){
                    numGames += stats.numGames(numGuesses);
                }
            }
            else{
                int upperBound = BIN_EDGES[binIndex+1];
                for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }

            JLabel resultLabel = resultsLabels.get(binIndex);
            resultLabel.setText(Integer.toString(numGames));
        }
    }
}
