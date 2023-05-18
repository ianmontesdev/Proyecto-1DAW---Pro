package Controler.com.company;

import view.com.company.ViewEntrada;

import java.util.concurrent.TimeUnit;

public class ControllerEntrada {
    private final ViewEntrada frEntrada = new ViewEntrada();

    public ControllerEntrada(){
        iniciarVentana();
        iniciarApp();
    }


    public void iniciarVentana() {
        frEntrada.setVisible(true);
    }

    public void iniciarApp() {
        try {
            TimeUnit.SECONDS.sleep(3);
            frEntrada.dispose();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

}
