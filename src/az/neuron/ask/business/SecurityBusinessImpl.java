package az.neuron.ask.business;

import az.neuron.ask.dao.SecurityDao;
import az.neuron.ask.domain.*;
import az.neuron.ask.domain.security.Column;
import az.neuron.ask.domain.security.Grid;
import az.neuron.ask.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityBusinessImpl implements SecurityBusiness {
    private SecurityDao securityDao;

    public SecurityBusinessImpl(SecurityDao securityDao) {
        this.securityDao =securityDao;
    }

    @Override
    public User getUser(String userName, String password) throws Exception {
        return securityDao.getUser(userName ,password);
    }

    @Override
    public Map<String, List<Operation>> getModulOperationMap() throws Exception {
        return securityDao.getModulOperationMap();
    }

    @Override
    public List<Modul> getModulList() throws Exception {
        return securityDao.getModulList();
    }

    @Override
    public List<Operation> getOperationList() throws Exception {
        return securityDao.getOperationList();
    }

    @Override
    public List<Operation> getPersonPrivilagies(long userId) throws Exception {
        return securityDao.getPersonPrivilagies(userId);
    }

    @Override
    public long checkUserName(long userId, String userName) throws Exception {
        return securityDao.checkUserName(userId, userName);
    }

    @Override
    public long securityOperation(User user) throws Exception {
        return securityDao.securityOperation(user);
    }

    @Override
    public User getUserInfo(long userId) throws Exception {
        return securityDao.getUserInfo(userId);
    }

    @Override
    public boolean changeUserPassword(long userId, String password) throws Exception {
        return securityDao.changeUserPassword(userId, password);
    }

    @Override
    public long userLogHistoryOperation(User user) throws Exception {
        return securityDao.userLogHistoryOperation(user);
    }

    @Override
    public long insertRole(Rolls rolls) throws Exception {
        return securityDao.insertRole(rolls);
    }

    @Override
    public List<Long> getTemplateModulOperationIdList(long rollId) throws Exception {
        return securityDao.getTemplateModulOperationIdList(rollId);
    }

    @Override
    public List<Rolls> getTemplateRollList(long orgId) throws Exception {
        return securityDao.getTemplateRollList(orgId);
    }

    @Override
    public long settingOperation(long userSettingId, String customValue) throws Exception {
        return securityDao.settingOperation(userSettingId, customValue);
    }

    @Override
    public long updateUserPrimaryEmail(long userId, String email) throws Exception {
        return securityDao.updateUserPrimaryEmail(userId, email);
    }

    @Override
    public User getUserForEmail(String email) throws Exception {
        return securityDao.getUserForEmail(email);
    }

    @Override
    public long updateUserRandomText(long userId, String randomText) throws Exception {
        return securityDao.updateUserRandomText(userId, randomText);
    }

    @Override
    public long userBlockOperation(String userIdStr) throws Exception {
        return securityDao.userBlockOperation(userIdStr);
    }

    @Override
    public Result<UserHistory> getUserHistoryByUserId(long userId, PagingObject pagingObject) throws Exception {
        return securityDao.getUserHistoryByUserId(userId, pagingObject);
    }

    @Override
    public Grid getSecirityGrid( User user ) throws Exception {
        Grid grid = new Grid();
        List<Column> columnList = new ArrayList<Column>();
        Column col = new Column();
        col.setName("Modullar");
        col.setModel("modul_name");
        col.setWidth(250);
        col.setHidden(false);
        col.setAlign("left");
        col.setStatus(1);
        columnList.add(col);
        for (Operation operation : user.getPersonPrivilegiesList()){
            Column column = new Column();
            String text = Helper.separateText(operation.getOperationName(), 15);
            column.setName("<span class=\"vertical_text vertical_text_dim4_sec\">" + text + "</span>");
            column.setModel(String.valueOf(operation.getId()));
            column.setWidth(50);
            column.setHidden(false);
            column.setAlign("center");
            column.setStatus(1);

            columnList.add(column);
        }
        grid.setColumnList(columnList);
        return grid;
    }
}
