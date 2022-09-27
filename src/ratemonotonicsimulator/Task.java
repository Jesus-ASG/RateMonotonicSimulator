package ratemonotonicsimulator;

import java.util.concurrent.ThreadLocalRandom;
import static ratemonotonicsimulator.RateMonotonicSimulator.tiempo_total;
import static ratemonotonicsimulator.RateMonotonicSimulator.tiempo_maximo;
import static ratemonotonicsimulator.RateMonotonicSimulator.arr;
import static ratemonotonicsimulator.RateMonotonicSimulator.index_list;

public class Task implements Runnable {

    // variables de tarea
    public String id;
    public int c;
    public int t;

    // variables de control
    private int c_actual = 0;
    private int c_reposo = 0;
    public boolean completada = false;

    public Task(String id, int c, int t) {
        this.id = id;
        this.c = c;
        this.t = t;
    }

    @Override
    public void run() {
        while (tiempo_total <= tiempo_maximo) {
            //System.out.println(taux);

            // Setear como no completada cada periodo
            if (tiempo_total % t == 0) {
                completada = false;
            }

            // Si es su turno
            if (arr.get(
                    index_list).id.equals(this.id)) {
                System.out.println("turno de : " + id);

                // si no está completada
                if (!completada) {
                    // si tiene espacio delante --> hace una unidad
                    //if ((tiempo_total + 1) % t != 0) {

                    // esperar a que el reloj cambie
                    int ant = tiempo_total;
                    while (true) {
                        if (ant != tiempo_total) {
                            break;
                        }
                        sleep(10);
                    }
                    
                    // checar si hay espacio 
                    if (!hayEspacio()){
                        pasarTurno();
                    }
                    
                    // checar si hay tiempo
                    if (tiempo_total - 1 < tiempo_maximo) {
                        System.out.println("" + id + " " + index_list + " " + tiempo_total);
                        Ventana.Table.setValueAt(id, index_list, tiempo_total - 1);

                        System.out.println(this.id + " en tiempo total: " + tiempo_total);

                        c_actual++;
                    }

                    // Para saber si completó sus unidades
                    if (c_actual >= c) {
                        completada = true;
                        c_actual = 0;
                        pasarTurno();
                    }
                    //} // de no tener espacio debe pasar el turno
                    /*else {
                        pasarTurno();
                    }*/
                } // de estar completa debe marcarse como completa y pasar turno
                else {
                    pasarTurno();
                }

            }
            sleep(10);
        }

    }

    private void pasarTurno() {

        if (RateMonotonicSimulator.index_list + 1 < RateMonotonicSimulator.arr.size()) {
            RateMonotonicSimulator.index_list++;
        } else {
            RateMonotonicSimulator.index_list = 0;
        }
    }
    
    private boolean hayEspacio(){
        for (int i = 0; i < arr.size(); i++) {
            if (tiempo_total%arr.get(i).t==0)
                return false;
        }
        return true;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

}
