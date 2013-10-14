package az.neuron.ask.dao;

import az.neuron.ask.domain.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonDao {
    public Map<Long, List<Dictionary>> getDictionaryMap() throws Exception;
}
