package com.example.application.data.service;

import com.example.application.data.entity.Auditory;
import com.example.application.data.entity.Studentgroup;
import com.example.application.data.repository.AuditoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoryService {

    @Autowired
    private AuditoryRepository auditoryRepository;

    public List<Auditory> findAll() {
        return auditoryRepository.findAll();
    }

    public Long countAll() {
        return auditoryRepository.count();
    }

    public List<Auditory> selectAll() {
        return auditoryRepository.selectAll();
    }

    public List<Auditory> findByNumber(String number) {
        return auditoryRepository.findByNumber(number);
    }
}