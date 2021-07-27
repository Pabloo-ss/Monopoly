package Gui;

import Excepciones.Error;
import Juego.*;
import Excepciones.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Unicamente se encarga de mostrar info visualmente, la lógica tiene lugar en el Menu
public class MenuGui extends JPanel {
    private final int sizeCasilla = 76;
    private Juego.Menu menu;
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

    public MenuGui(Juego.Menu menu){
        this.setBounds(this.sizeCasilla * 2, this.sizeCasilla * 2, this.sizeCasilla * 9, this.sizeCasilla * 9);
        this.setLayout(new BorderLayout(15, 20));
        this.setBackground(Color.GREEN);

        this.menu = menu;
        //Atributos
        this.info = new JTextArea();
        this.info.setEditable(false);
        this.jugador = new JTextField();
        this.jugador.setEditable(false);
        this.jugador.setPreferredSize(new Dimension(55, 25));
        this.propiedades = new JTable();
        this.propiedades.setModel(new Gui.TablaProp());
        this.dinero = new JTextField();
        this.dinero.setEditable(false);
        this.dinero.setPreferredSize(new Dimension(35, 25));
        this.movimientos = new JTextArea();
        this.movimientos.setEditable(false);
        this.tirada = new JTextField();
        this.tirada.setEditable(false);
        this.tirada.setPreferredSize(new Dimension(30, 25));
        this.in = new JTextField();
        this.in.setPreferredSize(new Dimension(55, 25));
        this.in.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Error.in = in.getText();
                Error.inout = true;
            }});
        this.bComprar = new JButton("Comprar");
        this.bComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.comprar();
            }
        });
        this.bEdificar = new JButton("Edificar");
        this.bEdificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.edificar();
            }
        });
        this.bTurno = new JButton("Acabar");
        this.bTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.acabarTurno();
            }
        });
        this.bHipotecar = new JButton("Hipotecar");
        this.bHipotecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menu.hipotecar();
            }
        });

        this.info.setLineWrap(true);
        this.movimientos.setLineWrap(true);

        JPanel norte = new JPanel();
        JPanel sur = new JPanel();

        norte.add(jugador);
        norte.add(dinero);
        norte.add(tirada);
        sur.add(in);
        sur.add(bComprar);
        sur.add(bEdificar);
        sur.add(bTurno);
        sur.add(bHipotecar);

            //Posiciones
        this.add(this.info, BorderLayout.CENTER);
        this.add(norte, BorderLayout.NORTH);
        this.add(this.propiedades, BorderLayout.EAST);
        this.add(this.movimientos, BorderLayout.WEST);
        this.add(sur, BorderLayout.SOUTH);


    }

    public void actJugador(Jugador j){
        this.jugador.setText(j.getNombre());
        this.actDinero(j.getDinero());

        TablaProp t = (TablaProp) this.propiedades.getModel();
        t.setFilas(new ArrayList<>(j.getPropiedades().values()));
        this.actTirada(0);

    }

    public void actDinero(float dinero){
        this.dinero.setText(Float.toString(dinero));
    }

    public void actTirada(int num){
        this.tirada.setText(Integer.toString(num));
    }

    public void actInfo(Calle c){
        String res = c.getNombre() + "\n" + c.getPrecio() + " bitcoins\n" + "Alquiler:" + c.getAlquiler() + "\nIncremento: " + c.getIncremento();

        this.info.setText(res);
    }


    public void actInfo(Estacion c){
        String res = c.getNombre() +  "\n" + c.getPrecio() + " bitcoins\n" + "Alquiler:" + c.getAlquiler1() + "\nIncremento: " + (c.getAlquiler2() - c.getAlquiler1());

        this.info.setText(res);
    }

    public void actInfo(Servicio c){
        String res = c.getNombre() +  "\n" + c.getPrecio() + " bitcoins\n" + "Multiplicador1:" + c.getMultiplicador1() + "\nMultiplicador2: " + c.getMultiplicador2();

        this.info.setText(res);
    }

    public void actInfo(Salida c){
        String res = "Estas en la salida\n " +
                "Recibes 40000 al pasar por aqui";

        this.info.setText(res);
    }

    public void actInfo(Carcel c){
        String res = "";

        if(c.isVisita())
            res = "Estas visitando la carcel\n" +
                    "No te emociones que acabas aqui";
        else
            res = "Siento decirte que\n" +
                    "estas encarcelado"
            ;

        this.info.setText(res);
    }

    public void actInfo(Taxes c){
        String res = "España nos roba\n" +
                "A ti en concreto " + c.getImpuesto() + " bitcoins";

        this.info.setText(res);
    }

    public void actInfo(Parking c){
        String res = "Aparcado tranquilamente";

        this.info.setText(res);
    }

    public void actMovi(boolean pagar, String segunda, float cant){
        String res = "";

        if(pagar)
            res = this.jugador.getText() + " paga " + cant + " bitcoins a " + segunda;
        else
            res = this.jugador.getText() + " recibe " + cant + " bitcoins de " + segunda;;

        this.info.setText(res);
    }
}
