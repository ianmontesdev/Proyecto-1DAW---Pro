package view.com.company;
import javax.swing.*;

public class ViewEntrada extends JFrame{

    private JPanel Entrada;

    public ViewEntrada(){
        super("Gestor universidad");
        setContentPane(Entrada);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/3;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/3;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (ancho/2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (alto/2)));
        setUndecorated(true);
    }

}
