package io.bcn.springConference.view.conferences;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.bcn.springConference.model.Book;
import io.bcn.springConference.model.Conference;
import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.BookRepository;
import io.bcn.springConference.repository.ConferenceRepository;
import io.bcn.springConference.repository.SpeakerRepository;
import io.bcn.springConference.utilities.Views;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@PageTitle("Conferences")
@Route("conferences")
@Menu(order = 3, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class ConferencesView extends Composite<VerticalLayout> {

    @Autowired
    ConferenceRepository conferenceRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    private final Grid<Conference> conferencesGrid;

    private final TextField textFieldConference;
    private final TextField textFieldTitle;
    private final DatePicker datePickerDate;
    private final TextField textFieldYoutube;
    private final TextField textFieldContent;
    private final IntegerField integerFieldDuration;
    private final TextField textFieldRoom;

    @PropertyId("book")
    private final Select<Book> selectBook;
    @PropertyId("Speaker")
    private final Select<Speaker> selectSpeaker;

    private final Binder<Conference> binder;

    public ConferencesView() {
        textFieldConference = new TextField("Conference");
        textFieldTitle = new TextField("Title");
        datePickerDate = new DatePicker("Date");
        textFieldYoutube = new TextField("Youtube");
        textFieldContent = new TextField("Content");
        integerFieldDuration = new IntegerField("Duration");
        textFieldRoom = new TextField("Room");
        conferencesGrid = getConferencesGrid();

        selectBook = new Select<>();
        selectBook.setLabel("Book");
        selectBook.setItemLabelGenerator(Book::getTitle);
        selectSpeaker = new Select<>();
        selectSpeaker.setLabel("Speaker");
        selectSpeaker.setItemLabelGenerator(Speaker::getName);

        conferencesGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        conferencesGrid.setWidth("100%");
        conferencesGrid.getStyle().set("flex-grow", "0");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(conferencesGrid);
        getContent().add(new Hr());
        getContent().add(getConferenceForm());

        binder = new Binder<>(Conference.class, true);
        binder.bind(textFieldConference, Conference::getConference, Conference::setConference);
        binder.bind(textFieldTitle, Conference::getTitle, Conference::setTitle);
        binder.bind(datePickerDate, Conference::getDate, Conference::setDate);
        binder.bind(textFieldYoutube, Conference::getYoutube, Conference::setYoutube);
        binder.bind(textFieldContent, Conference::getContent, Conference::setContent);
        binder.bind(integerFieldDuration, Conference::getDuration, Conference::setDuration);
        binder.bind(textFieldRoom, Conference::getRoom, Conference::setRoom);
        binder.bind(selectBook, Conference::getBook, Conference::setBook);
        binder.bind(selectSpeaker, Conference::getSpeaker, Conference::setSpeaker);

        conferencesGrid.asSingleSelect().addValueChangeListener(
                event ->{
                    Conference conference = event.getValue();
                    if(conference != null){
                        binder.setBean(conference);
                    } else{
                        clearConferenceForm();
                    }
                }
        );
    }

    private Grid<Conference> getConferencesGrid(){
        Grid<Conference> grid = new Grid<>(Conference.class,false);

        Grid.Column<Conference> col = grid.addColumn(Conference::getId);
        col.setVisible(false);
        grid.addColumn(Conference::getConference).setHeader("Conference");
        grid.addColumn(Conference::getTitle).setHeader("Title");
        grid.addColumn(Conference::getDate).setHeader("Date");
        grid.addColumn(Conference::getYoutube).setHeader("Youtube");
        grid.addColumn(Conference::getContent).setHeader("Content");
        grid.addColumn(Conference::getDuration).setHeader("Duration");
        grid.addColumn(Conference::getRoom).setHeader("Room");

        grid.addColumn(new ComponentRenderer<>( conference -> {
            return new Span(((Conference)conference).getBook().getTitle());
        })).setHeader("Book");
        grid.addColumn(new ComponentRenderer<>( conference -> {
            return new Span(((Conference)conference).getSpeaker().getName());
        })).setHeader("Speaker");

        grid.getColumns().forEach( column -> column.setSortable(true) );

        return grid;
    }


    @PostConstruct
    private void setGridData() {
        if (conferenceRepository != null) {
            conferencesGrid.setItems(conferenceRepository.findAll());
        }
    }

    @PostConstruct
    private void setSelectsData() {
        if (bookRepository != null) {
            selectBook.setItems(bookRepository.findAll());
        }
        if (speakerRepository != null) {
            selectSpeaker.setItems(speakerRepository.findAll());
        }
    }

    private void saveConference() {
        if (conferenceRepository == null) {
            return;
        }

        Conference conference = binder.getBean();
        if( conference == null ){
            conference = new Conference();
        }
        if( conference.getId() == null ){
            conference.setId(UUID.randomUUID());
        }
        conferenceRepository.save(conference);
        clearConferenceForm();
        setGridData();
    }

    private void deleteConference(){
        if( conferenceRepository == null ){
            return;
        }

        Conference conference = binder.getBean();
        UUID uuid = conference.getId();
        if( uuid != null ){
            conferenceRepository.deleteById(uuid);
            clearConferenceForm();
            setGridData();
        }
    }

    private void clearConferenceForm(){
        binder.setBean(new Conference());
    }

    private FormLayout getConferenceForm(){
        FormLayout form = new FormLayout();

        Button buttonSave = new Button("Insert / Update", clickEvent -> { saveConference(); });
        Button buttonDelete = new Button("Delete", clickEvent -> { deleteConference(); });
        HorizontalLayout row = Views.getNewRow();
        row.setMargin(true);
        row.add(buttonSave,buttonDelete);

        List<FormLayout.ResponsiveStep> steps =  Views.getResponsiveStepsForForms();
        form.setResponsiveSteps(steps);
        form.add(textFieldConference, textFieldTitle, datePickerDate, textFieldYoutube);
        form.add(textFieldContent, integerFieldDuration, textFieldRoom);
        form.add(selectBook, selectSpeaker, row);
        // force buttons in one line
        form.setColspan(row, steps.size());

        return form;
    }

    private HorizontalLayout getRow1ConferenceFields(){
        HorizontalLayout row = Views.getNewRow();

        row.add(textFieldConference);
        row.add(textFieldTitle);
        row.add(datePickerDate);
        row.add(textFieldYoutube);

        return row;
    }

    private HorizontalLayout getRow2ConferenceFields(){
        HorizontalLayout row = Views.getNewRow();

        row.add(textFieldContent);
        row.add(integerFieldDuration);
        row.add(textFieldRoom);
        row.add(selectBook);
        row.add(selectSpeaker);

        return row;
    }

    private HorizontalLayout getRowConferenceButtons() {
        HorizontalLayout row = Views.getNewRow();

        Button buttonSave = new Button("Insert / Update", clickEvent -> { saveConference(); });
        Button buttonDelete = new Button("Delete", clickEvent -> { deleteConference(); });

        row.add(buttonSave);
        row.add(buttonDelete);

        return row;
    }


}
