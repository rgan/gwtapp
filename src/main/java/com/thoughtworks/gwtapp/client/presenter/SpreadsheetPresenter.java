package com.thoughtworks.gwtapp.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.service.SpreadsheetDataService;

import java.util.List;

public class SpreadsheetPresenter implements SpreadsheetUiHandler {

    private View view;
    private SpreadsheetDataService service;
    private Spreadsheet spreadsheet;
    private int totalRows;

    @Inject
    public SpreadsheetPresenter(View view, SpreadsheetDataService service) {
        this.view = view;
        this.service = service;
        view.setUiHandler(this);
    }

    public View getView() {
        return view;
    }

    public void go() {
        spreadsheet = new Spreadsheet(service.columns());
        totalRows = service.totalRows();
        render();
    }

    @Override
    public void onCellRightClick(SpreadsheetCell cell, int clientX, int clientY) {
        view.showContextMenu(cell, clientX, clientY);
    }

    @Override
    public void hideColumn(SpreadsheetColumn column) {
        GWT.log("Hide column");
        spreadsheet.hideColumn(column);
        render();
    }

    @Override
    public void unHideAllColumns() {
        spreadsheet.unHideAllColumns();
        render();
    }

    private void render() {
        view.render(spreadsheet.columns(), totalRows);
    }

    public interface View extends IsWidget {

        void render(List<SpreadsheetColumn> columns, int total);

        void showContextMenu(SpreadsheetCell cell, int clientX, int clientY);

        void setUiHandler(SpreadsheetUiHandler uiHandler);
    }
}
