package com.example.application.data.service;

import com.example.application.data.entity.Auditory;
import com.example.application.data.entity.Teachers;
import com.example.application.data.repository.AuditoryRepository;
import com.example.application.data.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
public class TeachersService {

    @Autowired
    private TeachersRepository teachersRepository;

    public List<Teachers> findAll() {
        return teachersRepository.findAll();
    }

    public Long countAll() {
        return teachersRepository.count();
    }

    public List<Teachers> selectAll() {
        return teachersRepository.selectAll();
    }

    public List<Teachers> findByName(String name) {
        return teachersRepository.findByName(name);
    }

    public void insert(String file) {
        teachersRepository.insert(file);
    }

    @Transactional
    public void truncate() {
        teachersRepository.truncate();
    }


}