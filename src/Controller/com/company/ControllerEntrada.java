package Controller.com.company;

import model.com.company.ModelPersonas;
import view.com.company.ViewEntrada;
import java.util.concurrent.TimeUnit;

public class ControllerEntrada {
    private final ViewEntrada frEntrada = new ViewEntrada();

    public ControllerEntrada() {
        iniciarVentana();
        iniciarApp();
    }


    public void iniciarVentana() {
        frEntrada.setVisible(true);
    }

    public void iniciarApp() {
        /*
        Pantalla de entrada, en la cuál abrimos la conexión con la base de datos de forma global, en caso de registrar un
        NullPointerException (lo cuál significaría que la conexión con la BBDD no ha sido satisfactoria), el programa terminará
        */
        try {
            new ModelPersonas();
            TimeUnit.SECONDS.sleep(3);
            frEntrada.dispose();
            new ControllerPersonas();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            frEntrada.dispose();
        } catch (NullPointerException npe) {
        System.exit(0);
    }

    }
}