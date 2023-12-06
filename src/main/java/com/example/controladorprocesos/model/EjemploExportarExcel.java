package com.example.controladorprocesos.model;

public class EjemploExportarExcel {
    public static void main(String[] args) {
        // Crear procesos
        Proceso proceso1 = new Proceso(1, "Proceso 1");
        Proceso proceso2 = new Proceso(2, "Proceso 2");
        Proceso proceso3 = new Proceso(3, "Proceso 3");

        // Crear actividades para el proceso 1
        Actividad actividad11 = new Actividad("Actividad 1.1", "Descripción 1.1", true);
        Actividad actividad12 = new Actividad("Actividad 1.2", "Descripción 1.2", true);

        // Crear tareas para la actividad 1.1
        Tarea tarea111 = new Tarea("Tarea 1.1.1", "Descripción 1.1.1", true, 10.0);
        Tarea tarea112 = new Tarea("Tarea 1.1.2", "Descripción 1.1.2", true, 15.0);
        Tarea tarea113 = new Tarea("Tarea 1.1.3", "Descripción 1.1.3", false, 20.0);

        // Agregar tareas a la actividad 1.1
        actividad11.getTareas().agregarFinal(tarea111);
        actividad11.getTareas().agregarFinal(tarea112);
        actividad11.getTareas().agregarFinal(tarea113);

        // Crear tareas para la actividad 1.2
        Tarea tarea121 = new Tarea("Tarea 1.2.1", "Descripción 1.2.1", true, 8.0);
        Tarea tarea122 = new Tarea("Tarea 1.2.2", "Descripción 1.2.2", false, 12.0);
        Tarea tarea123 = new Tarea("Tarea 1.2.3", "Descripción 1.2.3", true, 18.0);

        // Agregar tareas a la actividad 1.2
        actividad12.getTareas().agregarFinal(tarea121);
        actividad12.getTareas().agregarFinal(tarea122);
        actividad12.getTareas().agregarFinal(tarea123);

        // Agregar actividades al proceso 1
        proceso1.getActividades().agregarFinal(actividad11);
        proceso1.getActividades().agregarFinal(actividad12);

        // Crear actividades para el proceso 2
        Actividad actividad21 = new Actividad("Actividad 2.1", "Descripción 2.1", true);
        Actividad actividad22 = new Actividad("Actividad 2.2", "Descripción 2.2", false);

        // Crear tareas para la actividad 2.1
        Tarea tarea211 = new Tarea("Tarea 2.1.1", "Descripción 2.1.1", false, 25.0);
        Tarea tarea212 = new Tarea("Tarea 2.1.2", "Descripción 2.1.2", true, 18.0);
        Tarea tarea213 = new Tarea("Tarea 2.1.3", "Descripción 2.1.3", true, 12.0);

        // Agregar tareas a la actividad 2.1
        actividad21.getTareas().agregarFinal(tarea211);
        actividad21.getTareas().agregarFinal(tarea212);
        actividad21.getTareas().agregarFinal(tarea213);

        // Crear tareas para la actividad 2.2
        Tarea tarea221 = new Tarea("Tarea 2.2.1", "Descripción 2.2.1", false, 15.0);
        Tarea tarea222 = new Tarea("Tarea 2.2.2", "Descripción 2.2.2", true, 20.0);
        Tarea tarea223 = new Tarea("Tarea 2.2.3", "Descripción 2.2.3", true, 30.0);

        // Agregar tareas a la actividad 2.2
        actividad22.getTareas().agregarFinal(tarea221);
        actividad22.getTareas().agregarFinal(tarea222);
        actividad22.getTareas().agregarFinal(tarea223);

        // Agregar actividades al proceso 2
        proceso2.getActividades().agregarFinal(actividad21);
        proceso2.getActividades().agregarFinal(actividad22);

        // Crear actividades para el proceso 3
        Actividad actividad31 = new Actividad("Actividad 3.1", "Descripción 3.1", true);
        Actividad actividad32 = new Actividad("Actividad 3.2", "Descripción 3.2", true);

        // Crear tareas para la actividad 3.1
        Tarea tarea311 = new Tarea("Tarea 3.1.1", "Descripción 3.1.1", true, 12.0);
        Tarea tarea312 = new Tarea("Tarea 3.1.2", "Descripción 3.1.2", false, 15.0);
        Tarea tarea313 = new Tarea("Tarea 3.1.3", "Descripción 3.1.3", true, 10.0);

        // Agregar tareas a la actividad 3.1
        actividad31.getTareas().agregarFinal(tarea311);
        actividad31.getTareas().agregarFinal(tarea312);
        actividad31.getTareas().agregarFinal(tarea313);

        // Crear tareas para la actividad 3.2
        Tarea tarea321 = new Tarea("Tarea 3.2.1", "Descripción 3.2.1", false, 18.0);
        Tarea tarea322 = new Tarea("Tarea 3.2.2", "Descripción 3.2.2", true, 20.0);
        Tarea tarea323 = new Tarea("Tarea 3.2.3", "Descripción 3.2.3", true, 25.0);

        // Agregar tareas a la actividad 3.2
        actividad32.getTareas().agregarFinal(tarea321);
        actividad32.getTareas().agregarFinal(tarea322);
        actividad32.getTareas().agregarFinal(tarea323);

        // Agregar actividades al proceso 3
        proceso3.getActividades().agregarFinal(actividad31);
        proceso3.getActividades().agregarFinal(actividad32);

        Gestor gestor = new Gestor();
        ListaEnlazadaDoble<Proceso> procesos = new ListaEnlazadaDoble<>();
        procesos.add(proceso1);
        procesos.add(proceso2);
        procesos.add(proceso3);
        gestor.setProcesos(procesos);

        gestor.exportarExcelListaProcesos("src/main/resources/archivos/procesos.xlsx");

/*        // Imprimir información de procesos
        imprimirInformacionProceso(proceso1);
        imprimirInformacionProceso(proceso2);
        imprimirInformacionProceso(proceso3);*/
    }

    private static void imprimirInformacionProceso(Proceso proceso) {
        System.out.println("Proceso: " + proceso.getNombre());
        for (Actividad actividad : proceso.getActividades()) {
            System.out.println("  Actividad: " + actividad.getNombre());
            for (Tarea tarea : actividad.getTareas()) {
                System.out.println("    Tarea: " + tarea.getNombre());
            }
        }
        System.out.println();
    }
}
