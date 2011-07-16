package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;

public class SpreadsheetView extends Composite implements SpreadsheetPresenter.View {
    @UiField
    HTMLPanel main;

    public interface Binder extends UiBinder<HTMLPanel, SpreadsheetView> {
    }

    @Inject
    public SpreadsheetView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    public void render(Spreadsheet spreadsheet) {
        main.clear();
        CellTable<SpreadSheetRow> cellTable = new CellTable<SpreadSheetRow>(SpreadSheetRow.KEY_PROVIDER);

        for (final SpreadsheetColumn column : spreadsheet.columns()) {
            SortableTextColumn<SpreadSheetRow> sortableTextColumn = new SortableTextColumn<SpreadSheetRow>(new TextInputCell()) {
                @Override
                public String getValue(SpreadSheetRow row) {
                    return row.getColumnValue(column);
                }
            };
            cellTable.addColumn(sortableTextColumn, column.getName());
        }

        cellTable.setWidth("auto", true);
        for (int i = 0; i < spreadsheet.columns().size(); i++) {
            cellTable.setColumnWidth(cellTable.getColumn(i), 10.0, Style.Unit.PX);
        }

        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(cellTable);

        ListDataProvider<SpreadSheetRow> dataProvider = new ListDataProvider<SpreadSheetRow>(spreadsheet.rows());
        dataProvider.addDataDisplay(cellTable);
        main.add(cellTable);
        main.add(pager);
    }
}