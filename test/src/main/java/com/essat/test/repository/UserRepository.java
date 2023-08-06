package com.essat.test.repository;

import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.essat.test.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT p FROM User p WHERE p.datedeb=:x and p.datefin=:y and p.id=:r")
     User ccccc(@Param("x") String x,@Param("y") String y,@Param("r") int id);

}
