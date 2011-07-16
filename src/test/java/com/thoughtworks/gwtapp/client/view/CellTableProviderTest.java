package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.cellview.client.CellTable;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

public class CellTableProviderTest {

    @Mock
    private CellTable cellTable;

    @Before
    public void setup() {
        GWTMockUtilities.disarm();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddColumnsToCellTable() {
        CellTableProvider cellTableProvider = new CellTableProvider(cellTable);
        Spreadsheet spreadSheet = new Spreadsheet();
        spreadSheet.addColumn("name", SpreadsheetColumn.Type.TEXT);
        cellTableProvider.create(spreadSheet.columns());
        verify(cellTable).addColumn(isA(SortableTextColumn.class), eq("name"));
    }
}
