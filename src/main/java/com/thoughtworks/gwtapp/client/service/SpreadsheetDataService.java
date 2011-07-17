package com.thoughtworks.gwtapp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;

import java.util.ArrayList;
import java.util.List;

public class SpreadsheetDataService {

    private List<SpreadsheetColumn> columns;
    private static final int TOTAL_ROWS = 100;
    private static final int NUM_COLS = 100;

    public SpreadsheetDataService() {
        columns = new ArrayList<SpreadsheetColumn>();
        for (int i = 0; i < NUM_COLS; i++) {
            columns.add(new SpreadsheetColumn("Col " + i, SpreadsheetColumn.Type.TEXT));
        }
    }

    // TODO: this should make a remote service call
    public void getData(int start, int length, AsyncCallback<List<SpreadSheetRow>> callback) {
        callback.onSuccess(getRows(start, length));
    }

    private List<SpreadSheetRow> getRows(int start, int noRows) {
        List<SpreadsheetColumn> columns = columns();
        List<SpreadSheetRow> rows = new ArrayList<SpreadSheetRow>();
        for (int i = start; i < start + noRows; i++) {
            SpreadSheetRow row = new SpreadSheetRow(i);
            for (int j = 0; j <columns.size() ; j++) {
                row.setColumnValue(columns.get(j), "Val:" + i + j);
            }
            rows.add(row);
        }
        return rows;
    }

    public List<SpreadsheetColumn> columns() {
        return columns;
    }

    public int totalRows() {
        return TOTAL_ROWS;
    }
}
