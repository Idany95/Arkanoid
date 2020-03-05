package game;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.awt.Color;

import basics.Ball;
import basics.Block;
import basics.Rectangle;
import basics.Velocity;
import biuoop.DialogManager;
import biuoop.DrawSurface;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-03-07
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private Paddle userControlledPaddle;
    private Counter blockCounter = new Counter(0);
    private Counter ballCounter = new Counter(0);
    private Counter scoreCounter;
    private Counter livesCounter;
    private AnimationRunner runner = new AnimationRunner(this.gui, 60);
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation lvlInfo;
    private boolean lastLevel;
    private HighScoresTable table;

    /**
     * Constructor.
     * @param lvlInfo Level Information
     */
    public GameLevel(LevelInformation lvlInfo) {
        this.lvlInfo = lvlInfo;
    }

    /**
     * Constructor.
     * @param levelInfo Level Information
     * @param ar animation runner
     * @param gui gui
     * @param scoreCounter HitListener, score counter
     * @param livesCounter HitListener, lives counter
     * @param lastLevel says if this is the last level
     * @param table HighScoresTable
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, GUI gui,
            Counter scoreCounter, Counter livesCounter, boolean lastLevel, HighScoresTable table) {
        this.lvlInfo = levelInfo;
        this.runner = ar;
        this.gui = gui;
        this.keyboard = this.gui.getKeyboardSensor();
        this.scoreCounter = scoreCounter;
        this.livesCounter = livesCounter;
        this.lastLevel = lastLevel;
        this.table = table;
    }

    /**
     * Method for adding a Collidable to the Game's object GameEnvironment.
     * @param c a Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method for adding a sprite to the Game's object spriteCollection.
     * @param s a Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        Color [] colorArr = {new Color(123, 205, 186), new Color(98, 195, 112), new Color(255, 191, 0),
                             new Color(232, 63, 111), new Color(215, 38, 56), new Color(115, 29, 216)};
        // listeners
        HitListener blockRemover = new BlockRemover(this, this.blockCounter);
        HitListener ballRemover = new BallRemover(this, this.ballCounter);
        HitListener scoreListener = new ScoreTrackingListener(scoreCounter);
        //background
        Sprite background = this.lvlInfo.getBackground();
        this.sprites.addSprite(background);
        // score Sprite
        ScoreIndicator scoreDisplayer = new ScoreIndicator(this.scoreCounter);
        this.addSprite(scoreDisplayer);
        // lives Sprite
        LivesIndicator livesDisplayer = new LivesIndicator(this.livesCounter);
        this.addSprite(livesDisplayer);
        // level name Sprite
        LevelNameIndicator levelNameDisplayer = new LevelNameIndicator(this.lvlInfo.levelName());
        this.addSprite(levelNameDisplayer);
        // border blocks
        Block right = new Block(770, 50, 30, 550);
        right.addToGame(this);
        Block left = new Block(0, 50, 30, 550);
        left.addToGame(this);
        Block top = new Block(0, 20, 800, 30);
        top.addToGame(this);
        Block bottom = new Block(30, 600, 740, 30);
        bottom.addToGame(this);
        bottom.addHitListener(ballRemover);
        // in-game blocks
        for (Block b : this.lvlInfo.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            blockCounter.increase(1);
            b.addToGame(this);
        }
    }

    /**
     * Play one turn of the game. called by run().
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        while (this.livesCounter.getValue() > 0) {
           this.playOneTurn();
           this.livesCounter.decrease(1);
        }
        this.gui.close();
        return;
    }

    /**
     * remove a collidable from current game.
     * @param c a collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove a sprite from current game.
     * @param s a sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.removeSprite(this.userControlledPaddle);
            this.removeCollidable(this.userControlledPaddle);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.removeSprite(this.userControlledPaddle);
            this.removeCollidable(this.userControlledPaddle);
            this.running = false;
            this.livesCounter.decrease(1);
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
         }

        if (this.livesCounter.getValue() == 0 //lost
            || (this.lastLevel && this.livesCounter.getValue() > 0 && this.blockCounter.getValue() == 0)) { //won
            if (this.livesCounter.getValue() == 0) { //lost
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.scoreCounter.getValue(), false, this.gui)));
            } else { //won
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.scoreCounter.getValue(), true, this.gui)));
            }
            if (this.table.getRank(this.scoreCounter.getValue()) <= this.table.size()) {
                AnimationRunner tempRunner = new AnimationRunner(this.gui, 60);
                DialogManager dialog = this.gui.getDialogManager();
                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                //tempRunner.run(animation);
                ScoreInfo highscore = new ScoreInfo(name, this.scoreCounter.getValue());
                this.table.add(highscore);
            }
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                            new HighScoresAnimation(this.table)));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
     }

    /**
     * Method that creates balls on top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        // balls
        for (Velocity v : this.lvlInfo.initialBallVelocities()) {
            Ball tempBall = new Ball(400, 555, 5, java.awt.Color.white);
            tempBall.setVelocity(v);
            tempBall.setGameEnvironment(this.environment);
            tempBall.addToGame(this);
            this.ballCounter.increase(1);

        }
        // paddle
        int paddleX = 15 + ((770 - this.lvlInfo.paddleWidth()) / 2);
        Rectangle paddleRec = new Rectangle(paddleX, 560, this.lvlInfo.paddleWidth(), 10);
        this.userControlledPaddle = new Paddle(paddleRec, this.keyboard);
        this.userControlledPaddle.setSpeed(this.lvlInfo.paddleSpeed());
        this.userControlledPaddle.addToGame(this);
    }

    /**
     * BlockCounter getter.
     * @return a Block Counter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * LivesCounter getter.
     * @return a lives Counter
     */
    public Counter getlivesCounter() {
        return this.livesCounter;
    }

    /**
     * set ks.
     * @param ks keyobard sensor
     */
    public void setKS(KeyboardSensor ks) {
        this.keyboard = ks;
    }
}