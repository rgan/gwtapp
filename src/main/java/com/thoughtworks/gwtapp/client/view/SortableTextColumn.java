package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class SortableTextColumn<T> extends Column<T, String> {

    public SortableTextColumn(TextInputCell cell) {
        super(cell);
        setSortable(true);
    }
}
