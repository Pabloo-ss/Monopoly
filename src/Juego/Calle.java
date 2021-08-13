package Juego;

import Excepciones.*;
import Excepciones.Error;

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

                getInter().actDinero(j.getDinero());
                getInter().mostrarInfo("La propiedad " + getNombre() + " ha sido edificada");
                getInter().mostrarEdif(this.edificios, getPosicion());
                getInter().mostrarMovi(Error.banca, true, costeEdif);
            } catch (ExcepcionNumero | ExcepcionCapitalista e) {
                getInter().mostrarInfo(e.getMessage());
            }
        }else
            getInter().mostrarInfo("No eres el propietario");
    }

    public void cobrar(Jugador jugador, Turno turno){
        try {
            Comprobacion.cobrable(this, jugador, turno);
            float precio = (float) this.precio * (this.incremento * this.edificios);
            jugador.setDinero(jugador.getDinero() - precio);
            this.titular.setDinero(this.titular.getDinero() + precio);

            getInter().actDinero(jugador.getDinero());
            getInter().mostrarInfo("Lo siento pero tu money baja a " + jugador.getDinero() + " €");
            getInter().mostrarMovi(titular.getNombre(), true, precio);
            turno.setOk(true);
        }catch (ExcepcionCapitalista e){
            getInter().mostrarInfo(e.getMessage());
        }
    }

    public void describir(){//Creo q no lo uso
        super.describir();
        getInter().mostrarInfo("\tAlquiler: " + this.alquiler + "\n\tIncremento: " + this.incremento + "\n\tColor: " + this.color.toString() + "\n\tCoste edificacion: " + this.costeEdif );
    }
}
