package com.company;
import view.com.company.ViewPersonas;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DataValidation {
    private static boolean resultado;

    /*Esta clase se encarga de toda la validación de datos enviada a la BBDD, se ha creado una clase externa para poder reutilizar
    el código tanto en ControllerPersonas como en ControllerAsignaturas*/
    public static boolean ComprobarDatosPersona(String dni, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo, ViewPersonas frPersonas){
        resultado = true;
        comprobarDNIPersona(dni, frPersonas);
        comprobarTamaniosPersona(nombre, apellido1, apellido2, ciudad, direccion);
        comprobarTelefonoPersona(telefono);
        comprobarFechaPersona(fechaN);
        comprobarSexoPersona(sexo);
        comprobarTipoPersona(tipo);
        return resultado;
    }

    public static boolean ComprobarDatosAsignaturas(String nombre, String creditos, String tipo, String curso, String cuatrimestre, String id_profesor, String id_grado){
        resultado = true;
        comprobarNombreAsignatura(nombre);
        comprobarCreditosAsignatura(creditos);
        comprobarTipoAsignatura(tipo);
        comprobarTinyIntAsignatura(curso, "Curso");
        comprobarTinyIntAsignatura(cuatrimestre, "Cuatrimestre");
        comprobarIdProfesorAsignatura(id_profesor);
        comprobarIdGradoAsignatura(id_grado);
        return resultado;
    }


    /* El siguiente método se usa para gestionar los null en las consultas, debido a que quiero poder enviar tanto strings envueltas en ' ' como null que no pueden estar envueltos.
     * En el caso de ser un string vacío se convertirá a null, de no serlo se le concatenarán ' ' a ambos lados, y en caso de devolver un NullPointerException, el string recibido como argumento ya es null,
     * por lo que podemos ignorar el NullPointerException y la consulta enviada a la base de datos contendrá un verdadero null y no 'null'*/
    public static String gestionNull(String string){
        try {
            if (string.isEmpty()) {
                return null;
            } else{ return "'" + string + "'"; }
        } catch (NullPointerException ignored) {
            return null;
        }
    }
    
    private static void comprobarDNIPersona(String dni, ViewPersonas frPersonas){
        char[] letra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        try{
            int valor = Integer.parseInt(dni.substring(0, dni.length()-1)) % 23;
            if (valor >= 0 && valor <= 22 && dni.length() == 9){
                if (dni.charAt(dni.length()-1) == letra[valor]){
                    for (int i = 0; i <frPersonas.getTable1().getRowCount(); i++) {
                        if(frPersonas.getTable1().getValueAt(i, 0).equals(dni)){
                            if( frPersonas.getTable1().getSelectedRow() != i){
                                throw new NumberFormatException("El DNI introducido ya se encuentra en el sistema");
                            }
                        }
                    }
                } else{
                    throw new NumberFormatException("El DNI no es válido");
                }
            } else {
                throw new NumberFormatException("El DNI no es válido");
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            resultado = false;
        }
    }

    private static void comprobarTamaniosPersona(String nombre, String apellido1, String apellido2, String ciudad, String direccion){
        if ( nombre.length() == 0 || nombre.length() > 25) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El nombre debe contener entre 0 y 25 caracteres");
        }
        if ( apellido1.length() == 0 || apellido1.length() > 50) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El primer apellido debe contener entre 0 y 50 caracteres");
        }

        if(apellido2 != null){
            if (apellido2.length() > 50) {
                resultado = false;
                JOptionPane.showMessageDialog(null, "El segundo apellido debe contener entre 0 y 50 caracteres");
            }
        }

        if ( ciudad.length() == 0 || ciudad.length() > 25) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "La ciudad debe contener entre 0 y 25 caracteres");
        }
        if ( direccion.length() == 0 || direccion.length() > 50) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "La dirección debe contener entre 0 y 50 caracteres");
        }
    }

    private static void comprobarFechaPersona(String fechaN){
        try{
            LocalDate.parse(fechaN);
        } catch(DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "La fecha no es válida");
            resultado = false;
        }
    }

    private static void comprobarSexoPersona(String sexo){
        String[] enumerados = {"H", "M"};
        if (!Arrays.asList(enumerados).contains(sexo)){
            resultado = false;
            JOptionPane.showMessageDialog(null, "El sexo sólo puede ser \"H\" o \"M\"");
        }
    }

    private static void comprobarTipoPersona(String tipo){
        String[] enumerados = {"alumno", "profesor"};
        if (!Arrays.asList(enumerados).contains(tipo)) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El tipo sólo puede ser \"alumno\" o \"profesor\"");
        }
    }

    private static void comprobarTelefonoPersona(String telefono){
        if(telefono != null){
            try{
                if (telefono.length() == 9){
                    Integer.parseInt(telefono);
                } else if (telefono.isEmpty()) {
                    telefono = null;
                } else {
                    throw new NumberFormatException("Teléfono inválido");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "El teléfono no es válido");
                resultado = false;
            }
        }
    }

    private static void comprobarTipoAsignatura(String tipo){
        String[] enumerados = {"básica", "optativa", "obligatoria"};
        if (!Arrays.asList(enumerados).contains(tipo)) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El tipo sólo puede ser \"básica\", \"optativa\" o \"profesor\"");
        }
    }

    private static void comprobarIdProfesorAsignatura(String id_profesor){
        System.out.println(id_profesor);
        if(id_profesor != null){
            try{
                if (id_profesor.isEmpty()){
                    id_profesor = null;
                } else if (id_profesor.length() <= 10) {
                    Integer.parseInt(id_profesor);
                } else {
                    throw new NumberFormatException("id inválida");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "La id de profesor es inválida");
                resultado = false;
            }
        }
    }

    private static void comprobarNombreAsignatura(String nombre){
        if ( nombre.length() == 0 || nombre.length() > 100) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El nombre debe contener entre 0 y 25 caracteres");
        }
    }

    private static void comprobarCreditosAsignatura(String creditos){
        try{
            Float.parseFloat(creditos);
        } catch (Exception e) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "Créditos debe ser de tipo Float");
        }
    }

    private static void comprobarTinyIntAsignatura(String curso, String campo){
        try{
            if (Integer.parseInt(curso) == 0 || Integer.parseInt(curso) >= 255){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            resultado = false;
            JOptionPane.showMessageDialog(null, campo + " debe ser un entero de entre 0 y 255");
        }
    }

    private static void comprobarIdGradoAsignatura(String id_grado){
        try{
            if (id_grado.length() <= 10){
                Integer.parseInt(id_grado);
            } else {throw new NumberFormatException();}
        } catch (NumberFormatException e) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "id grado debe ser un entero de máximo 10 caracteres");
        }
    }
}