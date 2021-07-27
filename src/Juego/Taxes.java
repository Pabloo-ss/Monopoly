package Juego;

import Juego.Casilla;
import Juego.Consola;
import Juego.Jugador;

public class Taxes extends Casilla {
    private float impuesto;

    public Taxes(float impuesto){
        this.impuesto = impuesto;
    }

    public void cobrar(Jugador jugador){
        float saldo = jugador.getDinero();
        float impuesto = saldo * this.impuesto;

        if(impuesto >= saldo){
            Consola.imprimir("Safaste, dinero insuficiente para el cobro de impuestos");
        }else{
            jugador.setDinero(saldo - impuesto);

            Consola.imprimir("Lo siento pero... efectivamente España nos roba " + impuesto + " de euros en impuestos");
            Consola.imprimir("Saldo actual: " + jugador.getDinero());
        }
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void describir(){
        super.describir();
        Consola.imprimir("Si caes en las fauces de Hacienda se te cobrara " + this.impuesto + " en impuestos");
    }
}
