package com.tutorial.reporteservice.service.algoritmosSueldo;

import com.tutorial.reporteservice.entity.Reporte;
import com.tutorial.reporteservice.model.DatoHora;
import com.tutorial.reporteservice.model.Empleado;
import com.tutorial.reporteservice.model.HrExtra;
import com.tutorial.reporteservice.model.Justificativo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlgoritmosCalculo {

    private AlgsCalcCompReportes algsCalcCompReportes = new AlgsCalcCompReportes();

    public Reporte AreaAdminCategoriaA(Empleado empleado, Reporte reporte,
                                       List<DatoHora> datoHoras,
                                       List<HrExtra> hrExtras,
                                       List<Justificativo> justificativos) {
        int sueldoFijoMensual = 1700000;

        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);
        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;
    }

    public Reporte AreaAdminCategoriaB(Empleado empleado, Reporte reporte,
                                       List<DatoHora> datoHoras,
                                       List<HrExtra> hrExtras,
                                       List<Justificativo> justificativos) {
        int sueldoFijoMensual = 1200000;


        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);
        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;
    }


    public Reporte AreaAdminCategoriaC(Empleado empleado, Reporte reporte,
                                       List<DatoHora> datoHoras,
                                       List<HrExtra> hrExtras,
                                       List<Justificativo> justificativos) {
        int sueldoFijoMensual = 800000;
        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);

        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;
    }


    public Reporte AreaOperCategoriaA(Empleado empleado, Reporte reporte,
                                      List<DatoHora> datoHoras,
                                      List<HrExtra> hrExtras,
                                      List<Justificativo> justificativos) {
        int sueldoFijoMensual = 2300000;
        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);
        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;
    }


    public Reporte AreaOperCategoriaB(Empleado empleado, Reporte reporte,
                                      List<DatoHora> datoHoras,
                                      List<HrExtra> hrExtras,
                                      List<Justificativo> justificativos) {
        int sueldoFijoMensual = 1600000;
        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);
        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;

    }

    public Reporte AreaOperCategoriaC(Empleado empleado, Reporte reporte,
                                      List<DatoHora> datoHoras,
                                      List<HrExtra> hrExtras,
                                      List<Justificativo> justificativos){
        int sueldoFijoMensual = 900000;
        List<Double> componentes = algsCalcCompReportes.calculoConReloj(empleado, sueldoFijoMensual, datoHoras, hrExtras, justificativos);
        int tiempoServicio = algsCalcCompReportes.tiempoServicio(empleado);
        int bonifTiempoServicio = algsCalcCompReportes.bonifTiempoServicio(sueldoFijoMensual,tiempoServicio);
        double bonifPuntualidad = sueldoFijoMensual*algsCalcCompReportes.bonifPuntualidad(datoHoras);
        double descuentoTardanza = componentes.get(0);
        double descuentoRetiro = componentes.get(1);
        double bonifHrExtras = componentes.get(3);
        double sueldoBruto =  sueldoFijoMensual + bonifHrExtras + bonifTiempoServicio
                + bonifPuntualidad - (descuentoTardanza + descuentoRetiro);

        if (sueldoBruto < 0){
            sueldoBruto = 0;
        }
        double cotizacionPrevisional = algsCalcCompReportes.cotizacionPrevisional(empleado, (int) sueldoBruto);
        double cotizacionSalud = algsCalcCompReportes.cotizacionSalud(empleado, (int) sueldoBruto);
        double sueldoFinal = sueldoBruto - cotizacionPrevisional - cotizacionSalud;

        reporte.setRut(empleado.getRut());
        reporte.setNombreCompleto(empleado.getNombre() + " " + empleado.getApellido());
        reporte.setCategoria(empleado.getCategoria());
        reporte.setServicioEmpresa(tiempoServicio);
        reporte.setSueldoFijoMensual(sueldoFijoMensual);
        reporte.setBonificacionHrsExtras(bonifHrExtras);
        reporte.setBonificacionTiempoServicio(bonifTiempoServicio);
        reporte.setBonificacionPuntualidad(bonifPuntualidad);
        reporte.setDescuentoTardanza(descuentoTardanza);
        reporte.setDescuentoRetiro(descuentoRetiro);
        reporte.setSueldoBruto(sueldoBruto);
        reporte.setCotizacionPrevisional(cotizacionPrevisional);
        reporte.setCotizacionSalud(cotizacionSalud);
        reporte.setMontoSueldoFinal(sueldoFinal);

        return reporte;

    }






}
