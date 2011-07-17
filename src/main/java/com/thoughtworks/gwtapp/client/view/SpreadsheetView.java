package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetDataProvider;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetUiHandler;

import java.util.List;

public class SpreadsheetView extends Composite implements SpreadsheetPresenter.View {
    private static final double WIDTH = 10.0;
    @UiField
    HTMLPanel main;
    private SpreadsheetCellTableProvider cellTableProvider;
    private SpreadsheetDataProvider dataProvider;
    private SpreadsheetUiHandler uiHandler;
    private SimplePager pager;

    public interface Binder extends UiBinder<HTMLPanel, SpreadsheetView> {
    }

    @Inject
    public SpreadsheetView(Binder binder, SpreadsheetCellTableProvider cellTableProvider,
                           SimplePager.Resources pagerResources, SpreadsheetDataProvider dataProvider) {
        this.cellTableProvider = cellTableProvider;
        this.dataProvider = dataProvider;
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        initWidget(binder.createAndBindUi(this));
    }

    public void render(List<SpreadsheetColumn> columns, int total) {
        int page = pager.getPage();
        main.clear();
        CellTable<SpreadSheetRow> cellTable = cellTableProvider.create(columns, uiHandler);

        cellTable.setWidth("auto", true);
        for (int i = 0; i < cellTable.getColumnCount(); i++) {
            cellTable.setColumnWidth(cellTable.getColumn(i), WIDTH, Style.Unit.PX);
        }

        pager.setDisplay(cellTable);
        pager.setPage(page == -1 ? 0 : page);

        dataProvider.addDataDisplay(cellTable);
        dataProvider.updateRowCount(total, true);

        main.add(cellTable);
        main.add(pager);
    }

    @Override
    public void showContextMenu(final SpreadsheetCell cell, final int clientX, final int clientY) {
        final PopupPanel popupPanel = new PopupPanel(true);
        final HTMLPanel panel = new HTMLPanel("");
        panel.add(createHideMenuItem(cell, popupPanel));
        panel.add(createUnHideMenuItem(popupPanel));
        popupPanel.add(panel);
        popupPanel.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            @Override
            public void setPosition(int offsetWidth, int offsetHeight) {
                popupPanel.setPopupPosition(clientX, clientY);
            }
        });
    }

    private Label createUnHideMenuItem(final PopupPanel popupPanel) {
        Label unHideMenuItem = new Label("Unhide All");
        unHideMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popupPanel.hide();
                uiHandler.unHideAllColumns();
            }
        });
        return unHideMenuItem;
    }

    private Label createHideMenuItem(final SpreadsheetCell cell, final PopupPanel popupPanel) {
        Label hideMenuItem = new Label("Hide this column");
        hideMenuItem.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popupPanel.hide();
                uiHandler.hideColumn(cell.getColumn());
            }
        });
        return hideMenuItem;
    }

    @Override
    public void setUiHandler(SpreadsheetUiHandler uiHandler) {
        this.uiHandler = uiHandler;
    }

}