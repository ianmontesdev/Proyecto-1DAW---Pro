package Controller.com.company;

import Connection.ConectionBD;
import com.company.filterTabla;
import com.company.dataHandler;
import model.com.company.ModelAsignaturas;
import model.com.company.ModelPersonas;
import view.com.company.ViewAsignaturas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAsignaturas implements ActionListener {

    private final ViewAsignaturas frAsignaturas = new ViewAsignaturas();

    Statement stmt;
    private final DefaultTableModel m = null;

    private boolean esNuevaEntrada = false;

    public ControllerAsignaturas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
    }

    public void iniciarVentana() {
        frAsignaturas.setVisible(true);
        frAsignaturas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void iniciarEventos() {
        frAsignaturas.getPersonasButton().addActionListener(this::actionPerformed);
        frAsignaturas.getAsignaturasButton().addActionListener(this::actionPerformed);
        frAsignaturas.getMenuAniadir().addActionListener(this::actionPerformed);
        frAsignaturas.getMenuEditar().addActionListener(this::actionPerformed);
        frAsignaturas.getMenuEliminar().addActionListener(this::actionPerformed);
        frAsignaturas.getCampoBusqueda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frAsignaturas.getCampoBusqueda().getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frAsignaturas.getCampoBusqueda().getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frAsignaturas.getCampoBusqueda().getText());
            }
        });
    }

    private void actualizarFiltro(String input) {
        String filtro = frAsignaturas.getCampoBusqueda().getText();
        DefaultTableModel tabla = (DefaultTableModel) frAsignaturas.getTable1().getModel();
        filterTabla.filtrarTabla(input, tabla);
    }

    public void prepararBaseDatos() {
        ModelAsignaturas entrada = new ModelAsignaturas();
        frAsignaturas.getTable1().setModel(entrada.CargaDatos(m));
    }

    //TODO Modificar EliminarEntrada para que funcione con asignaturas
    private void eliminarEntrada(String id) throws SQLException {
        String consulta = "DELETE FROM asignatura WHERE `asignatura`.`id` = " + id;
        stmt = ConectionBD.getStmt();
        stmt.executeUpdate(consulta);
        prepararBaseDatos();
        esNuevaEntrada = false;
        JOptionPane.showMessageDialog(null, "La asignatura con el id " + id + " fue eliminado con éxito");
    }

    //TODO Modificar actualizarEntrada para que funcione con asignaturas
    private void actualizarEntrada(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado) throws SQLException {
        try {
            if (id_profesor.isEmpty()) {
                id_profesor = null;
            } else{ id_profesor = "'" + id_profesor + "'"; }
        } catch (NullPointerException e) {
            id_profesor = null;
        }

        String consulta = "UPDATE `asignatura` " +
                "SET `nombre` = '" + nombre + "', " +
                "`creditos` = '" + creditos + "', " +
                "`tipo` = '" + tipo + "', " +
                "`curso` = '" + curso + "', " +
                "`cuatrimestre` = '" + cuatrimestre + "', " +
                "`id_profesor` = " + id_profesor + ", " +
                "`id_grado` = '" + id_grado + "' " +
                "WHERE `asignatura`.`id` = 1";

        if (dataHandler.ComprobarDatosAsignaturas(nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado, frAsignaturas)) {
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            prepararBaseDatos();
        }
    }

    //TODO Contar cuántas columnas tiene asignaturas para modificar el String[]
    private void aniadirColumna() {
        DefaultTableModel tabla = (DefaultTableModel) frAsignaturas.getTable1().getModel();
        tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
        esNuevaEntrada = true;
    }

    //TODO Modificar aniadirEntrada para que funcione con asignaturas
    private void aniadirEntrada(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado) throws SQLException {

        if (id_profesor.length() == 0) {
            id_profesor = null;
        }
        ;

        String consulta = "INSERT INTO `asignatura`" +
                "(`nombre`, `creditos`, `tipo`, `curso`, `cuatrimestre`, `id_profesor`, `id_grado`)" +
                "VALUES ('" + nombre + "','" + creditos + "','" + tipo + "','" + curso + "','" + cuatrimestre + "'," + id_profesor + ",'" + id_grado + "')";

        if (dataHandler.ComprobarDatosAsignaturas(nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado, frAsignaturas)) {
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            ModelPersonas persona = new ModelPersonas();
            frAsignaturas.getTable1().setModel(persona.CargaDatos(m));
            esNuevaEntrada = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Personas":
                new ControllerPersonas();
                frAsignaturas.dispose();
                break;
            case "Asignaturas":
                ModelAsignaturas persona = new ModelAsignaturas();
                frAsignaturas.getTable1().setModel(persona.CargaDatos(m));
                break;
            case "Añadir nueva asignatura":
                aniadirColumna();
                break;
            case "Eliminar de la BBDD":
                try {
                    eliminarEntrada(frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 0).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Guardar cambios":
                //TODO modificar guardarcambios para que funcione con asignaturas
                if (esNuevaEntrada && frAsignaturas.getTable1().getSelectedRow() == (frAsignaturas.getTable1().getRowCount() - 1)) {
                    try {
                        aniadirEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3));
                    try {
                        actualizarEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("Editado");
                break;
        }
    }
}
