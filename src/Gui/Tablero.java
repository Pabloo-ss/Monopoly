package Gui;

import Excepciones.Comprobacion;
import Juego.Calle;
import Juego.Casilla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import Excepciones.Error;
import Juego.*;
import Juego.Menu;

public class Tablero extends JFrame {
    private HashMap<Integer, Casilla> casillas;
    private HashMap<Integer, CasillaGui> casillasGui;
    private PortadaGui portada;
    private MenuGui menuGui;
    private Menu menu;
    private HashMap<String, Ficha> fichas;

    public Tablero(HashMap<Integer, Casilla> casillas, Menu menu){
        super("Monopoly");
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.casillas = casillas;
        this.casillasGui = new HashMap<>();
        this.fichas = new HashMap<>();
        this.menu = menu;

        init();
        this.setVisible(true);
    }

    public MenuGui getMenuGui() {
        return menuGui;
    }

    public void init(){
        ArrayList<Orientacion> orientaciones = new ArrayList<>();
        orientaciones.add(0, Orientacion.abajo);
        orientaciones.add(1, Orientacion.izquierda);
        orientaciones.add(2, Orientacion.arriba);
        orientaciones.add(3, Orientacion.derecha);
        int j = -1;

        for(int i = 0; i < Error.maxCasillas; i++){
            Casilla c = this.casillas.get(i);
            if(i % Error.maxFila == 0)
                j++;
            if(c instanceof Propiedad) {
                if (c instanceof Calle)
                    this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), ((Propiedad) c).getNombre(), ((Calle) c).getColor()));
                else
                    this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), ((Propiedad) c).getNombre(), null));
            } else if(c instanceof Carcel) {
                if (((Carcel) c).isVisita())
                    this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Carcel", null));
                else
                    this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Vaya a la carcel dierctamente", null));
            } else if(c instanceof Salida)
                this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Salida\nRecibira 40000 al pasar por aqui", null));
            else if(c instanceof Sorpresa)
                this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Tarjeta Sorpresa", null));
            else if(c instanceof Parking)
                this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Parking gratuito", null));
            else
                this.casillasGui.put(i, new CasillaGui(i, orientaciones.get(j), "Hacienda somos todos", null));
        }

        for(CasillaGui c: this.casillasGui.values())
            this.add(c);

        this.add((this.portada = new PortadaGui(this)));

    }

    public void empezar(ArrayList<String> jugadores){
        //this.remove(this.portada);
        this.portada.setVisible(false);
        this.add((this.menuGui = new MenuGui(this.menu)));
        this.setSize(1008, 1020);

        ArrayList<Color> colores = new ArrayList<>();
        colores.add(Color.CYAN); colores.add(Color.YELLOW); colores.add(Color.PINK); colores.add(Color.ORANGE);
        for (int i = 0; i < jugadores.size(); i++)
            this.fichas.put(jugadores.get(i), new Ficha(colores.get(i)));

        for (Ficha f: this.fichas.values())
            this.casillasGui.get(0).getpEti().add(f);

        this.menu.crearJugadores(jugadores);
    }

    public void moverFicha(Turno t){
        Ficha f = this.fichas.get(t.getJugador().getNombre());
        CasillaGui c = this.casillasGui.get(f.getPos());

        c.getpEti().remove(c);

        this.casillasGui.get(f.getPos() + t.getTirada() % Error.maxCasillas).getpEti().add(f);

    }
}
