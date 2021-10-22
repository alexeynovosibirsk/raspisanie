package com.example.application.views.bydate;

import com.example.application.data.entity.Raspisanie;
import com.example.application.data.service.RaspisanieService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("по датам")
@Route(value = "date", layout = MainLayout.class)
@AnonymousAllowed
public class ByDateView extends VerticalLayout {

    public ByDateView(RaspisanieService raspisanieService) {
        setSpacing(false);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        //Grid
        Grid<Raspisanie> grid = new Grid<>(Raspisanie.class);
        grid.setColumns("id", "timeslot", "group1", "group2");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.getColumns().forEach(col -> col.setResizable(true));
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setItems(raspisanieService.findAll());

        add(grid);

    }

}
