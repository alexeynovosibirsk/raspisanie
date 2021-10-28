package com.example.application.data.templates;

//import com.example.application.data.entity.Studentgroup;
//import com.example.application.data.service.StudentgroupService;
//import com.example.application.views.bydate.ByDateView;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

//public class GridTemplates {
//    StudentgroupService studentgroupService = new StudentgroupService();
//    private List<String> raspisanieList = new ArrayList<>();
//
//    private Map<String, String> values = new HashMap<>();
//
//
//
//
//
//
//    public void set(String key, String val) {
//         values.put(key, val);
//    }
//
//    public String get(String key) {
//        return values.get(key);
//    }

//    private List<String> columns = new ArrayList<>(Arrays.asList(new String("Время")));
//    private StringBuilder sb;
//// Создает колонки
//    private void addColumns(String column) { columns.add(column);
//    }
//    //Заполняет колонки
//    public List<String> fillColumns() {
//
//        List<String> l = studentgroupService.findAll().stream().map(Studentgroup::getNumber).collect(Collectors.toList());
//        for(String s : l) {
//            System.out.println(s);
//        }
//
//        addColumns("group 2");
//
//        return columns;
//    }
//
//    public List<String> getColumn() { return columns; }
//
//    public int colAmount() {
//        return columns.size();
//    }
//
//
//    private void fillRaspisanie(String time, String group1, String group2) {
//
//
//            raspisanieList = new ArrayList<>(Arrays.asList(time, group1, group2));
//    }
//
//    public List<String> pool() {
//        fillRaspisanie("9:00 - 11:00", "Математика\r\n Иванов И.И. аудитория 2", "Алгебра, Петров П.П., аудитория 3");
//        fillRaspisanie("11-15", "", "");
//        fillRaspisanie("15-17", "sdfdsf", "sdfsdffd");
//        return raspisanieList;
//    }
//}
