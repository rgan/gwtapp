package com.thoughtworks.gwtapp.client.presenter;

import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SpreadsheetPresenterTest {

    @Mock
    private SpreadsheetPresenter.View view;
    private SpreadsheetPresenter presenter;
    private Spreadsheet model;
    private SpreadsheetColumn column;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new SpreadsheetPresenter(view);
        model = new Spreadsheet();
        column = model.addColumn("name", SpreadsheetColumn.Type.TEXT);
    }

    @Test
    public void shouldSetUiHandlerOnView() {
        verify(view).setUiHandler(presenter);
    }

    @Test
    public void shouldInvokeRenderOnViewOnGo() {
        presenter.go(model);

        verify(view).render(model.rows(), model.columns());
    }

    @Test
    public void shouldInvokeShowContextMenuOnCellRightClick() {
        SpreadsheetCell cell = new SpreadsheetCell(column, "value");
        presenter.onCellRightClick(cell, 10,10);
        verify(view).showContextMenu(cell, 10, 10);
    }

    @Test
    public void shouldHideColumnAndRender() {
        presenter.go(model);
        presenter.hideColumn(column);
        assertFalse(column.isVisible());
        verify(view, times(2)).render(model.rows(), model.columns());
    }

    @Test
    public void shouldUnHideColumnsAndRender() {
        presenter.go(model);
        presenter.unHideAllColumns();

        assertTrue(column.isVisible());
        verify(view, times(2)).render(model.rows(), model.columns());
    }
}
