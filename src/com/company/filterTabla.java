package com.company;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
public class filterTabla {
    public static void filtrarTabla(String input, DefaultTableModel tabla) {
        for (int i = tabla.getRowCount() - 1; i >= 0; i--) {
            ArrayList<String> miArrayList = new ArrayList<>();
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                //Try catch para evitar errores con los campos en NULL en la BBDD
                try {
                    String valor = tabla.getValueAt(i, j).toString().toLowerCase();
                    if (valor.contains(input.toLowerCase())) {
                        miArrayList.add(valor);
                    }
                } catch (Exception e) {
                    System.out.println("Encontrado campo con valor NULL");
                }
            }
            if (miArrayList.size() == 0) {
                tabla.removeRow(i);
            }
        }
    }
}
