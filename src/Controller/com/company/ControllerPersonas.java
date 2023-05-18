package Controller.com.company;

import model.com.company.ModelAsignaturas;
import model.com.company.ModelPersonas;
import view.com.company.ViewAsignaturas;
import view.com.company.ViewPersonas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ControllerPersonas implements ActionListener, MouseListener, WindowListener, KeyListener {

    private final ViewPersonas frPersonas = new ViewPersonas();
    private final DefaultTableModel m = null;

    public ControllerPersonas(){
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
    }

    public void iniciarVentana() {
        frPersonas.setVisible(true);
    }


    public void iniciarEventos() {
        frPersonas.getPersonasButton().addActionListener(this::actionPerformed);
        frPersonas.getAsignaturasButton().addActionListener(this::actionPerformed);
    }

    public void prepararBaseDatos() {
        ModelPersonas entrada = new ModelPersonas();
        frPersonas.getTable1().setModel(entrada.CargaDatos(m));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Personas":
                ModelPersonas persona = new ModelPersonas();
                frPersonas.getTable1().setModel(persona.CargaDatos(m));
                break;
            case "Asignaturas":
                new ControllerAsignaturas();
                frPersonas.dispose();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
