package com.application.sobretiemposervice.repository;

import com.application.sobretiemposervice.entities.HrExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrExtraRepository extends JpaRepository<HrExtra, Integer> {

    List<HrExtra> findByRut(String empleadoRut);
}
