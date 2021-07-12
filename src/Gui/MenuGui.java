package Gui;

import javax.swing.*;
import java.awt.*;

//Unicamente se encarga de mostrar info visualmente, la lógica tiene lugar en el Menu
public class MenuGui extends JPanel {
    private final int sizeCasilla = 76;
    private JTextArea info;
    private JTextField jugador;
    private JTextField dinero;
    private JTextArea propiedades;
    private JTextArea movimientos;
    private JTextField tirada;

    public MenuGui(){
        this.setBounds(this.sizeCasilla * 9, this.sizeCasilla * 9, this.sizeCasilla * 2, this.sizeCasilla * 2);

        //Atributos
        this.info = new JTextArea();
        this.jugador = new JTextField();
        this.propiedades = new JTextArea();
        this.dinero = new JTextField();
        this.movimientos = new JTextArea();
        this.tirada = new JTextField();

        this.info.setLineWrap(true);
        this.propiedades.setLineWrap(true);
        this.movimientos.setLineWrap(true);

        this.info.setPreferredSize(new Dimension(this.sizeCasilla * 4, this.sizeCasilla * 3));

    }
}
