package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FormatUI {

    /*Quiero aplicar distintos temas, por lo que considero que la mejor forma de implementar esta
     funcionalidad es creando una clase que se encargue de formatear las distintas vistas*/
    private static Color color1 = new Color(39, 39, 47);
    private static Color color2 = new Color(53,54,64);
    private static Color color3 = new Color(56,57, 67);
    private static Color color4 = new Color(66,67,76);
    private static Color color5 = new Color(133,134, 140);
    private static Color color6 = new Color(191,191,191);
    private static Color color7 = new Color(222, 205, 119);

    private static Font font1 = new Font("Roboto", Font.BOLD, 12);

    private static Font font2 = new Font("Roboto", Font.PLAIN, 12);
    private static Font font3 = new Font("Roboto", Font.BOLD, 14);
    private static Font font4 = new Font("Roboto", Font.PLAIN, 14);

    public static void formatView(JPanel view){
        view.setBackground(color2);
    }
    public static void formatTable(JTable table){
        table.getTableHeader().setFont(font1);
        table.getTableHeader().setBackground(color1);
        table.getTableHeader().setForeground(color5);
        table.getTableHeader().setPreferredSize(new Dimension(10, 35));
        table.getTableHeader().setBorder(new LineBorder(color1, 2));

        table.setRowHeight(30);
        table.setBackground(color4);
        table.setForeground(color5);
        table.setSelectionBackground(color7);
        table.setSelectionForeground(color1);
        table.setFont(font2);

    }

    public static void formatButton(JButton button){
        button.setFont(font3);
        button.setBorderPainted(false);
        button.setBackground(color2);
        button.setIconTextGap(10);
        button.setMargin(new Insets(6,5,5,6));
        button.setForeground(color6);
    }

    public static void formatButtonContainer(JPanel buttonsContainer){
        buttonsContainer.setBackground(color2);
    }

    public static void formatSearch(JTextField busqueda){
        busqueda.setFont(font2);
        busqueda.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, color1));
        busqueda.setBackground(color4);
        busqueda.setForeground(color6);
    }

    public static void formatScrollPane(JScrollPane scrollPane){
        scrollPane.setBackground(color3);
        scrollPane.getViewport().setBackground(color3);
        scrollPane.setBorder(new LineBorder(color3, 2));
    }

    public static void formatJLabel(JLabel jlabel){
        jlabel.setFont(font3);
        jlabel.setBackground(color2);
        jlabel.setForeground(color6);
    }

    public static void setAppIcon(JFrame view){
        view.setIconImage(new ImageIcon("src/resources/icon.png").getImage());
    }

    public static Color getColor4() {
        return color4;
    }

    public static void setColor4(Color color4) {
        FormatUI.color4 = color4;
    }

    public static Color getColor1() {
        return color1;
    }

    public static void setColor1(Color color1) {
        FormatUI.color1 = color1;
    }

    public static Color getColor5() {
        return color5;
    }

    public static void setColor5(Color color5) {
        FormatUI.color5 = color5;
    }

    public static Color getColor3() {
        return color3;
    }

    public static void setColor3(Color color3) {
        FormatUI.color3 = color3;
    }

    public static Color getColor6() {
        return color6;
    }

    public static void setColor6(Color color6) {
        FormatUI.color6 = color6;
    }

    public static Color getColor7() {
        return color7;
    }

    public static void setColor7(Color color7) {
        FormatUI.color7 = color7;
    }

    public static Color getColor2() {
        return color2;
    }

    public static void setColor2(Color color2) {
        FormatUI.color2 = color2;
    }

    public static Font getFont1() {
        return font1;
    }

    public static void setFont1(Font font1) {
        FormatUI.font1 = font1;
    }

    public static Font getFont2() {
        return font2;
    }

    public static void setFont2(Font font2) {
        FormatUI.font2 = font2;
    }

    public static Font getFont3() {
        return font3;
    }

    public static void setFont3(Font font3) {
        FormatUI.font3 = font3;
    }

    public static Font getFont4() {
        return font4;
    }

    public static void setFont4(Font font4) {
        FormatUI.font4 = font4;
    }
}
