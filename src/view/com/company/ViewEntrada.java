package view.com.company;
import javax.swing.*;

public class ViewEntrada extends JFrame{

    private JPanel Entrada;

    public ViewEntrada(){
        super("Gestor universidad");
        setContentPane(Entrada);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/2;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/2;
        setSize(ancho, alto);
        setUndecorated(true);
    }

}
