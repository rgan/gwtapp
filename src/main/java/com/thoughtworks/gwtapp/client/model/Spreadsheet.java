package com.thoughtworks.gwtapp.client.model;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
    private List<SpreadsheetColumn> columns;
    private List<SpreadSheetRow> rows;

    public Spreadsheet() {
        this.columns = new ArrayList<SpreadsheetColumn>();
        rows = new ArrayList<SpreadSheetRow>();
    }

    public List<SpreadSheetRow> rows() {
        return rows;
    }

    public List<SpreadsheetColumn> columns() {
        return columns;
    }

    public SpreadsheetColumn addColumn(String name, SpreadsheetColumn.Type type) {
        SpreadsheetColumn spreadsheetColumn = new SpreadsheetColumn(name, type);
        columns.add(spreadsheetColumn);
        return spreadsheetColumn;
    }

    public void addRow(SpreadSheetRow row) {
        rows.add(row);
    }

    public void unHideAllColumns() {
        for (SpreadsheetColumn column : columns) {
            column.unHide();
        }
    }
}
