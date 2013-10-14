package az.neuron.ask.domain.security;

import az.neuron.ask.domain.Base;

import java.util.List;


public class Grid extends Base {
    private List<Column> columnList ;

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
