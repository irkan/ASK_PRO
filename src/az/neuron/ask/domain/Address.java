package az.neuron.ask.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 10/29/12
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Address extends Base {
    protected String address;
    protected String postIndex;
    protected String postCode;
    protected String areaCode;
    protected Dictionary country;
    protected Region region;
    protected SubRegion subRegion;
    protected Village village;
    protected Dictionary addressType;
    protected Date givenDate;

    protected String countryIdStr;
    protected String regionIdStr;
    protected String villageIdStr;
    protected String subRegionIdStr;
    protected String lineAddressStr;
    protected String statusStr;
    protected String tableIdStr;
    protected String typeIdStr;
    protected String operation;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTypeIdStr() {
        return typeIdStr;
    }

    public void setTypeIdStr(String typeIdStr) {
        this.typeIdStr = typeIdStr;
    }

    public String getCountryIdStr() {
        return countryIdStr;
    }

    public void setCountryIdStr(String countryIdStr) {
        this.countryIdStr = countryIdStr;
    }

    public String getRegionIdStr() {
        return regionIdStr;
    }

    public void setRegionIdStr(String regionIdStr) {
        this.regionIdStr = regionIdStr;
    }

    public String getVillageIdStr() {
        return villageIdStr;
    }

    public void setVillageIdStr(String villageIdStr) {
        this.villageIdStr = villageIdStr;
    }

    public String getSubRegionIdStr() {
        return subRegionIdStr;
    }

    public void setSubRegionIdStr(String subRegionIdStr) {
        this.subRegionIdStr = subRegionIdStr;
    }

    public String getLineAddressStr() {
        return lineAddressStr;
    }

    public void setLineAddressStr(String lineAddressStr) {
        this.lineAddressStr = lineAddressStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getTableIdStr() {
        return tableIdStr;
    }

    public void setTableIdStr(String tableIdStr) {
        this.tableIdStr = tableIdStr;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Dictionary getAddressType() {
        return addressType;
    }

    public void setAddressType(Dictionary addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Dictionary getCountry() {
        return country;
    }

    public void setCountry(Dictionary country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public SubRegion getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(SubRegion subRegion) {
        this.subRegion = subRegion;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
