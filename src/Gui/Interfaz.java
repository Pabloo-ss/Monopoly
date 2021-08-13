package Gui;

import Juego.Propiedad;
import Excepciones.Error;

import java.util.ArrayList;

public class Interfaz {
    private MenuGui m;
    private Tablero tablero;

    public Interfaz(MenuGui m){
        this.m = m;
        tablero = m.getMenu().getTablero();
    }

    public void mostrarInfo(String cadena){
        m.actInfo(cadena);
    }

    public void mostrarMovi(String duenho, boolean pagar, float cant){
        m.actMovi(pagar, duenho, cant);
    }

    public void mostrarEdif(int num, int pos){
        m.getMenu().getTablero().actEdif(num, pos);
    }

    public void actHipoteca(int pos, boolean hipotecar){
        m.getMenu().getTablero().actHipoteca(hipotecar, pos);
    }

    public void actDinero(float dinero){
        m.actDinero(dinero);
    }

    public void actPropiedades(ArrayList<Propiedad> prop){
        m.actPropiedades(prop);
    }

    public String leer(){
        while (!Error.inout);

        Error.inout = false;

        return Error.in;
    }
}
