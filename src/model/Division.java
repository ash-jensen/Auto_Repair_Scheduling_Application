package model;

/**
 * This class makes Division objects and includes getters/setters for divId, divName, and countryId, and a
 * toString() override.
 *
 * @author Ashley Jensen
 */
public class Division {
    /**
     * Variable to hold integer of division id.
     */
    int divId;
    /**
     * Variable to hold String of division name.
     */
    String divName;
    /**
     * Variable to hold integer of country id.
     */
    int countryId;

    /**
     * This is the Division constructor method with divId, divName, and countryId.
     * @param divId sets integer divId
     * @param divName sets String divName
     * @param countryId sets integer countryid
     */
    public Division(int divId, String divName, int countryId) {
        this.divId = divId;
        this.divName = divName;
        this.countryId = countryId;
    }

    /**
     * This method returns divId.
     * @return integer divId
     */
    public int getDivId() {
        return divId;
    }

    /**
     * This method sets divId variable to divId parameter.
     * @param divId sets integer divId
     */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /**
     * This method returns divName.
     * @return String divName
     */
    public String getDivName() {
        return divName;
    }

    /**
     * This method sets divName variable to divName parameter.
     * @param divName sets String divName
     */
    public void setDivName(String divName) {
        this.divName = divName;
    }

    /**
     * This method returns countryId.
     * @return integer countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method sets countryId variable to countryId parameter.
     * @param countryId  sets integer countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This method overrides toString() to return the String divName.
     * @return String divName
     */
    @Override
    public String toString() {
        return divName;
    }
}
