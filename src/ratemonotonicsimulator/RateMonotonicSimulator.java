package ratemonotonicsimulator;

import java.util.ArrayList;

public class RateMonotonicSimulator {

    public static boolean ub = false;
    public static int tiempo_total = 0;
    public static String turno_de = "";
    public static ArrayList<Task> arr;
    public static ArrayList<Double> u;
    public static int index_list = 0;
    public static int t_min;
    public static int tiempo_maximo;
    public static double consumo;

    public RateMonotonicSimulator() {

        // Definir las tareas
        Task t1 = new Task("T1", 1, 4, 0);
        Task t2 = new Task("T2", 2, 6, 1);
        Task t3 = new Task("T3", 2, 10, 2);

        // Tiempo maximo de simulación
        tiempo_maximo = 40;

        // definir prioridades  t1 > t2 > t3
        arr = new ArrayList<>();
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);

        // indicar prioridades
        turno_de = arr.get(0).id;

        // Obtener periodo mínimo y de una vez
        // ver si las tareas se pueden realizar
        consumo = 0;
        u = new ArrayList<>();
        t_min = arr.get(0).t;
        for (int i = 0; i < arr.size(); i++) {
            if (t_min > arr.get(i).t) {
                t_min = arr.get(i).t;
            }
            double aux = (double) arr.get(i).c / (double) arr.get(i).t;
            u.add(aux);
            consumo = consumo + aux;
        }

        if (consumo > 1) {
            Thread displayTablaUB = new Thread(new DisplayTablaUB());
            displayTablaUB.start();
        } else {
            // Creación de hilos
            Thread displayTablaUB = new Thread(new DisplayTablaUB());
            Thread thr_t1 = new Thread(t1);
            Thread thr_t2 = new Thread(t2);
            Thread thr_t3 = new Thread(t3);

            displayTablaUB.start();
            thr_t1.start();
            thr_t2.start();
            thr_t3.start();

            // Esto es como un reloj
            while (true) {
                try {
                    Thread.sleep(500);
                    tiempo_total++;

                } catch (Exception e) {
                }

            }
        }

    }
}
