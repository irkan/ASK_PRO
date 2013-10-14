package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/9/12
 * Time: 12:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrgName extends Base {
    protected String name;
    protected Date startDate;
    protected Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
