package com.thoughtworks.gwtapp.client.model;

public class SpreadsheetColumn {

    public enum Type {
        TEXT;

    }

    private String name;
    private Type type;

    private boolean visible = true;
    public SpreadsheetColumn(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void unHide() {
        visible = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpreadsheetColumn that = (SpreadsheetColumn) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
