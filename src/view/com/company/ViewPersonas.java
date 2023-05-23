package view.com.company;

import javax.swing.*;

public class ViewPersonas extends JFrame {
    private JPanel ViewPersonas;
    private JPanel panelContainer;
    private JButton aniadirpersonasButton;
    private JButton aniadirasignaturasButton;
    private JTable table1;
    private JButton personasButton;
    private JButton asignaturasButton;
    private JTextField campoBusqueda;
    private JScrollPane ScrollPane;
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

    public JPanel getPanelContainer() {
        return panelContainer;
    }

    public void setPanelContainer(JPanel panelContainer) {
        this.panelContainer = panelContainer;
    }

    public JButton getAniadirpersonasButton() {
        return aniadirpersonasButton;
    }

    public void setAniadirpersonasButton(JButton aniadirpersonasButton) {
        this.aniadirpersonasButton = aniadirpersonasButton;
    }

    public JButton getAniadirasignaturasButton() {
        return aniadirasignaturasButton;
    }

    public void setAniadirasignaturasButton(JButton aniadirasignaturasButton) {
        this.aniadirasignaturasButton = aniadirasignaturasButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getPersonasButton() {
        return personasButton;
    }

    public void setPersonasButton(JButton personasButton) {
        this.personasButton = personasButton;
    }

    public JButton getAsignaturasButton() {
        return asignaturasButton;
    }

    public void setAsignaturasButton(JButton asignaturasButton) {
        this.asignaturasButton = asignaturasButton;
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

    public void setCampoBusqueda(JTextField campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }
}
