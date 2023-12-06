package com.example.controladorprocesos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una actividad en un proceso.
 */
public class Actividad {
    // Atributos
    private String nombre;
    private String descripcion;
    private boolean obligatoria;
    private double tiempo;
    private ListaEnlazadaDoble<Tarea> tareas;
    private int numeroTareas;
    private String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    // Constructores
    /**
     * Constructor vacío de la actividad.
     */
    public Actividad() {
    }

    public int getNumeroTareas() {
        return tareas.getSize();
    }

    /**
     * Constructor de la actividad con parámetros.
     *
     * @param nombre       Nombre de la actividad.
     * @param descripcion  Descripción de la actividad.
     * @param obligatoria  Indica si la actividad es obligatoria.
     * @param tareas       Lista de tareas asociadas a la actividad.
     */
    public Actividad(String nombre, String descripcion, boolean obligatoria, ListaEnlazadaDoble<Tarea> tareas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.tareas = tareas;
    }

    public Actividad(String nombre, String descripcion, boolean obligatoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.tareas =  new ListaEnlazadaDoble<>();
    }

    public Actividad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = false;
        this.tareas = new ListaEnlazadaDoble<>();
    }

    public Actividad(String nombre) {
        this.nombre = nombre;
        this.obligatoria = false;
        this.tareas = new ListaEnlazadaDoble<>();
    }

    // Getters y Setters


    public void actualizarTiempo(){
        this.tiempo = calcularDuracionEstimada();
    }
    public void actualizarNumeroTareas(){
        numeroTareas = tareas.getSize();
    }
    public double getTiempo() {
        actualizarTiempo();
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Obtiene la lista de tareas asociadas a la actividad.
     *
     * @return Lista de tareas.
     */
    public ListaEnlazadaDoble<Tarea> getTareas() {
        return tareas;
    }

    public List<Tarea> obtenerTareasList(){
        List<Tarea> tareas = new ArrayList<>();
        for (Tarea tarea: this.tareas) {
            tareas.add(tarea);
        }
        return tareas;
    }

    /**
     * Establece la lista de tareas asociadas a la actividad.
     *
     * @param tareas Nueva lista de tareas.
     */
    public void setTareas(ListaEnlazadaDoble<Tarea> tareas) {
        this.tareas = tareas;
        this.numeroTareas = tareas.getSize();
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return Nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la actividad.
     *
     * @param nombre Nuevo nombre de la actividad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la actividad.
     *
     * @return Descripción de la actividad.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la actividad.
     *
     * @param descripcion Nueva descripción de la actividad.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Verifica si la actividad es obligatoria.
     *
     * @return true si es obligatoria, false de lo contrario.
     */
    public boolean isObligatoria() {
        return obligatoria;
    }

    /**
     * Establece si la actividad es obligatoria.
     *
     * @param obligatoria Nuevo valor de obligatoriedad.
     */
    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    // Métodos de la clase
    /**
     * Agrega una tarea al final de la lista de tareas.
     *
     * @param tarea Tarea a agregar.
     */
    public boolean agregarTarea(Tarea tarea) {
        tareas.agregarFinal(tarea);
        numeroTareas++;
        return true;
    }

    public boolean comprobarTareaValida(Tarea tareaAnterior, Tarea nueva, Tarea siguiente){
        if(siguiente == null){
            if(tareaAnterior != null){
                return tareaAnterior.isObligatoria() && nueva.isObligatoria();
            }
        }else{
            if(tareaAnterior != null){
                return tareaAnterior.isObligatoria() && nueva.isObligatoria() || nueva.isObligatoria() && siguiente.isObligatoria();
            }else{
                return nueva.isObligatoria() && siguiente.isObligatoria();
            }
        }
        return false;
    }

    /**
     * Agrega una tarea en una posición específica de la lista de tareas.
     *
     * @param tarea Tarea a agregar.
     * @param i     Posición en la que se agregará la tarea.
     */
    public boolean agregarTarea(Tarea tarea, int i) {
        if (!comprobarTareaValida(tareas.get(i-1),tarea,tareas.get(i))) {
            tareas.agregar(tarea, i);
            numeroTareas++;
            return true;
        }
        return false;
    }

    /**
     * Elimina una tarea de la lista de tareas.
     *
     * @param tarea Tarea a eliminar.
     */
    public void eliminarTarea(Tarea tarea) {
        tareas.eliminar(tarea);
        numeroTareas--;
    }

    /**
     * Obtiene la lista de tareas asociadas a la actividad.
     *
     * @return Lista de tareas.
     */
    public ListaEnlazadaDoble<Tarea> obtenerTareas() {
        return tareas;
    }

    /**
     * Calcula la duración estimada total de todas las tareas asociadas a la actividad.
     *
     * @return Duración estimada total.
     */
    public double calcularDuracionEstimada() {
        double aux = 0;
        for (Tarea tarea : tareas) {
            aux += tarea.getTiempoMinutos();
        }
        return aux;
    }

    /**
     * Busca una tarea por su nombre en la lista de tareas.
     *
     * @param nombreTarea Nombre de la tarea a buscar.
     * @return Tarea encontrada o null si no existe.
     */
    public Tarea buscarTareaPorNombre(String nombreTarea) {
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equalsIgnoreCase(nombreTarea)) {
                return tarea;
            }
        }
        return null;
    }

    /**
     * Intercambia los datos (nombre, descripción, obligatoriedad) con otra actividad.
     *
     * @param actividad2 Actividad con la que se intercambiarán los datos.
     */
    public void intercambiarDatos(Actividad actividad2) {
        if (actividad2 != null && actividad2.getNombre() != null && actividad2.getDescripcion() != null) {
            Actividad temp = new Actividad();

            temp.setNombre(getNombre());
            temp.setDescripcion(getDescripcion());
            temp.setObligatoria(isObligatoria());

            setNombre(actividad2.getNombre());
            setDescripcion(actividad2.getDescripcion());
            setObligatoria(actividad2.isObligatoria());

            actividad2.setNombre(temp.getNombre());
            actividad2.setDescripcion(temp.getDescripcion());
            actividad2.setObligatoria(temp.isObligatoria());
        }
    }


    /**
     * Intercambia las listas de tareas con otra actividad.
     *
     * @param actividad2 Actividad con la que se intercambiarán las listas de tareas.
     */
    public void intercambiarTareas(Actividad actividad2) {
        ListaEnlazadaDoble<Tarea> copiaTareas1 = new ListaEnlazadaDoble<>(this.tareas);
        ListaEnlazadaDoble<Tarea> copiaTareas2 = new ListaEnlazadaDoble<>(actividad2.getTareas());

        setTareas(copiaTareas2);
        actividad2.setTareas(copiaTareas1);
    }

    public void realizarActividad(String usuario){
        this.usuario = usuario;
        for (Tarea tarea:tareas) {
            tarea.realizarTarea(usuario);
            enviarNotificacionActividadTerminada("la actividad "+ nombre + " ha sido completada con exito");
        }
    }

    public void enviarNotificacionActividadTerminada(String mensaje){
        Notificacion n = new Notificacion(1,mensaje);
        n.enviarCorreoElectronico(usuario,nombre,mensaje);
    }
}
