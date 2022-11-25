package com.application.justificativoservice.service;

import com.application.justificativoservice.entities.Justificativo;
import com.application.justificativoservice.repository.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JustificativoServiceImp implements JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    @Override
    public List<Justificativo> getAll() {
        return justificativoRepository.findAll();
    }

    @Override
    public Justificativo getJustificativoById(int id) {
        return justificativoRepository.findById(id).orElse(null);
    }
    @Override
    public List<Justificativo> getJustificativoByRut(String rut) {
        return justificativoRepository.findByRut(rut);
    }

    @Override
    public Justificativo save(Justificativo justificativo) {
        Justificativo justificativoNew = justificativoRepository.save(justificativo);
        return justificativoNew;
    }

}
