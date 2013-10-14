package az.neuron.ask.dao;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SqlCall {
    public static final String get_dictionary_type_list="{call ASK_USER.PKG_COMMON.get_dictionary_type_list(?)}";
    public static final String get_dictionary_list="{call ASK_USER.PKG_COMMON.get_dictionary_list(?,?)}";
}
