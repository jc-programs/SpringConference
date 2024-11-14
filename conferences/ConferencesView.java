package com.example.application.views.conferences;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Conferences")
@Route("conferences")
@Menu(order = 3, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class ConferencesView extends Composite<VerticalLayout> {

    public ConferencesView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
