package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 5/23/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Setting extends Base {
    private String settingName;
    private String htmlElement;
    private String callFunction;
    private String settingValue;
    private String settingDefaultValue;
    private long userSettingId;
    private String htmlElementId;
    private String htmlElementType;

    public String getHtmlElementType() {
        return htmlElementType;
    }

    public void setHtmlElementType(String htmlElementType) {
        this.htmlElementType = htmlElementType;
    }

    public String getHtmlElementId() {
        return htmlElementId;
    }

    public void setHtmlElementId(String htmlElementId) {
        this.htmlElementId = htmlElementId;
    }

    public long getUserSettingId() {
        return userSettingId;
    }

    public void setUserSettingId(long userSettingId) {
        this.userSettingId = userSettingId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getHtmlElement() {
        return htmlElement;
    }

    public void setHtmlElement(String htmlElement) {
        this.htmlElement = htmlElement;
    }

    public String getCallFunction() {
        return callFunction;
    }

    public void setCallFunction(String callFunction) {
        this.callFunction = callFunction;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingDefaultValue() {
        return settingDefaultValue;
    }

    public void setSettingDefaultValue(String settingDefaultValue) {
        this.settingDefaultValue = settingDefaultValue;
    }
}
