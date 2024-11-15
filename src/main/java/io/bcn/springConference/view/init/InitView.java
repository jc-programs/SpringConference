package io.bcn.springConference.view.init;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Welcome")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class InitView extends Composite<VerticalLayout> {

    public InitView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Paragraph paragraph = new Paragraph("Welcome to Conference IO");
        getContent().add(paragraph);

    }
}
