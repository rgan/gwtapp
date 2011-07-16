package com.thoughtworks.gwtapp.client.view;

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
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;

import java.util.List;

public class SpreadsheetView extends Composite implements SpreadsheetPresenter.View {
    private static final double WIDTH = 10.0;
    @UiField
    HTMLPanel main;
    private CellTableProvider cellTableProvider;
    private SimplePager.Resources pagerResources;

    public interface Binder extends UiBinder<HTMLPanel, SpreadsheetView> {
    }

    @Inject
    public SpreadsheetView(Binder binder, CellTableProvider cellTableProvider, SimplePager.Resources pagerResources) {
        this.cellTableProvider = cellTableProvider;
        this.pagerResources = pagerResources;
        initWidget(binder.createAndBindUi(this));
    }

    public void render(List<SpreadSheetRow> rows, List<SpreadsheetColumn> columns) {
        main.clear();
        CellTable<SpreadSheetRow> cellTable = cellTableProvider.create(columns);

        cellTable.setWidth("auto", true);
        for (int i = 0; i < cellTable.getColumnCount(); i++) {
            cellTable.setColumnWidth(cellTable.getColumn(i), WIDTH, Style.Unit.PX);
        }

        SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(cellTable);

        ListDataProvider<SpreadSheetRow> dataProvider = new ListDataProvider<SpreadSheetRow>(rows);
        dataProvider.addDataDisplay(cellTable);

        main.add(cellTable);
        main.add(pager);
    }

}