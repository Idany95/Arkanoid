package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-06-01
 * @param <T> an object
 */
public interface Menu<T> extends Animation {

    /**
     * add a selection to menu.
     * @param key a string
     * @param message a message
     * @param returnVal return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get status of menu.
     * @return T, generic object (Task)
     */
    T getStatus();

    /**
     * a sub menu.
     * @param key a string
     * @param message a string
     * @param subMenu a menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * add selection to menu.
     * @param s a selection
     */
    void addSelection(Selection s);
 }