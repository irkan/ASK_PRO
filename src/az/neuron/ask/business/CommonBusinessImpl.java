package az.neuron.ask.business;

import az.neuron.ask.dao.CommonDao;

import az.neuron.ask.domain.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommonBusinessImpl implements CommonBusiness {
    private CommonDao commonDao;

    public CommonBusinessImpl(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public Map<Long, List<Dictionary>> getDictionaryMap() throws Exception {
        return commonDao.getDictionaryMap();
    }
}
