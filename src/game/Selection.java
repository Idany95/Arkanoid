package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-01-06
 * @param <T> generic object
 */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * Constructor.
     * @param key a key
     * @param message a message
     * @param returnVal a return value
     */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * key getter.
     * @return key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * message getter.
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * returnVal getter.
     * @return return value
     */
    public T getReturnVal() {
        return this.returnVal;
    }
}