package com.thoughtworks.gwtapp.client.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpreadsheetTest {

    @Test
    public void shouldAddRow() {
        Spreadsheet sheet = new Spreadsheet();
        SpreadSheetRow row = new SpreadSheetRow(1);
        sheet.addRow(row);
        assertEquals(1, sheet.rows().size());
    }

    @Test
    public void shouldAddColumn() {
        Spreadsheet sheet = new Spreadsheet();
        sheet.addColumn("name", SpreadsheetColumn.Type.TEXT);
        assertEquals(1, sheet.columns().size());
    }
}
