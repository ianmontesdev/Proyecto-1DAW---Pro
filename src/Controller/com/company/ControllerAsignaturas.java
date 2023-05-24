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

public class ControllerAsignaturas implements ActionListener{

    private final ViewAsignaturas frAsignaturas = new ViewAsignaturas();

    Statement stmt;
    private final DefaultTableModel m = null;

    private boolean esNuevaEntrada = false;

    public ControllerAsignaturas(){
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
    private void eliminarEntrada(String nif) throws SQLException {
        String consulta = "DELETE FROM `persona` WHERE `persona`.`nif` = " + "'" + nif+ "'";
        stmt = ConectionBD.getStmt();
        stmt.executeUpdate(consulta);
        prepararBaseDatos();
        esNuevaEntrada = false;
        JOptionPane.showMessageDialog(null, "El usuario con nif " + nif + " fue eliminado con éxito");
    }

    //TODO Modificar actualizarEntrada para que funcione con asignaturas
    private void actualizarEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        try{
            if (telefono.isEmpty()){
                telefono = null;
            }
        } catch (NullPointerException e){
            telefono = null;
        }
        try{
            if (apellido2.isEmpty()){
                apellido2 = null;
            } else{
                apellido2 = "'" + apellido2 + "'";
            }
        } catch (NullPointerException e){
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
                "WHERE `persona`.`nif` = " + "'" + nif+ "'";

        if (dataHandler.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frAsignaturas)){
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            prepararBaseDatos();
        }
    }

    //TODO Contar cuántas columnas tiene asignaturas para modificar el String[]
    private void aniadirColumna(){
        DefaultTableModel tabla = (DefaultTableModel) frAsignaturas.getTable1().getModel();
        tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
        esNuevaEntrada = true;
    }

    //TODO Modificar aniadirEntrada para que funcione con asignaturas
    private void aniadirEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {

        if(telefono.length() == 0){ telefono = null;};
        if(apellido2.length() == 0){ apellido2 = null;}

        String consulta = "INSERT INTO `persona` " +
                "(`nif`, `nombre`, `apellido1`, `apellido2`, `ciudad`, `direccion`, `telefono`, `fecha_nacimiento`, `sexo`, `tipo`) " +
                "VALUES ('"+ nif + "', '"+ nombre +"', '"+ apellido1 +"', "+ apellido2 +", '"+ ciudad +"', '"+ direccion +"', "+ telefono +", '"+ fechaN +"', '"+ sexo +"', '"+ tipo +"')";

        if (dataHandler.ComprobarDatosPersona(nif, nombre, apellido1, apellido2, ciudad, direccion, telefono, fechaN, sexo, tipo, frAsignaturas)){
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
                if (esNuevaEntrada && frAsignaturas.getTable1().getSelectedRow() == (frAsignaturas.getTable1().getRowCount()-1)){
                    try {
                        aniadirEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 0),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 8),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 9));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3));
                    try {
                        actualizarEntrada((String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 0),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 1),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 2),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 3),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 4),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 5),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 6),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 7),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 8),
                                (String) frAsignaturas.getTable1().getValueAt(frAsignaturas.getTable1().getSelectedRow(), 9));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("Editado");
                break;
        }
    }
}
