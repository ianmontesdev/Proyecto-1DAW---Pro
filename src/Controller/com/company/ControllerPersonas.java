package Controller.com.company;

import Connection.ConectionBD;
import com.company.DataValidation;
import com.company.MenuBar;
import model.com.company.ModelPersonas;
import view.com.company.ViewPersonas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;

import static com.company.DataValidation.gestionNull;
import static com.company.FilterTabla.actualizarFiltro;
import static com.company.FormatUI.setAppIcon;

public class ControllerPersonas implements ActionListener, WindowListener {
    private final ViewPersonas frPersonas = new ViewPersonas();
    private final DefaultTableModel m = null;
    private boolean esNuevaEntrada = false;
    public ControllerPersonas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
    }
    public void iniciarVentana() {
        setAppIcon(frPersonas);
        frPersonas.setVisible(true);
        frPersonas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar();
        frPersonas.setJMenuBar(menuBar.MenuBar());
    }
    public void iniciarEventos() {
        frPersonas.getPersonasButton().addActionListener(this);
        frPersonas.getAsignaturasButton().addActionListener(this);
        frPersonas.getMenuAniadir().addActionListener(this);
        frPersonas.getMenuEditar().addActionListener(this);
        frPersonas.getMenuEliminar().addActionListener(this);
        frPersonas.getCampoBusqueda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarFiltro(frPersonas.getCampoBusqueda().getText(), frPersonas);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frPersonas.getCampoBusqueda().getText(), frPersonas);
            }
            @Override
            public void changedUpdate(DocumentEvent e){}});
    }

    public void prepararBaseDatos() {
        frPersonas.getTable1().setModel(ModelPersonas.CargaDatos(m));
    }

    private void eliminarEntrada(String nif) throws SQLException {
        if(esNuevaEntrada && frPersonas.getTable1().getRowCount()-1 == frPersonas.getTable1().getSelectedRow()){
            DefaultTableModel dt = (DefaultTableModel) frPersonas.getTable1().getModel();
            dt.removeRow(frPersonas.getTable1().getRowCount()-1);
            esNuevaEntrada = false;
        }else {
            String consulta = "DELETE FROM `persona` WHERE `persona`.`nif` = " + "'" + nif + "'";
            ModelPersonas.getStmt().executeUpdate(consulta);
            prepararBaseDatos();
            esNuevaEntrada = false;
            JOptionPane.showMessageDialog(null, "El usuario con nif " + nif + " fue eliminado con éxito");
        }
    }

    private void actualizarEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        String consulta = "UPDATE `persona`" +
                "SET `nombre` = '" + nombre + "', " +
                "`apellido1` = '" + apellido1 + "', " +
                "`apellido2` = " + gestionNull(apellido2) + ", " +
                "`ciudad` = '" + ciudad + "', " +
                "`direccion` = '" + direccion + "', " +
                "`telefono` = " + gestionNull(telefono) + ", " +
                "`fecha_nacimiento` = '" + fechaN + "', " +
                "`sexo` = '" + sexo + "', " +
                "`tipo` = '" + tipo + "'" +
                "WHERE `persona`.`nif` = " + "'" + nif + "'";

        if (DataValidation.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frPersonas)) {
                ModelPersonas.getStmt().executeUpdate(consulta);
                prepararBaseDatos();
        }
    }
    private void aniadirColumna() {
        DefaultTableModel tabla = (DefaultTableModel) frPersonas.getTable1().getModel();
        tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
        frPersonas.getScrollPane().getVerticalScrollBar().setValue(frPersonas.getScrollPane().getVerticalScrollBar().getMaximum());
        esNuevaEntrada = true;
    }

    private void aniadirEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        String consulta = "INSERT INTO `persona` " +
                "(`nif`, `nombre`, `apellido1`, `apellido2`, `ciudad`, `direccion`, `telefono`, `fecha_nacimiento`, `sexo`, `tipo`) " +
                "VALUES ('" + nif + "', '" + nombre + "', '" + apellido1 + "', " + gestionNull(apellido2) + ", '" + ciudad + "', '" + direccion + "', " + gestionNull(telefono) + ", '" + fechaN + "', '" + sexo + "', '" + tipo + "')";

        if (DataValidation.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frPersonas)) {
            ModelPersonas.getStmt().executeUpdate(consulta);
            frPersonas.getTable1().setModel(ModelPersonas.CargaDatos(m));
            esNuevaEntrada = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Personas" -> {
                esNuevaEntrada = false;
                frPersonas.getTable1().setModel(ModelPersonas.CargaDatos(m));
            }
            case "Asignaturas" -> {
                esNuevaEntrada = false;
                new ControllerAsignaturas();
                frPersonas.dispose();
            }
            case "Añadir nueva persona" -> aniadirColumna();
            case "Eliminar de la BBDD" -> {
                try {
                    eliminarEntrada(frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ArrayIndexOutOfBoundsException aie) {
                    JOptionPane.showMessageDialog(null, "Primero debes seleccionar una fila para eliminar");
                }
            }
            case "Guardar cambios" -> {
                if (esNuevaEntrada && frPersonas.getTable1().getSelectedRow() == (frPersonas.getTable1().getRowCount() - 1)) {
                    try {
                        aniadirEntrada((String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 1),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 2),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 3),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 4),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 5),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 6),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 7),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 8),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 9));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        actualizarEntrada((String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 1),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 2),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 3),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 4),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 5),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 6),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 7),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 8),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 9));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        ConectionBD.closeConn();
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
