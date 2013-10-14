package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Base {
    private long id;
    private int status;
    private String strStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }
}
