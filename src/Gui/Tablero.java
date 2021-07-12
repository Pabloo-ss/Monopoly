package Gui;

import Juego.Calle;
import Juego.Casilla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import Excepciones.Error;
import Juego.*;

public class Tablero extends JFrame {
    private HashMap<Integer, Casilla> casillas;
    private HashMap<Integer, CasillaGui> casillasGui;

    public Tablero(HashMap<Integer, Casilla> casillas){
        super("Monopoly");
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        this.casillas = casillas;
        this.casillasGui = new HashMap<>();

        init();
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

        this.add(new MenuGui());

    }
}
