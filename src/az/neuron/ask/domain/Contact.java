package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 10/29/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Contact extends Base {
    protected String value;
    protected Dictionary contactType;
    protected String contactTypeIdStr;
    protected String contactValueStr;
    protected String contactTableIdStr;
    protected String contactStatusStr;

    public String getContactTypeIdStr() {
        return contactTypeIdStr;
    }

    public void setContactTypeIdStr(String contactTypeIdStr) {
        this.contactTypeIdStr = contactTypeIdStr;
    }

    public String getContactValueStr() {
        return contactValueStr;
    }

    public void setContactValueStr(String contactValueStr) {
        this.contactValueStr = contactValueStr;
    }

    public String getContactTableIdStr() {
        return contactTableIdStr;
    }

    public void setContactTableIdStr(String contactTableIdStr) {
        this.contactTableIdStr = contactTableIdStr;
    }

    public String getContactStatusStr() {
        return contactStatusStr;
    }

    public void setContactStatusStr(String contactStatusStr) {
        this.contactStatusStr = contactStatusStr;
    }

    public Dictionary getContactType() {
        return contactType;
    }

    public void setContactType(Dictionary contactType) {
        this.contactType = contactType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
