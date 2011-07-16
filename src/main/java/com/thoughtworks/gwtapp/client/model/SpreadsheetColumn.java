package com.thoughtworks.gwtapp.client.model;

public class SpreadsheetColumn {

    public enum Type {
        TEXT;
    }

    private String name;
    private Type type;
    private boolean visible;

    public SpreadsheetColumn(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
}
