package com.thoughtworks.gwtapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.thoughtworks.gwtapp.client.ioc.DesktopInjector;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;

import java.util.ArrayList;
import java.util.List;

public class Main implements EntryPoint {

    private static final DesktopInjector injector = GWT.create(DesktopInjector.class);

    /**
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {

        Spreadsheet spreadsheet = createSpreadsheet(10, 10);

        SpreadsheetPresenter spreadsheetPresenter = injector.getSpreadsheetPresenter();
        spreadsheetPresenter.go(spreadsheet);

        RootPanel.get().add(spreadsheetPresenter.getView());
    }

    private Spreadsheet createSpreadsheet(int noColumns, int noRows) {
        Spreadsheet spreadsheet = new Spreadsheet();
        List<SpreadsheetColumn> columns = new ArrayList<SpreadsheetColumn>();
        for (int i = 0; i < noColumns; i++) {
            columns.add(spreadsheet.addColumn("Col " + i, SpreadsheetColumn.Type.TEXT));
        }
        for (int i = 0; i < noRows; i++) {
            SpreadSheetRow row = new SpreadSheetRow(i);
            for (int j = 0; j < noColumns; j++) {
                row.setColumnValue(columns.get(j), "Val:" + i+j);
            }
            spreadsheet.addRow(row);
        }
        return spreadsheet;
    }
}
