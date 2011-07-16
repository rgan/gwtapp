package com.thoughtworks.gwtapp.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;

import java.util.List;

public class SpreadsheetPresenter implements SpreadsheetUiHandler {

    private View view;
    private Spreadsheet spreadsheet;

    @Inject
    public SpreadsheetPresenter(View view) {
        this.view = view;
        view.setUiHandler(this);
    }

    public View getView() {
        return view;
    }

    public void go(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
        render();
    }

    @Override
    public void onCellRightClick(SpreadsheetCell cell, int clientX, int clientY) {
        view.showContextMenu(cell, clientX, clientY);
    }

    @Override
    public void hideColumn(SpreadsheetColumn column) {
        column.hide();
        render();
    }

    @Override
    public void unHideAllColumns() {
        spreadsheet.unHideAllColumns();
        render();
    }

    private void render() {
        view.render(spreadsheet.rows(), spreadsheet.columns());
    }

    public interface View extends IsWidget {

        void render(List<SpreadSheetRow> rows, List<SpreadsheetColumn> columns);

        void showContextMenu(SpreadsheetCell cell, int clientX, int clientY);

        void setUiHandler(SpreadsheetUiHandler uiHandler);
    }
}
