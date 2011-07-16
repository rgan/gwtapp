package com.thoughtworks.gwtapp.client.presenter;

import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.model.SpreadsheetColumn;

public interface SpreadsheetUiHandler {
    void onCellRightClick(SpreadsheetCell cell, int clientX, int clientY);

    void hideColumn(SpreadsheetColumn column);

    void unHideAllColumns();
}
