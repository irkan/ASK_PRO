package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/10/13
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Operation extends Base {
    private Dictionary operType;
    private String operationName;
    private String operationButtonId;
    private String operationButtonBackImage;
    private String operationButtonHint;
    private String operationButtonClickFunc;
    private Modul modul;
    private long modulOperationId;

    public long getModulOperationId() {
        return modulOperationId;
    }

    public void setModulOperationId(long modulOperationId) {
        this.modulOperationId = modulOperationId;
    }

    public String getOperationButtonClickFunc() {
        return operationButtonClickFunc;
    }

    public void setOperationButtonClickFunc(String operationButtonClickFunc) {
        this.operationButtonClickFunc = operationButtonClickFunc;
    }

    public String getOperationButtonHint() {
        return operationButtonHint;
    }

    public void setOperationButtonHint(String operationButtonHint) {
        this.operationButtonHint = operationButtonHint;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationButtonId() {
        return operationButtonId;
    }

    public void setOperationButtonId(String operationButtonId) {
        this.operationButtonId = operationButtonId;
    }

    public String getOperationButtonBackImage() {
        return operationButtonBackImage;
    }

    public void setOperationButtonBackImage(String operationButtonBackImage) {
        this.operationButtonBackImage = operationButtonBackImage;
    }

    public Modul getModul() {
        return modul;
    }

    public void setModul(Modul modul) {
        this.modul = modul;
    }

    public Dictionary getOperType() {
        return operType;
    }

    public void setOperType(Dictionary operType) {
        this.operType = operType;
    }
}
