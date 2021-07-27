package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PortadaGui extends JPanel {
    private final int sizeCasilla = 76;
    private JButton bEmpezar;
    private JTextField j1;
    private JTextField j2;
    private JTextField j3;
    private JTextField j4;
    private JLabel foto1;
    private JLabel foto2;
    private Tablero t;

    public PortadaGui(Tablero tablero){
        this.setBounds(this.sizeCasilla * 2, this.sizeCasilla * 2, this.sizeCasilla * 9, this.sizeCasilla * 9);
        this.setBackground(Color.BLUE.brighter());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        this.bEmpezar = new JButton("Empezar");
        this.bEmpezar.setPreferredSize(new Dimension(200, 35));
        this.j1 = new JTextField("Jugador1");
        this.j1.setPreferredSize(new Dimension(70, 30));
        this.j1.setBackground(Color.CYAN);
        this.j2 = new JTextField("Jugador2");
        this.j2.setPreferredSize(new Dimension(70, 30));
        this.j2.setBackground(Color.YELLOW);
        this.j3 = new JTextField("Jugador3");
        this.j3.setPreferredSize(new Dimension(70, 30));
        this.j3.setBackground(Color.PINK);
        this.j4 = new JTextField("Jugador4");
        this.j4.setPreferredSize(new Dimension(70, 30));
        this.j4.setBackground(Color.ORANGE);
        this.t = tablero;

        this.foto1 = new JLabel(new ImageIcon("crypto.jpg"));
        this.foto2 = new JLabel(new ImageIcon("sir.jpg"));


        this.bEmpezar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArrayList<String> jugadores = new ArrayList<>();


                if(!j1.getText().equals("Jugador1"))
                    jugadores.add(j1.getText());
                if(!j2.getText().equals("Jugador2"))
                    jugadores.add(j2.getText());
                if(!j3.getText().equals("Jugador3"))
                    jugadores.add(j3.getText());
                if(!j4.getText().equals("Jugador4"))
                    jugadores.add(j4.getText());

                t.empezar(jugadores);
            }
        });

        this.add(this.j1);
        this.add(this.j2);
        this.add(this.j3);
        this.add(this.j4);
        this.add(this.foto1, BorderLayout.SOUTH);
        this.add(this.bEmpezar, BorderLayout.AFTER_LINE_ENDS);
        this.add(this.foto2, BorderLayout.SOUTH);

    }

}
