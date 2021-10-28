package com.example.application.views.bydate;

import com.example.application.data.entity.Studentgroup;
import com.example.application.data.service.StudentgroupService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@PageTitle("по датам")
@Route(value = "date", layout = MainLayout.class)
@AnonymousAllowed
public class ByDateView extends VerticalLayout {

    @Autowired
    StudentgroupService studentgroupService;

    public ByDateView(StudentgroupService studentgroupService) {
        setSpacing(false);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        Grid<Studentgroup> grid = new Grid<>(Studentgroup.class);
//        Grid<List<String>> grid = createGrid();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.getColumns().forEach(col -> col.setResizable(true));
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.getColumns().get(0).setVisible(false);
        grid.setItems(studentgroupService.findAll());



        add(grid);

    }


//    private Grid<List<String>> createGrid() {
//        // Instead of usig bean, you can use List or HashMap for column values
//        // here we use integer value for column id, so List is the simplest case.
//        final Grid<List<String>> grid = new Grid<>();
//
//        Binder<List<String>> binder = new Binder<>();
//        grid.getEditor().setBinder(binder);
//        grid.getEditor().setBuffered(false);
//        StudentgroupService studentgroupService = new StudentgroupService();
//        List<String> studentgroup = studentgroupService.findAll().stream().map(Studentgroup::getNumber).collect(Collectors.toList());
//        int x = 0;
//        final int index = x;
//        for( String s : studentgroup) {
//
//
//            grid.addColumn(lst -> studentgroup.get(index)).setHeader(studentgroup.get(index));
//            x++;
//        }
//        int week = 1;
//        for (int i = 0; i < 65; i++) {
//            final int index = i;
//            // add column with value provider and renderer
//            TextField textField = new TextField();
//            binder.forField(textField)
//                    .withConverter(new StringToIntegerConverter("Not a number"))
//                    .bind(list -> list.get(index), (list, value) -> list.set(index, value));
//            if ((i % 5) == 0) {
//                grid.addColumn(list -> list.get(index)).setKey("" + i).setHeader("M" + (i / 5)).setClassNameGenerator(item -> "month");
//            } else {
//                grid.addColumn(list -> list.get(index)).setKey("" + i).setHeader("W" + (week)).setEditorComponent(textField);
//                week++;
//            }
//        }
//        HeaderRow header = grid.prependHeaderRow();
//        int z = 1;
//        header.getCell(grid.getColumnByKey(""+z));
//        Random random = new Random();
//        List<List<Integer>> items = new ArrayList<>();
//        for (int j=0;j<1000;j++) {
//            final List<Integer> values = new ArrayList<>();
//            int sum = 0;
//            for (int i=0;i<66;i++) {
//                if (i%5 == 0 && i > 0) {
//                    values.add(i-5,sum);
//                    sum = 0;
//                } else {
//                    int number = random.nextInt(10000);
//                    values.add(i, number);
//                    sum = sum + number;
//                }
//            }
//            values.add(j);
//            items.add(values);
//        }
//        return grid;
//    }
}
