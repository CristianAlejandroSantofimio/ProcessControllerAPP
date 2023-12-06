package com.example.controladorprocesos.controllers;

import com.example.controladorprocesos.model.*;

import java.util.List;

public class ModelFactoryController {

    Gestor gestor;
    String usuario;
    final String rutaExcelProcesos = "src/main/resources/archivos/procesos.xlsx";
    final String rutaTxtProcesos = "src/main/resources/archivos/procesos.txt";
    final String rutaExcelUsuarios = "src/main/resources/archivos/usuarios.xlsx";
    final String rutaTxtUsuarios = "src/main/resources/archivos/usuarios.txt";

    public Usuario login(Usuario usuario){
        return gestor.login(usuario.getNombreUsuario(),usuario.getContrasenia());
    }


    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aquí al ser
        // protected
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public void inicializarDatos(){
        Proceso proceso1 = new Proceso(1, "Proceso 1");
        Proceso proceso2 = new Proceso(2, "Proceso 2");
        Proceso proceso3 = new Proceso(3, "Proceso 3");

        // Crear actividades para el proceso 1
        Actividad actividad11 = new Actividad("Actividad 1.1", "Descripción 1.1", true);
        Actividad actividad12 = new Actividad("Actividad 1.2", "Descripción 1.2", true);

        // Crear tareas para la actividad 1.1
        Tarea tarea111 = new Tarea("Tarea 1.1.1", "Descripción 1.1.1", true, 1.0);
        Tarea tarea112 = new Tarea("Tarea 1.1.2", "Descripción 1.1.2", true, 2.0);
        Tarea tarea113 = new Tarea("Tarea 1.1.3", "Descripción 1.1.3", false, 3.0);

        // Agregar tareas a la actividad 1.1
        actividad11.getTareas().agregarFinal(tarea111);
        actividad11.getTareas().agregarFinal(tarea112);
        actividad11.getTareas().agregarFinal(tarea113);

        // Crear tareas para la actividad 1.2
        Tarea tarea121 = new Tarea("Tarea 1.2.1", "Descripción 1.2.1", true, 1.0);
        Tarea tarea122 = new Tarea("Tarea 1.2.2", "Descripción 1.2.2", false, 1.0);
        Tarea tarea123 = new Tarea("Tarea 1.2.3", "Descripción 1.2.3", true, 2.0);

        // Agregar tareas a la actividad 1.2
        actividad12.getTareas().agregarFinal(tarea121);
        actividad12.getTareas().agregarFinal(tarea122);
        actividad12.getTareas().agregarFinal(tarea123);

        // Agregar actividades al proceso 1
        proceso1.getActividades().agregarFinal(actividad11);
        proceso1.getActividades().agregarFinal(actividad12);

        // Crear actividades para el proceso 2
        Actividad actividad21 = new Actividad("Actividad 2.1", "Descripción 2.1", true);
        Actividad actividad22 = new Actividad("Actividad 2.2", "Descripción 2.2", false);

        // Crear tareas para la actividad 2.1
        Tarea tarea211 = new Tarea("Tarea 2.1.1", "Descripción 2.1.1", false, 5.0);
        Tarea tarea212 = new Tarea("Tarea 2.1.2", "Descripción 2.1.2", true, 4.0);
        Tarea tarea213 = new Tarea("Tarea 2.1.3", "Descripción 2.1.3", true, 6.0);

        // Agregar tareas a la actividad 2.1
        actividad21.getTareas().agregarFinal(tarea211);
        actividad21.getTareas().agregarFinal(tarea212);
        actividad21.getTareas().agregarFinal(tarea213);

        // Crear tareas para la actividad 2.2
        Tarea tarea221 = new Tarea("Tarea 2.2.1", "Descripción 2.2.1", false, 15.0);
        Tarea tarea222 = new Tarea("Tarea 2.2.2", "Descripción 2.2.2", true, 20.0);
        Tarea tarea223 = new Tarea("Tarea 2.2.3", "Descripción 2.2.3", false, 30.0);

        // Agregar tareas a la actividad 2.2
        actividad22.getTareas().agregarFinal(tarea221);
        actividad22.getTareas().agregarFinal(tarea222);
        actividad22.getTareas().agregarFinal(tarea223);

        // Agregar actividades al proceso 2
        proceso2.getActividades().agregarFinal(actividad21);
        proceso2.getActividades().agregarFinal(actividad22);

        // Crear actividades para el proceso 3
        Actividad actividad31 = new Actividad("Actividad 3.1", "Descripción 3.1", true);
        Actividad actividad32 = new Actividad("Actividad 3.2", "Descripción 3.2", true);

        // Crear tareas para la actividad 3.1
        Tarea tarea311 = new Tarea("Tarea 3.1.1", "Descripción 3.1.1", true, 12.0);
        Tarea tarea312 = new Tarea("Tarea 3.1.2", "Descripción 3.1.2", false, 15.0);
        Tarea tarea313 = new Tarea("Tarea 3.1.3", "Descripción 3.1.3", true, 10.0);

        // Agregar tareas a la actividad 3.1
        actividad31.getTareas().agregarFinal(tarea311);
        actividad31.getTareas().agregarFinal(tarea312);
        actividad31.getTareas().agregarFinal(tarea313);

        // Crear tareas para la actividad 3.2
        Tarea tarea321 = new Tarea("Tarea 3.2.1", "Descripción 3.2.1", false, 18.0);
        Tarea tarea322 = new Tarea("Tarea 3.2.2", "Descripción 3.2.2", true, 20.0);
        Tarea tarea323 = new Tarea("Tarea 3.2.3", "Descripción 3.2.3", false, 25.0);

        // Agregar tareas a la actividad 3.2
        actividad32.getTareas().agregarFinal(tarea321);
        actividad32.getTareas().agregarFinal(tarea322);
        actividad32.getTareas().agregarFinal(tarea323);

        // Agregar actividades al proceso 3
        proceso3.getActividades().agregarFinal(actividad31);
        proceso3.getActividades().agregarFinal(actividad32);

        Gestor gestor = new Gestor();
        ListaEnlazadaDoble<Proceso> procesos = new ListaEnlazadaDoble<>();
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        gestor.setProcesos(procesos);

        gestor.agregarUsuario(new Usuario("Bryan", "1"));
        gestor.agregarUsuario(new Usuario("bryangomez1625@gmail.com", "1"));


        this.gestor = gestor;

    }

    ///////////////////////////////// login ///////////////////////////////////////
    public boolean loginIniciarSesion(String nombreUsuario, String contrasenia) {
        if(gestor.usuarioCorrecto(nombreUsuario, contrasenia)){
            this.usuario = nombreUsuario;
            return true;
        }
        return false;
    }

    ///////////////////////////////// usuario ///////////////////////////////////////
    public List<Proceso> buscarProcesosNombre(String nombreProceso) {
        return gestor.buscarProcesosPorNombre(nombreProceso);
    }

    public Proceso buscarProcesoId(int idProceso) {
        return gestor.buscarProcesoPorId(idProceso);
    }

    public List<Actividad> buscarActividadesNombre(String nombreActividad) {
        return gestor.buscarActividadesPorNombre(nombreActividad);
    }

    public Actividad buscarActividadNombre(Proceso proceso, String nombreActividad) {
        return proceso.buscarActividad(nombreActividad);
    }


    public List<Tarea> buscarTareasNombre(String nombreTarea) {
        return gestor.buscarTareasPorNombre(nombreTarea);
    }

    public Tarea bucarTareaProceso(Proceso proceso, String nombreActividad){
        return proceso.buscarTareaInicio(nombreActividad);
    }

    public List<Tarea> obtenerTareasProceso(Proceso proceso){
        return proceso.obtenerTareasProceso();
    }

    public void exportarDatosUsuariosExcel(){
        gestor.exportarExcelListaUsuarios(rutaExcelUsuarios);
    }

    public void exportarDatosExcel() {
        gestor.exportarExcelListaProcesos(rutaExcelProcesos);
    }

    public void importarProcesosCSV() {
        gestor.cargarProcesosDesdeCSV(rutaTxtProcesos);
    }

    public void actualizarProceso(Proceso proceso, int id, String nombre) {
        proceso.setId(id);
        proceso.setNombre(nombre);
    }

    public void actualizarActividad(Actividad actividad, String nombre, String descripcion, boolean obligatoria) {
        actividad.setNombre(nombre);
        actividad.setDescripcion(descripcion);
        actividad.setObligatoria(obligatoria);
    }

    public void actualizarTarea(Tarea tarea, String nombre, String descripcion, boolean obligatoria, double tiempo) {
        tarea.setNombre(nombre);
        tarea.setDescripcion(descripcion);
        tarea.setObligatoria(obligatoria);
        tarea.setTiempoMinutos(tiempo);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        return gestor.buscarUsuarioPorNombre(nombreUsuario);
    }

    public void actualizarUsuario(Usuario original, String usuario, String contrasenia) {
        original.setNombreUsuario(usuario);
        original.setContrasenia(contrasenia);
    }

    public void eliminarUsuario(Usuario usuario) {
        gestor.eliminarUsuario(usuario);
    }

    public boolean agregarUsuario(Usuario usuario) {
        return gestor.agregarUsuario(usuario);
    }

    public void importarUsuariosCSV() {
        gestor.cargarUsuariosDesdeCSV(rutaTxtUsuarios);
    }

    public void eliminarTarea(Actividad actividad, Tarea tarea) {
        actividad.eliminarTarea(tarea);
    }

    public void eliminarActividad(Proceso proceso, Actividad actividad) {
        proceso.eliminarActividad(actividad);
    }

    public void eliminarProceso(Proceso proceso) {
        gestor.eliminarProceso(proceso);
    }

    public void agregarProceso(Proceso proceso) {
        gestor.agregarProceso(proceso);
    }

    public void agregarActividad(Proceso proceso, Actividad actividad) {
        proceso.agregarActividad(actividad);
    }

    public void agregarActividadDespuesDeOtra(Proceso proceso,Actividad actividad, Actividad actividad2){
        proceso.agregarActividadDespuesDeOtra(actividad,actividad2);
    }

    public void agregarActividadDespuesDeUltima(Proceso proceso, Actividad actividad){
        proceso.agregarDespuesUltimaActividad(actividad);
    }

    public boolean agregarTarea(Actividad actividad, Tarea tarea) {
        return actividad.agregarTarea(tarea);
    }

}
