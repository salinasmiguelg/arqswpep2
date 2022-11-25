package com.application.sobretiemposervice.entities;

import javax.persistence.*;

@Entity
@Table(name="hrExtra")
public class HrExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_hrExtras")
    @Column(name="id")
    private int id;

    @Column(name="rut")
    private String rut;

    @Column(name="fecha")
    private String fecha;

    @Column(name="horas_autorizadas")
    private int horasAutorizadas;

    @Column(name="descripcion")
    private String descripcion;

    public HrExtra(){}

    public HrExtra(String rut, String fecha, int horasAutorizadas, String descripcion) {
        super();
        this.rut = rut;
        this.fecha = fecha;
        this.horasAutorizadas = horasAutorizadas;
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

    public int getHorasAutorizadas() {
        return horasAutorizadas;
    }

    public void setHorasAutorizadas(int horasAutorizadas) {
        this.horasAutorizadas = horasAutorizadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
