package com.application.relojesservice.entities;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@CrossOrigin
@Entity
@Table(name="dato_hora")
public class DatoHora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_dato_hora")
    @Column(name="id")
    private int id;

    @Column(name="fecha")
    private String fecha;

    @Column(name="hora")
    private String hora;

    @Column(name="rut")
    private String rut;

    @Column(name="fileType")
    private String fileType;
    
    @Transient
    private MultipartFile file;
    public DatoHora(){}

    public DatoHora(String fecha, String hora, String rut, String fileType) {
        super();
        this.fecha = fecha;
        this.hora = hora;
        this.rut = rut;
        this.fileType = fileType;
    }

    /* Getters and setters*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
