package com.company;

import Controller.com.company.ControllerPersonas;
import view.com.company.ViewAsignaturas;
import view.com.company.ViewPersonas;

import javax.swing.*;
import java.util.Arrays;

public class dataHandler {
    private static boolean resultado;

    //TODO Añadir comprobaciones para datos de asignaturas
    public static boolean ComprobarDatosPersona(String dni, String nombre, String apellido1, String apellido2, String ciudad, String direccion, String telefono, String fechaN, String sexo, String tipo, ViewPersonas frPersonas){
        resultado = true;
        comprobarDNI(dni, frPersonas);
        comprobarTamanios(nombre, apellido1, apellido2, ciudad, direccion);
        comprobarTelefono(telefono);
        comprobarFecha(fechaN);
        comprobarSexo(sexo);
        comprobarTipo(tipo);
        return resultado;
    }

    public static boolean ComprobarDatosAsignaturas(ViewAsignaturas frAsignaturas){
        resultado = true;

        return resultado;
    }
    
    private static void comprobarDNI(String dni, ViewPersonas frPersonas){
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

    private static void comprobarTamanios (String nombre, String apellido1, String apellido2, String ciudad, String direccion){
        if ( nombre.length() <= 0 || nombre.length() > 25) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El nombre debe contener entre 0 y 25 caracteres");
        }
        if ( apellido1.length() <= 0 || apellido1.length() > 50) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El primer apellido debe contener entre 0 y 50 caracteres");
        }

        if(apellido2 != null){
            if ( apellido2.length() <= 0 || apellido2.length() > 50) {
                resultado = false;
                JOptionPane.showMessageDialog(null, "El segundo apellido debe contener entre 0 y 50 caracteres");
            }
        }

        if ( ciudad.length() <= 0 || ciudad.length() > 25) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "La ciudad debe contener entre 0 y 25 caracteres");
        }
        if ( direccion.length() <= 0 || direccion.length() > 50) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "La dirección debe contener entre 0 y 50 caracteres");
        }
    }

    private static void comprobarFecha (String fechaN){
        String[] fechaFormat = fechaN.split("-");
        try{
            if (fechaFormat[0].length() == 4 && fechaFormat[1].length() == 2 && fechaFormat[2].length() == 2){
                    Integer.parseInt(fechaFormat[0]);
                    Integer.parseInt(fechaFormat[1]);
                    Integer.parseInt(fechaFormat[2]);
            } else {
                throw new NumberFormatException("Fecha inválida");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La fecha no es válida");
            resultado = false;
        }
    }

    private static void comprobarSexo (String sexo){
        String[] enumerados = {"H", "M"};
        if (!Arrays.asList(enumerados).contains(sexo)){
            resultado = false;
            JOptionPane.showMessageDialog(null, "El sexo sólo puede ser \"H\" o \"M\"");
        }
    }

    private static void comprobarTipo (String tipo){
        String[] enumerados = {"alumno", "profesor"};
        if (!Arrays.asList(enumerados).contains(tipo)) {
            resultado = false;
            JOptionPane.showMessageDialog(null, "El tipo sólo puede ser \"alumno\" o \"profesor\"");
        }
    }

    private static void comprobarTelefono (String telefono){
        if(telefono != null){
            try{
                if (telefono.length() == 9){
                    Integer.parseInt(telefono);
                }else {
                    throw new NumberFormatException("Teléfono inválido");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "El teléfono no es válido");
                resultado = false;
            }
        }
    }
}