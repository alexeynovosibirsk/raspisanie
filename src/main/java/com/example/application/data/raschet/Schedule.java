package com.example.application.data.raschet;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    public List<List<String>> contains(String studentgroup) {
        List<List<String>> monday = new ArrayList<>();

        List<String> monday0 = new ArrayList<>(5); // "09:00 - 11:00" , номер группы, предмет препод аудитория
        monday0.add("09:00 - 11:00");
        monday0.add(studentgroup);
        List<String> monday1 = new ArrayList<>(5);
        monday1.add("11:00 - 13:00");
        monday1.add(studentgroup);
        List<String> monday2 = new ArrayList<>(5);
        monday2.add("13:00 - 15:00");
        monday2.add(studentgroup);
        List<String> monday3 = new ArrayList<>(5);
        monday3.add("15:00 - 17:00");
        monday3.add(studentgroup);

        monday.add(monday0);
        monday.add(monday1);
        monday.add(monday2);
        monday.add(monday3);

        return monday;
    }
}
