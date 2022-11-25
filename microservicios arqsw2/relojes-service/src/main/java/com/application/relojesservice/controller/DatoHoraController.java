package com.application.relojesservice.controller;

import com.application.relojesservice.entities.DatoHora;
import com.application.relojesservice.service.DatoHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/relojes/datoHora")
public class DatoHoraController {

    @Autowired
    private DatoHoraService datoHoraService;

    @GetMapping
    public ResponseEntity<List<DatoHora>> getAll() {
        List<DatoHora> datoHoras = datoHoraService.getAll();
        if(datoHoras.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(datoHoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatoHora> getById(@PathVariable("id") int id) {
        DatoHora datoHora = datoHoraService.getDatoHoraById(id);
        if(datoHora == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(datoHora);
    }

    @GetMapping("/byempleadorut/{empleadoRut}")
    public ResponseEntity<List<DatoHora>> getByEmpleadoRut(@PathVariable("empleadoRut") String empleadoRut) {
        List<DatoHora> datoHoras = datoHoraService.byEmpleadoRun(empleadoRut);
        return ResponseEntity.ok(datoHoras);
    }

    @PostMapping()
    public ResponseEntity<DatoHora> save(@RequestBody DatoHora datoHora) {
        DatoHora datoHoraNew = datoHoraService.save(datoHora);
        return ResponseEntity.ok(datoHoraNew);
    }


    @PostMapping(value = "/datesupload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            datoHoraService.saveDataFromUploadFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("Bien subido :D");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error al subir el archivo");
        }
    }


}
