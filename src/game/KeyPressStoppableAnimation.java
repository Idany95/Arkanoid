package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-27-05
 */
public class KeyPressStoppableAnimation implements Animation {
    private biuoop.KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor a keyboard sensor
     * @param key a spesific key
     * @param animation an animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * {@inheritDoc}
     */
    public void doOneFrame(DrawSurface d) {
        if (!this.shouldStop()) {
            animation.doOneFrame(d);
        }
        if (!this.ks.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean shouldStop() {
        if (this.ks.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
            }
        return this.stop;
    }
}