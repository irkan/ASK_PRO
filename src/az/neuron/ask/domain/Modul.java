package az.neuron.ask.domain;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 1/10/13
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Modul extends Base {
    private String modul_name;
    private String modul_link_id;
    private String modul_view_address;
    private int modulGroup;
    private long modulLinkNumber;

    public long getModulLinkNumber() {
        return modulLinkNumber;
    }

    public void setModulLinkNumber(long modulLinkNumber) {
        this.modulLinkNumber = modulLinkNumber;
    }

    public int getModulGroup() {
        return modulGroup;
    }

    public void setModulGroup(int modulGroup) {
        this.modulGroup = modulGroup;
    }

    public String getModul_view_address() {
        return modul_view_address;
    }

    public void setModul_view_address(String modul_view_address) {
        this.modul_view_address = modul_view_address;
    }

    public String getModul_link_id() {
        return modul_link_id;
    }

    public void setModul_link_id(String modul_link_id) {
        this.modul_link_id = modul_link_id;
    }

    public String getModul_name() {
        return modul_name;
    }

    public void setModul_name(String modul_name) {
        this.modul_name = modul_name;
    }
}
