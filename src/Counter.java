/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * .
 * A Counter
 */
public class Counter {
    private int count;

    /**
     * .
     * Constructor
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * .
     * Constructor
     *
     * @param value the val
     */
    public Counter(int value) {
        this.count = value;
    }

    /**
     * .
     * add number to current count.
     *
     * @param number num
     */
    void increase(int number) {
       this.count += number;
   }

    /**
     * .
     * subtract number from current count.
     *
     * @param number num
     */
    void decrease(int number) {
       this.count -= number;
   }

    /**
     * .
     * Get current count.
     *
     * @return count value
     */
    int getValue() {
       return this.count;
   }
}