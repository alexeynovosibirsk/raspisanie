package com.example.application.data.service;

import com.example.application.data.entity.Studentgroup;
import com.example.application.data.repository.StudentgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentgroupService {

    @Autowired
    private StudentgroupRepository studentgroupRepository;

    public List<Studentgroup> findAll() {
        return studentgroupRepository.findAll();
    }

    public Long countAll() {
        return studentgroupRepository.count();
    }

    public List<Studentgroup> selectAll() {
        return studentgroupRepository.selectAll();
    }

    public List<Studentgroup> findByNumber(String number) {
        return studentgroupRepository.findByNumber(number);
    }


}
