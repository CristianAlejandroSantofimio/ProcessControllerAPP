package com.example.controladorprocesos.controllers;

import com.example.controladorprocesos.model.*;
import javafx.event.ActionEvent;

import java.util.List;

public class AdministradorController {
    ModelFactoryController modelFactoryController;
    public AdministradorController(){
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

    public Usuario buscarUsuario(String nombre) {
        return modelFactoryController.buscarUsuario(nombre);
    }

    public void eliminarUsuario(Usuario usuarioSeleccionado) {
        modelFactoryController.eliminarUsuario(usuarioSeleccionado);
    }

    public void agregarUsuario(Usuario usuario) {
        modelFactoryController.agregarUsuario(usuario);
    }

    public void eliminarTarea(Actividad actividad, Tarea tareaSeleccionada) {
        modelFactoryController.eliminarTarea(actividad, tareaSeleccionada);
    }

    public void eliminarActividad(Proceso procesoSeleccionado, Actividad actividadSeleccionada) {
        modelFactoryController.eliminarActividad(procesoSeleccionado,actividadSeleccionada);
    }

    public void eliminarProceso(Proceso procesoSeleccionado) {
        modelFactoryController.eliminarProceso(procesoSeleccionado);
    }

    public void agregarProceso(Proceso proceso) {
        modelFactoryController.agregarProceso(proceso);
    }

    public void agregarActividad(Proceso procesoSeleccionado, Actividad actividad) {
        modelFactoryController.agregarActividadDespuesDeUltima(procesoSeleccionado,actividad);
    }

    public boolean agregarTarea(Actividad actividad, Tarea tarea) {
        return modelFactoryController.agregarTarea(actividad, tarea);
    }

    public void agregarActividadDespuesDeOtra(Proceso proceso, Actividad actividadSeleccionada, Actividad actividad) {
        modelFactoryController.agregarActividadDespuesDeOtra(proceso, actividadSeleccionada, actividad);
    }

    public void exportarDatosUsuariosExcel() {
        modelFactoryController.exportarDatosUsuariosExcel();
    }

    public void actualizarProceso(Proceso procesoSeleccionado, int id, String nombre) {
        modelFactoryController.actualizarProceso(procesoSeleccionado, id , nombre);
    }

    public void cargarProcesosCSV() {
        modelFactoryController.importarProcesosCSV();
    }

    public void cargarUsuariosCSV() {
        modelFactoryController.importarUsuariosCSV();
    }
}
