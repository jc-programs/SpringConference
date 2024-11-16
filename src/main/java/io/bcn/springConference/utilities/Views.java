package io.bcn.springConference.utilities;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.bcn.springConference.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<FormLayout.ResponsiveStep> getResponsiveStepsForForms(){
        // https://getbootstrap.com/docs/5.0/layout/breakpoints/#available-breakpoints
        // $grid-breakpoints: (
        //  xs: 0,
        //  sm: 576px,
        //  md: 768px,
        //  lg: 992px,
        //  xl: 1200px,
        //  xxl: 1400px
        //);
        return Arrays.asList(
                new FormLayout.ResponsiveStep("0",1),
                new FormLayout.ResponsiveStep("600px",2),
                new FormLayout.ResponsiveStep("900px",3),
                new FormLayout.ResponsiveStep("1200px",4),
                new FormLayout.ResponsiveStep("1500px",5)
        );
    }

    public static ComponentRenderer<FlexLayout, Book> getBookRender(){
        return new ComponentRenderer<>(
                book -> {
                    FlexLayout wrapper = new FlexLayout();
                    wrapper.setAlignItems(FlexComponent.Alignment.CENTER);

                    Div info = new Div();
                    info.setText(book.getTitle());

                    Div author = new Div();
                    author.setText(book.getAuthor());
                    author.getStyle().set("font-size", "var(--lumo-font-size-s)");
                    author.getStyle().set("color", "var(--lumo-secondary-text-color)");
                    info.add(author);

                    wrapper.add(info);

                    return wrapper;
                }
        );
    }
}
