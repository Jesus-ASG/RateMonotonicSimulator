package ratemonotonicsimulator;

import static ratemonotonicsimulator.RateMonotonicSimulator.tiempo_total;
import static ratemonotonicsimulator.RateMonotonicSimulator.tiempo_maximo;
import static ratemonotonicsimulator.RateMonotonicSimulator.arr;
import static ratemonotonicsimulator.RateMonotonicSimulator.index_list;

public class Task implements Runnable {

    // variables de tarea
    public String id;
    public int c;
    public int t;
    public int fila;

    // variables de control
    private int c_actual = 0;
    public boolean completada = false;

    public Task(String id, int c, int t, int fila) {
        this.id = id;
        this.c = c;
        this.t = t;
        this.fila = fila;
    }

    @Override
    public void run() {
        while (tiempo_total <= tiempo_maximo) {
            // Setear como no completada cada periodo
            if (tiempo_total % t == 0) 
                completada = false;

            // Si es su turno
            if (arr.get(index_list).id.equals(this.id)){

                // si no está completada
                if (!completada) {
                    // esperar a que el reloj cambie
                    int ant = tiempo_total;
                    while (true) {
                        if (ant != tiempo_total) 
                            break;
                        sleep(1);
                    }

                    // checar si hay espacio 
                    if (!hayEspacio())
                        pasarTurno();

                    // checar si hay tiempo
                    if (tiempo_total - 1 < tiempo_maximo) {
                        if (tiempo_total -1 < 20) // Mostrar en tabla 1
                            Ventana.Table.setValueAt(id, fila, tiempo_total - 1);
                        else if (tiempo_total -1 <40) // Mostrar en tabla 2
                            Ventana.Table1.setValueAt(id, fila, tiempo_total - 1 -20);
                        c_actual++;
                    }

                    // Para saber si completó sus unidades
                    if (c_actual >= c) {
                        completada = true;
                        c_actual = 0;
                        pasarTurno();
                    }
                } // de estar completa debe marcarse como completa y pasar turno
                else
                    pasarTurno();
            }
            sleep(10);
        }

    }

    private void pasarTurno() {
        if (index_list + 1 < arr.size()) {
            index_list++;
        } else {
            index_list = 0;
        }
    }

    private boolean hayEspacio() {
        for (int i = 0; i < arr.size(); i++)
            if (tiempo_total % arr.get(i).t == 0)
                return false;
        return true;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {}
    }

}
