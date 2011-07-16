package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.ioc.CellTableProvider;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetUiHandler;

import java.util.List;

public class SpreadsheetCellTableProvider {

    private CellTableProvider cellTableProvider;

    @Inject
    public SpreadsheetCellTableProvider(CellTableProvider cellTableProvider) {

        this.cellTableProvider = cellTableProvider;
    }


    public CellTable<SpreadSheetRow> create(List<SpreadsheetColumn> columns, SpreadsheetUiHandler uiHandler) {
        CellTable cellTable = cellTableProvider.get();

        for (final SpreadsheetColumn column : columns) {
            if (column.isVisible()) {
                SortableTextColumn<SpreadSheetRow, SpreadsheetCell> sortableTextColumn = new SortableTextColumn<SpreadSheetRow, SpreadsheetCell>(new SpreadsheetCellView(uiHandler)) {
                    @Override
                    public SpreadsheetCell getValue(SpreadSheetRow row) {
                        return row.getColumnValue(column);
                    }
                };
                cellTable.addColumn(sortableTextColumn, column.getName());
            }
        }

        return cellTable;
    }
}
