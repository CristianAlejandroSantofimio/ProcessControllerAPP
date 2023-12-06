package com.example.controladorprocesos.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaEnlazadaDoble<T> implements Iterable<T>{

    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private Nodo<T> ultimaInsercion;
    private int size;


    public ListaEnlazadaDoble() {
        nodoPrimero = null;
        nodoUltimo = null;
        size = 0;
    }

    /**
     * crea una lista a partir de los elementos de otra
     * @param otraLista la lista que contiene los elementos a agregar
     */
    public ListaEnlazadaDoble(ListaEnlazadaDoble<T> otraLista) {
        if (otraLista != null) {
            for (T valor : otraLista) {
                agregarFinal(valor);
            }
        }
    }

    //Agregar al inicio de la lista
    public void agregarInicio(T valorNodo) {
        Nodo<T> nuevoNodo = new Nodo<>(valorNodo);

        if (isEmpty()) {
            nodoPrimero = nodoUltimo = nuevoNodo;
        } else {
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero.setAnteriorNodo(nuevoNodo);
            nodoPrimero = nuevoNodo;
        }
        size++;
    }



    //Agregar al final de la lista

    public void agregarFinal(T valorNodo) {
        Nodo<T> nuevoNodo = new Nodo<>(valorNodo);

        if (isEmpty()) {
            nodoPrimero = nodoUltimo = nuevoNodo;
        } else {
            nuevoNodo.setAnteriorNodo(nodoUltimo);
            nodoUltimo.setSiguienteNodo(nuevoNodo);
            nodoUltimo = nuevoNodo;
        }
        size++;
    }



    /**
     * Agrega un valor en la lista en una posición específica
     * @param indice índice donde se va a guardar el dato
     * @param dato El valor a guardar
     */
    public void agregar(T dato, int indice) {
        if (indiceValido(indice)) {
            Nodo<T> nuevo = new Nodo<>(dato);

            if (indice == 0) {
                agregarInicio(dato);
            } else if (indice == size) {
                agregarFinal(dato);
            } else {
                Nodo<T> actual = obtenerNodo(indice - 1);

                if (actual != null) {
                    nuevo.setSiguienteNodo(actual.getSiguienteNodo());
                    nuevo.setAnteriorNodo(actual);
                    actual.setSiguienteNodo(nuevo);

                    if (nuevo.getSiguienteNodo() != null) {
                        nuevo.getSiguienteNodo().setAnteriorNodo(nuevo);
                    }

                    size++;
                }
            }
        }
    }



    /**
     * Borra completamente la Lista
     */
    public void clear() {
        nodoPrimero = nodoUltimo = null;
        size = 0;
    }

    /**
     * Usado para saber en que posicion se encuentra un elemento de la lista
     * @param valor el elemento buscado
     * @return retorna la posicion del elemento buscado, y si no lo encuentra, devuelve -1
     */
    public int indexOf(T valor) {
        Nodo<T> actual = nodoPrimero;
        int indice = 0;

        while (actual != null) {
            if (actual.getValorNodo().equals(valor)) {
                return indice;
            }
            actual = actual.getSiguienteNodo();
            indice++;
        }

        return -1; // Si el valor no se encuentra en la lista
    }



    //Obtener Nodo el valor de un Nodo
    public T obtenerValorNodo(int indice) {

        Nodo<T> nodoTemporal = null;
        int contador = 0;

        if(indiceValido(indice))
        {
            nodoTemporal = nodoPrimero;

            while (contador < indice) {

                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }

        if(nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            return null;
    }


    //Verificar si indice es valido
    private boolean indiceValido(int indice) {
        if( indice>=0 && indice<=size ) {
            return true;
        }
        throw new RuntimeException("Índice no válido");
    }


    //Verificar si la lista esta vacia
    public boolean isEmpty() {
        return nodoPrimero == null && nodoUltimo == null;
    }



    //Eliminar dado el valor de un nodo
    /**
     * Elimina un elemento de la lista
     * @param dato dato a eliminar
     * @return El dato que se elimina
     */
    public T eliminar(T dato) {

        Nodo<T> nodo = buscarNodo(dato);

        if(nodo!=null) {

            Nodo<T> previo = nodo.getAnteriorNodo();
            Nodo<T> siguiente = nodo.getSiguienteNodo();

            if(previo==null) {
                nodoPrimero = siguiente;
            }else {
                previo.setSiguienteNodo(siguiente);
            }

            if(siguiente==null) {
                nodoUltimo = previo;
            }else {
                siguiente.setAnteriorNodo(previo);
            }

            nodo=null;
            size--;

            return dato;
        }

        throw new RuntimeException("El valor no existe");
    }


    //Elimina el primer nodo de la lista
    public T eliminarPrimero() {

        if( !isEmpty() ) {
            Nodo<T> nodoAux = nodoPrimero;
            T valor = nodoAux.getValorNodo();
            nodoPrimero = nodoAux.getSiguienteNodo();

            if(nodoPrimero==null) {
                nodoUltimo = null;
            }
            else
            {
                nodoPrimero.setAnteriorNodo(null);
            }

            size--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }


    public T eliminarUltimo() {

        if( !isEmpty() ) {
            T valor = nodoUltimo.getValorNodo();
            Nodo<T> prev = obtenerNodo(size-2);
            nodoUltimo = prev;

            if(nodoUltimo==null) {
                nodoPrimero=null;
            }else {
                prev.setSiguienteNodo(null);
            }

            size--;
            return valor;
        }

        throw new RuntimeException("Lista vacía");
    }


    /**
     * Devuelve el Nodo de una lista dado su posición
     * @param indice índice para obtener el Nodo
     * @return Nodo objeto
     */
    private Nodo<T> obtenerNodo(int indice) {

        if(indice>=0 && indice<=size) {

            Nodo<T> nodo = nodoPrimero;

            for (int i = 0; i < indice; i++) {
                try {
                    nodo = nodo.getSiguienteNodo();
                } catch (NullPointerException e) {
                    throw new IndexOutOfBoundsException("Índice fuera de los límites de la lista");
                }
            }

            return nodo;
        }

        return null;
    }

    /**
     * Devuelve un nodo que contenga un dato específico
     * @param dato Dato a buscar
     * @return Nodo
     */
    private Nodo<T> buscarNodo(T dato){

        Nodo<T> aux = nodoPrimero;

        while(aux!=null) {
            if(aux.getValorNodo().equals(dato)) {
                return aux;
            }
            aux = aux.getSiguienteNodo();
        }

        return null;
    }



    /**
     * Cambia el valor de un nodo dada su posición en la lista
     * @param indice posición donde se va a cambiar el dato
     * @param nuevo nuevo valor por el que se actualizará el nodo
     */
    public void modificarNodo(int indice, T nuevo) {

        if( indiceValido(indice) ) {
            Nodo<T> nodo = obtenerNodo(indice);
            nodo.setValorNodo(nuevo);
        }

    }


    /**
     * Retorna la primera posición donde está guardado el dato
     * @param dato valor a buscar dentro de la lista
     * @return primera posición del dato
     */
    public int obtenerPosicionNodo(T dato) {

        int i = 0;

        for( Nodo<T> aux = nodoPrimero ; aux!=null ; aux = aux.getSiguienteNodo() ) {
            if( aux.getValorNodo().equals(dato) ) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public void remove(T valor) {
        if(nodoPrimero!=null) {
            eliminar(valor);
        }
    }

    public void add(T e) {
        agregarFinal(e);
    }



    /**
     * Devuelve un elemento de la lista dado su índice
     * @param indice índice de la lista
     * @return dato guardado en el índice especificado
     */
    public T get(int indice) {

        if( indiceValido(indice) ) {
            Nodo<T> n = obtenerNodo(indice);

            if(n!=null) {
                return n.getValorNodo();
            }
        }

        return null;
    }


    @Override
    public Iterator<T> iterator() {

        return new IteradorListaDoble (nodoPrimero, nodoUltimo);
    }

    private IteradorListaDoble iteratorListaDoble() {

        return new IteradorListaDoble (nodoPrimero, nodoUltimo);
    }

    public boolean contains(T valor) {
        return buscarNodo(valor) != null;
    }

    public int size() {
        return getSize();
    }


    protected class IteradorListaDoble implements Iterator<T> {

        private Nodo<T> nodoPrimero;
        private Nodo<T> nodoUltimo;
        private int posicion;

        /**
         * Constructor de la clase Iterador
         * @param nodoPrimero Primer Nodo de la lista
         */
        public IteradorListaDoble(Nodo<T> nodoPrimero, Nodo<T> nodoUltimo) {
            this.nodoPrimero = nodoPrimero;
            this.nodoUltimo = nodoUltimo;
            this.posicion = 0;
        }

        @Override
        public boolean hasNext() {
            return nodoPrimero!=null;
        }

        @Override
        public T next() {
            T valor = nodoPrimero.getValorNodo();
            nodoPrimero = nodoPrimero.getSiguienteNodo();
            posicion++;
            return valor;
        }


        public boolean hasPrevious() {
            return nodoUltimo!=null;
        }


        public T previous() {
            T aux = nodoUltimo.getValorNodo();
            nodoUltimo = nodoUltimo.getAnteriorNodo();
            posicion--;
            return aux;
        }

        public int nextIndex() {
            return posicion;
        }


        public int previousIndex() {
            return posicion-1;
        }

        public void remove() {
            if(nodoPrimero!=null) {
                eliminar(nodoPrimero.getValorNodo());
            }
        }

        public void set(T e) {
            if(nodoPrimero!=null) {
                nodoPrimero.setValorNodo(e);
            }
        }

        public void add(T e) {
            agregarFinal(e);
        }


        /**
         * Posición actual de la lista
         * @return posición
         */
        public int getPosicion() {
            return posicion;
        }

    }


    public Nodo<T> getNodoPrimero() {
        return nodoPrimero;
    }


    public void setNodoPrimero(Nodo<T> nodoPrimero) {
        this.nodoPrimero = nodoPrimero;
    }


    public int getSize() {
        return size;
    }


    public void setSize(int tamaño) {
        this.size = tamaño;
    }


    public Nodo<T> getNodoUltimo() {
        return nodoUltimo;
    }


    public void setNodoUltimo(Nodo<T> nodoUltimo) {
        this.nodoUltimo = nodoUltimo;
    }

    /**
     * Metodo usado para insertar un nuevo nodo, despues del nodo mandado como parametro,
     * de tal forma que se desplazan los siguientes
     * @param nodo el nodo al que se le agrega el nuevo como siguiente
     * @param valor el valor del nodo por agregar
     */
    public void insertarSiguienteNodo(Nodo<T> nodo,T valor){
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if(nodo.getSiguienteNodo() != null) {
            nuevoNodo.setSiguienteNodo(nodo.getSiguienteNodo());
        }
        nuevoNodo.setAnteriorNodo(nodo);
        nodo.setSiguienteNodo(nuevoNodo);
        size ++;
    }

    /**
     * convierte la lista en un arraylist para despues usarlo
     * para agregar los elementos en un observablelist,
     * para despues mostrarlos en tableviews
     * @return lista con los elementos
     */
    public List<T> toArrayList() {
        List<T> lista = new ArrayList<>();
        Nodo<T> actual = nodoPrimero;

        while (actual != null) {
            lista.add(actual.getValorNodo());
            actual = actual.getSiguienteNodo();
        }

        return lista;
    }

}
