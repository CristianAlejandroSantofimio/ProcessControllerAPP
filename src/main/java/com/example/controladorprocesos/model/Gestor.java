package com.example.controladorprocesos.model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.sl.usermodel.PresetColor.Desktop;

/**
 * Clase que gestiona procesos, usuarios y notificaciones en la aplicación.
 */
public class Gestor implements Runnable{
    // Atributos
    private ListaEnlazadaDoble<Proceso> procesos;
    private ListaEnlazadaDoble<Usuario> usuarios;
    private ListaEnlazadaDoble<Notificacion> notificaciones;
    private Usuario usuario;
    private boolean loggedIn = false;

    // Constructor
    /**
     * Constructor que inicializa las listas de procesos, usuarios y notificaciones.
     */
    public Gestor() {
        this.procesos = new ListaEnlazadaDoble<>();
        this.usuarios = new ListaEnlazadaDoble<>();
        this.notificaciones = new ListaEnlazadaDoble<>();
    }

    public ListaEnlazadaDoble<Proceso> getProcesos() {
        return procesos;
    }
    public List<Proceso> obtenerListaProcesos(){
        List<Proceso> procesosl = new ArrayList<>();
        for (Proceso proceso: procesos) {
            procesosl.add(proceso);
        }
        return procesosl;
    }

    public void setProcesos(ListaEnlazadaDoble<Proceso> procesos) {
        this.procesos = procesos;
    }

    public ListaEnlazadaDoble<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Usuario> obtenerUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        for (Usuario usuario: this.usuarios) {
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public void setUsuarios(ListaEnlazadaDoble<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ListaEnlazadaDoble<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(ListaEnlazadaDoble<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }


    public List<Actividad> obtenerTodasLasActividades() {
        List<Actividad> actividades = new ArrayList<>();
        for (Proceso proceso : procesos) {
            actividades.addAll(proceso.obtenerTodasLasActividades());
        }
        return actividades;
    }

    public List<Tarea> obtenerTodasLasTareas() {
        List<Tarea> tareas = new ArrayList<>();
        for (Proceso proceso : procesos) {
            for (Actividad actividad: proceso.getActividades()) {
                tareas.addAll(actividad.obtenerTareasList());
            }
        }
        return tareas;
    }

    // Métodos para gestionar procesos
    /**
     * Agrega un proceso a la lista de procesos.
     *
     * @param proceso Proceso a agregar.
     */
    public void agregarProceso(Proceso proceso) {
        procesos.add(proceso);
    }

    /**
     * Elimina un proceso de la lista de procesos.
     *
     * @param proceso Proceso a eliminar.
     */
    public void eliminarProceso(Proceso proceso) {
        procesos.remove(proceso);
    }

    /**
     * Busca un proceso por su nombre en la lista de procesos.
     *
     * @param nombre Nombre del proceso a buscar.
     * @return Proceso encontrado o null si no existe.
     */
    public Proceso buscarProcesoPorNombre(String nombre) {
        for (Proceso proceso : procesos) {
            if (proceso.getNombre().equalsIgnoreCase(nombre)) {
                return proceso;
            }
        }
        return null;
    }

    public List<Proceso> buscarProcesosPorNombre(String nombre) {
        List<Proceso> procesosEncontrados = new ArrayList<>();
        for (Proceso proceso : procesos) {
            if (proceso.getNombre().equalsIgnoreCase(nombre)) {
                procesosEncontrados.add(proceso);
            }
        }
        return procesosEncontrados;
    }


    // Métodos para gestionar usuarios
    /**
     * Agrega un usuario a la lista de usuarios.
     *
     * @param usuario Usuario a agregar.
     */
    public boolean agregarUsuario(Usuario usuario) {
        boolean exito = usuarios.contains(usuario);
        if(!exito) {
            usuarios.add(usuario);
        }
        return exito;
    }

    /**
     * Elimina un usuario de la lista de usuarios.
     *
     * @param usuario Usuario a eliminar.
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    /**
     * Busca un usuario por su nombre en la lista de usuarios.
     *
     * @param nombre Nombre del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    public Usuario buscarUsuarioPorNombre(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombre)) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }


    // Métodos para gestionar notificaciones
    /**
     * Agrega una notificación a la lista de notificaciones.
     *
     * @param notificacion Notificación a agregar.
     */
    public void agregarNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
    }

    /**
     * Elimina una notificación de la lista de notificaciones.
     *
     * @param notificacion Notificación a eliminar.
     */
    public void eliminarNotificacion(Notificacion notificacion) {
        notificaciones.remove(notificacion);
    }

    /**
     * Busca una notificación por su ID en la lista de notificaciones.
     *
     * @param id ID de la notificación a buscar.
     * @return Notificación encontrada o null si no existe.
     */
    public Notificacion buscarNotificacionPorId(int id) {
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.getId() == id) {
                return notificacion;
            }
        }
        return null;
    }

    // Métodos adicionales
    /**
     * Asigna una actividad a un proceso.
     *
     * @param proceso   Proceso al que se asignará la actividad.
     * @param actividad Actividad a asignar.
     */
    public void asignarActividadAProceso(Proceso proceso, Actividad actividad) {
        proceso.agregarActividad(actividad);
    }

    /**
     * Elimina una actividad de un proceso.
     *
     * @param proceso   Proceso del que se eliminará la actividad.
     * @param actividad Actividad a eliminar.
     */
    public void eliminarActividadDeProceso(Proceso proceso, Actividad actividad) {
        proceso.eliminarActividad(actividad);
    }

    /**
     * Intercambia dos actividades en un proceso.
     *
     * @param proceso     Proceso en el que se realizará el intercambio.
     * @param actividad1  Primera actividad a intercambiar.
     * @param actividad2  Segunda actividad a intercambiar.
     */
    public void intercambiarActividadesEnProceso(Proceso proceso, Actividad actividad1, Actividad actividad2) {
        proceso.intercambiarActividades(actividad1, actividad2);
    }

    /**
     * Busca una tarea en un proceso por su nombre.
     *
     * @param proceso     Proceso en el que se buscará la tarea.
     * @param nombreTarea Nombre de la tarea a buscar.
     * @return Tarea encontrada o null si no existe.
     */
    public Tarea buscarTareaEnProceso(Proceso proceso, String nombreTarea) {
        return proceso.buscarTareaInicio(nombreTarea);
    }

    public List<Tarea> buscarTareasPorNombre(String nombreTarea) {
        List<Tarea> tareasEncontradas = new ArrayList<>();
        for (Proceso proceso : procesos) {
            for (Actividad actividad : proceso.getActividades()) {
                for (Tarea tarea : actividad.getTareas()) {
                    if (tarea.getNombre().equalsIgnoreCase(nombreTarea)) {
                        tareasEncontradas.add(tarea);
                    }
                }
            }
        }
        return tareasEncontradas;
    }



    public void exportarExcelProceso(Proceso proceso, String nombreArchivo) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Proceso");

            // Crear la primera fila con los encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Tiempo (minutos)");

            // Crear una fila con los datos del proceso
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(proceso.getId());
            dataRow.createCell(1).setCellValue(proceso.getNombre());
            dataRow.createCell(2).setCellValue(proceso.getTiempoMinutos());

            // Escribir el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void exportarExcelListaUsuarios(String nombreArchivo) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Usuarios");

            // Crear la primera fila con los encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Usuario");
            headerRow.createCell(1).setCellValue("contraseña");

            // Crear filas con los datos de cada proceso
            int rowNum = 1;
            for (Usuario usuario : usuarios) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(usuario.getNombreUsuario());
                dataRow.createCell(1).setCellValue(usuario.getContrasenia());
            }

            // Escribir el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
                workbook.write(fileOut);
                String archivoExcelPath = "ruta/al/archivo.xlsx"; // Reemplaza con la ruta de tu archivo

                // Verifica si el sistema operativo es Windows
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", archivoExcelPath);
                    builder.start();
                } else {
                    System.out.println("Este código funciona solo en sistemas Windows.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void exportarExcelListaProcesos(String nombreArchivo) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Procesos");

            // Crear la primera fila con los encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Tiempo (minutos)");

            // Crear filas con los datos de cada proceso
            int rowNum = 1;
            for (Proceso proceso : procesos) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(proceso.getId());
                dataRow.createCell(1).setCellValue(proceso.getNombre());
                dataRow.createCell(2).setCellValue(proceso.getTiempoMinutos());
            }

            // Escribir el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarUsuariosDesdeCSV(String archivoUsuarios) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String usuario = partes[0].trim();
                    String contraseña = partes[1].trim();
                    agregarUsuario(new Usuario(usuario, contraseña));
                }
            }
            System.out.println("Usuarios cargados desde CSV con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarProcesosDesdeCSV(String archivoProcesos) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoProcesos))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    int id = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    agregarProceso(new Proceso(id, nombre));
                }
            }
            System.out.println("Procesos cargados desde CSV con éxito.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Usuario login(String usuario, String contrasenia){
        for (Usuario u:usuarios) {
            if (usuario.equals(u.getNombreUsuario()) && contrasenia.equals(u.getContrasenia())) {
                this.usuario = u;
                return u;
            }
        }
        return null;
    }

    public boolean usuarioCorrecto(String usuario, String contrasenia){
        for (Usuario u:usuarios) {
            if (usuario == u.getNombreUsuario() && contrasenia == u.getContrasenia()) {
                this.usuario = u;
                return true;
            }
        }
        return false;
    }

    public void realizarProcesos(){
        for (Proceso p: procesos) {
            p.realizarProceso(usuario.getNombreUsuario());
        }
    }

    // Método genérico para buscar procesos por ID
    public Proceso buscarProcesoPorId(int idProceso) {
        for (Proceso proceso : procesos) {
            if (proceso.getId() == idProceso) {
                return proceso;
            }
        }
        return null;
    }

    // Método genérico para buscar actividades por nombre
    public Actividad buscarActividadPorNombre(String nombreActividad) {
        for (Proceso proceso : procesos) {
            for (Actividad actividad : proceso.getActividades()) {
                if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                    return actividad;
                }
            }
        }
        return null;
    }

    public List<Actividad> buscarActividadesPorNombre(String nombreActividad){
        List<Actividad> actividades = new ArrayList<>();
        for (Proceso proceso : procesos) {
            for (Actividad actividad : proceso.getActividades()) {
                if (actividad.getNombre().equalsIgnoreCase(nombreActividad)) {
                    actividades.add(actividad);
                }
            }
        }
        return actividades;
    }

    // Método genérico para buscar tareas por nombre
    public Tarea buscarTareaPorNombre(String nombreTarea) {
        for (Proceso proceso : procesos) {
            for (Actividad actividad : proceso.getActividades()) {
                for (Tarea tarea : actividad.getTareas()) {
                    if (tarea.getNombre().equalsIgnoreCase(nombreTarea)) {
                        return tarea;
                    }
                }
            }
        }
        return null;
    }

    public boolean loginAndStart(String usuario, String contrasenia) {
        login(usuario,contrasenia);
        boolean loginExitoso = usuarioCorrecto(usuario, contrasenia);

        if (loginExitoso) {
            Thread procesoThread = new Thread(this);
            procesoThread.start();
        }

        return loginExitoso;
    }


    @Override
    public void run() {
        if (!loggedIn) {
            // Si no se ha iniciado sesión, no realizar procesos
            System.out.println("Usuario no ha iniciado sesión. No se realizarán procesos.");
            return;
        }

        while (!Thread.interrupted()) {
            for (Proceso p : procesos) {
                p.realizarProceso(usuario.getNombreUsuario());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
