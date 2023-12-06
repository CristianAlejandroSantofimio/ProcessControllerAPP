package com.example.controladorprocesos.model;

/**
 * Representa una tarea que puede ser parte de una actividad.
 */
public class Tarea {
    private String nombre;
    private String descripcion;
    private boolean obligatoria = false;
    private double tiempoMinutos;
    private long tiempoRestante;
    private String usuario;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Constructor de la clase Tarea con parámetros.
     *
     * @param nombre        Nombre de la tarea.
     * @param descripcion   Descripción de la tarea.
     * @param obligatoria   Indica si la tarea es obligatoria.
     * @param tiempoMinutos Tiempo estimado en minutos para completar la tarea.
     */
    public Tarea(String nombre, String descripcion, boolean obligatoria, double tiempoMinutos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatoria = obligatoria;
        this.tiempoMinutos = tiempoMinutos;
    }

    public Tarea(String nombre, String descripcion, double tiempoMinutos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoMinutos = tiempoMinutos;
    }

    public void actualizarTiempoMinutos() {
        this.tiempoMinutos = tiempoRestante / 60.0; // Convertir segundos a minutos
    }

    /**
     * Constructor por defecto de la clase Tarea.
     */
    public Tarea() {
    }

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.obligatoria = false;
    }

    public Tarea(String nombre, double tiempoMinutos) {
        this.nombre = nombre;
        this.tiempoMinutos = tiempoMinutos;
    }

    public Tarea(String nombre, boolean obligatoria, int tiempoMinutos) {
        this.nombre = nombre;
        this.obligatoria = obligatoria;
        this.tiempoMinutos = tiempoMinutos;
    }

    /**
     * Obtiene el nombre de la tarea.
     *
     * @return Nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la tarea.
     *
     * @param nombre Nombre de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la tarea.
     *
     * @return Descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la tarea.
     *
     * @param descripcion Descripción de la tarea.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Verifica si la tarea es obligatoria.
     *
     * @return true si la tarea es obligatoria, false de lo contrario.
     */
    public boolean isObligatoria() {
        return obligatoria;
    }

    /**
     * Establece si la tarea es obligatoria.
     *
     * @param obligatoria Indica si la tarea es obligatoria.
     */
    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    /**
     * Obtiene el tiempo estimado en minutos para completar la tarea.
     *
     * @return Tiempo estimado en minutos.
     */
    public double getTiempoMinutos() {
        return tiempoMinutos;
    }

    /**
     * Establece el tiempo estimado en minutos para completar la tarea.
     *
     * @param tiempoMinutos Tiempo estimado en minutos.
     */
    public void setTiempoMinutos(double tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    /**
     * Simula la realización de la tarea esperando el tiempo estimado.
     */
    public void realizarTarea(String usuario) {
        this.usuario = usuario;
        tiempoRestante = (long) (tiempoMinutos * 60L); // Convertir minutos a segundos

        while (tiempoRestante > 0 && !Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                tiempoRestante--;
                actualizarTiempoMinutos();

                System.out.println("Tiempo restante para la tarea '" + nombre + "': " + tiempoRestante + " segundos");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

        public void enviarNotificacionTareaTerminada(String mensaje){
            Notificacion n = new Notificacion(1, mensaje);
            n.enviarCorreoElectronico(usuario, nombre, mensaje);
        }
}