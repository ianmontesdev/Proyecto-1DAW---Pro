package model.com.company;

import Connection.ConectionBD;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelPersonas {

    private static Statement stmt;

    public ModelPersonas() {
        ConectionBD.openConn();
        stmt =ConectionBD.getStmt();
    }

    public static DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = {"NIF", "Nombre", "Apellido1", "Apellido2", "Ciudad", "Dirección", "Teléfono", "Fecha Nacimiento", "Sexo", "Tipo"};
        m = new DefaultTableModel(null, titulos);
        try {
            ResultSet rs = stmt.executeQuery("select * from persona");
            String[] fila = new String[10];

            while (rs.next()) {
                fila[0] = rs.getString("nif");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido1");
                fila[3] = rs.getString("apellido2");
                fila[4] = rs.getString("ciudad");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("telefono");
                fila[7] = rs.getString("fecha_nacimiento");
                fila[8] = rs.getString("sexo");
                fila[9] = rs.getString("tipo");
                m.addRow(fila);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }

    public static Statement getStmt() {
        return stmt;
    }
}
