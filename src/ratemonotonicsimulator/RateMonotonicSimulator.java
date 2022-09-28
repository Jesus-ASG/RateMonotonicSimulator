package ratemonotonicsimulator;

import java.util.ArrayList;

public class RateMonotonicSimulator {

    public static boolean ub = false;
    public static int tiempo_total = 0;
    public static ArrayList<Task> arr;
    public static ArrayList<Double> u;
    public static int index_list = 0;
    public static int t_min;
    public static int tiempo_maximo;
    public static double consumo;

    public RateMonotonicSimulator() {

        // Tiempo maximo de simulación
        tiempo_maximo = 12;

        // Definir las tareas
        Task t1 = new Task("T1", 1, 4, 0);
        Task t2 = new Task("T2", 2, 6, 1);
        Task t3 = new Task("T3", 2, 10, 2);

        // Definir prioridades  t1 > t2 > t3
        arr = new ArrayList<>();
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);

        // Ver si las tareas se pueden realizar
        u = new ArrayList<>();
        consumo = 0;
        for (int i = 0; i < arr.size(); i++) {
            double aux = (double) arr.get(i).c / (double) arr.get(i).t;
            u.add(aux);
            consumo = consumo + aux;
        }

        if (consumo > 1) {
            Thread displayTablaUB = new Thread(new DisplayTablaUB());
            displayTablaUB.start();
        } else {
            // Hilo para tabla
            Thread displayTablaUB = new Thread(new DisplayTablaUB());
            displayTablaUB.start();

            // Hilos para las tareas
            Thread thr_t1 = new Thread(t1);
            Thread thr_t2 = new Thread(t2);
            Thread thr_t3 = new Thread(t3);

            thr_t1.start();
            thr_t2.start();
            thr_t3.start();

            reloj(500);
        }
    }

    private void reloj(int millis) {
        while (true) {
            try {
                Thread.sleep(millis);
                tiempo_total++;
            } catch (Exception e) {}
        }
    }
}
