package com.thoughtworks.gwtapp.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.service.SpreadsheetDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpreadsheetDataProviderTest {

    @Mock
    private SpreadsheetDataService service;
    @Mock
    private HasData<SpreadSheetRow> display;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInvokeServiceOnRangeChanged() {
        SpreadsheetDataProvider dataProvider = new SpreadsheetDataProvider(service);
        Range range = new Range(0, 15);
        when(display.getVisibleRange()).thenReturn(range);
        dataProvider.onRangeChanged(display);

        verify(service).getData(eq(0), eq(15), isA(AsyncCallback.class));
    }
}
