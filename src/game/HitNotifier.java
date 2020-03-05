package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public interface HitNotifier {
   /**
    * Add hl as a listener to hit events.
    * @param hl an hit listener
    */
    void addHitListener(HitListener hl);
   /**
    * Remove hl from the list of listeners to hit events.
    * @param hl an hit listener
    */
    void removeHitListener(HitListener hl);
}