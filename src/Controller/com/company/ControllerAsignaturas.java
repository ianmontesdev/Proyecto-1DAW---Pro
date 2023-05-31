package Controller.com.company;

import Connection.ConectionBD;
import com.company.DataValidation;
import com.company.MenuBar;
import model.com.company.ModelAsignaturas;
import model.com.company.ModelPersonas;
import view.com.company.ViewAsignaturas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static com.company.DataValidation.gestionNull;
import static com.company.FilterTabla.actualizarFiltro;
import static com.company.FormatUI.setAppIcon;

public class ControllerAsignaturas implements ActionListener, WindowListener {

    private final ViewAsignaturas frAsignaturas = new ViewAsignaturas();
    private final DefaultTableModel m = null;

    private boolean esNuevaEntrada = false;

    public ControllerAsignaturas() {
        iniciarVentana();
        iniciarEventos();
        prepararBaseDatos();
    }

    public void iniciarVentana() {
        setAppIcon(frAsignaturas);
        frAsignaturas.setVisible(true);
        frAsignaturas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar();
        frAsignaturas.setJMenuBar(menuBar.createMenuBar());
    }


    public void iniciarEventos() {
        frAsignaturas.getPersonasButton().addActionListener(this);
        frAsignaturas.getAsignaturasButton().addActionListener(this);
        frAsignaturas.getMenuAniadir().addActionListener(this);
        frAsignaturas.getMenuEditar().addActionListener(this);
        frAsignaturas.getMenuEliminar().addActionListener(this);
        frAsignaturas.getCampoBusqueda().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarFiltro(frAsignaturas.getCampoBusqueda().getText(), frAsignaturas);
                esNuevaEntrada = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                prepararBaseDatos();
                actualizarFiltro(frAsignaturas.getCampoBusqueda().getText(), frAsignaturas);
                esNuevaEntrada = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void prepararBaseDatos() {
        frAsignaturas.getTable1().setModel(ModelAsignaturas.CargaDatos(m));
    }

    private void eliminarEntrada(String id) throws SQLException {
        if (esNuevaEntrada && frAsignaturas.getTable1().getRowCount() - 1 == frAsignaturas.getTable1().getSelectedRow()) {
            DefaultTableModel dt = (DefaultTableModel) frAsignaturas.getTable1().getModel();
            dt.removeRow(frAsignaturas.getTable1().getRowCount() - 1);
            esNuevaEntrada = false;
        } else {
            String consulta = "DELETE FROM asignatura WHERE `asignatura`.`id` = " + id;
            ModelPersonas.getStmt().executeUpdate(consulta);
            prepararBaseDatos();
            esNuevaEntrada = false;
            JOptionPane.showMessageDialog(null, "La asignatura con el id " + id + " fue eliminado con éxito");
        }
    }

    private void actualizarEntrada(String id, String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado) throws SQLException {

        String consulta = "UPDATE `asignatura` " + "SET `nombre` = '" + nombre + "', " + "`creditos` = '" + creditos + "', " + "`tipo` = '" + tipo + "', " + "`curso` = '" + curso + "', " + "`cuatrimestre` = '" + cuatrimestre + "', " + "`id_profesor` = " + gestionNull(id_profesor) + ", " + "`id_grado` = '" + id_grado + "' " + "WHERE `asignatura`.`id` = " + id;

        if (DataValidation.ComprobarDatosAsignaturas(nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado)) {
            ModelPersonas.getStmt().executeUpdate(consulta);
            prepararBaseDatos();
        }
    }

    private void aniadirColumna() {
        if (!esNuevaEntrada) {
            DefaultTableModel tabla = (DefaultTableModel) frAsignaturas.getTable1().getModel();
            tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
            /*He necesitado usar un Thread porque sino visualmente la barra no bajaba hasta la última posición, sino la penúltima,
             * de esta manera me aseguro que la función haya terminado de ejecutarse y posteriormente, ya con la última fila pintada,
             * el programa baja hasta la última posición revelando la fila a rellenar.*/
            Thread sleep = new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    frAsignaturas.getScrollPane().getVerticalScrollBar().setValue(frAsignaturas.getScrollPane().getVerticalScrollBar().getMaximum());
                    frAsignaturas.getTable1().setRowSelectionInterval(frAsignaturas.getTable1().getRowCount() - 1, frAsignaturas.getTable1().getRowCount() - 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            sleep.start();
            esNuevaEntrada = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ya hay una fila nueva creada");
        }
    }

    private void aniadirEntrada(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado) throws SQLException {
        String consulta = "INSERT INTO `asignatura`" + "(`nombre`, `creditos`, `tipo`, `curso`, `cuatrimestre`, `id_profesor`, `id_grado`)" + "VALUES ('" + nombre + "','" + creditos + "','" + tipo + "','" + curso + "','" + cuatrimestre + "'," + gestionNull(id_profesor) + ",'" + id_grado + "')";

        if (DataValidation.ComprobarDatosAsignaturas(nombre, creditos, tipo, curso, cuatrimestre, id_profesor, id_grado)) {
            ModelPersonas.getStmt().executeUpdate(consulta);
            prepararBaseDatos();
            esNuevaEntrada = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "Personas" -> {
                new ControllerPersonas();
                frAsignaturas.dispose();
            }
            case "Asignaturas" -> frAsignaturas.getTable1().setModel(ModelAsignaturas.CargaDatos(m));
            case "Añadir nueva asignatura" -> aniadirColumna();
            case "Eliminar de la BBDD" -> {
                try {
                    eliminarEntrada(frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 0).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ArrayIndexOutOfBoundsException aie) {
                    JOptionPane.showMessageDialog(null, "Primero debes seleccionar una fila para eliminar");
                }
            }
            case "Guardar cambios" -> {
                if (esNuevaEntrada && frAsignaturas.getTable1().getSelectedRow() == (frAsignaturas.getTable1().getRowCount() - 1)) {
                    try {
                        aniadirEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        actualizarEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 0), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6), (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7));
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
