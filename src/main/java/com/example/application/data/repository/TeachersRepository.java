package com.example.application.data.repository;

import com.example.application.data.entity.Teachers;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

public interface TeachersRepository extends JpaRepository<Teachers, Long> {

    @Query(value = "SELECT * FROM TEACHERS",
    nativeQuery = true)
    List<Teachers> selectAll();

    List<Teachers> findByName(String name);

    @Modifying
    @Query(value = "INSERT INTO TEACHERS (id, name) SELECT CONVERT (\"id\", INT), \"name\" FROM CSVREAD ('upload/преподы.csv', 'id,name', null)", nativeQuery = true)
    @Transactional
    void insert(@Param("file") String file);

    @Modifying
    @Query(value = "TRUNCATE TABLE TEACHERS", nativeQuery = true)
    public void truncate();


//
//    @Modifying
//    @Query(value = "INSERT INTO TEACHERS (name) AS SELECT * FROM CSVREAD(:file)", nativeQuery = true)



}
