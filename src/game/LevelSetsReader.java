package game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import levels.LevelInformation;
import levels.LevelSpecificationReader;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-06-01
 */
public class LevelSetsReader {
    private List<Selection> selections = new ArrayList<Selection>();

    /**
     * read sets from a file.
     * @param reader a reader
     * @param gameFlow Arkanoid's gameFlow
     * @return a list of selections (sets)
     */
    public List<Selection> fromReader(java.io.Reader reader, GameFlow gameFlow) {
        String key = null;
        String message = null;
        Task<Void> task = null;
        String line;
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        try {
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 == 1) {
                    key = line.substring(0, 1);
                    message = line.substring(2);
                }
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    final String staticLine = line;
                    task = new Task<Void>() {

                        @Override
                        public Void run() {
                            LevelSpecificationReader levelReader = new LevelSpecificationReader();
                            List<LevelInformation> levels = null;
                            try {
                                InputStream is = null;
                                is = ClassLoader.getSystemClassLoader().getResourceAsStream(staticLine);
                                InputStreamReader r = new InputStreamReader(is);
                                BufferedReader bf = new BufferedReader(r);
                                levels = levelReader.fromReader(bf);
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                            gameFlow.runLevels(levels);
                            return null;
                        }
                    };
                    this.selections.add(new Selection(key, message, task));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return this.selections;
    }
}
