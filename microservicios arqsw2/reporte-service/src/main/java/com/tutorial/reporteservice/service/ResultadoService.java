package com.tutorial.reporteservice.service;

import com.tutorial.reporteservice.entity.Reporte;

import java.io.IOException;
import java.util.List;

public interface ResultadoService {
    List<Reporte> getAll();

    Reporte getReporteById(int id);

    Reporte save(Reporte reporte);

    public List<Reporte> calcularReportes();
}
