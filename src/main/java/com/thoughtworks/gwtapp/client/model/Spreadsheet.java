package com.thoughtworks.gwtapp.client.model;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
    private List<SpreadsheetColumn> columns;
    private List<SpreadSheetRow> rows;

    public Spreadsheet() {
        this(new ArrayList<SpreadsheetColumn>());
    }

    public Spreadsheet(List<SpreadsheetColumn> columns) {
        this.columns = columns;
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

    public void hideColumn(SpreadsheetColumn column) {
        for (SpreadsheetColumn spreadsheetColumn : columns) {
            if (spreadsheetColumn.equals(column)) {
                spreadsheetColumn.hide();
            }
        }
    }
}
