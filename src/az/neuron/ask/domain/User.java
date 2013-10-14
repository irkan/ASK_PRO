package az.neuron.ask.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan
 * Date: 3/18/12
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class User extends Person {
    protected String userName;
    protected String password;
    private Map<String,List<Operation>> privilegieList;
    private List<Modul> modulList;
    private Date loginDate;
    private List<Operation> personPrivilegiesList;
    private String userPrivilagies;
    private long isTempPassword;
    private String remoteAddress;
    private long userHistoryId;
    private String logHistoryStatus;
    private String logDate;
    private String userSessionId;
    private String isOnline;
    private long personId;
    private String userImagePath;
    private boolean isOpen;
    private List<Setting> settingList;
    private String userEmail;
    private String md5Password;
    private String projectURL;
    private String successRandomText;
    private String mailSendType;
    private long isBlocked;
    private String userId;
    private List<Organization> orgList;

    public List<Organization> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Organization> orgList) {
        this.orgList = orgList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getBlocked() {
        return isBlocked;
    }

    public void setBlocked(long blocked) {
        isBlocked = blocked;
    }

    public String getMailSendType() {
        return mailSendType;
    }

    public void setMailSendType(String mailSendType) {
        this.mailSendType = mailSendType;
    }

    public String getSuccessRandomText() {
        return successRandomText;
    }

    public void setSuccessRandomText(String successRandomText) {
        this.successRandomText = successRandomText;
    }

    public String getProjectURL() {
        return projectURL;
    }

    public void setProjectURL(String projectURL) {
        this.projectURL = projectURL;
    }

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Setting> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<Setting> settingList) {
        this.settingList = settingList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getOnline() {
        return isOnline;
    }

    public void setOnline(String online) {
        isOnline = online;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLogHistoryStatus() {
        return logHistoryStatus;
    }

    public void setLogHistoryStatus(String logHistoryStatus) {
        this.logHistoryStatus = logHistoryStatus;
    }

    public long getUserHistoryId() {
        return userHistoryId;
    }

    public void setUserHistoryId(long userHistoryId) {
        this.userHistoryId = userHistoryId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public long getTempPassword() {
        return isTempPassword;
    }

    public void setTempPassword(long tempPassword) {
        isTempPassword = tempPassword;
    }

    public String getUserPrivilagies() {
        return userPrivilagies;
    }

    public void setUserPrivilagies(String userPrivilagies) {
        this.userPrivilagies = userPrivilagies;
    }

    public List<Operation> getPersonPrivilegiesList() {
        return personPrivilegiesList;
    }

    public void setPersonPrivilegiesList(List<Operation> personPrivilegiesList) {
        this.personPrivilegiesList = personPrivilegiesList;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public List<Modul> getModulList() {
        return modulList;
    }

    public void setModulList(List<Modul> modulList) {
        this.modulList = modulList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Map<String, List<Operation>> getPrivilegieList() {
        return privilegieList;
    }

    public void setPrivilegieList(Map<String, List<Operation>> privilegieList) {
        this.privilegieList = privilegieList;
    }

    public boolean CheckPrivilagies(String modul_name, long oper_id){
        boolean bool = false;
        List<Operation> operationList = this.getPrivilegieList().get(modul_name);
        for(int i=0; i<operationList.size(); i++){
            if(operationList.get(i).getModulOperationId()==oper_id){
               bool = true;
                break;
            }
        }
        return  bool;
    }
}
