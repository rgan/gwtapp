package com.thoughtworks.gwtapp.client.view;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.thoughtworks.gwtapp.client.model.SpreadsheetCell;
import com.thoughtworks.gwtapp.client.presenter.SpreadsheetUiHandler;

public class SpreadsheetCellView extends AbstractCell<SpreadsheetCell> {

    private static final String CONTEXTMENU = "contextmenu";
    private SpreadsheetUiHandler uiHandler;

    interface Template extends SafeHtmlTemplates {
        @Template("<input type=\"text\" value=\"{0}\" tabindex=\"-1\"></input>")
        SafeHtml input(String value);
    }

    private static Template template = GWT.create(Template.class);

    public SpreadsheetCellView(SpreadsheetUiHandler uiHandler) {
        super(CONTEXTMENU);
        this.uiHandler = uiHandler;
    }

    @Override
    public void render(Context context, SpreadsheetCell value, SafeHtmlBuilder sb) {
        sb.append(template.input(value.getValue()));
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, SpreadsheetCell value, NativeEvent event, ValueUpdater<SpreadsheetCell> spreadsheetCellValueUpdater) {
        super.onBrowserEvent(context, parent, value, event, spreadsheetCellValueUpdater);
        if (CONTEXTMENU.equals(event.getType())) {
            event.preventDefault();
            uiHandler.onCellRightClick(value, event.getClientX(), event.getClientY());
        }
    }
}
