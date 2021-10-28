package com.example.application.data.raschet;

import com.example.application.data.entity.Studentgroup;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateRaspisanie {

    private static List<Map<Integer, List<List<String>>>> weekList = new ArrayList<>();
    private List<String> studentgroupList = new ArrayList<>();
    private List<String> thursdayTeachers = new HashSet<>();

    public void setThursdayteachers(List<String> set) {
        thursdayTeachers = set;
    }

    public void setStudentgroup(List<String> studentgroup) {
            List<String> studentgroupList = studentgroup;
            Collections.sort(studentgroupList);
    }

    public List<String> getStudentgroup() {
        return studentgroupList;
    }

    public List<List<String>> skeletDay(String studentgroup) {
        List<List<String>> day = new ArrayList<>();

        List<String> para0 = new ArrayList<>(5); // "09:00 - 11:00" , номер группы, предмет препод аудитория
        para0.add("09:00 - 11:00");
        para0.add(studentgroup);
        List<String> para1 = new ArrayList<>(5);
        para1.add("11:00 - 13:00");
        para1.add(studentgroup);
        List<String> para2 = new ArrayList<>(5);
        para2.add("13:00 - 15:00");
        para2.add(studentgroup);
        List<String> para3 = new ArrayList<>(5);
        para3.add("15:00 - 17:00");
        para3.add(studentgroup);

        day.add(para0);
        day.add(para1);
        day.add(para2);
        day.add(para3);

        return day;
    }

    public void skeletWeek(List<String> studentgroup) {
        List<String> studentgroupList = studentgroup;
        Collections.sort(studentgroupList);

        for (int j = 0; j < 5; j++) {

            Map<Integer, List<List<String>>> dayMap = new LinkedHashMap<>();
            int i = 0;
            for (String s : studentgroup) {
                dayMap.put(i, skeletDay(s));
                i++;
            }

            dayMap.forEach((k, v) -> {
                System.out.println("<<< " + k);
            });
            weekList.add(dayMap);
        }
    }

    public List<Map<Integer, List<List<String>>>> getWeekList() {
        return weekList;
    }

    public void addTeachers(List<String> teachers) {
        List<String> teachersList = teachers;
//        Collections.sort(teachersList);
        for (String s : teachersList) {
            System.out.println(s);
        }

        for (int j = 0; j < weekList.size(); j++) {

            Map<Integer, List<List<String>>> dayMap = getWeekList().get(j); // day
            int groups = dayMap.size();
            int teachersInt = 0;
            for (int para = 0; para < 4; para++) {
                teachersInt = para;
            for (int i = 0; i < groups; i++) {

                    dayMap.get(i).get(para).add(2, teachersList.get(teachersInt));
                        teachersInt++;

                    if (teachersInt == teachersList.size()) {
                        teachersInt = 0;
                    }
                }
            }


//            weekList.add(dayMap);

                                        //day  group   para  teacher
            System.out.println(weekList.get(0).get(0).get(0));
            System.out.println(weekList.get(0).get(0).get(1));
            System.out.println(weekList.get(0).get(0).get(2));
            System.out.println(weekList.get(0).get(0).get(3));
//            System.out.println("Tuesday");
//            System.out.println(weekList.get(1).get(0).get(0));
//            System.out.println(weekList.get(1).get(0).get(1));
//            System.out.println(weekList.get(1).get(0).get(2));
//            System.out.println(weekList.get(1).get(0).get(3));
//            System.out.println("Wednesday");
//            System.out.println(weekList.get(2).get(0).get(0));
//            System.out.println(weekList.get(2).get(0).get(1));
//            System.out.println(weekList.get(2).get(0).get(2));
//            System.out.println(weekList.get(2).get(0).get(3));
//            System.out.println("Thursday");
//            System.out.println(weekList.get(3).get(0).get(0));
//            System.out.println(weekList.get(3).get(0).get(1));
//            System.out.println(weekList.get(3).get(0).get(2));
//            System.out.println(weekList.get(3).get(0).get(3));
//            System.out.println("Friday");
//            System.out.println(weekList.get(4).get(0).get(0));
//            System.out.println(weekList.get(4).get(0).get(1));
//            System.out.println(weekList.get(4).get(0).get(2));
//            System.out.println(weekList.get(4).get(0).get(3));
            System.out.println("2");
            System.out.println(weekList.get(0).get(1).get(0));
            System.out.println(weekList.get(0).get(1).get(1));
            System.out.println(weekList.get(0).get(1).get(2));
            System.out.println(weekList.get(0).get(1).get(3));
            System.out.println("3");
            System.out.println(weekList.get(0).get(2).get(0));
            System.out.println(weekList.get(0).get(2).get(1));
            System.out.println(weekList.get(0).get(2).get(2));
            System.out.println(weekList.get(0).get(2).get(3));
            System.out.println("4");
            System.out.println(weekList.get(0).get(3).get(0));
            System.out.println(weekList.get(0).get(3).get(1));
            System.out.println(weekList.get(0).get(3).get(2));
            System.out.println(weekList.get(0).get(3).get(3));

//            System.out.println(dayMap.get(0).get(0).get(2));
//            System.out.println(dayMap.get(0).get(1).get(2));
//            System.out.println(dayMap.get(0).get(0).get(1));
//            System.out.println(dayMap.get(1).get(1).get(1));

            if((dayMap.get(0).get(0).get(2)).equals(dayMap.get(1).get(0).get(2))) {
                System.out.println("YEAH");
                dayMap.get(1).get(0).set(2, "!!!!!!!");
            }

            }
        System.out.println("=================================" + thursdayTeachers.size());
        List<String> thur = thursdayTeachers.stream().map(String::toString).collect(Collectors.toList());
        for(String s : thur) {
            System.out.println("------------");
            System.out.println(s);

        }


    }







}


