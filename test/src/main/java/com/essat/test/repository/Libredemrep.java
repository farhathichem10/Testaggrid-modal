package com.essat.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.essat.test.entity.Libre_demande;



public interface Libredemrep extends JpaRepository<Libre_demande, Long> {
	@Query("select a from Libre_demande a where a.idlibredemande=:x ")
	 public Libre_demande gettByid(@Param("x") Long mat);
	

}
