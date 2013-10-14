package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/9/12
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrgType extends Base {
    protected Dictionary type;
    protected Date startDate;
    protected Date endDate;

    public Dictionary getType() {
        return type;
    }

    public void setType(Dictionary type) {
        this.type = type;
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
