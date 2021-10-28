package com.example.application.data.repository;

import com.example.application.data.entity.Auditory;
import com.example.application.data.entity.Studentgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditoryRepository extends JpaRepository<Auditory, Long> {

    @Query(value = "SELECT * FROM AUDITORY",
    nativeQuery = true)
    List<Auditory> selectAll();

    List<Auditory> findByNumber(String number);

}
