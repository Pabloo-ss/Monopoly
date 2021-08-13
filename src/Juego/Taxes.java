package Juego;

import Excepciones.Error;


public class Taxes extends Casilla {
    private float impuesto;

    public Taxes(float impuesto){
        this.impuesto = impuesto;
    }

    public void cobrar(Jugador jugador){
        float saldo = jugador.getDinero();
        float impuesto = saldo * this.impuesto;

        if(impuesto >= saldo){
            getInter().mostrarInfo("Safaste, dinero insuficiente para el cobro de impuestos");
        }else{
            jugador.setDinero(saldo - impuesto);
            getInter().actDinero(jugador.getDinero());

            getInter().mostrarInfo("Lo siento pero... efectivamente España nos roba " + impuesto + " de bitcoins en impuestos");
            getInter().mostrarMovi(Error.banca, true, impuesto);
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
