package com.application.relojesservice.service;


import com.application.relojesservice.entities.DatoHora;
import com.application.relojesservice.repository.DatoHoraRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@Service
@Transactional
public class DatoHoraServiceImp implements DatoHoraService {

    @Autowired
    private DatoHoraRepository datoHoraRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<DatoHora> getAll() {return (List<DatoHora>) datoHoraRepository.findAll();}

    @Override
    public DatoHora getDatoHoraById(int id) {
        return datoHoraRepository.findById(id).orElse(null);
    }

    @Override
    public DatoHora save(DatoHora datoHora) {
        DatoHora datoHoraNew = datoHoraRepository.save(datoHora);
        return datoHoraNew;
    }

    @Override
    public List<DatoHora> byEmpleadoRun(String empleadoRut) {
        return datoHoraRepository.findByRut(empleadoRut);
    }

    @Override
    public boolean saveDataFromUploadFile(MultipartFile file) {
        boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equalsIgnoreCase("json")){
            isFlag = readDateFromJson(file);
        } else if (extension.equalsIgnoreCase("txt")) {
            isFlag = readDateFromCsv(file);
        }
        return isFlag;
    }

    private boolean readDateFromCsv(MultipartFile file) {
        try{
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVParser parser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
            List<String[]> rows = csvReader.readAll();
            for (String[] row:rows){
                datoHoraRepository.save(new DatoHora(row[0],row[1],row[2], FilenameUtils.getExtension(file.getOriginalFilename())));
            }
            return true;
        }catch (Exception e){
            System.out.println("Exception " + e);
            return false;
        }
    }

    private boolean readDateFromJson(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<DatoHora> datosHora = Arrays.asList(mapper.readValue(inputStream, DatoHora[].class));
            if (datosHora !=null && datosHora.size()>0){
                for (DatoHora datoHora: datosHora){
                    datoHora.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                    datoHoraRepository.save(datoHora);
                }
            }
            return true;
        }catch (Exception e){
            System.out.println("Exception " + e);
            return false;
        }
    }



}
