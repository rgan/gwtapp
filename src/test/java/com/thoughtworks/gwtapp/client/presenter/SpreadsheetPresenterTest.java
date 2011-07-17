package com.thoughtworks.gwtapp.client.presenter;

import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.service.SpreadsheetDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpreadsheetPresenterTest {

    @Mock
    private SpreadsheetPresenter.View view;
    private SpreadsheetPresenter presenter;
    private Spreadsheet model;
    private SpreadsheetColumn column;
    @Mock
    private SpreadsheetDataService service;
    private int total=100;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new SpreadsheetPresenter(view, service);
        model = new Spreadsheet();
        column = model.addColumn("name", SpreadsheetColumn.Type.TEXT);
        List<SpreadsheetColumn> columns = new ArrayList<SpreadsheetColumn>();
        columns.add(column);
        when(service.columns()).thenReturn(columns);
        when(service.totalRows()).thenReturn(total);
    }

    @Test
    public void shouldSetUiHandlerOnView() {
        verify(view).setUiHandler(presenter);
    }

    @Test
    public void shouldInvokeRenderOnViewOnGo() {
        presenter.go();

        verify(view).render(model.columns(), total);
    }

    @Test
    public void shouldInvokeShowContextMenuOnCellRightClick() {
        SpreadsheetCell cell = new SpreadsheetCell(column, "value");
        presenter.onCellRightClick(cell, 10,10);
        verify(view).showContextMenu(cell, 10, 10);
    }

    @Test
    public void shouldHideColumnAndRender() {
        presenter.go();
        presenter.hideColumn(column);
        assertFalse(column.isVisible());
        verify(view, times(2)).render(model.columns(), total);
    }

    @Test
    public void shouldUnHideColumnsAndRender() {
        presenter.go();
        presenter.unHideAllColumns();

        assertTrue(column.isVisible());
        verify(view, times(2)).render(model.columns(), total);
    }
}
