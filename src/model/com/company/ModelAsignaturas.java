package model.com.company;


import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelAsignaturas {
    public static DefaultTableModel CargaDatos(DefaultTableModel m) {
        String[] titulos = {"ID", "Nombre", "Créditos", "Tipo", "Curso", "Cuatrimestre", "Id Profesor", "Id Grado"};
        m = new DefaultTableModel(null, titulos);

        try {
            ResultSet rs = ModelPersonas.getStmt().executeQuery("select * from asignatura");
            String[] fila = new String[8];
            while (rs.next()) {
                fila[0] = rs.getString("id");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("creditos");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("curso");
                fila[5] = rs.getString("cuatrimestre");
                fila[6] = rs.getString("id_profesor");
                fila[7] = rs.getString("id_grado");
                m.addRow(fila);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }
}
