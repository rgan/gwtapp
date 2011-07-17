package com.thoughtworks.gwtapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.thoughtworks.gwtapp.client.ioc.DesktopInjector;
import com.thoughtworks.gwtapp.client.model.SpreadSheetRow;
import com.thoughtworks.gwtapp.client.model.Spreadsheet;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;

import java.util.ArrayList;
import java.util.List;

public class Main implements EntryPoint {

    private static final DesktopInjector injector = GWT.create(DesktopInjector.class);

    @Override
    public void onModuleLoad() {

        SpreadsheetPresenter spreadsheetPresenter = injector.getSpreadsheetPresenter();

        spreadsheetPresenter.go();

        RootPanel.get().add(spreadsheetPresenter.getView());
    }

}
