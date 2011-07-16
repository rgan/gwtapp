package com.thoughtworks.gwtapp.client.model;

import com.google.gwt.view.client.ProvidesKey;

import java.util.HashMap;
import java.util.Map;

public class SpreadSheetRow {

    private Map<String, String> columnMap;
    private Integer id;

    public static final ProvidesKey<SpreadSheetRow> KEY_PROVIDER = new ProvidesKey<SpreadSheetRow>() {
        public Object getKey(SpreadSheetRow item) {
            return item == null ? null : item.getId();
        }
    };

    public SpreadSheetRow(Integer id) {
        this.id = id;
        columnMap = new HashMap<String, String>();
    }

    public String getColumnValue(SpreadsheetColumn column) {
        return columnMap.get(column.getName());
    }

    public void setColumnValue(SpreadsheetColumn column, String value) {
        columnMap.put(column.getName(), value);
    }

    public Integer getId() {
        return id;
    }
}
