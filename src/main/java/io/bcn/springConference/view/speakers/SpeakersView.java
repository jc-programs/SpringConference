package io.bcn.springConference.view.speakers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Speakers")
@Route("speakers")
@Menu(order = 2, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class SpeakersView extends Composite<VerticalLayout> {

    public SpeakersView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }
}
