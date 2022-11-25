package com.tutorial.reporteservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Justificativo {
    private String rut;
    private String fecha;
    private String descripcion;
}
