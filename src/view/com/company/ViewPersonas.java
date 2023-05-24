package view.com.company;

import javax.swing.*;

public class ViewPersonas extends JFrame {
    private JPanel ViewPersonas;
    private JTable table1;
    private JButton personasButton;
    private JButton asignaturasButton;
    private JTextField campoBusqueda;
    JPopupMenu popupMenu = new JPopupMenu();

    private JMenuItem menuAniadir = new JMenuItem("Añadir nueva persona");
    private JMenuItem menuEliminar = new JMenuItem("Eliminar de la BBDD");
    private JMenuItem menuEditar = new JMenuItem("Guardar cambios");

    public ViewPersonas(){
        super("Gestor universidad");
        setContentPane(ViewPersonas);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/2;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/2;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (ancho/2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (alto/2)));

        popupMenu.add(menuAniadir);
        popupMenu.add(menuEditar);
        popupMenu.add(menuEliminar);
        table1.setComponentPopupMenu(popupMenu);
    }
    public JTable getTable1() {return table1;}

    public JButton getPersonasButton() {
        return personasButton;
    }

    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }

    public JMenuItem getMenuAniadir() {
        return menuAniadir;
    }

    public JMenuItem getMenuEliminar() {
        return menuEliminar;
    }

    public JMenuItem getMenuEditar() {
        return menuEditar;
    }

    public JTextField getCampoBusqueda() {
        return campoBusqueda;
    }
}
