package io.bcn.springConference.view.books;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.bcn.springConference.model.Book;
import io.bcn.springConference.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;



@PageTitle("Books")
@Route("books")
@Menu(order = 1, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class BooksView extends Composite<VerticalLayout> {

    @Autowired
    BookRepository bookRepository;

    private final Grid booksGrid;

    private final TextField textFieldTitle;
    private final TextField textFieldAuthor;
    private final TextField textFieldISBN;

    public BooksView() {
        booksGrid = new Grid(Book.class);
        textFieldTitle = new TextField("Title");
        textFieldAuthor = new TextField("Author");
        textFieldISBN = new TextField("ISBN");

        VerticalLayout layoutColumn = new VerticalLayout();
        HorizontalLayout layoutRow1 = new HorizontalLayout();


        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        booksGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        booksGrid.setWidth("100%");
        booksGrid.getStyle().set("flex-grow", "0");

        layoutColumn.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn);
        layoutColumn.setWidth("100%");
        layoutColumn.setHeight("min-content");
        layoutColumn.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn.setAlignItems(Alignment.CENTER);
        layoutRow1.setWidthFull();
        layoutColumn.setFlexGrow(1.0, layoutRow1);
        layoutRow1.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow1.setWidth("100%");
        layoutRow1.setHeight("min-content");
        layoutRow1.setAlignItems(Alignment.CENTER);
        layoutRow1.setJustifyContentMode(JustifyContentMode.CENTER);


        layoutRow1.add(textFieldTitle);
        layoutRow1.add(textFieldAuthor);
        layoutRow1.add(textFieldISBN);
        layoutColumn.add(layoutRow1);

        Button button = new Button("Reload", clickEvent -> { setGridData(); });

        getContent().add(booksGrid);
        getContent().add(button);
    }

    @PostConstruct
    private void setGridData() {
        if (bookRepository != null) {
            booksGrid.setItems(bookRepository.findAll());
        }
    }

}
