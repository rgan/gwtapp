package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;

public class CellTableProvider {

    private CellTable cellTable;

    @Inject
    public CellTableProvider(CellTable cellTable) {

        this.cellTable = cellTable;
    }


    public CellTable<SpreadSheetRow> create(Spreadsheet spreadsheet) {

        for (final SpreadsheetColumn column : spreadsheet.columns()) {
            SortableTextColumn<SpreadSheetRow> sortableTextColumn = new SortableTextColumn<SpreadSheetRow>(new TextInputCell()) {
                @Override
                public String getValue(SpreadSheetRow row) {
                    return row.getColumnValue(column);
                }
            };
            cellTable.addColumn(sortableTextColumn, column.getName());
        }

        return cellTable;
    }
}
