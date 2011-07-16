package com.thoughtworks.gwtapp.client.model;

public class SpreadsheetCell {
    private SpreadsheetColumn column;
    private String value;

    public SpreadsheetCell(SpreadsheetColumn column, String value) {
        this.column = column;
        this.value = value;
    }

    public SpreadsheetColumn getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
