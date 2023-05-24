package view.com.company;

import javax.swing.*;

public class ViewAsignaturas extends JFrame {
    private JPanel ViewAsignaturas;
    private JTable table1;
    private JButton personasButton;
    private JButton asignaturasButton;
    private JTextField CampoBusqueda;
    JPopupMenu popupMenu = new JPopupMenu();

    private JMenuItem menuAniadir = new JMenuItem("Añadir nueva asignatura");
    private JMenuItem menuEliminar = new JMenuItem("Eliminar de la BBDD");
    private JMenuItem menuEditar = new JMenuItem("Guardar cambios");

    public ViewAsignaturas(){
        super("Gestor universidad");
        setContentPane(ViewAsignaturas);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/2;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/2;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (ancho/2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (alto/2)));

        popupMenu.add(menuAniadir);
        popupMenu.add(menuEliminar);
        popupMenu.add(menuEditar);
        table1.setComponentPopupMenu(popupMenu);
    }
    public JTable getTable1() {return table1;}

    public JButton getPersonasButton() {
        return personasButton;
    }

    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }

    public JMenuItem getMenuAniadir() {return menuAniadir;}

    public JMenuItem getMenuEliminar() {return menuEliminar;}

    public JMenuItem getMenuEditar() {return menuEditar;}

    public JTextField getCampoBusqueda() {return CampoBusqueda;}
}
