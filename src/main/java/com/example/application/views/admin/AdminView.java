package com.example.application.views.admin;

import com.example.application.data.entity.Auditory;
import com.example.application.data.entity.Studentgroup;
import com.example.application.data.entity.Teachers;
import com.example.application.data.entity.Thursday;
import com.example.application.data.raschet.CreateRaspisanie;
import com.example.application.data.service.AuditoryService;
import com.example.application.data.service.StudentgroupService;
import com.example.application.data.service.TeachersService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@PageTitle("Составить расписание")
@Route(value = "admin", layout = MainLayout.class)
@RolesAllowed("admin")
public class AdminView extends VerticalLayout {

    private Set<Teachers> selectedTeachers = new HashSet<>();
    private Set<String> thursdayTeachersSelected = new HashSet<>();
    private Set<String> mondayTeachersSelected = new HashSet<>();
    private Set<String> threeDaysTeachersSelected = new HashSet<>();
    private Set<String> twoLessonsTeachersSelected = new HashSet<>();

    private Set<Studentgroup> selectedStudentgroup = new TreeSet<>();

    private Set<String> thursdayTeachears = new HashSet<>();
    private Set<String> mondayTeachears = new HashSet<>();
    private Set<String> threeDaysTeachers = new HashSet<>();
    private Set<String> twoLessonsTeachers = new HashSet<>();

    private Grid<String> thursdayGrid = new Grid<>();
    private Grid<String> mondayGrid = new Grid<>();
    private Grid<String> threeDaysGrid = new Grid<>();
    private Grid<String> twoLessonsGrid = new Grid<>();

    private TextField totalTeachersThuesdayField = new TextField();
    private TextField totalTeachersMondayField = new TextField();
    private TextField totalTeachersTrheeDaysField = new TextField();

    public AdminView(StudentgroupService studentgroupService, AuditoryService auditoryService, TeachersService teachersService) {

        setSpacing(false);

        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
// Учебные группы
        Grid<Studentgroup> studentgroupGrid = new Grid<>(Studentgroup.class);
        studentgroupGrid.setMaxHeight("200px");
        studentgroupGrid.setColumns("number");
        studentgroupGrid.setItems(studentgroupService.findAll());
        studentgroupGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        studentgroupGrid.addSelectionListener(event -> {
            selectedStudentgroup = event.getAllSelectedItems();

            Notification.show(" Выбрано групп: " + selectedStudentgroup.size()).setPosition(Notification.Position.MIDDLE);
        });

        TextField totalField = new TextField();
        totalField.setValueChangeMode(ValueChangeMode.EAGER);
        totalField.setReadOnly(true);
        totalField.setMaxWidth("100px");
        totalField.setValue("Всего: " + studentgroupService.countAll());

        TextField findByNumberField = new TextField();
        findByNumberField.setValueChangeMode(ValueChangeMode.EAGER);
        findByNumberField.setMinWidth("300px");
        findByNumberField.setClearButtonVisible(true);
        findByNumberField.setPlaceholder("Поиск по номеру группы");
        if(findByNumberField.getValue() == null) {
            findByNumberField.setValue("'%%'");
        }

        Button findButton = new Button(new Icon(VaadinIcon.SEARCH));
        findButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findButton.addClickListener(e -> {
                    studentgroupGrid.setItems(studentgroupService.findByNumber(findByNumberField.getValue()));
                    studentgroupGrid.getDataProvider().refreshAll();
                });

        Button refreshButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
                refreshButton.addClickListener(e -> {
                    studentgroupGrid.setItems(studentgroupService.findAll());
                });

        HorizontalLayout studentgrouppanel = new HorizontalLayout(totalField, findByNumberField, findButton, refreshButton);
// Aудитории
        Grid<Auditory> auditoryGrid = new Grid<>(Auditory.class);
        auditoryGrid.setMaxHeight("200px");
        auditoryGrid.setColumns("number");
        auditoryGrid.setItems(auditoryService.findAll());
        auditoryGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        auditoryGrid.addSelectionListener(event -> {
            Set<Auditory> selected = event.getAllSelectedItems();
            Notification.show(" Выбрано aудиторий: " + selected.size()).setPosition(Notification.Position.MIDDLE);
        });

        TextField totalAuditoryField = new TextField();
        totalAuditoryField.setValueChangeMode(ValueChangeMode.EAGER);
        totalAuditoryField.setReadOnly(true);
        totalAuditoryField.setMaxWidth("100px");
        totalAuditoryField.setValue("Всего: " + auditoryService.countAll());

        TextField findByNumberAuditoryField = new TextField();
        findByNumberAuditoryField.setMinWidth("300px");
        findByNumberAuditoryField.setClearButtonVisible(true);
        findByNumberAuditoryField.setPlaceholder("Поиск по номеру аудитории");
        if(findByNumberAuditoryField.getValue() == null) {
            findByNumberAuditoryField.setValue("'%%'");
        }

        Button findAuditoryButton = new Button(new Icon(VaadinIcon.SEARCH));
        findAuditoryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findAuditoryButton.addClickListener(e -> {
            auditoryGrid.setItems(auditoryService.findByNumber(findByNumberAuditoryField.getValue()));
            auditoryGrid.getDataProvider().refreshAll();
        });

        Button refreshAuditoryButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshAuditoryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        refreshAuditoryButton.addClickListener(e -> {
            auditoryGrid.setItems(auditoryService.findAll());
        });
        HorizontalLayout auditorypanel = new HorizontalLayout(totalAuditoryField, findByNumberAuditoryField, findAuditoryButton, refreshAuditoryButton);
        auditorypanel.setPadding(false);
// Преподаватели
        Grid<Teachers> teachersGrid = new Grid<>(Teachers.class);
        teachersGrid.setMaxHeight("200px");
        teachersGrid.setColumns("name");
        teachersGrid.setItems(teachersService.findAll());
        teachersGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        teachersGrid.addSelectionListener(event -> {
            selectedTeachers = event.getAllSelectedItems();

            thursdayTeachears.clear();
            mondayTeachears.clear();

            thursdayTeachears = selectedTeachers.stream().map(Teachers::getName).collect(Collectors.toSet());
            mondayTeachears = selectedTeachers.stream().map(Teachers::getName).collect(Collectors.toSet());

            thursdayGrid.setItems(thursdayTeachears);
            mondayGrid.setItems(mondayTeachears);

            thursdayGrid.getDataProvider().refreshAll();
            mondayGrid.getDataProvider().refreshAll();

            totalTeachersThuesdayField.setValue("Всего: " + thursdayTeachears.size());
            totalTeachersMondayField.setValue("Всего: " + mondayTeachears.size());

            Notification.show(" Выбрано преподавателей: " + selectedTeachers.size()).setPosition(Notification.Position.MIDDLE);
        });

        TextField totalTeachersField = new TextField();
        totalTeachersField.setValueChangeMode(ValueChangeMode.EAGER);
        totalTeachersField.setReadOnly(true);
        totalTeachersField.setMaxWidth("100px");
        totalTeachersField.setValue("Всего: " + teachersService.countAll());

        TextField findByNameTeachersField = new TextField();
        findByNameTeachersField.setMinWidth("300px");
        findByNameTeachersField.setClearButtonVisible(true);
        findByNameTeachersField.setPlaceholder("Поиск по Фамилии И.О.");
        if(findByNameTeachersField.getValue() == null) {
            findByNameTeachersField.setValue("'%%'");
        }

        Button findTeachersButton = new Button(new Icon(VaadinIcon.SEARCH));
        findTeachersButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findTeachersButton.addClickListener(e -> {
            teachersGrid.setItems(teachersService.findByName(findByNameTeachersField.getValue()));
            teachersGrid.getDataProvider().refreshAll();
        });

        Button refreshTeachersButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshTeachersButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        refreshTeachersButton.addClickListener(e -> {
            teachersGrid.setItems(teachersService.findAll());
        });

        HorizontalLayout teacherspanel = new HorizontalLayout(totalTeachersField, findByNameTeachersField, findTeachersButton, refreshTeachersButton);
// FreeThuesday
        thursdayGrid.setMaxWidth("380px");
        thursdayGrid.setMaxHeight("200px");
        thursdayGrid.addColumn(String::valueOf).setHeader("Фамилия, инициалы");
        thursdayGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        thursdayGrid.addSelectionListener(event -> {
           thursdayTeachersSelected = event.getAllSelectedItems();
            Notification.show(" Свободный четверг. Выбрано преподавателей: " + thursdayTeachersSelected.size()).setPosition(Notification.Position.MIDDLE);

        });

        TextField findByNameTeachersThursdayField = new TextField();
        findByNameTeachersThursdayField.setMaxWidth("150px");
        findByNameTeachersThursdayField.setClearButtonVisible(true);
        findByNameTeachersThursdayField.setPlaceholder("ФИО");
        if(findByNameTeachersThursdayField.getValue() == null) {
            findByNameTeachersThursdayField.setValue("'%%'");
        }

        Button findTeachersThuesdayButton = new Button(new Icon(VaadinIcon.SEARCH));
        findTeachersThuesdayButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findTeachersThuesdayButton.addClickListener(e -> {
            thursdayGrid.setItems(thursdayTeachears.stream().filter(item -> item.equals
                    (findByNameTeachersThursdayField.getValue())).findFirst().get());
            thursdayGrid.getDataProvider().refreshAll();
        });

        Button refreshTeachersThuesdayButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshTeachersThuesdayButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        refreshTeachersThuesdayButton.addClickListener(e -> {
            thursdayGrid.setItems(thursdayTeachears);
        });

        totalTeachersThuesdayField.setValueChangeMode(ValueChangeMode.EAGER);
        totalTeachersThuesdayField.setReadOnly(true);
        totalTeachersThuesdayField.setMaxWidth("95px");
        totalTeachersThuesdayField.setValue("Всего: " + thursdayTeachears.size());

        HorizontalLayout teachersThursdayspanel = new HorizontalLayout(totalTeachersThuesdayField, findByNameTeachersThursdayField,
                findTeachersThuesdayButton, refreshTeachersThuesdayButton);

// MondayWindow from 10 till 15
        mondayGrid.setMaxWidth("380px");
        mondayGrid.setMaxHeight("200px");
        mondayGrid.addColumn(String::valueOf).setHeader("Фамилия, инициалы");
        mondayGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        mondayGrid.addSelectionListener(event -> {
            mondayTeachersSelected = event.getAllSelectedItems();
            Notification.show("Окно в понедельник. Выбрано преподавателей: " + mondayTeachersSelected.size()).setPosition(Notification.Position.MIDDLE);
        });

        TextField findByNameMondayTeachersField = new TextField();
        findByNameMondayTeachersField.setMaxWidth("150px");
        findByNameMondayTeachersField.setClearButtonVisible(true);
        findByNameMondayTeachersField.setPlaceholder("ФИО");
        if(findByNameMondayTeachersField.getValue() == null) {
            findByNameMondayTeachersField.setValue("'%%'");
        }

        Button findTeachersMondayButton = new Button(new Icon(VaadinIcon.SEARCH));
        findTeachersMondayButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findTeachersMondayButton.addClickListener(e -> {
            mondayGrid.setItems(mondayTeachersSelected.stream().filter(item -> item.equals
                    (findByNameMondayTeachersField.getValue())).findFirst().get());
            mondayGrid.getDataProvider().refreshAll();
        });

        Button refreshTeachersMondayButton = new Button(new Icon(VaadinIcon.REFRESH));
        refreshTeachersMondayButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        refreshTeachersMondayButton.addClickListener(e -> {
            mondayGrid.setItems(mondayTeachears);
        });

        totalTeachersMondayField.setValueChangeMode(ValueChangeMode.EAGER);
        totalTeachersMondayField.setReadOnly(true);
        totalTeachersMondayField.setMaxWidth("95px");
        totalTeachersMondayField.setValue("Всего: " + mondayTeachears.size());

        HorizontalLayout teachersmondayspanel = new HorizontalLayout(totalTeachersMondayField, findByNameMondayTeachersField,
                findTeachersMondayButton, refreshTeachersMondayButton);

        DatePicker startDate = new DatePicker("Начальная дата");
        startDate.setValue(LocalDate.now());
        DatePicker endDate = new DatePicker("Конечная дата");
        endDate.addValueChangeListener(event -> {
                    if (event.getValue() == null) {
//                endDate.setText("No date selected");
                    } else {
//                value.setText("Selected date: " + event.getValue());
                        System.out.println(endDate.getValue());
                    }
                });


        Button createScheduleButton = new Button("Cоздать расписание");
        createScheduleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        CreateRaspisanie createRaspisanie = new CreateRaspisanie();
        createScheduleButton.addClickListener(e -> {
            if(selectedStudentgroup.stream().map(Studentgroup::getNumber).collect(Collectors.toList()).isEmpty()) {
                Notification.show("Не выбраны группы!", 3000, Notification.Position.MIDDLE).addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                createRaspisanie.skeletWeek(selectedStudentgroup.stream().map(Studentgroup::getNumber).collect(Collectors.toList()));
                createRaspisanie.addTeachers(selectedTeachers.stream().map(Teachers::getName).collect(Collectors.toList()));
//                if(!thursdayTeachersSelected.isEmpty()) {
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + "not empty");
                    createRaspisanie.setThursdayteachers(thursdayTeachersSelected.stream().map(Thursday::getName).collect(Collectors.toList()));
//                }


            }
        });

        Button saveScheduleButton = new Button("Cохранить расписание");
        saveScheduleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveScheduleButton.addClickListener(e -> {

        });

        Button downloadScheduleButton = new Button("Cкачать расписание");
        downloadScheduleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        downloadScheduleButton.addClickListener(e -> {

        });

//UPLOAD
        String fullPath = "upload/";
        String[] excelName = new String[]{"unknown"};
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        Button uploadButton = new Button("Загрузить список преподавателей");
        upload.setUploadButton(uploadButton);
        upload.setWidth("100%");
        upload.setDropAllowed(false);
        Span dropLabel = new Span("Перетащите файл сюда");
        upload.setDropLabel(dropLabel);
        upload.addFinishedListener((event) -> {
                    excelName[0] = event.getFileName();

//            if (!CheckFileType.go(event.getFileName()).equals("text/csv")) {
//                Notification.show("Неправильный формат файла!", 3000, Notification.Position.MIDDLE).addThemeVariants.NotificationVariant.LUMO_ERROR});
//                logger.info("Upload error: " + event.getFileName() + " file is not excel");
//            } else {
//                DropoutStatusEnrichment dropoutStatusEnrichment = new DropoutStatusEnrichment();
//                ParserXlsx parserXlsx = new ParserXlsx();
                    InputStream inputStream = buffer.getInputStream();

                    try {
                        File file = new File(fullPath + event.getFileName());
                        if (file.exists()) {
                            int int_random = ThreadLocalRandom.current().nextInt();
                            file = new File(fullPath + event.getFileName() + "_" + int_random);
                        }

                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] bytes = new byte[1024];

                        int read;
                        while ((read = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }

                        outputStream.close();
                        inputStream.close();
                    } catch (FileNotFoundException var16) {
                        var16.printStackTrace();
                    } catch (IOException var17) {
                        var17.printStackTrace();
                    }
                    teachersService.truncate();
                    teachersService.insert(event.getFileName());
            Notification.show("Загружен файл: " + event.getFileName(), 5000, Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                });
            Button uploadButton1 = new Button("Загрузить курсы");
            Button uploadButton2 = new Button("Загрузить учебные группы");
            Button uploadButton3 = new Button("Загрузить аудитории");

// The layouts
        FormLayout Row1 = new FormLayout();
        Row1.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3));
        FormLayout Row2 = new FormLayout();
        Row2.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3));
        FormLayout Row3 = new FormLayout();
        Row3.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3));
        FormLayout Row4 = new FormLayout();
        Row4.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row5 = new FormLayout();
        Row5.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row6 = new FormLayout();
        Row6.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row7 = new FormLayout();
        Row7.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4));
        FormLayout Row8 = new FormLayout();
        Row8.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4));
        FormLayout Row9 = new FormLayout();
        Row9.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4));
        FormLayout Row10 = new FormLayout();
        Row10.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row11 = new FormLayout();
        Row11.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row12 = new FormLayout();
        Row12.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row13 = new FormLayout();
        Row13.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4),
                new FormLayout.ResponsiveStep("25em", 5));
        FormLayout Row14 = new FormLayout();
        Row14.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row15 = new FormLayout();
        Row15.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row16 = new FormLayout();
        Row16.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1));
        FormLayout Row17 = new FormLayout();
        Row17.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4));

//Labels
        Label labelStudentgroups = new Label("Учебные группы:");
        Label labelAuditory = new Label("Aудитории:");
        Label labelTeachers = new Label("Преподаватели:");
        Label labelConstraints = new Label("Ограничения:");
        Label labelThursdayTeachers = new Label("Cвободный четверг:");
        Label labelMondayTeachers = new Label("Oкно в понедельник с 10 до 15");
        Label labelCreateSchedule = new Label("Cоздать расписание:");
        Label uploadData = new Label("Загрузка данных:");

//rows
        Row1.add(labelStudentgroups, labelAuditory, labelTeachers);
        Row2.add(studentgrouppanel,  auditorypanel,  teacherspanel);
        Row3.add(studentgroupGrid,  auditoryGrid, teachersGrid);

        Row4.add(new Hr());
        Row5.add(labelConstraints);
        Row6.add(new Hr());

        Row7.add(labelThursdayTeachers, labelMondayTeachers);
        Row8.add(teachersThursdayspanel, teachersmondayspanel);
        Row9.add(thursdayGrid, mondayGrid);

        Row10.add(new Hr());
        Row11.add(labelCreateSchedule);
        Row12.add(new Hr());

        Row13.add(startDate, endDate, createScheduleButton, saveScheduleButton, downloadScheduleButton);

        Row14.add(new Hr());
        Row15.add(uploadData);
        Row16.add(new Hr());

        Row17.add(upload, uploadButton1, uploadButton2, uploadButton3);

        add(Row1, Row2, Row3, Row4, Row5, Row6, Row7, Row8, Row9, Row10, Row11, Row12, Row13, Row14, Row15, Row16, Row17);







    }


}
