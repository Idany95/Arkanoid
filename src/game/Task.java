package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 * @param <T> generic object
 */
public interface Task<T> {

    /**
     * run a task.
     * @return T the object to run
     */
    T run();
 }