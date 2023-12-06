package com.example.controladorprocesos.controllers;

import com.example.controladorprocesos.model.*;

import java.util.List;

public class UsuarioController {
    ModelFactoryController modelFactoryController;
    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }
    public List<Proceso> buscarProcesoNombre(String nombreProceso) {
        return modelFactoryController.buscarProcesosNombre(nombreProceso);
    }

    public Proceso buscarProcesoId(int idProceso) {
        return modelFactoryController.buscarProcesoId(idProceso);
    }

    public List<Actividad> buscarActividadesNombre(String nombre) {
        return modelFactoryController.gestor.buscarActividadesPorNombre(nombre);
    }

    public List<Tarea> buscarTareasNombre(String nombre) {
        return modelFactoryController.gestor.buscarTareasPorNombre(nombre);
    }

    public void exportarDatosExcel() {
        modelFactoryController.exportarDatosExcel();
    }
}
