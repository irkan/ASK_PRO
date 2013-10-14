package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/9/12
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrgDescription extends Base {
    protected String description;
    protected Date startDate;
    protected Date endDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
