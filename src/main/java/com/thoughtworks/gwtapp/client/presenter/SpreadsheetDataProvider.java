package com.thoughtworks.gwtapp.client.presenter;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.service.SpreadsheetDataService;

import java.util.List;

public class SpreadsheetDataProvider extends AsyncDataProvider<SpreadSheetRow> {

    private SpreadsheetDataService service;

    @Inject
    public SpreadsheetDataProvider(SpreadsheetDataService service) {
        this.service = service;
    }

    @Override
    protected void onRangeChanged(HasData<SpreadSheetRow> display) {
        final int start = display.getVisibleRange().getStart();
        int length = display.getVisibleRange().getLength();
        service.getData(start, length, new AsyncCallback<List<SpreadSheetRow>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Failed to get data");
            }

            @Override
            public void onSuccess(List<SpreadSheetRow> result) {
                updateRowData(start, result);
            }
        });
    }
}
