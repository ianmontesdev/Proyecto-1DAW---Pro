package Controller.com.company;

import Connection.ConectionBD;
import com.company.filterTabla;
import com.company.dataHandler;
import model.com.company.ModelPersonas;
import view.com.company.ViewPersonas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerPersonas implements ActionListener {

    private final ViewPersonas frPersonas = new ViewPersonas();
    Statement stmt;
    private final DefaultTableModel m = null;
    private boolean esNuevaEntrada = false;

    public ControllerPersonas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
    }

    public void iniciarVentana() {
        frPersonas.setVisible(true);
        frPersonas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void iniciarEventos() {
        frPersonas.getPersonasButton().addActionListener(this::actionPerformed);
        frPersonas.getAsignaturasButton().addActionListener(this::actionPerformed);
        frPersonas.getMenuAniadir().addActionListener(this::actionPerformed);
        frPersonas.getMenuEditar().addActionListener(this::actionPerformed);
        frPersonas.getMenuEliminar().addActionListener(this::actionPerformed);
        frPersonas.getCampoBusqueda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frPersonas.getCampoBusqueda().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frPersonas.getCampoBusqueda().getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frPersonas.getCampoBusqueda().getText());
            }
        });
    }

    private void actualizarFiltro(String input) {
        String filtro = frPersonas.getCampoBusqueda().getText();
        DefaultTableModel tabla = (DefaultTableModel) frPersonas.getTable1().getModel();
        filterTabla.filtrarTabla(input, tabla);
    }

    public void prepararBaseDatos() {
        ModelPersonas entrada = new ModelPersonas();
        frPersonas.getTable1().setModel(entrada.CargaDatos(m));
    }

    private void eliminarEntrada(String nif) throws SQLException {
        String consulta = "DELETE FROM `persona` WHERE `persona`.`nif` = " + "'" + nif + "'";
        stmt = ConectionBD.getStmt();
        stmt.executeUpdate(consulta);
        prepararBaseDatos();
        esNuevaEntrada = false;
        JOptionPane.showMessageDialog(null, "El usuario con nif " + nif + " fue eliminado con éxito");
    }

    private void actualizarEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        try {
            if (telefono.isEmpty()) {
                telefono = null;
            }
        } catch (NullPointerException e) {
            telefono = null;
        }
        try {
            if (apellido2.isEmpty()) {
                apellido2 = null;
            } else {
                apellido2 = "'" + apellido2 + "'";
            }
        } catch (NullPointerException e) {
            apellido2 = null;
        }

        String consulta = "UPDATE `persona`" +
                "SET `nombre` = '" + nombre + "', " +
                "`apellido1` = '" + apellido1 + "', " +
                "`apellido2` = " + apellido2 + ", " +
                "`ciudad` = '" + ciudad + "', " +
                "`direccion` = '" + direccion + "', " +
                "`telefono` = " + telefono + ", " +
                "`fecha_nacimiento` = '" + fechaN + "', " +
                "`sexo` = '" + sexo + "', " +
                "`tipo` = '" + tipo + "'" +
                "WHERE `persona`.`nif` = " + "'" + nif + "'";

        if (dataHandler.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frPersonas)) {
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            prepararBaseDatos();
        }
    }

    private void aniadirColumna() {
        DefaultTableModel tabla = (DefaultTableModel) frPersonas.getTable1().getModel();
        tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
        esNuevaEntrada = true;
    }

    private void aniadirEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {

        if (telefono.length() == 0) {
            telefono = null;
        }
        ;
        if (apellido2.length() == 0) {
            apellido2 = null;
        } else {
            apellido2 = "'" + apellido2 + "'";
        }

        String consulta = "INSERT INTO `persona` " +
                "(`nif`, `nombre`, `apellido1`, `apellido2`, `ciudad`, `direccion`, `telefono`, `fecha_nacimiento`, `sexo`, `tipo`) " +
                "VALUES ('" + nif + "', '" + nombre + "', '" + apellido1 + "', " + apellido2 + ", '" + ciudad + "', '" + direccion + "', " + telefono + ", '" + fechaN + "', '" + sexo + "', '" + tipo + "')";

        if (dataHandler.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frPersonas)) {
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            ModelPersonas persona = new ModelPersonas();
            frPersonas.getTable1().setModel(persona.CargaDatos(m));
            esNuevaEntrada = false;
        }

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
            case "Añadir nueva persona":
                aniadirColumna();
                break;
            case "Eliminar de la BBDD":
                try {
                    eliminarEntrada(frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Guardar cambios":

                if (esNuevaEntrada && frPersonas.getTable1().getSelectedRow() == (frPersonas.getTable1().getRowCount() - 1)) {
                    try {
                        aniadirEntrada((String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 2),
                                (String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 1),
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
                    System.out.println((String) frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 3));
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
                System.out.println("Editado");
                break;
        }

    }
}
