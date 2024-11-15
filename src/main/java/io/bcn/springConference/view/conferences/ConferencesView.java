package io.bcn.springConference.view.conferences;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.bcn.springConference.model.Conference;
import io.bcn.springConference.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Conferences")
@Route("conferences")
@Menu(order = 3, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class ConferencesView extends Composite<VerticalLayout> {

    @Autowired
    ConferenceRepository conferenceRepository;
/*
    private final Grid<Conference> conferencesGrid;

    private final TextField textFieldConference;
    private final TextField textFieldTitle;
    private final DatePicker datePickerDate;
    private final TextField textFieldYoutube;
    private final TextField textFieldContent;
    private final NumberField numberFieldDuration;
    private final TextField textFieldRoom;

    private final Binder<Conference> binder;

    public ConferencesView() {
        textFieldConference = new TextField("Conference");
        textFieldTitle = new TextField("Title");
        datePickerDate = new DatePicker("Date");
        textFieldYoutube = new TextField("Youtube");
        textFieldContent = new TextField("Content");
        numberFieldDuration = new NumberField("Duration");
        textFieldRoom = new TextField("Room");
        conferencesGrid = new Grid<>(Conference.class);

        conferencesGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        conferencesGrid.setWidth("100%");
        conferencesGrid.getStyle().set("flex-grow", "0");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(conferencesGrid);
        getContent().add(getRowConferenceFields());
        getContent().add(getRowConferenceButtons());

        binder = new Binder<>(Conference.class);
        binder.bind(textFieldConference, Conference::getConference, Conference::setConference);
        binder.bind(textFieldTitle, Conference::getTitle, Conference::setTitle);
        binder.forField(datePickerDate).bind(Conference::getDate, Conference::setDate);
        binder.bind(datePickerDate, Conference::getDate, Conference::setDate);
        binder.bind(textFieldYoutube, Conference::getYoutube, Conference::setYoutube);
        binder.bind(textFieldContent, Conference::getContent, Conference::setContent);
        binder.bind(numberFieldDuration, Conference::getDuration, Conference::setDuration);
        binder.bind(textFieldRoom, Conference::getRoom, Conference::setRoom);

        conferencesGrid.asSingleSelect().addValueChangeListener(
                event ->{
                    if(event.getValue() != null){
                        binder.setBean(event.getValue());
                    } else{
                        clearConferenceForm();
                    }
                }
        );
    }

 */
}
