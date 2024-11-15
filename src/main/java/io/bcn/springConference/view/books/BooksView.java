package io.bcn.springConference.view.books;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.bcn.springConference.model.Book;
import io.bcn.springConference.repository.BookRepository;
import io.bcn.springConference.utilities.Views;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


@PageTitle("Books")
@Route("books")
@Menu(order = 1, icon = "line-awesome/svg/pencil-ruler-solid.svg")
public class BooksView extends Composite<VerticalLayout> {

    @Autowired
    BookRepository bookRepository;

    private final Grid<Book> booksGrid;

    private final TextField textFieldTitle;
    private final TextField textFieldAuthor;
    private final TextField textFieldISBN;

    private final Binder<Book> binder;

    public BooksView() {
        textFieldTitle = new TextField("Title");
        textFieldAuthor = new TextField("Author");
        textFieldISBN = new TextField("ISBN");
        booksGrid = new Grid<>(Book.class);

        booksGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        booksGrid.setWidth("100%");
        booksGrid.getStyle().set("flex-grow", "0");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(booksGrid);
        getContent().add(new Hr());
        getContent().add(getBookForm());

        binder = new Binder<>(Book.class);
        binder.bind(textFieldAuthor, Book::getAuthor, Book::setAuthor);
        binder.bind(textFieldTitle, Book::getTitle, Book::setTitle);
        binder.bind(textFieldISBN, Book::getISBN, Book::setISBN);

        booksGrid.asSingleSelect().addValueChangeListener(
            event ->{
                if(event.getValue() != null){
                    binder.setBean(event.getValue());
                } else{
                    clearBookForm();
                }
            }
        );
    }

    @PostConstruct
    private void setGridData() {
        if (bookRepository != null) {
            booksGrid.setItems(bookRepository.findAll());
        }
    }

    private void saveBook() {
        if (bookRepository == null) {
          return;
        }

        Book book = binder.getBean();
        if( book == null ){
            book = new Book();
        }
        if( book.getId() == null ){
            book.setId(UUID.randomUUID());
        }
        bookRepository.save(book);
        clearBookForm();
        setGridData();
    }

    private void deleteBook(){
        if( bookRepository == null ){
            return;
        }

        Book book = binder.getBean();
        UUID uuid = book.getId();
        if( uuid != null ){
            bookRepository.deleteById(uuid);
            clearBookForm();
            setGridData();
        }
    }

    private void clearBookForm(){
        binder.setBean(new Book());
    }

    private FormLayout getBookForm(){
        FormLayout form = new FormLayout();

        Button buttonSave = new Button("Insert / Update", clickEvent -> { saveBook(); });
        Button buttonDelete = new Button("Delete", clickEvent -> { deleteBook(); });
        HorizontalLayout row = Views.getNewRow();
        row.setMargin(true);
        row.add(buttonSave,buttonDelete);

        List<FormLayout.ResponsiveStep> steps =  Views.getResponsiveStepsForForms();
        form.setResponsiveSteps(steps);
        form.add(textFieldTitle, textFieldAuthor, textFieldISBN, row);
        // force buttons in one line
        form.setColspan(row, steps.size());

        return form;
    }

}
