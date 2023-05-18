package view.com.company;

import javax.swing.*;

public class ViewAsignaturas extends JFrame {
    private JPanel ViewAsignaturas;
    private JTable table1;
    private JPanel ViewPersonas;
    private JPanel panelContainer;
    private JButton personasButton;
    private JButton asignaturasButton;
    private JTextField textField1;

    JPopupMenu popupMenu = new JPopupMenu();

    public ViewAsignaturas(){
        super("Gestor universidad");
        setContentPane(ViewAsignaturas);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/2;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/2;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (ancho/2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (alto/2)));

        JMenuItem menuItemAdd = new JMenuItem("Añadir nueva Asignatura");
        JMenuItem menuItemRemove = new JMenuItem("Eliminar de la BBDD");
        JMenuItem menuItemRemoveAll = new JMenuItem("Editar");

        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
        table1.setComponentPopupMenu(popupMenu);
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JPanel getViewPersonas() {
        return ViewPersonas;
    }

    public void setViewPersonas(JPanel viewPersonas) {
        ViewPersonas = viewPersonas;
    }

    public JPanel getPanelContainer() {
        return panelContainer;
    }

    public void setPanelContainer(JPanel panelContainer) {
        this.panelContainer = panelContainer;
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

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }
}
