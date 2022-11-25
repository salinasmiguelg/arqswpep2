package com.tutorial.reporteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrExtra {
    private String rut;
    private String fecha;
    private int horasAutorizadas;
    private String descripcion;
}
