package com.tutorial.reporteservice.service.algoritmosSueldo;

import com.tutorial.reporteservice.model.HrExtra;
import com.tutorial.reporteservice.model.Empleado;
import com.tutorial.reporteservice.model.DatoHora;
import com.tutorial.reporteservice.model.Justificativo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlgsCalcCompReportes {


    public int tiempoServicio(Empleado empleado) {
        String ingresoEmpleado = empleado.getFechaIngreso();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaIngreso = LocalDate.parse(ingresoEmpleado,formatter);
        System.out.println(fechaIngreso);
        LocalDate fechaHoy = LocalDate.now();
        int tiempoServicio = Period.between(fechaIngreso, fechaHoy).getYears();
        return tiempoServicio;
    }


    public int bonifTiempoServicio(int sueldoFijo, int tiempoServicio){
        if(tiempoServicio < 5){
            return 0;
        } else if (5<=tiempoServicio && tiempoServicio<10) {
            return (int) (sueldoFijo*0.05);
        } else if (10<=tiempoServicio && tiempoServicio<15) {
            return (int) (sueldoFijo*0.08);
        } else if (15<=tiempoServicio && tiempoServicio<20) {
            return (int) (sueldoFijo*0.11);
        } else if (20<=tiempoServicio && tiempoServicio<25) {
            return (int) (sueldoFijo*0.14);
        }else{
            return (int) (sueldoFijo*0.17);
        }
    }


    public double cotizacionPrevisional(Empleado empleado, double sueldoBruto){
        String ingresoEmpleado = empleado.getFechaIngreso();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaIngreso = LocalDate.parse(ingresoEmpleado,formatter);
        int annoIngreso = fechaIngreso.getYear();

        if(annoIngreso < 1980){
            return (sueldoBruto*0.07);
        } else if (1980 <= annoIngreso && annoIngreso < 2000) {
            return (sueldoBruto*0.09);
        } else {
            return (sueldoBruto*0.1);
        }
    }


    public double cotizacionSalud(Empleado empleado, double sueldoBruto){
        String ingresoEmpleado = empleado.getFechaIngreso();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaIngreso = LocalDate.parse(ingresoEmpleado,formatter);
        int annoIngreso = fechaIngreso.getYear();

        if(annoIngreso < 1980){
            return  (sueldoBruto*0.07);
        } else if (1980 <= annoIngreso && annoIngreso < 2000) {
            return  (sueldoBruto*0.08);
        } else {
            return (sueldoBruto*0.08);
        }
    }



    public double bonifPuntualidad(List<DatoHora> datoHora) {
        // Retorno 0 si not tiene bonif, 1 si tiene >90 y 2 si tiene >80
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horaEntrada = "08:00";
        String horaSalida = "18:00";


        int numDiasMarcados = datoHora.size()/2;
        int contadorEntradaPuntual = 0;
        int contadorSalidaPuntual = 0;
        int j = 0;
        for (int i = 0; i < numDiasMarcados; i++) {
            try {
                Date horaEnt = dateFormat.parse(horaEntrada);
                Date horaSal = dateFormat.parse(horaSalida);

                // Considerando que no hay desorden en las marcas de horas
                String marcaEntrada = datoHora.get(j).getHora();
                String marcaSalida = datoHora.get(j + 1).getHora();
                Date marcaEnt = dateFormat.parse(marcaEntrada);
                Date marcaSal = dateFormat.parse(marcaSalida);

                if (marcaEnt.compareTo(horaEnt) < 0 || marcaEnt.compareTo(horaEnt) == 0) {
                    contadorEntradaPuntual++;
                }

                if (marcaSal.compareTo(horaSal) > 0 || marcaSal.compareTo(horaSal) == 0) {
                    contadorSalidaPuntual++;
                }
            } catch (ParseException e) {
                System.out.println("Error en el formato de marca");
                return 0;
            }

            j = j + 2;
        }

        // Considerando 20 días habiles, 90% (18) y 80%(16)
        if (contadorEntradaPuntual > 18 && contadorSalidaPuntual > 18) {
            System.out.println("salida 1");
            double bonif = 0.08;
            return bonif;
        } else if (contadorEntradaPuntual > 16 && contadorSalidaPuntual > 16) {
            System.out.println("salida 2");
            double bonif = 0.05;
            return bonif;
        } else {
            System.out.println("salida 3");
            double bonif = 0;
            return bonif;
        }
    }


    public List<Double> calculoConReloj(Empleado empleado, int sueldoFijo, List<DatoHora> datoHoras,
                                  List<HrExtra> hrExtras, List<Justificativo> justificativos){
        System.out.println("\n---> Entre :D\n");
        // index 0 = desc tardanza
        // index 1 = desc retiro
        // index 2 = desc inasistencia
        // index 3 = bonif hr extras
        List<Double> componentes = new ArrayList<>();

        int totalDias = 20;
        int diasAsistidos = datoHoras.size()/2;
        int diasInasistentes = totalDias - diasAsistidos;
        double descuentoTardanza = 0;
        double descuentoRetiro = 0;
        double descuentoInasistencia = 0;

        int j = 0;
        // Se itera por dias para calcular los descuentos por tardanza o retiro temprano
        for (int i = 0; i < diasAsistidos; i++){

            String marcaEntrada = datoHoras.get(j).getHora();
            String marcaSalida = datoHoras.get(j + 1).getHora();
            boolean huboAtraso = verificarTardanza(marcaEntrada);
            boolean huboRetiro = verificarRetiroTemprano(marcaSalida);

            if (huboAtraso || huboRetiro){
                int tipoAtraso = verificarTipoTardanza(marcaEntrada);
                int tipoRetiro = verificarTipoRetiroTemp(marcaSalida);
                int casoActual = casos(tipoAtraso,tipoRetiro);
                switch (casoActual){
                    case 1:
                        descuentoRetiro = descuentoRetiro + 0.02*sueldoFijo;
                        break;
                    case 2:
                        descuentoRetiro = descuentoRetiro + 0.04*sueldoFijo;
                        break;
                    case 3:
                        descuentoRetiro = descuentoRetiro + 0.07*sueldoFijo;
                        break;
                    case 4:
                        diasInasistentes++;
                        break;
                    case 5:
                        descuentoTardanza = descuentoTardanza + 0.01*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.02*sueldoFijo;
                        break;
                    case 6:
                        descuentoTardanza = descuentoTardanza + 0.01*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.04*sueldoFijo;
                        break;
                    case 7:
                        descuentoTardanza = descuentoTardanza + 0.01*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.07*sueldoFijo;
                        break;
                    case 8:
                        diasInasistentes++;
                        break;
                    case 9:
                        descuentoTardanza = descuentoTardanza + 0.03*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.02*sueldoFijo;
                        break;
                    case 10:
                        descuentoTardanza = descuentoTardanza + 0.03*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.04*sueldoFijo;
                        break;
                    case 11:
                        descuentoTardanza = descuentoTardanza + 0.03*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.07*sueldoFijo;
                        break;
                    case 12:
                        diasInasistentes++;
                        break;

                    case 13:
                        descuentoTardanza = descuentoTardanza + 0.06*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.02*sueldoFijo;
                        break;
                    case 14:
                        descuentoTardanza = descuentoTardanza + 0.06*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.04*sueldoFijo;
                        break;
                    case 15:
                        descuentoTardanza = descuentoTardanza + 0.06*sueldoFijo;
                        descuentoRetiro = descuentoRetiro + 0.07*sueldoFijo;
                        break;
                    case 16:
                        diasInasistentes++;
                        break;
                    case 17:
                        diasInasistentes++;
                        break;
                    case 18:
                        diasInasistentes++;
                        break;
                    case 19:
                        diasInasistentes++;
                        break;
                    case 20:
                        diasInasistentes++;
                        break;
                }
            }

            j = j + 2;
        }
        int cantJustificativos = justificativos.size();
        diasInasistentes = diasInasistentes - cantJustificativos;

        if (diasInasistentes > 0){
            descuentoInasistencia = diasInasistentes * sueldoFijo * 0.15;
        }

        componentes.add(descuentoTardanza);
        componentes.add(descuentoRetiro);
        componentes.add(descuentoInasistencia);

        double hrsExtrasHechas = horasExtrasHechas(datoHoras,hrExtras);
        double bonifHrExtras = 0;

        if (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("A")){
            bonifHrExtras = hrsExtrasHechas * 35000;
        } else if  (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("B")) {
            bonifHrExtras = hrsExtrasHechas * 25000;
        } else if  (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("C")) {
            bonifHrExtras = hrsExtrasHechas * 15000;
        } else if  (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("A")) {
            bonifHrExtras = hrsExtrasHechas * 55000;
        } else if (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("B")) {
            bonifHrExtras = hrsExtrasHechas * 40000;
        } else if (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("C")) {
            bonifHrExtras = hrsExtrasHechas * 25000;
        }

        componentes.add(bonifHrExtras);
        System.out.println("\n---> " + descuentoTardanza);
        System.out.println("\n---> " + descuentoRetiro);
        System.out.println("\n---> " + descuentoInasistencia);
        System.out.println("\n---> " + bonifHrExtras);
        System.out.println("\n---> Sali :D\n");
        return componentes;
    }

    public static int casos(int tipoAtraso, int tipoRetiro){
        if (tipoAtraso == 0 && tipoRetiro == 1){ //1
            return 1;
        } else if (tipoAtraso == 0 && tipoRetiro == 2) { //2
            return 2;
        } else if (tipoAtraso == 0 && tipoRetiro == 3) { //3
            return 3;
        } else if (tipoAtraso == 0 && tipoRetiro == 4) { //4
            return 4;
        } else if (tipoAtraso == 1 && tipoRetiro == 1) { //5
            return 5;
        } else if (tipoAtraso == 1 && tipoRetiro == 2) { //6
            return 6;
        } else if (tipoAtraso == 1 && tipoRetiro == 3) { //7
            return 7;
        } else if (tipoAtraso == 1 && tipoRetiro == 4) { //8
            return 8;
        } else if (tipoAtraso == 2 && tipoRetiro == 1) { //9
            return 9;
        } else if (tipoAtraso == 2 && tipoRetiro == 2) { //10
            return 10;
        } else if (tipoAtraso == 2 && tipoRetiro == 3) { //11
            return 11;
        } else if (tipoAtraso == 2 && tipoRetiro == 4) { //12
            return 12;
        }  else if (tipoAtraso == 3 && tipoRetiro == 1) { //13
            return 13;
        }  else if (tipoAtraso == 3 && tipoRetiro == 2) { //14
            return 14;
        }  else if (tipoAtraso == 3 && tipoRetiro == 3) { //15
            return 15;
        }  else if (tipoAtraso == 3 && tipoRetiro == 4) { //16
            return 16;
        }  else if (tipoAtraso == 4 && tipoRetiro == 1) { //17
            return 17;
        }  else if (tipoAtraso == 4 && tipoRetiro == 2) { //18
            return 18;
        }  else if (tipoAtraso == 4 && tipoRetiro == 3) { //19
            return 19;
        }  else{ //20
            return 20;
        }
    }



    //Verifica si un dia hubo tardanza o no
    public static boolean verificarTardanza(String marcaEntrada){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horaEntrada = "08:00";
        try {
            Date horaEnt = dateFormat.parse(horaEntrada);
            Date marcaEnt = dateFormat.parse(marcaEntrada);

            if (marcaEnt.compareTo(horaEnt) > 0) { // marca despues de la hora -> atraso
                return true;
            }

        } catch (ParseException e) {
            System.out.println("Error en el formato de marca");
            return false;
        }

        return false;
    }

    //Verifica si un dia se considera insistente por retiro temprano
    public static boolean verificarRetiroTemprano(String marcaSalida){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horaSalida = "18:00";
        try {
            Date horaSal = dateFormat.parse(horaSalida);
            Date marcaSal = dateFormat.parse(marcaSalida);

            if (marcaSal.compareTo(horaSal) < 0 ) { //Marca de salida antes que la hora de salida -> retiro temprano
                return true;
            }

        } catch (ParseException e) {
            System.out.println("Error en el formato de marca");
            return false;
        }

        return false;
    }

    //Ver cual es el tipo de tardanza
    public static int verificarTipoTardanza(String marcaEntrada){
        int minutosAtraso = minutosAtrasado(marcaEntrada);
        if (0< minutosAtraso && minutosAtraso<10){
            return 0;
        } else if (10< minutosAtraso && minutosAtraso<25) {
            return 1;
        } else if (25< minutosAtraso && minutosAtraso<45) {
            return 2;
        } else if (45< minutosAtraso && minutosAtraso<70) {
            return 3;
        }else {
            return 4;
        }
    }

    //Verifica si un dia se considera insistente por retiro temprano
    public static int verificarTipoRetiroTemp(String marcaSalida){
        int minutosRetiro = minutosRetiroTemprano(marcaSalida);
        if (0< minutosRetiro && minutosRetiro<=15){
            return 1;
        } else if (16<= minutosRetiro && minutosRetiro<=30) {
            return 2;
        } else if (31<= minutosRetiro && minutosRetiro<=45) {
            return 3;
        } else {
            return 4;
        }

    }

    public static int minutosAtrasado(String marcaEntrada){
        int minutosHoraEntrada = 480;
        String[] tiemposMarca = marcaEntrada.split(":");
        int horas;
        int minutos;
        int totalMinutos;
        try{
            horas = Integer.valueOf(tiemposMarca[0]);
            minutos = Integer.valueOf(tiemposMarca[1]);
            totalMinutos = minutos + 60*horas;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            return 0;
        }

        int minutosAtraso = totalMinutos - minutosHoraEntrada;

        return minutosAtraso;
    }

    public static int minutosRetiroTemprano(String marcaSalida){
        int minutosHoraSalida = 1080; //18:00 son 1080 min
        String[] tiemposMarca = marcaSalida.split(":");
        int horas;
        int minutos;
        int totalMinutos;
        try{
            horas = Integer.valueOf(tiemposMarca[0]);
            minutos = Integer.valueOf(tiemposMarca[1]);
            totalMinutos = minutos + 60*horas;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            return 0;
        }

        int minutostempranos = minutosHoraSalida - totalMinutos;

        return minutostempranos;
    }


    public static int minutosTrasHoraSalida(String marcaSalida){
        int minutosHoraSalida = 1080; //18:00 son 1080 min
        String[] tiemposMarca = marcaSalida.split(":");
        int horas;
        int minutos;
        int totalMinutos;
        try{
            horas = Integer.valueOf(tiemposMarca[0]);
            minutos = Integer.valueOf(tiemposMarca[1]);
            totalMinutos = minutos + 60*horas;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            return 0;
        }

        int minutosExtras = totalMinutos - minutosHoraSalida;

        return minutosExtras;
    }


    public static double horasExtrasHechas(List<DatoHora> datoHoras, List<HrExtra> hrExtras){
        String marcaSalida;
        int minutosExtras = 0;
        double horasExt = 0;
        double decimal = 0;
        double horasExtrasHechas = 0;


        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String horaSalida = "18:00";

        int numDiasMarcados = datoHoras.size()/2;
        int j = 0;
        for (int i = 0; i < numDiasMarcados; i++) {

            try {
                marcaSalida = datoHoras.get(j + 1).getHora();
                Date horaSal = dateFormat.parse(horaSalida);
                Date marcaSal = dateFormat.parse(marcaSalida);


                if (marcaSal.compareTo(horaSal) > 0 ) {
                    minutosExtras = minutosTrasHoraSalida(marcaSalida);
                    horasExt = minutosExtras/60;
                    decimal = horasExt%1;
                    horasExtrasHechas = horasExtrasHechas + horasExt-decimal;
                }
            } catch (ParseException e) {
                System.out.println("Error en el formato de marca");
                return 0;
            }
            j = j + 2;
        }

        double horasAutorizadas = 0;
        for (HrExtra hrExtra : hrExtras) {
            horasAutorizadas = horasAutorizadas + hrExtra.getHorasAutorizadas();
        }

        if (horasExtrasHechas>=horasAutorizadas){
            return horasAutorizadas;
        }
        return 0;
    }

}
