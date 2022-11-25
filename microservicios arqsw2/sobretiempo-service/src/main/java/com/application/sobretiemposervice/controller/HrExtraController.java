package com.application.sobretiemposervice.controller;

import com.application.sobretiemposervice.entities.HrExtra;
import com.application.sobretiemposervice.service.HrExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sobretiempo")
public class HrExtraController {
    @Autowired
    private HrExtraService hrExtraService;

    @GetMapping
    public ResponseEntity<List<HrExtra>> getAll() {
        List<HrExtra> hrExtras = hrExtraService.getAll();
        if(hrExtras.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(hrExtras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HrExtra> getById(@PathVariable("id") int id) {
        HrExtra hrExtra = hrExtraService.getHrExtraById(id);
        if(hrExtra == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(hrExtra);
    }

    @GetMapping("/byempleadorut/{empleadorut}")
    public ResponseEntity<List<HrExtra>> getByRut(@PathVariable("empleadorut") String empleadorut) {
        List<HrExtra> hrExtrasByRut = hrExtraService.getHrExtraByRut(empleadorut);
        if(hrExtrasByRut.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(hrExtrasByRut);
    }

    @PostMapping()
    public ResponseEntity<HrExtra> save(@RequestBody HrExtra hrExtra) {
        HrExtra hrExtraNew = hrExtraService.save(hrExtra);
        return ResponseEntity.ok(hrExtraNew);
    }

    @PostMapping("/saveHrExtras")
    public ResponseEntity<String> saveHrExtras(@RequestBody List<HrExtra> hrExtras) {
        for (HrExtra hrExtra: hrExtras) {
            HrExtra hrExtraNew = hrExtraService.save(hrExtra);
        }
        return ResponseEntity.status(HttpStatus.OK).body("HrExtras bien subidos :D");
    }

}
