package Juego;

import Gui.Interfaz;

public abstract class Casilla {
    private int posicion;
    protected Interfaz inter;


    public int getPosicion(){
        return this.posicion;
    }

    public void setPosicion(int pos){
        posicion = pos;
    }

    public Interfaz getInter() {
        return inter;
    }

    public void setInter(Interfaz inter) {
        this.inter = inter;
    }

    public void describir(){
        Consola.imprimir("********* " + this.posicion + "********");
    }
}
