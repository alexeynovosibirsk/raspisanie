package com.example.application.data.raschet;

import com.example.application.data.entity.Auditory;
import com.example.application.data.entity.Studentgroup;
import com.example.application.data.service.AuditoryService;
import com.example.application.data.service.StudentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentgroupPool {

    @Autowired
    StudentgroupService studentgroupService;

    public List<String> getFromTable() {


        List<String> studentgroupList = studentgroupService.selectAll().stream().map(Studentgroup::getNumber).collect(Collectors.toList());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<" + studentgroupList.size());

        return studentgroupList;
    }
}
