package com.example.application.views.enter;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("О программе")
@Route(value = "authentication", layout = MainLayout.class)
@AnonymousAllowed
public class EnterView extends VerticalLayout {

    public EnterView() {
        setSpacing(false);

        Image img = new Image("images/hg.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("Решение команды \"Мэкхэнд\""));
        add(new Paragraph("Приложение \"Расписание\" Версия: альфа"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
