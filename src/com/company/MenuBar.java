package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static com.company.FormatUI.*;

public class MenuBar implements ActionListener {

    /*Esta clase se encarga de crear una barra de menús en la cuál se va a crear una pequeña sección de ayuda y una sección de ajustes gráficos.
     * He decidido crear una clase separada ya que de este modo puedo con un simple constructor incluir el menú
     * en cualquier ventana, sin repetir código.*/

    private final Color colorFondo = getColor1();
    private final Color colorTexto = getColor6();
    private final Font textoFont = getFont2();

    public JMenuBar createMenuBar() {

        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorForeground", colorTexto);
        UIManager.getLookAndFeelDefaults().put("MenuItem.acceleratorFont", textoFont);
        JMenuBar menuBar = new JMenuBar();


        JMenu menuAjustes = new JMenu("Ajustes");
        menuAjustes.setBackground(colorFondo);
        menuAjustes.setForeground(colorTexto);
        menuAjustes.setFont(textoFont);
        menuBar.add(menuAjustes);

        JMenuItem menuTamanioMas = new JMenuItem("Aumentar 1pt");
        menuTamanioMas.setBackground(colorFondo);
        menuTamanioMas.setForeground(colorTexto);
        menuTamanioMas.setFont(textoFont);
        menuTamanioMas.addActionListener(this);
        menuAjustes.add(menuTamanioMas);
        menuTamanioMas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTamanioReset = new JMenuItem("Restaurar a 12pt");
        menuTamanioReset.setBackground(colorFondo);
        menuTamanioReset.setForeground(colorTexto);
        menuTamanioReset.setFont(textoFont);
        menuTamanioReset.addActionListener(this);
        menuAjustes.add(menuTamanioReset);
        menuTamanioReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTamanioMenos = new JMenuItem("Reducir 1pt");
        menuTamanioMenos.setBackground(colorFondo);
        menuTamanioMenos.setForeground(colorTexto);
        menuTamanioMenos.setFont(textoFont);
        menuTamanioMenos.addActionListener(this);
        menuAjustes.add(menuTamanioMenos);
        menuTamanioMenos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK));

        JMenu menuTemas = new JMenu("Temas");
        menuTemas.setBackground(colorFondo);
        menuTemas.setForeground(colorTexto);
        menuTemas.setFont(textoFont);
        menuBar.add(menuTemas);

        JMenuItem menuTema1 = new JMenuItem("Oscuro");
        menuTema1.setBackground(colorFondo);
        menuTema1.setForeground(colorTexto);
        menuTema1.setFont(textoFont);
        menuTema1.addActionListener(this);
        menuTemas.add(menuTema1);
        menuTema1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTema2 = new JMenuItem("Claro");
        menuTema2.setBackground(colorFondo);
        menuTema2.setForeground(colorTexto);
        menuTema2.setFont(textoFont);
        menuTema2.addActionListener(this);
        menuTemas.add(menuTema2);
        menuTema2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTema3 = new JMenuItem("Alto contraste");
        menuTema3.setBackground(colorFondo);
        menuTema3.setForeground(colorTexto);
        menuTema3.setFont(textoFont);
        menuTema3.addActionListener(this);
        menuTemas.add(menuTema3);
        menuTema3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTema4 = new JMenuItem("80's !");
        menuTema4.setBackground(colorFondo);
        menuTema4.setForeground(colorTexto);
        menuTema4.setFont(textoFont);
        menuTema4.addActionListener(this);
        menuTemas.add(menuTema4);
        menuTema4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTema5 = new JMenuItem("Pastel");
        menuTema5.setBackground(colorFondo);
        menuTema5.setForeground(colorTexto);
        menuTema5.setFont(textoFont);
        menuTema5.addActionListener(this);
        menuTemas.add(menuTema5);
        menuTema5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_DOWN_MASK));

        JMenuItem menuTema6 = new JMenuItem("Daltonismo");
        menuTema6.setBackground(colorFondo);
        menuTema6.setForeground(colorTexto);
        menuTema6.setFont(textoFont);
        menuTema6.addActionListener(this);
        menuTemas.add(menuTema6);
        menuTema6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.CTRL_DOWN_MASK));

        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.setBackground(colorFondo);
        menuAyuda.setForeground(colorTexto);
        menuAyuda.setFont(textoFont);
        menuBar.add(menuAyuda);

        JMenuItem menuAyuda1 = new JMenuItem("¿Cómo navego por la interfaz?");
        menuAyuda1.setBackground(colorFondo);
        menuAyuda1.setForeground(colorTexto);
        menuAyuda1.setFont(textoFont);
        menuAyuda1.addActionListener(this);
        menuAyuda.add(menuAyuda1);

        JMenuItem menuAyuda2 = new JMenuItem("¿Cómo creo una nueva entrada?");
        menuAyuda2.setBackground(colorFondo);
        menuAyuda2.setForeground(colorTexto);
        menuAyuda2.setFont(textoFont);
        menuAyuda2.addActionListener(this);
        menuAyuda.add(menuAyuda2);

        JMenuItem menuAyuda3 = new JMenuItem("¿Cómo modifico una entrada?");
        menuAyuda3.setBackground(colorFondo);
        menuAyuda3.setForeground(colorTexto);
        menuAyuda3.setFont(textoFont);
        menuAyuda3.addActionListener(this);
        menuAyuda.add(menuAyuda3);

        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.setBackground(colorFondo);
        return menuBar;
    }

    private void aumentarTexto() {
        setFont1(new Font(getFont1().getFamily(), getFont1().getStyle(), getFont1().getSize() + 1));
        setFont2(new Font(getFont2().getFamily(), getFont2().getStyle(), getFont2().getSize() + 1));
        setFont3(new Font(getFont3().getFamily(), getFont3().getStyle(), getFont3().getSize() + 1));
        setFont4(new Font(getFont4().getFamily(), getFont4().getStyle(), getFont4().getSize() + 1));
    }

    private void restaurarTexto() {
        setFont1(new Font(getFont1().getFamily(), getFont1().getStyle(), 12));
        setFont2(new Font(getFont2().getFamily(), getFont2().getStyle(), 12));
        setFont3(new Font(getFont3().getFamily(), getFont3().getStyle(), 14));
        setFont4(new Font(getFont4().getFamily(), getFont4().getStyle(), 14));
    }

    private void reducirTexto() {
        setFont1(new Font(getFont1().getFamily(), getFont1().getStyle(), getFont1().getSize() - 1));
        setFont2(new Font(getFont2().getFamily(), getFont2().getStyle(), getFont2().getSize() - 1));
        setFont3(new Font(getFont3().getFamily(), getFont3().getStyle(), getFont3().getSize() - 1));
        setFont4(new Font(getFont4().getFamily(), getFont4().getStyle(), getFont4().getSize() - 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entrada = e.getActionCommand();

        switch (entrada) {
            case "¿Cómo creo una nueva entrada?" -> JOptionPane.showMessageDialog(null, """
                    Para introducir una nueva entrada siga los siguientes pasos:

                    1º.  Haga click derecho en la tabla y seleccione la opción "añadir nueva entrada".
                    2º.  Se ha creado una fila al final de la tabla, rellénela!.
                    3º.  Seleccione la fila que acaba de rellenar y haga click derecho en ella.
                    4º.  Elija la opción "Guardar cambios" del menú que acaba de aparecer.

                    El programa le indicará cualquier error, asegúrese de corregirlo y vuelva a repetir pasos 3 y 4.""");
            case "¿Cómo navego por la interfaz?" -> JOptionPane.showMessageDialog(null, """
                        La interfaz se compone de dos botones en el panel izquierdo, cada uno
                    de ellos le llevará a la pantalla que gestiona la tabla de Personas o la
                    tabla de Asignaturas.

                        Por otra parte, si ya se encuentra, digamos, en la tabla de Personas, y hace
                    click en el botón con la misma etiqueta, la tabla se volverá a recargar
                    haciendo una consulta a la base de datos.
                                        
                        Si sufre de daltonismo, puede elegir el tema para daltónicos en el menú Temas, también
                    puede incrementar o reducir el tamaño de fuente en el menú Ajustes""");
            case "¿Cómo modifico una entrada?" -> JOptionPane.showMessageDialog(null, """
                        Para modificar una entrada tan sólo tiene que modificar los datos que desee
                    en la tabla, y con la fila modificada seleccionada hacer click derecho
                    y elegir la opción "Guardar cambios".
                                        
                        Del mismo modo, si desea eliminar una entrada sólo debe seleccionarla,
                    hacer click derecho y elegir "Eliminar entrada".""");
            case "Aumentar 1pt" -> aumentarTexto();
            case "Restaurar a 12pt" -> restaurarTexto();
            case "Reducir 1pt" -> reducirTexto();
            case "Oscuro" -> {
                setColor1(new Color(39, 39, 47));
                setColor2(new Color(53, 54, 64));
                setColor3(new Color(56, 57, 67));
                setColor4(new Color(66, 67, 76));
                setColor5(new Color(133, 134, 140));
                setColor6(new Color(191, 191, 191));
                setColor7(new Color(222, 205, 119));
                setColor8(new Color(133, 134, 140));
                setColor9(new Color(191, 191, 191));
            }
            case "Claro" -> {
                setColor1(new Color(220, 220, 211));
                setColor2(new Color(134, 133, 125));
                setColor3(new Color(205, 202, 191));
                setColor4(new Color(208, 205, 194));
                setColor5(new Color(136, 69, 55));
                setColor6(new Color(80, 80, 80));
                setColor7(new Color(136, 69, 55));
                setColor8(new Color(136, 69, 55));
                setColor9(new Color(80, 80, 80));
            }
            case "Alto contraste" -> {
                setColor1(new Color(0, 0, 0));
                setColor2(new Color(50, 50, 50));
                setColor3(new Color(55, 55, 55));
                setColor4(new Color(66, 66, 66));
                setColor5(new Color(132, 132, 132));
                setColor6(new Color(190, 190, 190));
                setColor7(new Color(255, 255, 255));
                setColor8(new Color(132, 132, 132));
                setColor9(new Color(190, 190, 190));
            }
            case "80's !" -> {
                setColor1(new Color(131, 138, 187));
                setColor2(new Color(35, 27, 46));
                setColor3(new Color(31, 3, 38));
                setColor4(new Color(214, 215, 200));
                setColor5(new Color(36, 33, 52));
                setColor6(new Color(218, 132, 182));
                setColor7(new Color(250, 222, 93));
                setColor8(new Color(36, 33, 52));
                setColor9(new Color(131, 138, 187));
            }
            case "Daltonismo" -> {
                setColor1(new Color(68, 170, 153));
                setColor2(new Color(137, 35, 85));
                setColor3(new Color(137, 35, 85));
                setColor4(new Color(255, 255, 255));
                setColor5(new Color(52, 41, 129));
                setColor6(new Color(137, 205, 239));
                setColor7(new Color(222, 205, 119));
                setColor8(new Color(52, 41, 129));
                setColor9(new Color(52, 41, 129));
            }
            case "Pastel" -> {
                setColor1(new Color(166, 160, 178));
                setColor2(new Color(156, 176, 178));
                setColor3(new Color(174, 204, 179));
                setColor4(new Color(255, 208, 202));
                setColor5(new Color(166, 160, 178));
                setColor6(new Color(213, 213, 213));
                setColor7(new Color(213, 213, 213));
                setColor8(new Color(213, 213, 213));
                setColor9(new Color(166, 160, 178));

                new Color(166, 160, 178);
            }
        }
    }
}
