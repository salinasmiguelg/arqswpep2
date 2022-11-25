package com.application.sobretiemposervice.service;

import com.application.sobretiemposervice.entities.HrExtra;
import com.application.sobretiemposervice.repository.HrExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HrExtraServiceImp implements HrExtraService {
    @Autowired
    HrExtraRepository hrExtraRepository;

    @Override
    public List<HrExtra> getAll() {
        return hrExtraRepository.findAll();
    }

    @Override
    public HrExtra getHrExtraById(int id) {
        return hrExtraRepository.findById(id).orElse(null);
    }
    @Override
    public List<HrExtra> getHrExtraByRut(String rut) {
        return hrExtraRepository.findByRut(rut);
    }

    @Override
    public HrExtra save(HrExtra hrExtra) {
        HrExtra hrExtraNew = hrExtraRepository.save(hrExtra);
        return hrExtraNew;
    }

}
