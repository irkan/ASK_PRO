package az.neuron.ask.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/9/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrgContact extends Base {
    protected List<Contact> contact;
    protected Contact orgContact;

    public Contact getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(Contact orgContact) {
        this.orgContact = orgContact;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
}
