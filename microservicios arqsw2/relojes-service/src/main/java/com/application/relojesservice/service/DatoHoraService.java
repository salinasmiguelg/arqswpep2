package com.application.relojesservice.service;

import com.application.relojesservice.entities.DatoHora;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DatoHoraService {

    List<DatoHora> getAll();

    DatoHora getDatoHoraById(int id);

    DatoHora save(DatoHora datoHora) ;

    List<DatoHora> byEmpleadoRun(String empleadoRut);

    boolean saveDataFromUploadFile(MultipartFile file);
}
