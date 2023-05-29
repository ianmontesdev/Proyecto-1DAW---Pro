package com.company;

import javax.swing.table.DefaultTableModel;

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
            if (!miArray[i].toString().toLowerCase().contains(input.toLowerCase())){
                tabla.removeRow(i);
            }
        }
    }

}
