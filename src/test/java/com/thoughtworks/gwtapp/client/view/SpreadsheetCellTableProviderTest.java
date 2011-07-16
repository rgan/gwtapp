package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.cellview.client.CellTable;
import com.thoughtworks.gwtapp.client.ioc.CellTableProvider;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetUiHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpreadsheetCellTableProviderTest {

    @Mock
    private CellTableProvider cellTableProvider;
    @Mock
    private SpreadsheetUiHandler uiHandler;
    @Mock
    private CellTable cellTable;

    @Before
    public void setup() {
        GWTMockUtilities.disarm();
        MockitoAnnotations.initMocks(this);
        when(cellTableProvider.get()).thenReturn(cellTable);
    }

    @Test
    public void shouldAddColumnsToCellTable() {
        SpreadsheetCellTableProvider spreadsheetCellTableProvider = new SpreadsheetCellTableProvider(cellTableProvider);
        Spreadsheet spreadSheet = new Spreadsheet();
        spreadSheet.addColumn("name", SpreadsheetColumn.Type.TEXT);
        spreadsheetCellTableProvider.create(spreadSheet.columns(), uiHandler);
        verify(cellTable).addColumn(isA(SortableTextColumn.class), eq("name"));
    }

    @Test
    public void shouldNotAddHiddenColumnsToCellTable() {
        SpreadsheetCellTableProvider spreadsheetCellTableProvider = new SpreadsheetCellTableProvider(cellTableProvider);
        Spreadsheet spreadSheet = new Spreadsheet();
        SpreadsheetColumn column = spreadSheet.addColumn("hidden", SpreadsheetColumn.Type.TEXT);
        column.hide();
        spreadsheetCellTableProvider.create(spreadSheet.columns(), uiHandler);
        verify(cellTable, never()).addColumn(isA(SortableTextColumn.class), eq("hidden"));
    }
}
