package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PortadaGui extends JPanel {
    private final int sizeCasilla = 76;
    private JButton bEmpezar;
    private JTextField j1;
    private JTextField j2;
    private JTextField j3;
    private JTextField j4;
    private Tablero t;

    public PortadaGui(Tablero tablero){
        this.setBounds(this.sizeCasilla * 2, this.sizeCasilla * 2, this.sizeCasilla * 9, this.sizeCasilla * 9);
        this.setBackground(Color.RED);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.bEmpezar = new JButton("Empezar");
        this.j1 = new JTextField("Jugador1");
        this.j2 = new JTextField("Jugador2");
        this.j3 = new JTextField("Jugador3");
        this.j4 = new JTextField("Jugador4");
        this.t = tablero;


        this.bEmpezar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArrayList<String> jugadores = new ArrayList<>();

                if(!j1.getText().equals("Jugador1"))
                    jugadores.add(j1.getText());
                if(!j2.getText().equals("Jugador1"))
                    jugadores.add(j2.getText());
                if(!j3.getText().equals("Jugador1"))
                    jugadores.add(j3.getText());
                if(!j4.getText().equals("Jugador1"))
                    jugadores.add(j4.getText());

                t.empezar(jugadores);
            }
        });

        this.add(this.bEmpezar);
        this.add(this.j1);
        this.add(this.j2);
        this.add(this.j3);
        this.add(this.j4);
    }

}
