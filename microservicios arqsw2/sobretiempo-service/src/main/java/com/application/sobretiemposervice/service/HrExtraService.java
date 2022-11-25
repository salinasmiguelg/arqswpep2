package com.application.sobretiemposervice.service;

import com.application.sobretiemposervice.entities.HrExtra;

import java.util.List;

public interface HrExtraService {
    List<HrExtra> getAll();

    HrExtra getHrExtraById(int id);

    List<HrExtra> getHrExtraByRut(String rut);

    HrExtra save(HrExtra hrExtra);

}
