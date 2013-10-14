package az.neuron.ask.dao;

import az.neuron.ask.util.DBUtility;
import az.neuron.ask.util.JdbcUtility;
import az.neuron.ask.domain.Dictionary;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import oracle.jdbc.OracleTypes;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class OracleCommonDao implements CommonDao {
    @Override
    public Map<Long, List<Dictionary>> getDictionaryMap() throws Exception {
        Map<Long, List<Dictionary>> dictionaryMap = new HashMap<Long, List<Dictionary>>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = SqlCall.get_dictionary_type_list;
        try {
            c = DBUtility.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR );
                cs.execute();
                rs=(ResultSet)cs.getObject(1);
                while (rs.next()) {
                    Long dictionaryTypeId = rs.getLong("DICTIONARY_TYPE_ID");
                    List<Dictionary> typeDetailList = OracleCommonDao.getDictionaryList(dictionaryTypeId, c);
                    dictionaryMap.put(dictionaryTypeId , typeDetailList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtility.close(rs, cs, c);
        }
        return dictionaryMap;
    }

    public static List<Dictionary> getDictionaryList(long typeId, Connection c) throws Exception {
        List<Dictionary> list = new ArrayList<Dictionary>();
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = SqlCall.get_dictionary_list;
        Dictionary dic = null;
        try {
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, typeId);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs=(ResultSet)cs.getObject(2);
                while (rs.next()) {
                    dic = new Dictionary();
                    dic.setId(rs.getLong("ID"));
                    dic.setName(rs.getString("NAME"));
                    dic.setShortName(rs.getString("SHORT_NAME"));
                    list.add(dic);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            JdbcUtility.close(rs, cs, null);
        }
        return list;
    }
}
