package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Irkan Ahmadov
 * Date: 4/26/13
 * Time: 6:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rolls extends Base {
    private long positionId;
    private long userId;
    private long orgId;
    private String modOperIdStr;
    private String positionName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getModOperIdStr() {
        return modOperIdStr;
    }

    public void setModOperIdStr(String modOperIdStr) {
        this.modOperIdStr = modOperIdStr;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
}
