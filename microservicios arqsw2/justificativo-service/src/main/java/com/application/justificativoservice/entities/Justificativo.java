package com.application.justificativoservice.entities;

import javax.persistence.*;

@Entity
@Table(name="justificativo")
public class Justificativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_justificativo")
    @Column(name="id")
    private int id;

    @Column(name="rut")
    private String rut;

    @Column(name="fecha")
    private String fecha;

    @Column(name="descripcion")
    private String descripcion;

    public Justificativo(){}

    public Justificativo(String rut, String fecha, String descripcion) {
        super();
        this.rut = rut;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
