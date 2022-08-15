package model;

/**
 * This class makes Advisor objects and includes getters/setters for id, name, and password, and a toString() override.
 *
 * @author Ashley Jensen
 */
public class Advisor {
    /**
     * Variable to hold integer of id.
     */
    int id;
    /**
     * Variable to hold String of name.
     */
    String name;
    /**
     * Variable to hold String of password.
     */
    String password;

    /**
     * This is the Advisor constructor method with id, name, and password.
     * @param id sets integer id
     * @param name sets String name
     * @param password sets String password
     */
    public Advisor(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * This is the user constructor method with id and name.
     * @param id sets integer id
     * @param name sets String name
     */
    public Advisor(int id, String name) {
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
     * This method returns name.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns password.
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets password variable to password parameter.
     * @param password sets String password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method sets name variable to name parameter.
     * @param name sets String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets id variable to id parameter.
     * @param id sets integer id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This methods overrides toString() to return the id and name of a user.
     * @return String of id + " - " + name
     */
    @Override
    public String toString() {
        return (id + " - " + name);
    }

}
