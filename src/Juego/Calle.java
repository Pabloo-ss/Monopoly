package Juego;

import Excepciones.Comprobacion;
import Excepciones.ExcepcionCapitalista;
import Excepciones.ExcepcionNumero;

import java.awt.*;

public class Calle extends Propiedad {
    private int alquiler;
    private float incremento;
    private Color color;
    private int costeEdif;
    private int edificios;

    public Calle(String nombre, Color color, int precio, int alquiler, float incremento, int costeEdif, int hipoteca) {
        super(nombre, precio, hipoteca);
        this.alquiler = alquiler;
        this.incremento = incremento;
        this.color = color;
        this.costeEdif = costeEdif;
        this.edificios = 0;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public float getIncremento() {
        return incremento;
    }

    public Color getColor() {
        return color;
    }

    public int getCosteEdif() {
        return costeEdif;
    }

    public int getEdificios() {
        return edificios;
    }

    public void setEdificios(int edificios) {
        this.edificios = edificios;
    }

    public void edificar(Jugador j){
        if(this.titular.equals(j)){
            try {
                Comprobacion.edificable(this);
                Comprobacion.saldoSuficiente(this.costeEdif, j.getDinero());
                this.edificios++;
                j.setDinero(j.getDinero() - this.costeEdif);
            } catch (ExcepcionNumero | ExcepcionCapitalista e) {
                Consola.imprimir(e.getMessage());
            }
        }else
            Consola.imprimir("No eres el propietario");
    }

    public void cobrar(Jugador jugador, Turno turno){
        try {
            Comprobacion.cobrable(this, jugador, turno);
            float precio = (float) this.precio * (this.incremento * this.edificios);
            jugador.setDinero(jugador.getDinero() - precio);
            this.titular.setDinero(this.titular.getDinero() + precio);

            Consola.imprimir("Lo siento pero tu money baja a " + jugador.getDinero() + " €");
            turno.setOk(true);
        }catch (ExcepcionCapitalista e){
            Consola.imprimir((e.getMessage()));
        }
    }

    public void describir(){
        super.describir();
        Consola.imprimir("\tAlquiler: " + this.alquiler + "\n\tIncremento: " + this.incremento + "\n\tColor: " + this.color.toString() + "\n\tCoste edificacion: " + this.costeEdif );
    }
}
