package com.tutorial.reporteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatoHora {
    private String fecha;
    private String hora;
    private String rut;
    private String fileType;
}
