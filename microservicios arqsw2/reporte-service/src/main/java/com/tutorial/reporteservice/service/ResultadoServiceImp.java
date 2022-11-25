package com.tutorial.reporteservice.service;


import com.tutorial.reporteservice.model.Empleado;
import com.tutorial.reporteservice.entity.Reporte;
import com.tutorial.reporteservice.model.DatoHora;

import com.tutorial.reporteservice.model.HrExtra;
import com.tutorial.reporteservice.model.Justificativo;
import com.tutorial.reporteservice.repository.ReporteRepository;
import com.tutorial.reporteservice.service.algoritmosSueldo.AlgoritmosCalculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ResultadoServiceImp implements ResultadoService {

    @Autowired
    ReporteRepository reporteRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Reporte> getAll() {
        return reporteRepository.findAll();
    }

    @Override
    public Reporte getReporteById(int id) {
        return reporteRepository.findById(id).orElse(null);
    }

    @Override
    public Reporte save(Reporte reporte) {
        Reporte reporteNew = reporteRepository.save(reporte);
        return reporteNew;
    }

    @Override
    public List<Reporte> calcularReportes(){

        Empleado[] empleados = restTemplate.getForObject("http://relojes-service/relojes/empleado/", Empleado[].class);

        DatoHora[] ListadoDatoHoras = restTemplate.getForObject("http://relojes-service/relojes/datoHora/", DatoHora[].class);

        Justificativo[] ListadoJustificativos = restTemplate.getForObject("http://justificativo-service/justificativo/todos", Justificativo[].class);

        HrExtra[] ListadoHrExtras = restTemplate.getForObject("http://sobretiempo-service/sobretiempo/", HrExtra[].class);


        List<Reporte> reportesSueldosEmpleados = new ArrayList<>();
        AlgoritmosCalculo algoritmosCalculo = new AlgoritmosCalculo();

        for (Empleado empleado: empleados) {

            String empleadoRut = empleado.getRut();
            System.out.println("RUT: " + empleadoRut+ "\n");



            List<DatoHora> datoHorasEmpleado = new ArrayList<>();
            for (DatoHora datoHora: ListadoDatoHoras) {
                if (datoHora.getRut().equals(empleadoRut)){
                    System.out.println("Rut match datoHora " + datoHora.getRut() + "\n");
                    datoHorasEmpleado.add(datoHora);
                }
            }

            List<Justificativo> justificativosEmpleado = new ArrayList<>();
            for (Justificativo justificativo: ListadoJustificativos) {
                if (justificativo.getRut().equals(empleadoRut)){
                    System.out.println("Rut match Justif " + justificativo.getRut() + "\n");
                    justificativosEmpleado.add(justificativo);
                }
            }

            List<HrExtra> hrExtrasEmpleado = new ArrayList<>();
            for (HrExtra hrExtra: ListadoHrExtras) {
                if (hrExtra.getRut().equals(empleadoRut)){
                    System.out.println("Rut match HrExtra " + hrExtra.getRut() + "\n");
                    hrExtrasEmpleado.add(hrExtra);
                }
            }

            Reporte reporteSueldoEmpleado = new Reporte();

            if (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("A")){

                reportesSueldosEmpleados.add(algoritmosCalculo.AreaAdminCategoriaA(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            } else if  (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("B")) {
                reportesSueldosEmpleados.add(algoritmosCalculo.AreaAdminCategoriaB(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            } else if  (empleado.getAreaTrabajo().equals("Administracion") && empleado.getCategoria().equals("C")) {
                reportesSueldosEmpleados.add(algoritmosCalculo.AreaAdminCategoriaC(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            } else if  (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("A")) {
                reportesSueldosEmpleados.add(algoritmosCalculo.AreaOperCategoriaA(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            } else if (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("B")) {
                reportesSueldosEmpleados.add(algoritmosCalculo.AreaOperCategoriaB(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            } else if (empleado.getAreaTrabajo().equals("Operacion") && empleado.getCategoria().equals("C")) {
                reportesSueldosEmpleados.add(algoritmosCalculo.AreaOperCategoriaC(empleado,reporteSueldoEmpleado,datoHorasEmpleado,hrExtrasEmpleado,justificativosEmpleado));

            }

        }
        return reportesSueldosEmpleados;
    }

}
