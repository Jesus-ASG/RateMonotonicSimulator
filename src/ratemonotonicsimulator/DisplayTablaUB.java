/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ratemonotonicsimulator;

import static ratemonotonicsimulator.RateMonotonicSimulator.arr;
import static ratemonotonicsimulator.RateMonotonicSimulator.consumo;
import static ratemonotonicsimulator.RateMonotonicSimulator.u;
import static ratemonotonicsimulator.RateMonotonicSimulator.ub;

/**
 *
 * @author ale-j
 */
public class DisplayTablaUB implements Runnable {

    @Override
    public void run() {
        sleep(300);
        for (int i = 0; i < arr.size(); i++) {
            Ventana.TestUB.setValueAt(arr.get(i).id, i, 0);
            Ventana.TestUB.setValueAt(arr.get(i).c, i, 1);
            Ventana.TestUB.setValueAt(arr.get(i).t, i, 2);
            Ventana.TestUB.setValueAt(u.get(i), i, 3);
        }
        ub = true;
        Ventana.TestUB.setValueAt("U(3) = ", arr.size(), 2);
        Ventana.TestUB.setValueAt(consumo, arr.size(), 3);
        
        if(consumo>1)
            Ventana.mensaje_de_error.setText("Consumo > 1, no se puede realizar");
        else
            Ventana.mensaje_de_error.setText("");
        
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }
}
