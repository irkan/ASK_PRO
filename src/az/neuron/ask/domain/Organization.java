package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: irkan
 * Date: 10/29/12
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Organization extends Base {
    protected String formula;
    protected Date creationDate;
    protected long parentId;
    protected OrgName orgName;
    protected OrgType orgType;
    protected OrgDescription orgDescription;
    protected OrgAddress orgAddress;
    protected OrgContact orgContact;
    protected String operStatus;
    protected String organizationIds;
    protected OrgPosition orgPosition;
    protected long level;

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public OrgPosition getOrgPosition() {
        return orgPosition;
    }

    public void setOrgPosition(OrgPosition orgPosition) {
        this.orgPosition = orgPosition;
    }

    public String getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(String organizationIds) {
        this.organizationIds = organizationIds;
    }

    public String getOperStatus() {
        return operStatus;
    }

    public void setOperStatus(String operStatus) {
        this.operStatus = operStatus;
    }

    public OrgName getOrgName() {
        return orgName;
    }

    public void setOrgName(OrgName orgName) {
        this.orgName = orgName;
    }

    public OrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }

    public OrgDescription getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(OrgDescription orgDescription) {
        this.orgDescription = orgDescription;
    }

    public OrgAddress getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(OrgAddress orgAddress) {
        this.orgAddress = orgAddress;
    }

    public OrgContact getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(OrgContact orgContact) {
        this.orgContact = orgContact;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
