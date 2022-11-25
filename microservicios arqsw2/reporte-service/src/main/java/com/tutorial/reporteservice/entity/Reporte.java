package com.tutorial.reporteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gen_reporte")
    @Column(unique = true, nullable = false)
    private int id;

    @Column(name="rut")
    private String rut;

    @Column(name="nombre_completo")
    private String nombreCompleto;

    @Column(name="categoria")
    private String categoria;

    @Column(name="servicio_empresa")
    private int servicioEmpresa;

    @Column(name="sueldo_fijo_mensual")
    private int sueldoFijoMensual;

    @Column(name="bonificacion_hrs_extras")
    private double bonificacionHrsExtras;

    @Column(name="bonificacion_tiempo_servicio")
    private int bonificacionTiempoServicio;

    @Column(name="bonificacion_puntualidad")
    private double bonificacionPuntualidad;

    @Column(name="descuento_tardanza")
    private double descuentoTardanza;

    @Column(name="descuento_retiro")
    private double descuentoRetiro;

    @Column(name="sueldo_bruto")
    private double sueldoBruto;

    @Column(name="cotizacion_previsional")
    private double cotizacionPrevisional;

    @Column(name="cotizacion_salud")
    private double cotizacionSalud;

    @Column(name="monto_sueldo_final")
    private double montoSueldoFinal;

    public Reporte(){}

    public Reporte(String rut, String nombreCompleto, String categoria, int servicioEmpresa, int sueldoFijoMensual, double bonificacionHrsExtras, int bonificacionTiempoServicio, double bonificacionPuntualidad, double descuentoTardanza, double descuentoRetiro, double sueldoBruto, double cotizacionPrevisional, double cotizacionSalud, double montoSueldoFinal) {
        super();
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.categoria = categoria;
        this.servicioEmpresa = servicioEmpresa;
        this.sueldoFijoMensual = sueldoFijoMensual;
        this.bonificacionHrsExtras = bonificacionHrsExtras;
        this.bonificacionTiempoServicio = bonificacionTiempoServicio;
        this.bonificacionPuntualidad = bonificacionPuntualidad;
        this.descuentoTardanza = descuentoTardanza;
        this.descuentoRetiro = descuentoRetiro;
        this.sueldoBruto = sueldoBruto;
        this.cotizacionPrevisional = cotizacionPrevisional;
        this.cotizacionSalud = cotizacionSalud;
        this.montoSueldoFinal = montoSueldoFinal;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getServicioEmpresa() {
        return servicioEmpresa;
    }

    public void setServicioEmpresa(int servicioEmpresa) {
        this.servicioEmpresa = servicioEmpresa;
    }

    public int getSueldoFijoMensual() {
        return sueldoFijoMensual;
    }

    public void setSueldoFijoMensual(int sueldoFijoMensual) {
        this.sueldoFijoMensual = sueldoFijoMensual;
    }

    public double getBonificacionHrsExtras() {
        return bonificacionHrsExtras;
    }

    public void setBonificacionHrsExtras(double bonificacionHrsExtras) {
        this.bonificacionHrsExtras = bonificacionHrsExtras;
    }

    public int getBonificacionTiempoServicio() {
        return bonificacionTiempoServicio;
    }

    public void setBonificacionTiempoServicio(int bonificacionTiempoServicio) {
        this.bonificacionTiempoServicio = bonificacionTiempoServicio;
    }

    public double getBonificacionPuntualidad() {
        return bonificacionPuntualidad;
    }

    public void setBonificacionPuntualidad(double bonificacionPuntualidad) {
        this.bonificacionPuntualidad = bonificacionPuntualidad;
    }

    public double getDescuentoTardanza() {
        return descuentoTardanza;
    }

    public void setDescuentoTardanza(double descuentoTardanza) {
        this.descuentoTardanza = descuentoTardanza;
    }

    public double getDescuentoRetiro() {
        return descuentoRetiro;
    }

    public void setDescuentoRetiro(double descuentoRetiro) {
        this.descuentoRetiro = descuentoRetiro;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public double getCotizacionPrevisional() {
        return cotizacionPrevisional;
    }

    public void setCotizacionPrevisional(double cotizacionPrevisional) {
        this.cotizacionPrevisional = cotizacionPrevisional;
    }

    public double getCotizacionSalud() {
        return cotizacionSalud;
    }

    public void setCotizacionSalud(double cotizacionSalud) {
        this.cotizacionSalud = cotizacionSalud;
    }

    public double getMontoSueldoFinal() {
        return montoSueldoFinal;
    }

    public void setMontoSueldoFinal(double montoSueldoFinal) {
        this.montoSueldoFinal = montoSueldoFinal;
    }
}
