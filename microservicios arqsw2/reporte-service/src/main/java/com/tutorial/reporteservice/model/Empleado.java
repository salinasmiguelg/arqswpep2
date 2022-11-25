package com.tutorial.reporteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private String rut;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String categoria;
    private String fechaIngreso;
    private String areaTrabajo;
}
