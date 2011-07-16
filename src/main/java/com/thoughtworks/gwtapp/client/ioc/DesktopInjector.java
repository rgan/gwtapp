package com.thoughtworks.gwtapp.client.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.thoughtworks.gwtapp.client.MainModule;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetPresenter;

@GinModules(value = {MainModule.class})
public interface DesktopInjector extends Ginjector {

    SpreadsheetPresenter getSpreadsheetPresenter();
}
