package com.application.justificativoservice.service;

import com.application.justificativoservice.entities.Justificativo;

import java.util.List;

public interface JustificativoService {
    List<Justificativo> getAll();

    Justificativo getJustificativoById(int id);

    List<Justificativo> getJustificativoByRut(String rut);

    Justificativo save(Justificativo justificativo);

}
