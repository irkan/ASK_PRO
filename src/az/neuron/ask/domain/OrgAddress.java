package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/9/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrgAddress extends Base {
    protected Address address;
    protected Date startDate;
    protected Date endDate;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
