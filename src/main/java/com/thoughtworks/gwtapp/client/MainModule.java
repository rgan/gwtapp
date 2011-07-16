package com.thoughtworks.gwtapp.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;
import com.thoughtworks.gwtapp.client.view.SpreadsheetView;

public class MainModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(SpreadsheetPresenter.View.class).to(SpreadsheetView.class);
        bind(SpreadsheetView.Binder.class).in(Singleton.class);
    }
}
