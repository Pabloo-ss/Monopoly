package Juego;

import Excepciones.Error;

public class Salida extends Casilla {
    private int premio; //Recompensa al pasar por el principio

    public Salida(int premio){
        this.premio = premio;
    }

    public void recompensar(Jugador j, Turno t){
        if(j.getPosicion() + t.getTirada() >= Error.maxCasillas){
            j.setDinero(j.getDinero() + this.premio);

            Consola.imprimir("Enhorabuena! recibes " + this.premio + " por tu cara bonita");
        }
    }

    public void describir(){
        super.describir();
        Consola.imprimir("Recibiras " + this.premio + " cada vez que pases por la salida");
    }
}
