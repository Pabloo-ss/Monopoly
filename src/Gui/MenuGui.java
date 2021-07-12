package Gui;

import javax.swing.*;
import java.awt.*;

//Unicamente se encarga de mostrar info visualmente, la lógica tiene lugar en el Menu
public class MenuGui extends JPanel {
    private final int sizeCasilla = 76;
    private JTextArea info;
    private JTextField jugador;
    private JTextField dinero;
    private JTable propiedades;
    private JTextArea movimientos;
    private JTextField tirada;
    private JTextField in;
    private JButton bComprar;
    private JButton bEdificar;
    private JButton bTurno;
    private JButton bHipotecar;

    public MenuGui(){
        this.setBounds(this.sizeCasilla * 2, this.sizeCasilla * 2, this.sizeCasilla * 9, this.sizeCasilla * 9);
        this.setLayout(new BorderLayout(15, 20));
        this.setBackground(Color.GREEN);

        //Atributos
        this.info = new JTextArea();
        this.info.setEditable(false);
        this.jugador = new JTextField();
        this.jugador.setEditable(false);
        this.propiedades = new JTable();
        this.propiedades.setModel(new Gui.TablaProp());
        this.dinero = new JTextField();
        this.dinero.setEditable(false);
        this.movimientos = new JTextArea();
        this.movimientos.setEditable(false);
        this.tirada = new JTextField();
        this.tirada.setEditable(false);
        this.in = new JTextField();
        this.bComprar = new JButton();
        this.bEdificar = new JButton();
        this.bTurno = new JButton();
        this.bHipotecar = new JButton();

        this.info.setLineWrap(true);
        this.movimientos.setLineWrap(true);

            //Posiciones
        this.add(this.info, BorderLayout.CENTER);
        this.add(this.jugador, BorderLayout.NORTH);
        this.add(this.propiedades, BorderLayout.EAST);
        this.add(this.dinero, BorderLayout.NORTH);
        this.add(this.movimientos, BorderLayout.WEST);
        this.add(this.tirada, BorderLayout.NORTH);
        this.add(this.in, BorderLayout.SOUTH);
        this.add(this.bComprar, BorderLayout.SOUTH);
        this.add(this.bEdificar, BorderLayout.SOUTH);
        this.add(this.bTurno, BorderLayout.SOUTH);
        this.add(this.bHipotecar, BorderLayout.SOUTH);



    }
}
