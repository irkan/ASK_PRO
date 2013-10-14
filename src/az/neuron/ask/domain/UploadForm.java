package az.neuron.ask.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan Ahmadov
 * Date: 4/8/12
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadForm {
    private File file;
    private Map<String, String> parameterMap;

    public UploadForm() {
        parameterMap = new HashMap<String, String>();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void put(String paramName, String paramValue) {
        parameterMap.put(paramName, paramValue);
    }
    
    public String get(String paramName) {
        return parameterMap.get(paramName);
    }
}
