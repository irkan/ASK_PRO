package az.neuron.ask.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 12/26/12
 * Time: 3:04 PM
 * To change this template use File | Settings | File Templates.
 */

public class OrgPosition extends Base {
    protected List<Position> positionList;
    protected Position position;
    protected String statStr;
    protected long stat;

    public long getStat() {
        return stat;
    }

    public void setStat(long stat) {
        this.stat = stat;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getStatStr() {
        return statStr;
    }

    public void setStatStr(String statStr) {
        this.statStr = statStr;
    }
}
