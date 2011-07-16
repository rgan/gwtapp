package com.thoughtworks.gwtapp.client.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpreadsheetColumnTest {

    @Test
    public void shouldBeVisibleByDefault() {
        SpreadsheetColumn column = new SpreadsheetColumn("name", SpreadsheetColumn.Type.TEXT);
        assertTrue(column.isVisible());
    }

    @Test
    public void shouldNotBeVisibleOnHide() {
        SpreadsheetColumn column = new SpreadsheetColumn("name", SpreadsheetColumn.Type.TEXT);
        column.hide();
        assertFalse(column.isVisible());
    }

    @Test
    public void shouldBeVisibleOnUnHide() {
        SpreadsheetColumn column = new SpreadsheetColumn("name", SpreadsheetColumn.Type.TEXT);
        column.hide();
        column.unHide();
        assertTrue(column.isVisible());
    }
}
