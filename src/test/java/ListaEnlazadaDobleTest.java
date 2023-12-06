import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import com.example.controladorprocesos.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaEnlazadaDobleTest {

    private ListaEnlazadaDoble<Integer> lista;

    @BeforeEach
    public void setUp() {
        lista = new ListaEnlazadaDoble<>();
    }

    @Test
    public void deberiaAgregarElementoAlInicio() {
        lista.agregarInicio(1);
        assertEquals(1, lista.get(0));
    }

    @Test
    public void deberiaAgregarElementoAlFinal() {
        lista.agregarFinal(1);
        assertEquals(1, lista.get(0));
    }

    @Test
    public void deberiaAgregarElementoEnPosicionEspecifica() {
        lista.agregar(1, 0);
        lista.agregar(2, 1);
        lista.agregar(3, 1);

        assertEquals(1, lista.get(0));
        assertEquals(3, lista.get(1));
        assertEquals(2, lista.get(2));
    }

    @Test
    public void deberiaEliminarElementoPorValor() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);
        lista.agregarInicio(3);

        assertEquals(3, lista.eliminar(3));
        assertEquals(2, lista.get(0));
        assertEquals(1, lista.get(1));
    }

    @Test
    public void deberiaEliminarPrimerElemento() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        assertEquals(2, lista.eliminarPrimero());
        assertEquals(1, lista.get(0));
    }

    @Test
    public void deberiaEliminarUltimoElemento() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        assertEquals(1, lista.eliminarUltimo());
        assertEquals(2, lista.get(0));
    }

    @Test
    public void deberiaLimpiarLaLista() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        lista.clear();

        assertTrue(lista.isEmpty());
    }

    @Test
    public void deberiaRetornarIndiceDeElemento() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);
        lista.agregarInicio(3);

        assertEquals(1, lista.indexOf(2));
    }

    @Test
    public void deberiaModificarElementoEnPosicion() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        lista.modificarNodo(1, 10);

        assertEquals(10, lista.get(1));
    }

    @Test
    public void deberiaDevolverTamanoCorrecto() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);
        lista.agregarInicio(3);

        assertEquals(3, lista.size());
    }

    @Test
    public void deberiaConvertirListaAArrayList() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);
        lista.agregarInicio(3);

        assertEquals(3, lista.toArrayList().size());
    }

    @Test
    public void deberiaRecorrerListaConIterator() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);
        lista.agregarInicio(3);

        Iterator<Integer> iterator = lista.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void deberiaVerificarSiContieneElemento() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        assertTrue(lista.contains(1));
        assertFalse(lista.contains(3));
    }

    @Test
    public void deberiaObtenerElementoPorIndice() {
        lista.agregarInicio(1);
        lista.agregarInicio(2);

        assertEquals(1, lista.get(1));
    }

    @Test
    public void deberiaInsertarSiguienteNodo() {
        lista.agregarInicio(1);
        lista.agregarInicio(3);

        Nodo<Integer> nodo = lista.getNodoPrimero();
        lista.insertarSiguienteNodo(nodo, 2);

        assertEquals(2, lista.get(1));
    }
}
