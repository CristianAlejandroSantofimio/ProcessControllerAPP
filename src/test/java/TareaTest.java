import com.example.controladorprocesos.model.Tarea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TareaTest {

    @Test
    void crearTarea_ConParametros_DeberiaEstablecerValoresCorrectos() {
        String nombre = "Tarea1";
        String descripcion = "Descripción de la tarea";
        boolean obligatoria = true;
        double tiempoMinutos = 30.0;

        Tarea tarea = new Tarea(nombre, descripcion, obligatoria, tiempoMinutos);

        assertEquals(nombre, tarea.getNombre());
        assertEquals(descripcion, tarea.getDescripcion());
        assertTrue(tarea.isObligatoria());
        assertEquals(tiempoMinutos, tarea.getTiempoMinutos());
    }

    @Test
    void crearTarea_ConNombreYTiempo_DeberiaEstablecerValoresCorrectos() {
        String nombre = "Tarea2";
        double tiempoMinutos = 15.0;

        Tarea tarea = new Tarea(nombre, tiempoMinutos);

        assertEquals(nombre, tarea.getNombre());
        assertFalse(tarea.isObligatoria());
        assertEquals(tiempoMinutos, tarea.getTiempoMinutos());
    }

    @Test
    void realizarTarea_DeberiaSimularRealizacionDeTarea() {
        Tarea tarea = new Tarea("Tarea3", 0.5); // 0.5 minutos = 30 segundos

        long startTime = System.currentTimeMillis();
        tarea.realizarTarea("bryangomez1625@gmail.com");
        long endTime = System.currentTimeMillis();

        // Asumimos que el tiempo de espera es aproximadamente igual al tiempo estimado de la tarea
        assertTrue(endTime - startTime >= 30_000 && endTime - startTime < 31_000);
    }

}
