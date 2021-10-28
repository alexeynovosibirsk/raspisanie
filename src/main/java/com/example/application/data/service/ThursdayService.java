package com.example.application.data.service;

import com.example.application.data.entity.Teachers;
import com.example.application.data.entity.Thursday;
import com.example.application.data.repository.TeachersRepository;
import com.example.application.data.repository.ThursdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThursdayService {

    @Autowired
    private ThursdayRepository thursdayRepository;

    public List<Thursday> findAll() {
        return thursdayRepository.findAll();
    }

    public Long countAll() {
        return thursdayRepository.count();
    }

    public List<Thursday> selectAll() {
        return thursdayRepository.selectAll();
    }

    public List<Thursday> findByName(String name) {
        return thursdayRepository.findByName(name);
    }

    public void insert(String name) {
        thursdayRepository.insert(name);
    }
}