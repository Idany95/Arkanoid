package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 */
public class ScoreInfo implements Comparable<ScoreInfo>, java.io.Serializable  {
    private static final long serialVersionUID = -4531178893805162644L;
    private String name;
    private int score;

    /**
     * Constructor.
     * @param name a name
     * @param score a score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * name getter.
     * @return this scoreInfo's name;
     */
    public String getName() {
        return this.name;
    }

    /**
     * score getter.
     * @return this scoreInfo's score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(ScoreInfo otherScoreInfo) {
        int otherScore = ((ScoreInfo) otherScoreInfo).getScore();
        return otherScore - this.score;
    }
 }