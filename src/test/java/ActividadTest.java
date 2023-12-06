import com.example.controladorprocesos.model.Actividad;
import com.example.controladorprocesos.model.ListaEnlazadaDoble;
import com.example.controladorprocesos.model.Tarea;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActividadTest {
    private Actividad actividad;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        actividad = new Actividad("Actividad de prueba", "Descripción de prueba", true, new ListaEnlazadaDoble<>());
    }

    @Test
    public void agregarTarea_DeberiaAgregarTareaAlFinal() {
        Tarea tarea = new Tarea("Tarea de prueba", "Descripción de tarea de prueba", true, 30.0);

        actividad.agregarTarea(tarea);

        assertEquals(tarea, actividad.getTareas().obtenerValorNodo(0));
    }

    @Test
    public void eliminarTarea_DeberiaEliminarTarea() {
        Tarea tarea = new Tarea("Tarea de prueba", "Descripción de tarea de prueba", true, 30.0);

        actividad.agregarTarea(tarea);
        actividad.eliminarTarea(tarea);

        assertTrue(actividad.getTareas().isEmpty());
    }

    @Test
    public void calcularDuracionEstimada_DeberiaCalcularDuracionCorrecta() {
        Tarea tarea1 = new Tarea("Tarea 1", "Descripción de tarea 1", true, 30.0);
        Tarea tarea2 = new Tarea("Tarea 2", "Descripción de tarea 2", false, 45.0);

        actividad.agregarTarea(tarea1);
        actividad.agregarTarea(tarea2);

        double duracionEstimada = actividad.calcularDuracionEstimada();

        assertEquals(75.0, duracionEstimada, 0.0);
    }

    @Test
    public void buscarTareaPorNombre_DeberiaEncontrarTarea() {
        Tarea tarea = new Tarea("Tarea de prueba", "Descripción de tarea de prueba", true, 30.0);

        actividad.agregarTarea(tarea);

        Tarea tareaEncontrada = actividad.buscarTareaPorNombre("Tarea de prueba");

        assertNotNull(tareaEncontrada);
        assertEquals(tarea, tareaEncontrada);
    }

    @Test
    public void intercambiarDatos_DeberiaIntercambiarDatosCorrectamente() {
        Actividad actividad2 = new Actividad("Otra actividad", "Otra descripción", false, new ListaEnlazadaDoble<>());

        actividad.intercambiarDatos(actividad2);

        assertEquals("Otra actividad", actividad.getNombre());
        assertEquals("Otra descripción", actividad.getDescripcion());
        assertFalse(actividad.isObligatoria());

        assertEquals("Actividad de prueba", actividad2.getNombre());
        assertEquals("Descripción de prueba", actividad2.getDescripcion());
        assertTrue(actividad2.isObligatoria());
    }

    @Test
    public void intercambiarTareas_DeberiaIntercambiarTareasCorrectamente() {
        Tarea tarea1 = new Tarea("Tarea 1", "Descripción de tarea 1", true, 30.0);
        Tarea tarea2 = new Tarea("Tarea 2", "Descripción de tarea 2", false, 45.0);

        actividad.agregarTarea(tarea1);
        actividad.agregarTarea(tarea2);

        Actividad actividad2 = new Actividad("Otra actividad", "Otra descripción", false, new ListaEnlazadaDoble<>());
        Tarea tarea3 = new Tarea("Tarea 3", "Descripción de tarea 3", true, 20.0);
        Tarea tarea4 = new Tarea("Tarea 4", "Descripción de tarea 4", false, 15.0);

        actividad2.agregarTarea(tarea3);
        actividad2.agregarTarea(tarea4);

        actividad.intercambiarTareas(actividad2);

        assertTrue(actividad.getTareas().contains(tarea3));
        assertTrue(actividad.getTareas().contains(tarea4));
        assertFalse(actividad.getTareas().contains(tarea1));
        assertFalse(actividad.getTareas().contains(tarea2));

        assertTrue(actividad2.getTareas().contains(tarea1));
        assertTrue(actividad2.getTareas().contains(tarea2));
        assertFalse(actividad2.getTareas().contains(tarea3));
        assertFalse(actividad2.getTareas().contains(tarea4));
    }

    @Test
    public void agregarTareaEnPosicionEspecifica_DeberiaAgregarTareaEnPosicionCorrecta() {
        Tarea tarea1 = new Tarea("Tarea 1", "Descripción de tarea 1", true, 30.0);
        Tarea tarea2 = new Tarea("Tarea 2", "Descripción de tarea 2", false, 45.0);

        actividad.agregarTarea(tarea1);
        actividad.agregarTarea(tarea2, 0); // Agregar en la posición 0

        assertEquals(tarea2, actividad.getTareas().obtenerValorNodo(0));
        assertEquals(tarea1, actividad.getTareas().obtenerValorNodo(1));
    }

    @Test
    public void intercambiarDatosConActividadNula_DeberiaManejarActividadNulaCorrectamente() {
        Actividad actividadNula = new Actividad();

        actividad.intercambiarDatos(actividadNula);

        // Verificar que los datos de la actividad no cambien
        assertEquals("Actividad de prueba", actividad.getNombre());
        assertEquals("Descripción de prueba", actividad.getDescripcion());
        assertTrue(actividad.isObligatoria());
    }
}
