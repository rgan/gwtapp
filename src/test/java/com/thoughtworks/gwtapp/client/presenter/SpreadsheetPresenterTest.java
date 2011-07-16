package com.thoughtworks.gwtapp.client.presenter;

import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class SpreadsheetPresenterTest {

    @Mock
    private SpreadsheetPresenter.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInvokeRenderOnViewOnGo() {
        SpreadsheetPresenter presenter = new SpreadsheetPresenter(view);
        Spreadsheet model = new Spreadsheet();
        presenter.go(model);

        verify(view).render(model);
    }
}
