package model;

/**
 * This class makes Country objects and includes getters/setters for id and name, and a toString() override.
 *
 * @author Ashley Jensen
 */
public class Country {
    /**
     * Variable to hold integer id.
     */
    private int id;
    /**
     * Variable to hold String name.
     */
    private String name;

    /**
     * This is the Country constructor with id and name.
     * @param id sets integer id
     * @param name sets String name
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * This method returns id.
     * @return integer id
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets id variable to id parameter.
     * @param id sets integer id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns name.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets name variable to name parameter.
     * @param name sets String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method overrides toString() to return the String name.
     * @return String name
     */
    @Override
    public String toString() {
        return name;
    }
}
