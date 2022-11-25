package com.application.relojesservice.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_empleado")
    @Column(name="id")
    private int id;

    @Column(name="rut")
    private String rut;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name="categoria")
    private String categoria;

    @Column(name="fecha_ingreso")
    private String fechaIngreso;

    @Column(name="area_trabajo")
    private String areaTrabajo;

    @Transient
    private MultipartFile file;

    public Empleado(){}

    public Empleado(String rut, String nombre, String apellido, String fechaNacimiento, String categoria, String fechaIngreso, String areaTrabajo, String fileType) {
        super();
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.categoria = categoria;
        this.fechaIngreso = fechaIngreso;
        this.areaTrabajo = areaTrabajo;
        /*this.fileType = fileType;*/
    }

    /* Getters and setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

}
