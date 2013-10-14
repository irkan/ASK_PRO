package az.neuron.ask.dao;

import az.neuron.ask.domain.*;
import az.neuron.ask.util.DBUtility;
import az.neuron.ask.util.JdbcUtility;
import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class OracleSecurityDao implements SecurityDao {
    @Override
    public User getUser(String userName, String password) throws Exception {
        User user = new User();
        Connection c = null;
        CallableStatement call = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
//PROCEDURE GET_USER(  US_NAME IN NVARCHAR2 ,
                    // PASSW IN NVARCHAR2 ,
                    // i out integer ,
                    // person out cursorType,
                    // MODULS  out cursorType,
                    // O_SETTINGS out cursorType,
                    // o_sub_orgs out cursorType )
        String sql= "{call ASK_USER.PKG_SECURITY.GET_USER(?,?,?,?,?,?,?)}";
        String sql_1= "{call ASK_USER.PKG_SECURITY.GET_USER_PRIV(?,?,?)}";
        try{
            c =  DBUtility.getConnection();
            if(c!=null){
                call = c.prepareCall(sql);
                call.setString( 1 , userName );
                call.setString( 2 , password );
                call.registerOutParameter( 3 , OracleTypes.INTEGER );
                call.registerOutParameter( 4 , OracleTypes.CURSOR );
                call.registerOutParameter( 5 , OracleTypes.CURSOR );
                call.registerOutParameter( 6 , OracleTypes.CURSOR );
                call.registerOutParameter( 7 , OracleTypes.CURSOR );
                call.execute();

                int login = (Integer) call.getObject( 3 );
                if (login == 1){

                    rs = (ResultSet) call.getObject( 4 );
                    if (rs.next()){
                        user.setId( rs.getLong("ID") );
                        user.setFirstName( rs.getString("FIRST_NAME") );
                        user.setLastName( rs.getString("LAST_NAME") );
                        user.setMiddleName( rs.getString("FATHER_NAME") );
                        user.setUserName( rs.getString("USER_NAME") );
                        user.setTempPassword( rs.getLong("IS_TEMPORARY") );
                        user.setPersonId( rs.getLong("PERSON_ID") );
                        user.setUserEmail( rs.getString("USER_EMAIL") );
                        user.setMd5Password( rs.getString("PASSWORD") );
                        //user.setSex( rs.getLong("GENDER_ID") );
                        user.setBlocked( rs.getLong("IS_BLOCKED") );
                    }
                    Setting setting = null;
                    List < Setting > settingList = new ArrayList < Setting >();
                    rs = ( ResultSet ) call.getObject( 6 );
                    while (rs.next()){
                        setting = new Setting();
                        setting.setId(rs.getLong("ID"));
                        setting.setSettingName(rs.getString("SETTING_NAME"));
                        setting.setHtmlElement(rs.getString("HTML_ELEMENT"));
                        setting.setCallFunction(rs.getString("CALL_FUNCTION"));
                        setting.setUserSettingId(rs.getLong("USER_SETTING_ID"));
                        setting.setHtmlElementId(rs.getString("HTML_ELEMENT_ID"));
                        setting.setHtmlElementType(rs.getString("HTML_ELEMENT_TYPE"));
                        setting.setSettingValue(rs.getString("SETTING_VALUE"));
                        settingList.add(setting);
                    }
                    user.setSettingList(settingList);
                    rs = (ResultSet)call.getObject(5);
                    List<Modul> modulList = new ArrayList<Modul>();
                    while (rs.next()){
                        Modul modul = new Modul();
                        modul.setId(rs.getLong("ID"));
                        modul.setModul_link_id(rs.getString("MODUL_LINK_ID"));
                        modul.setModul_name(rs.getString("MODUL_NAME"));
                        modulList.add(modul);
                    }
                    user.setModulList(modulList);
                    Organization org = null;
                    List<Organization> orgList = new ArrayList<Organization>();
                    /*rs = (ResultSet) call.getObject(7);
                    while (rs.next()){
                        org = new Organization();
                        org.setId(rs.getLong("ID"));
                        OrgName orgName = new OrgName();
                        orgName.setName(rs.getString("NAME"));
                        org.setOrgName(orgName);
                        orgList.add(org);
                    }*/
                    user.setOrgList(orgList);

                    //System.out.println(modulList.toString());
                    Map<String, List<Operation>> privilegieList = new HashMap<String, List<Operation>>();
                    for (Modul modul : user.getModulList()) {
                        call = c.prepareCall(sql_1);
                        call.setLong(1, user.getId());
                        call.setString(2, modul.getModul_link_id());
                        call.registerOutParameter(3, OracleTypes.CURSOR);
                        call.execute();
                        rs = (ResultSet) call.getObject(3);
                        List<Operation> operationList = new ArrayList<Operation>();
                        while (rs.next()) {
                            Operation operation = new Operation();
                            operation.setId(rs.getLong("OPERATION_ID"));
                            operation.setOperationName(rs.getString("OPER_NAME"));
                            operation.setOperationButtonId(rs.getString("BUTTON_ID"));
                            operation.setModulOperationId(rs.getLong("MOD_OPER_ID"));
                            /*operation.setOperationButtonBackImage(rs.getString("BUTTON_BACK_IMAGE"));*/
                            Dictionary dictionary = new Dictionary();
                            dictionary.setId(rs.getLong("OPER_TYPE"));
                            operation.setOperType(dictionary);
                            operation.setOperationButtonHint(rs.getString("OPER_NAME"));
                            operation.setOperationButtonClickFunc(rs.getString("BUTTON_CLICK_FUNC"));
                            operationList.add(operation);
                        }
                        privilegieList.put(modul.getModul_link_id(),operationList);
                    }
                    user.setPrivilegieList(privilegieList);
                }else if(login == 0){
                    user.setId(-1L);
                    System.out.println("Access Denied !!");
                }
            }else {
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return user;
    }

    @Override
    public Map<String, List<Operation> > getModulOperationMap() throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        Map<String, List<Operation>> privilegieList = new HashMap<String, List<Operation>>();
        String sql="{call ASK_USER.PKG_SECURITY.GET_MODUL(?)}";
        String sql_1="{call ASK_USER.PKG_SECURITY.GET_MODUL_OPER(?,?)}";
        try{
            c =  DBUtility.getConnection();
            if(c != null){
                call = c.prepareCall(sql);
                call.registerOutParameter(1,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(1);
                List<Modul> modulList = new ArrayList<Modul>();
                while (rs.next()){
                    Modul modul = new Modul();
                    modul.setId(rs.getLong("MODUL_ID"));
                    modul.setModul_link_id(rs.getString("MODUL_LINK_ID"));
                    modul.setModul_name(rs.getString("MODUL_NAME"));
                    modulList.add(modul);
                }
                for (Modul modul : modulList) {
                    call = c.prepareCall(sql_1);
                    call.setString(1, modul.getModul_link_id());
                    call.registerOutParameter(2, OracleTypes.CURSOR);
                    call.execute();
                    rs = (ResultSet) call.getObject(2);
                    List<Operation> operationList = new ArrayList<Operation>();
                    while (rs.next()){
                        Operation operation = new Operation();
                        operation.setId(rs.getLong("OPERATION_ID"));
                        operation.setOperationName(rs.getString("OPER_NAME"));
                        operation.setOperationButtonId(rs.getString("BUTTON_ID"));
                        operation.setModulOperationId(rs.getLong("MOD_OPER_ID"));
                        operation.setOperationButtonHint(rs.getString("BUTTON_HINT"));
                        operation.setOperationButtonClickFunc(rs.getString("CLICK_FUNCTION"));
                        operationList.add(operation);
                    }
                    privilegieList.put(modul.getModul_link_id(),operationList);
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return privilegieList;
    }

    @Override
    public List<Modul> getModulList() throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql="{call ASK_USER.PKG_SECURITY.GET_MODUL(?)}";
        List<Modul> modulList = new ArrayList<Modul>();
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.registerOutParameter(1,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(1);
                while (rs.next()){
                    Modul modul = new Modul();
                    modul.setId(rs.getLong("MODUL_ID"));
                    modul.setModul_link_id(rs.getString("MODUL_LINK_ID"));
                    modul.setModul_name(rs.getString("MODUL_NAME"));
                    modulList.add(modul);
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return modulList;
    }

    @Override
    public List<Operation> getOperationList() throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call ASK_USER.PKG_SECURITY.GET_OPERATION(?)}";
        List<Operation> operationList = new ArrayList<Operation>();
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.registerOutParameter(1,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(1);
                while (rs.next()){
                    Operation operation = new Operation();
                    operation.setId(rs.getLong("OPERATION_ID"));
                    operation.setOperationName(rs.getString("OPER_NAME"));
                    operation.setOperationButtonId(rs.getString("BUTTON_ID"));
                    //operation.setOperationButtonBackImage(rs.getString("BUTTON_BACK_IMAGE"));
                    operationList.add(operation);
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return operationList;
    }

    @Override
    public List<Operation> getPersonPrivilagies(long userId) throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.GET_USER_PRIVILEGIES(?,?)}";
        List<Operation> operationList = new ArrayList<Operation>();
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, userId);
                call.registerOutParameter(2,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(2);
                while (rs.next()){
                    Operation operation = new Operation();
                    operation.setModulOperationId(rs.getLong("ID"));
                    operationList.add(operation);
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return operationList;
    }

    @Override
    public long checkUserName(long userId, String userName) throws Exception {
        long status = 0;
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.CHECK_USER_NAME(?,?,?)}";
        List<Operation> operationList = new ArrayList<Operation>();
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, userId);
                call.setString(2, userName);
                call.registerOutParameter(3, OracleTypes.DECIMAL);
                call.execute();
                BigDecimal statusObject = (BigDecimal)call.getObject(3);
                status = Long.parseLong(statusObject.toString());
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return status;
    }

    @Override
    public long securityOperation(User user) throws Exception {
        System.out.println("ORACLE COMMON DAO YA GELDIK");
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.INSERT_USER_PRIVILAGIES(?,?,?,?,?)}";
        String sql1 = "{call LIB_COMMON.PKG_SECURITY.UPDATE_USER_PRIVILAGIES(?,?,?,?,?)}";
        long status = -1;
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                if(user.getUserId().equals("-1")){
                    cs = c.prepareCall(sql);
                    cs.setLong(1, user.getId());
                    cs.setString(2, user.getUserName());
                    cs.setString(3, user.getPassword());
                    cs.setString(4, user.getUserPrivilagies());
                    cs.registerOutParameter(5, OracleTypes.DECIMAL);
                    cs.execute();
                    BigDecimal statusObject = (BigDecimal)cs.getObject(5);
                    status = Long.parseLong(statusObject.toString());
                    if(status==1){
                        c.commit();
                    } else {
                        c.rollback();
                    }
                } else {
                    cs = c.prepareCall(sql1);
                    cs.setLong(1, Long.parseLong(user.getUserId()));
                    cs.setString(2, user.getUserName());
                    cs.setString(3, user.getPassword());
                    cs.setString(4, user.getUserPrivilagies());
                    cs.registerOutParameter(5, OracleTypes.DECIMAL);
                    cs.execute();
                    BigDecimal statusObject = (BigDecimal)cs.getObject(5);
                    status = Long.parseLong(statusObject.toString());
                    if(status==1){
                        c.commit();
                    } else {
                        c.rollback();
                    }
                }
            }
        } catch (Exception ex) {
            c.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            JdbcUtility.close(null, cs, c);
        }
        System.out.println("ORACLE COMMON DAO DAN CIXDIQ");
        return status;
    }

    @Override
    public User getUserInfo(long userId) throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.get_user_info(?,?)}";
        User user = null;
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, userId);
                call.registerOutParameter(2,OracleTypes.CURSOR);
                call.execute();
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return user;
    }

    @Override
    public boolean changeUserPassword(long userId, String password) throws Exception {
        boolean success= false;
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.change_user_password(?,?,?)}";
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, userId);
                call.setString(2, password);
                call.registerOutParameter(3,OracleTypes.NUMBER);
                call.execute();
                BigDecimal updated = (BigDecimal)call.getObject(3);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    success = true;
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return success;
    }

    @Override
    public long userLogHistoryOperation(User user) throws Exception {
        long historyId = -1l;
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call ASK_USER.PKG_SECURITY.USER_LOG_OPERATION(?,?,?,?,?,?)}";
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, user.getUserHistoryId());
                call.setLong(2, user.getId());
                call.setString(3, "11/11/2011");
                call.setString(4, user.getRemoteAddress());
                call.setString(5, user.getLogHistoryStatus());
                call.registerOutParameter(6,OracleTypes.NUMBER);
                call.execute();
                BigDecimal updated = (BigDecimal)call.getObject(6);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    historyId = Long.parseLong(updated.toString());
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return historyId;
    }

    @Override
    public long insertRole(Rolls rolls) throws Exception {
        long status=-1;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.INSERT_ROLLS(?,?,?,?,?)}";
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, rolls.getUserId());
                cs.setLong(2, rolls.getOrgId());
                cs.setLong(3,rolls.getPositionId());
                cs.setString(4, rolls.getModOperIdStr());
                cs.registerOutParameter(5,OracleTypes.DECIMAL);
                cs.execute();
                BigDecimal inserted=(BigDecimal)cs.getObject(5);
                if(Long.parseLong(inserted.toString())!=-1){
                    c.commit();
                    status=Long.parseLong(inserted.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(null,cs,c);
        }
        return status;
    }

    @Override
    public List<Long> getTemplateModulOperationIdList(long rollId) throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.GET_TEMP_MOD_OPER_LIST(?,?)}";
        List<Long> moIdList = new ArrayList<Long>();
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, rollId);
                call.registerOutParameter(2,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(2);
                while (rs.next()){
                    moIdList.add(rs.getLong("ID"));
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return moIdList;
    }

    @Override
    public List<Rolls> getTemplateRollList(long orgId) throws Exception {
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.get_template_roll_list(?,?)}";
        List<Rolls> rollList = new ArrayList<Rolls>();
        Rolls roll = null;
        try{
            c = DBUtility.getConnection();
            if (c!= null){
                call = c.prepareCall(sql);
                call.setLong(1, orgId);
                call.registerOutParameter(2,OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(2);
                while (rs.next()){
                    roll = new Rolls();
                    roll.setId(rs.getLong("ROLL_ID"));
                    roll.setPositionName(rs.getString("POSITION_NAME"));
                    roll.setPositionId(rs.getLong("POSITION_ID"));
                    rollList.add(roll);
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return rollList;
    }

    @Override
    public long settingOperation(long userSettingId, String customValue) throws Exception {
        long status=-1;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.SETTING_OPERATION(?,?,?)}";
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, userSettingId);
                cs.setString(2, customValue);
                cs.registerOutParameter(3,OracleTypes.DECIMAL);
                cs.execute();
                BigDecimal updated=(BigDecimal)cs.getObject(3);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    status=Long.parseLong(updated.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(null,cs,c);
        }
        return status;
    }

    @Override
    public long updateUserPrimaryEmail(long userId, String email) throws Exception {
        long status=-1;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.UPDATE_USER_PRIMARY_EMAIL(?,?,?)}";
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, userId);
                cs.setString(2, email);
                cs.registerOutParameter(3,OracleTypes.DECIMAL);
                cs.execute();
                BigDecimal updated=(BigDecimal)cs.getObject(3);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    status=Long.parseLong(updated.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(null,cs,c);
        }
        return status;
    }

    @Override
    public User getUserForEmail(String email) throws Exception {
        User user = new User();
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql= "{call LIB_COMMON.PKG_SECURITY.GET_USER_BY_EMAIL(?,?,?,?)}";
        try{
            c =  DBUtility.getConnection();
            if(c!=null){
                call = c.prepareCall(sql);
                call.setString(1, email);
                call.registerOutParameter(2,OracleTypes.CURSOR);
                call.registerOutParameter(3, OracleTypes.DECIMAL);
                call.registerOutParameter(4, OracleTypes.DECIMAL);
                call.execute();
                /*BigDecimal mainLibraryId = (BigDecimal)call.getObject(3);
                if(mainLibraryId!=null){
                    user.setMainLibraryId(Long.parseLong(mainLibraryId.toString()));
                }


                BigDecimal mainTypeId = (BigDecimal)call.getObject(4);
                if(mainTypeId!=null){
                    user.setMainTypeId(Long.parseLong(mainTypeId.toString()));
                }*/
                rs = (ResultSet) call.getObject(2);
                if (rs.next()){
                    user.setId(rs.getLong("ID"));
                    user.setFirstName(rs.getString("FIRST_NAME"));
                    user.setLastName(rs.getString("LAST_NAME"));
                    user.setMiddleName(rs.getString("FATHER_NAME"));
                    user.setUserName(rs.getString("USER_NAME"));
                    user.setTempPassword(rs.getLong("IS_TEMPORARY"));
                    user.setPersonId(rs.getLong("PERSON_ID"));
                    user.setUserEmail(rs.getString("USER_EMAIL"));
                    user.setMd5Password(rs.getString("PASSWORD"));
                    user.setSex(rs.getLong("GENDER_ID"));
                    user.setSuccessRandomText(rs.getString("RANDOM_TEXT"));
                }
            }else {
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            JdbcUtility.close(rs, call, c);
        }
        return user;
    }

    @Override
    public long updateUserRandomText(long userId, String randomText) throws Exception {
        long status=-1;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.UPDATE_USER_RANDOM_TEXT(?,?,?)}";
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, userId);
                cs.setString(2, randomText);
                cs.registerOutParameter(3,OracleTypes.DECIMAL);
                cs.execute();
                BigDecimal updated=(BigDecimal)cs.getObject(3);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    status=Long.parseLong(updated.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(null,cs,c);
        }
        return status;
    }

    @Override
    public long userBlockOperation(String userIdStr) throws Exception {
        long status=-1;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call LIB_COMMON.PKG_SECURITY.user_block_operation(?,?)}";
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, userIdStr);
                cs.registerOutParameter(2,OracleTypes.DECIMAL);
                cs.execute();
                BigDecimal updated=(BigDecimal)cs.getObject(2);
                if(Long.parseLong(updated.toString())!=-1){
                    c.commit();
                    status=Long.parseLong(updated.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(null,cs,c);
        }
        return status;
    }

    @Override
    public Result<UserHistory> getUserHistoryByUserId(long userId, PagingObject pagingObject) throws Exception {
        Result<UserHistory> userHistoryResult = new Result<UserHistory>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String sql = "{call lib_common.pkg_security.get_user_history_by_user_id(?,?,?,?,?)}";
        try {
            connection = DBUtility.getConnection();
            callableStatement = connection.prepareCall(sql);
            callableStatement.setLong(1, userId);
            callableStatement.setInt(2, pagingObject.getPageNumber());
            callableStatement.setInt(3, pagingObject.getPageSize());
            callableStatement.registerOutParameter(4, OracleTypes.NUMBER);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            userHistoryResult.setPageNumber(pagingObject.getPageNumber());
            userHistoryResult.setRecordCount(callableStatement.getInt(4));
            resultSet = (ResultSet) callableStatement.getObject(5);
            UserHistory userHistory = null;
            while (resultSet.next()) {
                userHistory = new UserHistory();
                userHistory.setId(resultSet.getLong("id"));
                userHistory.setLoginDate(resultSet.getString("LOGIN_DATE"));
                userHistory.setLogoutDate(resultSet.getString("LOGOUT_DATE"));
                userHistory.setTimePeriod(resultSet.getString("TIME_PERIOD"));
                userHistory.setLoginIP(resultSet.getString("LOGIN_IP"));
                userHistoryResult.add(userHistory);
            }

            userHistoryResult.setTotalPageCount(userHistoryResult.getRecordCount() / pagingObject.getPageSize());

            if (userHistoryResult.getRecordCount() % pagingObject.getPageSize() > 0) {
                userHistoryResult.setTotalPageCount(1 + userHistoryResult.getTotalPageCount());
            }

            if (userHistoryResult.getTotalPageCount() == 0) {
                userHistoryResult.setTotalPageCount(1);
            }

            if (pagingObject.getPageNumber() > userHistoryResult.getTotalPageCount()) {
                pagingObject.setPageNumber(userHistoryResult.getTotalPageCount());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(resultSet, callableStatement, connection);
        }
        return userHistoryResult;
    }
}
