package model;

/**
 * This class makes Tech objects and includes getters/setters for id, name, and email, and a toString() override.
 *
 * @author Ashley Jensen
 */
public class Tech {
    /**
     * Variable used to hold integer id.
     */
    private int id;
    /**
     * Variable used to hold String name.
     */
    private String name;
    /**
     * Variable used to hold String type.
     */
    private String type;
    /**
     * Variable used to hold String email.
     */
    private String email;

    /**
     * This is the contact constructor with id, name, and email.
     * @param id sets integer id
     * @param name sets String name
     * @param email sets String email
     */
    public Tech(int id, String name, String type, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    /**
     * This method returns id integer.
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
     * This method returns name String.
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
     * This method returns type String.
     * @return String type
     */
    public String getType() {
        return type;
    }

    /**
     * This mehtod sets type variable to type parameter.
     * @param type sets String type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns email string.
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets email variable to name parameter
     * @param email sets String email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method overrides toString() to return the id and name of a contact.
     * @return String of id + " - " + name
     */
    @Override
    public String toString() {
        return ( id + " - " + name + " - " + type);
    }

}
