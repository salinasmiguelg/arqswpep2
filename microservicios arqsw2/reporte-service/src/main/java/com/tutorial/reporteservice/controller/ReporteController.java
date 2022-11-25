package com.tutorial.reporteservice.controller;

import com.tutorial.reporteservice.entity.Reporte;
import com.tutorial.reporteservice.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    ResultadoService resultadoService;

    @GetMapping
    public ResponseEntity<List<Reporte>> getAll() {
        List<Reporte> reportes = resultadoService.getAll();
        if(reportes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getById(@PathVariable("id") int id) {
        Reporte reporte = resultadoService.getReporteById(id);
        if(reporte == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reporte);
    }

    @PostMapping()
    public ResponseEntity<Reporte> save(@RequestBody Reporte reporte) {
        Reporte reporteNew = resultadoService.save(reporte);
        return ResponseEntity.ok(reporteNew);
    }

    @PostMapping("/calcularReportes")
    public ResponseEntity<List<Reporte>> calcularGuardarReportes() {
        List<Reporte> reportes = resultadoService.calcularReportes();
        for (Reporte reporte: reportes) {
            resultadoService.save(reporte);
        }
        return ResponseEntity.ok(reportes);
    }

}
