package com.company;

import view.com.company.ViewAsignaturas;
import view.com.company.ViewPersonas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class FilterTabla {

    /*Este método permite filtrar la tabla por coincidencias comparándolo con el input recibido del JTextField,
      El enfoque ha sido crear un Array que contiene objetos genéricos con todos los datos de cada fila.

      Este objeto sería iterable con otro for y podría saber qué columna específicamente ha coincidido, pero debido a que
      mi único interés es saber si algún dato de la fila coincide, he decidido convertirlo a un String con el método
      toString() y hacer una única comparación.

      De este modo puedo filtrar la tabla con un único bucle for, que se
      encarga de recorrer cada uno de los objetos.*/
    public static void filtrarTabla(String input, DefaultTableModel tabla) {
        Object[] miArray = tabla.getDataVector().toArray();
        for (int i = miArray.length-1; i >= 0; i--) {
            if (!miArray[i].toString().toLowerCase().replaceAll("á", "a").replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ú","u")
                    .contains(input.toLowerCase().replaceAll("á", "a").replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ú","u"))){
                tabla.removeRow(i);
            }
        }
    }

    /*Este método cumple la función de actualizar la tabla dentro de nuestra vista, para poder hacerlo
    * compatible con los dos modelos de tabla se ha creado un trycatch que prueba el casteo a cada tipo
    * de JFrame (ViewPersonas o ViewAsignaturas)*/
    public static void actualizarFiltro(String input, JFrame view) {
        try{
            ViewPersonas view2 = (ViewPersonas) view;
            DefaultTableModel tabla = (DefaultTableModel) view2.getTable1().getModel();
            FilterTabla.filtrarTabla(input, tabla);
        }
        catch(ClassCastException ignored){
            ViewAsignaturas view2 = (ViewAsignaturas) view;
            DefaultTableModel tabla = (DefaultTableModel) view2.getTable1().getModel();
            FilterTabla.filtrarTabla(input, tabla);
        }
    }
}
