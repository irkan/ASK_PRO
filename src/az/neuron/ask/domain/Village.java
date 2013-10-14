package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 10/29/12
 * Time: 7:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Village extends Base {
    protected String name;
    protected String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
