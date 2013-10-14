package az.neuron.ask.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan Ahmadov
 * Date: 3/22/12
 * Time: 2:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Result<T> {
    private List<T> rows;
    private int pageNumber;
    private int totalPageCount;
    private int recordCount;
    
    public Result() {
        rows = new ArrayList<T>();
    }

    public void add(T row) {
        rows.add(row);
    }
    public int getRecordCount() {
        return recordCount;

    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "rows=" + rows +
                ", pageNumber=" + pageNumber +
                ", totalPageCount=" + totalPageCount +
                ", recordCount=" + recordCount +
                '}';
    }
}
