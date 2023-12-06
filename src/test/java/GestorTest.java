import static org.junit.Assert.*;

import com.example.controladorprocesos.model.*;
import org.junit.Before;
import org.junit.Test;

public class GestorTest {

    private Gestor gestor;
    private Proceso proceso1;
    private Proceso proceso2;
    private Usuario usuario1;
    private Usuario usuario2;
    private Notificacion notificacion1;
    private Notificacion notificacion2;

    @Before
    public void setUp() {
        gestor = new Gestor();
        proceso1 = new Proceso("Proceso 1");
        proceso2 = new Proceso("Proceso 2");
        usuario1 = new Usuario("Usuario1");
        usuario2 = new Usuario("Usuario2");
        notificacion1 = new Notificacion(1, "Notificacion 1");
        notificacion2 = new Notificacion(2, "Notificacion 2");
    }

    // Pruebas para gestionar procesos

    @Test
    public void deberiaAgregarProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        assertTrue(gestor.buscarProcesoPorNombre("Proceso 1") != null);
    }

    @Test
    public void deberiaEliminarProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        gestor.eliminarProceso(proceso1);
        assertNull(gestor.buscarProcesoPorNombre("Proceso 1"));
    }

    @Test
    public void deberiaBuscarProcesoPorNombreCorrectamente() {
        gestor.agregarProceso(proceso1);
        assertEquals(proceso1, gestor.buscarProcesoPorNombre("Proceso 1"));
    }

    // Pruebas para gestionar usuarios

    @Test
    public void deberiaAgregarUsuarioCorrectamente() {
        gestor.agregarUsuario(usuario1);
        assertTrue(gestor.buscarUsuarioPorNombre("Usuario1") != null);
    }

    @Test
    public void deberiaEliminarUsuarioCorrectamente() {
        gestor.agregarUsuario(usuario1);
        gestor.eliminarUsuario(usuario1);
        assertNull(gestor.buscarUsuarioPorNombre("Usuario1"));
    }

    @Test
    public void deberiaBuscarUsuarioPorNombreCorrectamente() {
        gestor.agregarUsuario(usuario1);
        assertEquals(usuario1, gestor.buscarUsuarioPorNombre("Usuario1"));
    }

    // Pruebas para gestionar notificaciones

    @Test
    public void deberiaAgregarNotificacionCorrectamente() {
        gestor.agregarNotificacion(notificacion1);
        assertTrue(gestor.buscarNotificacionPorId(1) != null);
    }

    @Test
    public void deberiaEliminarNotificacionCorrectamente() {
        gestor.agregarNotificacion(notificacion1);
        gestor.eliminarNotificacion(notificacion1);
        assertNull(gestor.buscarNotificacionPorId(1));
    }

    @Test
    public void deberiaBuscarNotificacionPorIdCorrectamente() {
        gestor.agregarNotificacion(notificacion1);
        assertEquals(notificacion1, gestor.buscarNotificacionPorId(1));
    }

    // Pruebas para los métodos adicionales

    @Test
    public void deberiaAsignarActividadAProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        gestor.asignarActividadAProceso(proceso1, new Actividad("Actividad 1"));
        assertEquals(1, proceso1.getActividades().size());
    }

    @Test
    public void deberiaEliminarActividadDeProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        Actividad actividad = new Actividad("Actividad 1");
        proceso1.agregarActividad(actividad);
        gestor.eliminarActividadDeProceso(proceso1, actividad);
        assertEquals(0, proceso1.getActividades().size());
    }

    @Test
    public void deberiaIntercambiarActividadesEnProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        Actividad actividad1 = new Actividad("Actividad 1");
        Actividad actividad2 = new Actividad("Actividad 2");
        actividad1.agregarTarea(new Tarea("tarea 1"));
        actividad2.agregarTarea(new Tarea("tarea 2"));
        proceso1.agregarActividad(actividad1);
        proceso1.agregarActividad(actividad2);
        gestor.intercambiarActividadesEnProceso(proceso1, actividad1, actividad2);
        assertEquals("tarea 2", proceso1.getActividades().get(0).getTareas().get(0).getNombre());
        assertEquals("tarea 1", proceso1.getActividades().get(1).getTareas().get(0).getNombre());
    }

    @Test
    public void deberiaBuscarTareaEnProcesoCorrectamente() {
        gestor.agregarProceso(proceso1);
        Actividad actividad = new Actividad("Actividad 1", "descripcion 1", false, new ListaEnlazadaDoble<>());
        Tarea tarea = new Tarea("Tarea 1");
        actividad.agregarTarea(tarea);
        proceso1.agregarActividad(actividad);
        assertEquals(tarea, gestor.buscarTareaEnProceso(proceso1, "Tarea 1"));
    }
}
