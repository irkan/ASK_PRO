package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 12/26/12
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Position extends Base {
    protected Dictionary positionType;
    protected String positionIdStr;
    protected String positionTableIdStr;
    protected String positionStatusStr;
    protected long positionStat;
    protected String positionName;
    protected String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public long getPositionStat() {
        return positionStat;
    }

    public void setPositionStat(long positionStat) {
        this.positionStat = positionStat;
    }

    public Dictionary getPositionType() {
        return positionType;
    }

    public void setPositionType(Dictionary positionType) {
        this.positionType = positionType;
    }

    public String getPositionIdStr() {
        return positionIdStr;
    }

    public void setPositionIdStr(String positionIdStr) {
        this.positionIdStr = positionIdStr;
    }

    public String getPositionTableIdStr() {
        return positionTableIdStr;
    }

    public void setPositionTableIdStr(String positionTableIdStr) {
        this.positionTableIdStr = positionTableIdStr;
    }

    public String getPositionStatusStr() {
        return positionStatusStr;
    }

    public void setPositionStatusStr(String positionStatusStr) {
        this.positionStatusStr = positionStatusStr;
    }
}
