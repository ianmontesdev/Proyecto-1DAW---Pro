package Controller.com.company;

import Connection.ConectionBD;
import com.company.filterTabla;
import model.com.company.ModelPersonas;
import view.com.company.ViewAniadirPersonas;
import view.com.company.ViewPersonas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerPersonas implements ActionListener{

    private final ViewPersonas frPersonas = new ViewPersonas();
    Statement stmt;
    private final DefaultTableModel m = null;
    private boolean esNuevaEntrada = false;

    public ControllerPersonas(){
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
        String consulta = "DELETE FROM `persona` WHERE `persona`.`nif` = " + "'" + nif+ "'";
        stmt = ConectionBD.getStmt();
        stmt.executeUpdate(consulta);
        ModelPersonas persona = new ModelPersonas();
        frPersonas.getTable1().setModel(persona.CargaDatos(m));
    }
    private void actualizarEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        String consulta = "UPDATE `persona`" +
                "SET `nombre` = '" + nombre + "', " +
                "`apellido1` = '" + apellido1 + "', " +
                "`apellido2` = '" + apellido2 + "', " +
                "`ciudad` = '" + ciudad + "', " +
                "`direccion` = '" + direccion + "', " +
                "`telefono` = '" + telefono + "', " +
                "`fecha_nacimiento` = '" + fechaN + "', " +
                "`sexo` = '" + sexo + "', " +
                "`tipo` = '" + tipo + "'" +
                "WHERE `persona`.`nif` = " + "'" + nif+ "'";
        if ((sexo.equals("H") || sexo.equals("M")) && (tipo.equals("alumno") || tipo.equals("profesor"))){
            stmt = ConectionBD.getStmt();
            stmt.executeUpdate(consulta);
            ModelPersonas persona = new ModelPersonas();
            frPersonas.getTable1().setModel(persona.CargaDatos(m));
        } else{
            JOptionPane.showMessageDialog(null, "Sexo sólo puede contener \"H\" o \"M\", y tipo sólo puede contener \"alumno\" o \"profesor\"");
        }
    }

    private void aniadirColumna(){
        DefaultTableModel tabla = (DefaultTableModel) frPersonas.getTable1().getModel();
        tabla.addRow(new String[]{"", "", "", "", "", "", "", "", "", ""});
        esNuevaEntrada = true;
    }

    private void aniadirEntrada(String nif, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo) throws SQLException {
        String consulta = "INSERT INTO `persona` " +
                "(`nif`, `nombre`, `apellido1`, `apellido2`, `ciudad`, `direccion`, `telefono`, `fecha_nacimiento`, `sexo`, `tipo`) " +
                "VALUES ('"+ nif + "', '"+ nombre +"', '"+ apellido1 +"', '"+ apellido2 +"', '"+ ciudad +"', '"+ direccion +"', '"+ telefono +"', '"+ fechaN +"', '"+ sexo +"', '"+ tipo +"')";
        stmt = ConectionBD.getStmt();
        stmt.executeUpdate(consulta);
        ModelPersonas persona = new ModelPersonas();
        frPersonas.getTable1().setModel(persona.CargaDatos(m));
        esNuevaEntrada = false;
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
                /*System.out.println("Añadir nueva persona");
                JPanel panelAniadir = new JPanel(new GridLayout(9, 2));
                JLabel nif = new JLabel("Nif:");
                JTextField nifTF = new JTextField("Introduce aquí el DNI");
                JLabel nombre = new JLabel("Nombre:");
                JTextField nombreTF = new JTextField("Introduce aquí el Nombre");
                JLabel apellido1 = new JLabel("Primer apellido:");
                JTextField apellido1TF = new JTextField("Introduce aquí el primer apellido");
                JLabel apellido2 = new JLabel("Segundo apellido:");
                JTextField apellido2TF = new JTextField("Introduce aquí el segundo apellido");
                JLabel ciudad = new JLabel("Ciudad:");
                JTextField ciudadTF = new JTextField("Introduce la ciudad");
                JLabel direccion = new JLabel("Dirección:");
                JTextField direccionTF = new JTextField("Introduce la dirección");
                JLabel telefono = new JLabel("Teléfono:");
                JTextField telefonoTF = new JTextField("Introduce el teléfono");
                JLabel fechaN = new JLabel("Fecha de nacimiento:");
                JTextField fechaNTF = new JTextField("AAAA-MM-DD");
                String[] sexo = {"Hombre", "Mujer"};
                JComboBox<String> selectorSexo = new JComboBox<>(sexo);
                String[] tipo = {"Profesor", "Alumno"};
                JComboBox<String> selectorTipo = new JComboBox<>(tipo);

                panelAniadir.add(nif);
                panelAniadir.add(nifTF);
                panelAniadir.add(nombre);
                panelAniadir.add(nombreTF);
                panelAniadir.add(apellido1);
                panelAniadir.add(apellido1TF);
                panelAniadir.add(apellido2);
                panelAniadir.add(apellido2TF);
                panelAniadir.add(ciudad);
                panelAniadir.add(ciudadTF);
                panelAniadir.add(direccion);
                panelAniadir.add(direccionTF);
                panelAniadir.add(telefono);
                panelAniadir.add(telefonoTF);
                panelAniadir.add(fechaN);
                panelAniadir.add(fechaNTF);
                panelAniadir.add(selectorSexo);
                panelAniadir.add(selectorTipo);
                System.out.println("paso 1");
                JPanel aniadirPersonas = new ViewAniadirPersonas();

                int opcion = JOptionPane.showOptionDialog(
                        null,
                        aniadirPersonas,
                        "Añadir entrada",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{"Enviar", "Cancelar"},
                        null
                );

                switch (opcion){
                    case 0:
                        JButton Boton = (JButton) ((Container) panelAniadir.getComponent(panelAniadir.getComponentCount() - 1)).getComponent(0);
                        ActionListener actionListener = e1 -> {

                            if (Boton.getText().equals("Enviar")) {
                                try {
                                    System.out.println("paso 3");
                                    agregarEntrada(nifTF.getText(), nombreTF.getText(), apellido1TF.getText(), apellido2TF.getText(), ciudadTF.getText(), direccionTF.getText(), telefonoTF.getText(),fechaNTF.getText(), selectorSexo.getSelectedItem().toString(), selectorTipo.getSelectedItem().toString());
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else{
                                JOptionPane.getRootFrame().dispose();
                            }
                        };
                        Boton.addActionListener(actionListener);
                        break;
                    case 1:
                        break;
                }*/
                break;
            case "Eliminar de la BBDD":
                System.out.println();
                try {
                    eliminarEntrada(frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0).toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Eliminar de la BBDD");
                break;
            case "Guardar cambios":

                if (esNuevaEntrada && frPersonas.getTable1().getSelectedRow() == (frPersonas.getTable1().getRowCount()-1)){
                    try {
                        aniadirEntrada(frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 1).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 2).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 3).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 4).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 5).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 6).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 7).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 8).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 9).toString());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        actualizarEntrada(frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 0).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 1).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 2).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 3).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 4).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 5).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 6).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 7).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 8).toString(),
                                frPersonas.getTable1().getValueAt(frPersonas.getTable1().getSelectedRow(), 9).toString());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println("Editado");
                break;
        }
    }
}
