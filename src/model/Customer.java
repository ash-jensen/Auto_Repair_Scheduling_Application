package model;

/**
 * This class makes Customer objects and includes getters/setters for id, name, address, postalCode, phoneNumber,  and divId,
 * and a toString() override.
 *
 * @author Ashley Jensen
 */
public class Customer {
    /**
     * Variable to hold integer of id.
     */
    private int id;
    /**
     * Variable to hold String name.
     */
    private String name;
    /**
     * Variable to hold String address.
     */
    private String address;
    /**
     * Variable to hold String postal code.
     */
    private String postalCode;
    /**
     * Variable to hold String phone number.
     */
    private String phoneNumber;
    /**
     * Variable to hold integer of division id.
     */
    private int divId;

    /**
     * This is the Customer constructor method with id, name, address, postalCode, phoneNumber, and divId.
     * @param id sets integer id
     * @param name sets String name
     * @param address sets String address
     * @param postalCode sets String postalCode
     * @param phoneNumber sets String phoneNumber
     * @param divId sets integer divId
     */
    public Customer( int id, String name, String address, String postalCode, String phoneNumber, int divId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divId = divId;
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
     * This method returns address.
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method returns postalCode.
     * @return String postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method returns phoneNumber.
     * @return String phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method returns divId.
     * @return integer divId
     */
    public int getDivId() {
        return divId;
    }

    /**
     * This method sets id variable to id parameter
     * @param id sets integer id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method sets name variable to name parameter.
     * @param name sets String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets address variable to address parameter.
     * @param address sets String address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method sets postalCode variable to postalCode parameter.
     * @param postalCode sets String postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method sets phoneNumber variable to phoneNumber parameter.
     * @param phoneNumber sets String phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method sets divId variable to divId parameter.
     * @param divId sets integer divId
     */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /**
     * This method overrides toString() to return the id and name of a customer.
     * @return String of id + " - " + name
     */
    @Override
    public String toString() {
        return (id + " - " + name);
    }
}
