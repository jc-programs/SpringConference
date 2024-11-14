package com.example.application.views.books;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Books")
@Route("books")
@Menu(order = 1, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class BooksView extends Composite<VerticalLayout> {

    public BooksView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
