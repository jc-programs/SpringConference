package io.bcn.springConference.view.speakers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import io.bcn.springConference.model.Speaker;
import io.bcn.springConference.repository.SpeakerRepository;


import io.bcn.springConference.utilities.Views;

@PageTitle("Speakers")
@Route("speakers")
@Menu(order = 2, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class SpeakersView extends Composite<VerticalLayout> {

    @Autowired
    SpeakerRepository speakerRepository;

    private final Grid<Speaker> speakersGrid;

    private final TextField textFieldName;
    private final TextField textFieldBio;
    private final TextField textFieldEmail;

    private final Binder<Speaker> binder;

    public SpeakersView() {
        textFieldName = new TextField("Name");
        textFieldBio = new TextField("Bio");
        textFieldEmail = new TextField("Email");
        speakersGrid = new Grid<>(Speaker.class);

        speakersGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        speakersGrid.setWidth("100%");
        speakersGrid.getStyle().set("flex-grow", "0");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(speakersGrid);
        getContent().add(getRowSpeakerFields());
        getContent().add(getRowSpeakerButtons());

        binder = new Binder<>(Speaker.class);
        binder.bind(textFieldName, Speaker::getName, Speaker::setName);
        binder.bind(textFieldBio, Speaker::getBio, Speaker::setBio);
        binder.bind(textFieldEmail, Speaker::getEmail, Speaker::setEmail);

        speakersGrid.asSingleSelect().addValueChangeListener(
                event ->{
                    if(event.getValue() != null){
                        binder.setBean(event.getValue());
                    } else{
                        clearSpeakerForm();
                    }
                }
        );
    }

    @PostConstruct
    private void setGridData() {
        if (speakerRepository != null) {
            speakersGrid.setItems(speakerRepository.findAll());
        }
    }

    private void saveSpeaker() {
        if (speakerRepository == null) {
            return;
        }

        Speaker speaker = binder.getBean();
        if( speaker == null ){
            speaker = new Speaker();
        }
        if( speaker.getId() == null ){
            speaker.setId(UUID.randomUUID());
        }
        speakerRepository.save(speaker);
        clearSpeakerForm();
        setGridData();
    }

    private void deleteSpeaker(){
        if( speakerRepository == null ){
            return;
        }

        Speaker speaker = binder.getBean();
        UUID uuid = speaker.getId();
        if( uuid != null ){
            speakerRepository.deleteById(uuid);
            clearSpeakerForm();
            setGridData();
        }
    }

    private void clearSpeakerForm(){
        binder.setBean(new Speaker());
    }

    private HorizontalLayout getRowSpeakerFields(){
        HorizontalLayout row = Views.getNewRow();

        row.add(textFieldName);
        row.add(textFieldBio);
        row.add(textFieldEmail);

        return row;
    }

    private HorizontalLayout getRowSpeakerButtons() {
        HorizontalLayout row = Views.getNewRow();

        Button buttonSave = new Button("Insert / Update", clickEvent -> { saveSpeaker(); });
        Button buttonDelete = new Button("Delete", clickEvent -> { deleteSpeaker(); });

        row.add(buttonSave);
        row.add(buttonDelete);

        return row;
    }

}
