package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Person extends Base {
    private String firstName;
    private String lastName;
    private String middleName;
    private String firstLastName;
    private String fullName;
    private long sex;

    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }

    public String getFirstLastName() {
        return lastName + " " + firstName;
    }

    public String getFullName(){
        return lastName + " " + firstName + " " + middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
