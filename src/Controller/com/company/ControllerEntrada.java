package Controller.com.company;

import view.com.company.ViewEntrada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

public class ControllerEntrada {
    private final ViewEntrada frEntrada = new ViewEntrada();
    private JButton botonDobleClick = new JButton();

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
            new ControllerPersonas();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }
}