package com.example.application.data.raschet;

import com.example.application.data.entity.Auditory;
import com.example.application.data.service.AuditoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuditoryPool {

    @Autowired
    private AuditoryService auditoryService;

    public List<String> takeFromBase() {
        List<String> auditoryList = new ArrayList<>();
        auditoryList.addAll(auditoryService.findAll().stream().map(Auditory::getNumber).collect(Collectors.toList()));

        return auditoryList;
    }
}
