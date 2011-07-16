package com.thoughtworks.gwtapp.client.model;

import com.google.gwt.view.client.ProvidesKey;

import java.util.HashMap;
import java.util.Map;

public class SpreadSheetRow {

    private Map<String, SpreadsheetCell> columnMap;
    private Integer id;

    public SpreadSheetRow(Integer id) {
        this.id = id;
        columnMap = new HashMap<String, SpreadsheetCell>();
    }

    public SpreadsheetCell getColumnValue(SpreadsheetColumn column) {
        return columnMap.get(column.getName());
    }

    public void setColumnValue(SpreadsheetColumn column, String value) {
        columnMap.put(column.getName(), new SpreadsheetCell(column, value));
    }

    public Integer getId() {
        return id;
    }
}
