package com.example.application.data.service;

import com.example.application.data.entity.Raspisanie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaspisanieService {

    @Autowired
    private RaspisanieRepository raspisanieRepository;

    public List<Raspisanie> findAll() {
        return raspisanieRepository.findAll();
    }

}
