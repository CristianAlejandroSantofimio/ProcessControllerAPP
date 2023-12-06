package com.example.controladorprocesos.model;

public class Nodo<T> {
    private Nodo<T> nodoSiguiente;
    private Nodo<T> nodoAnterior;
    private T valorNodo;
    public Nodo(T valorNodo) {
        this.valorNodo = valorNodo;
    }

    public void setSiguienteNodo(Nodo<T> nodo) {
        this.nodoSiguiente = nodo;
    }

    public void setAnteriorNodo(Nodo<T> nodo) {
        nodoAnterior = nodo;
    }

    public Nodo<T> getAnteriorNodo() {
        return nodoAnterior;
    }

    public Nodo<T> getSiguienteNodo() {
        return nodoSiguiente;
    }

    public T getValorNodo() {
        return valorNodo;
    }

    public void setValorNodo(T nuevo) {
        this.valorNodo = nuevo;
    }
}
