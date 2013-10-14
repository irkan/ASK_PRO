package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 11/11/12
 * Time: 11:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary extends Base{
    protected String name;
    protected String shortName;
    protected DictionaryType dicType;
    protected String elementType;

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public DictionaryType getDicType() {
        return dicType;
    }

    public void setDicType(DictionaryType dicType) {
        this.dicType = dicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
