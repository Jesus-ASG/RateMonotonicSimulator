package ratemonotonicsimulator;


import java.util.ArrayList;

public class RateMonotonicSimulator {
    public static boolean tik = false;
    public static int tiempo_total = 0;
    public static String turno_de = "";
    public static ArrayList<Task> arr;
    public static int index_list = 0;
    public static int t_min;
    public static int tiempo_maximo;
    

    public RateMonotonicSimulator() {
        
        // Definir las tareas
        Task t1 = new Task("t1", 1, 4);
        Task t2 = new Task("t2", 2, 6);
        Task t3 = new Task("t3", 2, 10);
        
        // Tiempo maximo de simulación
        tiempo_maximo = 20;
        
        // definir prioridades  t1 > t2 > t3
        arr = new ArrayList<>();
        arr.add(t1);
        arr.add(t2);
        arr.add(t3);
        
        // indicar prioridades
        turno_de = arr.get(0).id;
        
        // Obtener periodo mínimo y de una vez
        // ver si las tareas se pueden realizar
        double consumo = 0;
        t_min = arr.get(0).t;
        for (int i = 0; i < arr.size(); i++) {
            if (t_min > arr.get(i).t){
                t_min = arr.get(i).t;
            }
            double aux = (double)arr.get(i).c / (double)arr.get(i).t;
            consumo = consumo + aux;
        }
        
        if(consumo > 1){
            System.out.println("Consumo: "+consumo);
            System.out.println("No se puede realizar, saliendo...");
            System.exit(0);
        }
        System.out.println("El consumo será de "+consumo);
        
        // Creación de hilos
        Thread thr_t1 = new Thread(t1);
        Thread thr_t2 = new Thread(t2);
        Thread thr_t3 = new Thread(t3);
        
        thr_t1.start();
        thr_t2.start();
        thr_t3.start();
        
        
        // Esto es como un reloj
        while(true){
            try{
                Thread.sleep(500);
                tiempo_total++;
            } catch (Exception e){}
            
        }
        
        
    }
}
