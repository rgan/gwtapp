package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;

public abstract class SortableTextColumn<T,C> extends Column<T, C> {

    public SortableTextColumn(Cell<C> cell) {
        super(cell);
        setSortable(true);
    }
}
