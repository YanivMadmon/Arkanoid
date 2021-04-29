/**
 * .
 * An interface of generic task
 *
 * @param <T> generic param
 */
public interface Task<T> {
    /**
     * .
     * Run the task
     *
     * @return null t
     */
    T run();
}