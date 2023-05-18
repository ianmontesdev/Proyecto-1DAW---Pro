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
    private JTextField textField1;
    JPopupMenu popupMenu = new JPopupMenu();

    public ViewPersonas(){
        super("Gestor universidad");
        setContentPane(ViewPersonas);
        int ancho = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width)/2;
        int alto = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height)/2;
        setSize(ancho, alto);
        setLocation((java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (ancho/2)), (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (alto/2)));

        JMenuItem menuItemAdd = new JMenuItem("Añadir nueva persona");
        JMenuItem menuItemRemove = new JMenuItem("Eliminar de la BBDD");
        JMenuItem menuItemRemoveAll = new JMenuItem("Editar");

        popupMenu.add(menuItemAdd);
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemRemoveAll);
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
}
