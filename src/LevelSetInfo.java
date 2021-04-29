/**
 * @author Yaniv Madmon 204293005
 * @user: madmony
 */

/**
 * .
 * Contains Inforamtion about the set of the level
 */

public class LevelSetInfo {

    private String path;
    private String key;
    private String description;

    /**
     * .
     * Constructor
     *
     * @param path        the path of the file
     * @param key         the key
     * @param description the level dsecription
     */
    public LevelSetInfo(String path, String key, String description) {
        this.path = path;
        this.key = key;
        this.description = description;
    }

    /**
     * .
     * Return the path
     *
     * @return path path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * .
     * Return the key
     *
     * @return key key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * .
     * Return the description
     *
     * @return description description
     */
    public String getDescription() {
        return this.description;
    }

}
