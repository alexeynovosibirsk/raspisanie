package com.example.application.data.repository;

import com.example.application.data.entity.Teachers;
import com.example.application.data.entity.Thursday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ThursdayRepository extends JpaRepository<Thursday, Long> {

    @Query(value = "SELECT * FROM THURSDAY",
    nativeQuery = true)
    List<Thursday> selectAll();

    List<Thursday> findByName(String name);

    @Modifying
    @Query(value = "INSERT INTO THURSDAY (name) VALUES (:name)", nativeQuery = true)
    @Transactional
    void insert(@Param("name") String name);





}
