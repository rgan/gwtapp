package com.thoughtworks.gwtapp.client.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpreadSheetRowTest {

    @Test
    public void shouldSetColumnValue() {
        SpreadSheetRow row = new SpreadSheetRow(1);
        SpreadsheetColumn column = new SpreadsheetColumn("name", SpreadsheetColumn.Type.TEXT);
        row.setColumnValue(column, "foo");
        assertEquals("foo", row.getColumnValue(column));
    }
}
