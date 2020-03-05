package game;

/**
 * @author Idan Yarchi <idanyarchi1@gmail.com>
 * @version 1
 * @since 2019-05-10
 */
public class Counter {
    private int num = 0;

   /**
    * Constructor.
    * @param num a number
     */
    public Counter(int num) {
       this.num = num;
   }
    /**
    * add number to current count.
    * @param number a number.
    */
    void increase(int number) {
       this.num = this.num + number;
   }

    /**
    * subtract number from current count.
    * @param number a number.
    */
    void decrease(int number) {
       this.num = this.num - number;
   }

    /**
    * get current count.
    * @return a current count
    */
    int getValue() {
       return this.num;
   }
}