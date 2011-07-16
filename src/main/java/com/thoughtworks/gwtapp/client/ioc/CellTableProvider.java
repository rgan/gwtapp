package com.thoughtworks.gwtapp.client.ioc;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.inject.Provider;

public class CellTableProvider implements Provider<CellTable> {
    @Override
    public CellTable get() {
        return new CellTable();
    }
}
