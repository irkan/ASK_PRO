package az.neuron.ask.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan Ahmadov
 * Date: 3/23/12
 * Time: 8:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagingObject {
    private int pageNumber;
    private int pageSize;
    private String sortIndex;
    private String sortOrder;
    private String searchParam;
    private long g_orgId;
    private long g_type;

    public long getG_orgId() {
        return g_orgId;
    }

    public void setG_orgId(long g_orgId) {
        this.g_orgId = g_orgId;
    }

    public long getG_type() {
        return g_type;
    }

    public void setG_type(long g_type) {
        this.g_type = g_type;
    }

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "PagingObject{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sortIndex='" + sortIndex + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
