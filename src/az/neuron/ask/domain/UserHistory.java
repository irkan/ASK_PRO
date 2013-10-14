package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 7/19/13
 * Time: 12:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserHistory extends Base {
    private String loginDate;
    private String logoutDate;
    private String timePeriod;
    private String loginIP;

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }
}
