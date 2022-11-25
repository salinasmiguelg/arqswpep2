package com.application.relojesservice.service;

import com.application.relojesservice.entities.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> getAll();
    Empleado save(Empleado empleado);
    Empleado getEmpleadoById(int id);

    Empleado getEmpleadoByRut(String rut);

}
