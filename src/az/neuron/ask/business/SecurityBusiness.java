package az.neuron.ask.business;

import az.neuron.ask.domain.*;
import az.neuron.ask.domain.security.Grid;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SecurityBusiness {
    public User getUser(String userName , String password) throws Exception;
    public Map<String, List<Operation>> getModulOperationMap() throws Exception;
    public List<Modul> getModulList()throws Exception;
    public List<Operation>getOperationList()throws Exception;
    public List<Operation> getPersonPrivilagies(long userId) throws Exception;
    public long checkUserName(long userId, String userName) throws Exception;
    public long securityOperation(User user) throws Exception;
    public User getUserInfo(long userId) throws Exception;
    public boolean changeUserPassword(long userId, String password) throws Exception;
    public long userLogHistoryOperation(User user) throws Exception;
    public long insertRole(Rolls rolls) throws Exception;
    public List<Long> getTemplateModulOperationIdList(long rollId) throws Exception;
    public List<Rolls> getTemplateRollList(long orgId) throws Exception;
    public long settingOperation(long userSettingId, String customValue) throws Exception;
    public long updateUserPrimaryEmail(long userId, String email) throws Exception;
    public User getUserForEmail(String email) throws Exception;
    public long updateUserRandomText(long userId, String randomText) throws Exception;
    public long userBlockOperation(String userIdStr) throws Exception;
    public Result<UserHistory> getUserHistoryByUserId(long userId, PagingObject pagingObject) throws Exception;

    public Grid getSecirityGrid( User user ) throws Exception;
}
