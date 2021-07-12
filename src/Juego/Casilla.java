package Juego;

public abstract class Casilla {
    private int posicion;


    public int getPosicion(){
        return this.posicion;
    }

    public void setPosicion(int pos){
        posicion = pos;
    }

    public void describir(){
        Consola.imprimir("********* " + this.posicion + "********");
    }
}
