package com.application.relojesservice.service;

import com.application.relojesservice.entities.Empleado;
import com.application.relojesservice.repository.DatoHoraRepository;
import com.application.relojesservice.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmpleadoServiceImp implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    DatoHoraRepository datoHoraRepository;

    @Override
    public List<Empleado> getAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }
    @Override
    public Empleado getEmpleadoByRut(String rut) {
        return empleadoRepository.findByRut(rut);
    }

    @Override
    public Empleado save(Empleado empleado) {
        Empleado empleadoNew = empleadoRepository.save(empleado);
        return empleadoNew;
    }

}
