package io.bcn.springConference.utilities;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class Views {

    public static HorizontalLayout getNewRow(){
        HorizontalLayout layoutRow = new HorizontalLayout();

        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return layoutRow;
    }


}
