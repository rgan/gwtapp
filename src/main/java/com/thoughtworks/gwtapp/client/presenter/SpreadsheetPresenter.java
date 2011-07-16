package com.thoughtworks.gwtapp.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;

import java.util.List;

public class SpreadsheetPresenter {

    private View view;

    @Inject
    public SpreadsheetPresenter(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void go(Spreadsheet spreadsheet) {
        view.render(spreadsheet.rows(), spreadsheet.columns());
    }

    public interface View extends IsWidget {

        void render(List<SpreadSheetRow> rows, List<SpreadsheetColumn> columns);
    }
}
