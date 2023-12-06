import com.example.controladorprocesos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProcesoTest {

    private Proceso proceso;

    @BeforeEach
    void setUp() {
        // Inicializar el proceso antes de cada prueba
        proceso = new Proceso(1, "Proceso de prueba");
    }

    @Test
    void obtenerDuracionTotal_DeberiaRetornarCeroCuandoNoHayActividades() {
        assertEquals(0, proceso.obtenerDuracionTotal());
    }

    @Test
    void obtenerDuracionTotal_DeberiaRetornarDuracionCorrectaConActividades() {
        // Crear actividades y agregarlas al proceso
        Actividad actividad1 = new Actividad("Actividad 1");
        actividad1.agregarTarea(new Tarea("Tarea 1", 5));
        proceso.agregarActividad(actividad1);

        Actividad actividad2 = new Actividad("Actividad 2");
        actividad2.agregarTarea(new Tarea("Tarea 2", 3));
        proceso.agregarActividad(actividad2);

        // La duración total debería ser la suma de las duraciones de las tareas
        assertEquals(8, proceso.obtenerDuracionTotal());
    }

    // Otros métodos de prueba para la clase Proceso

    @Test
    void buscarTareaInicio_DeberiaRetornarNullCuandoNoExisteLaTarea() {
        assertNull(proceso.buscarTareaInicio("Tarea Inexistente"));
    }

    @Test
    void buscarTareaInicio_DeberiaEncontrarTareaCuandoExiste() {
        // Crear actividades y agregarlas al proceso con tareas
        Actividad actividad = new Actividad("Actividad de prueba");
        actividad.agregarTarea(new Tarea("Tarea de prueba", 3));
        proceso.agregarActividad(actividad);

        // Debería encontrar la tarea
        assertNotNull(proceso.buscarTareaInicio("Tarea de prueba"));
    }

    @Test
    void agregarActividad_DeberiaIncrementarElNumeroDeActividades() {
        assertEquals(0, proceso.getActividades().getSize());

        Actividad actividad = new Actividad("Actividad 1");
        proceso.agregarActividad(actividad);

        assertEquals(1, proceso.getActividades().getSize());
    }

    @Test
    void eliminarActividad_DeberiaReducirElNumeroDeActividades() {
        Actividad actividad1 = new Actividad("Actividad 1");
        Actividad actividad2 = new Actividad("Actividad 2");
        proceso.agregarActividad(actividad1);
        proceso.agregarActividad(actividad2);

        assertEquals(2, proceso.getActividades().getSize());

        proceso.eliminarActividad(actividad1);

        assertEquals(1, proceso.getActividades().getSize());
    }

    @Test
    void intercambiarActividades_DeberiaIntercambiarTareasEntreDosActividades() {
        Actividad actividad1 = new Actividad("Actividad 1");
        Actividad actividad2 = new Actividad("Actividad 2");
        proceso.agregarActividad(actividad1);
        proceso.agregarActividad(actividad2);

        // Agregar tareas a las actividades
        Tarea tarea1 = new Tarea("Tarea 1", true, 10);
        Tarea tarea2 = new Tarea("Tarea 2", false, 15);
        actividad1.agregarTarea(tarea1);
        actividad2.agregarTarea(tarea2);

        proceso.intercambiarActividades(actividad1, actividad2);

        // Verificar que las tareas se hayan intercambiado
        assertTrue(actividad1.getTareas().contains(tarea2));
        assertTrue(actividad2.getTareas().contains(tarea1));
    }
}
