package com.application.relojesservice.controller;

import com.application.relojesservice.entities.Empleado;
import com.application.relojesservice.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/relojes/empleado")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAll() {
        List<Empleado> empleados = empleadoService.getAll();
        if(empleados.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable("id") int id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if(empleado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/rut/{empleadorut}")
    public ResponseEntity<Empleado> getByRut(@PathVariable("empleadorut") String empleadorut) {
        Empleado empleado = empleadoService.getEmpleadoByRut(empleadorut);
        if(empleado == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empleado);
    }

    @PostMapping()
    public ResponseEntity<Empleado> save(@RequestBody Empleado empleado) {
        Empleado empleadoNew = empleadoService.save(empleado);
        return ResponseEntity.ok(empleadoNew);
    }
    @PostMapping("/saveEmpleados")
    public ResponseEntity<String> saveEmpleados(@RequestBody List<Empleado> empleados) {
        for (Empleado empleado: empleados) {
            Empleado empleadoNew = empleadoService.save(empleado);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Usuarios bien subidos :D");
    }
}
