package com.application.relojesservice.repository;

import com.application.relojesservice.entities.DatoHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatoHoraRepository extends JpaRepository<DatoHora, Integer> {
    List<DatoHora> findByRut(String empleadoRut);
}
