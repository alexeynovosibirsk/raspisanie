package com.example.application.data.repository;

import com.example.application.data.entity.Studentgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentgroupRepository extends JpaRepository<Studentgroup, Long> {

    @Query(value = "SELECT * FROM Studentgroup",
    nativeQuery = true)
    List<Studentgroup> selectAll();

    List<Studentgroup> findByNumber(String number);

}
