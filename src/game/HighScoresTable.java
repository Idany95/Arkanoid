package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class HighScoresTable implements java.io.Serializable {
    private static final long serialVersionUID = 6193894907785751603L;
    private int size;
    private int usedCells;
    private List<ScoreInfo> scores = new ArrayList<ScoreInfo>();

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size size of high scores tables
     */
    public HighScoresTable(int size) {
        this.size = size;
    }

    /**
     * Add a high-score.
     * @param score score to add
     */
    public void add(ScoreInfo score) {
        if (usedCells < this.size()) {
            this.scores.add(score);
            this.usedCells++;
        } else if (this.getRank(score.getScore()) <= this.size()) {
            this.scores.add(score);
            this.scores.remove(this.scores.get(this.size() - 1));
        }
    }

    /**
     * Return table size.
     * @return size of table, int
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest scores come first.
     * @return a list of scores;
     */
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.scores);
        return this.scores;
    }

    /**
     * return the rank of the current score.
     * where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     * @param score a specific score
     * @return a rank, int
     */
    public int getRank(int score) {
        if (this.usedCells < this.size()) {
            return (this.usedCells + 1);
        }
        Collections.sort(this.scores);
        for (ScoreInfo s : this.scores) {
            if (score > s.getScore()) {
                return scores.indexOf(s) + 1;
            }
        }
        return this.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     * @param filename a file
     * @throws IOException an exception
     */
    public void load(File filename) throws IOException {
            HighScoresTable temp = HighScoresTable.loadFromFile(filename);
            this.scores = temp.getHighScores();
            this.size = temp.size();
    }

    /**
     * Save table data to the specified file.
     * @param filename a file
     * @throws IOException an exception
     */
    public void save(File filename) throws IOException {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(this);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("IOException while saving HighScoresTables object");
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename a file
     * @return an HighScoresTable object
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(file);
            HighScoresTable temp = (HighScoresTable) input.readObject();
            input.close();
            file.close();
            return temp;
        } catch (Exception e) {
            HighScoresTable newTable = new HighScoresTable(10);
            try {
                newTable.save(filename);
                return newTable;
            } catch (Exception e2) {
                System.err.println(e2.getMessage());
            }

            return null;
        }
    }

    /**
     * usedCells getter.
     * @return number of used cells
     */
    public int getUsedCells() {
        return this.usedCells;
    }
}