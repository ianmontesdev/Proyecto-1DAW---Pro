package view.com.company;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class ViewEntrada extends JFrame {

    private JPanel Entrada;

    public ViewEntrada() {
        super("Gestor universidad");
        setContentPane(Entrada);
        int ancho = 750;
        int alto = 500;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - (ancho / 2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - (alto / 2)));
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, ancho, alto, 50, 50));
    }

}
