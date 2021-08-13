package Juego;

import Excepciones.Comprobacion;
import Excepciones.ExcepcionCapitalista;

public class Estacion extends Propiedad {
    private int alquiler1;
    private int alquiler2;
    private int alquiler3;
    private int alquiler4;

    public Estacion(String nombre, int precio, int hipoteca, int alquiler1, int alquiler2, int alquiler3, int alquiler4) {
        super(nombre, precio, hipoteca);
        this.alquiler1 = alquiler1;
        this.alquiler2 = alquiler2;
        this.alquiler3 = alquiler3;
        this.alquiler4 = alquiler4;
    }

    public int getAlquiler1() {
        return alquiler1;
    }

    public int getAlquiler2() {
        return alquiler2;
    }

    public int getAlquiler3() {
        return alquiler3;
    }

    public int getAlquiler4() {
        return alquiler4;
    }

    public void cobrar (Jugador jugador, Turno turno){
        float dinero = 0;
        try {
            Comprobacion.cobrable(this, jugador, turno);
            if(Comprobacion.propIguales(this) == 1) {
                dinero = alquiler1;
                jugador.setDinero(jugador.getDinero() - this.alquiler1);
                this.titular.setDinero(this.titular.getDinero() + this.alquiler1);
            }if(Comprobacion.propIguales(this) == 2) {
                dinero = alquiler2;
                jugador.setDinero(jugador.getDinero() - this.alquiler2);
                this.titular.setDinero(this.titular.getDinero() + this.alquiler2);
            }if(Comprobacion.propIguales(this) == 3) {
                dinero = alquiler3;
                jugador.setDinero(jugador.getDinero() - this.alquiler3);
                this.titular.setDinero(this.titular.getDinero() + this.alquiler3);
            }if(Comprobacion.propIguales(this) == 4) {
                dinero = alquiler4;
                jugador.setDinero(jugador.getDinero() - this.alquiler4);
                this.titular.setDinero(this.titular.getDinero() + this.alquiler4);
            }

            getInter().actDinero(jugador.getDinero());
            getInter().mostrarInfo("Lo siento pero tu money baja a " + jugador.getDinero() + " €");
            getInter().mostrarMovi(this.titular.getNombre(), true, dinero);
            turno.setOk(true);
        }catch (ExcepcionCapitalista e){
            Consola.imprimir((e.getMessage()));
        }
    }

    public void describir(){
        super.describir();
        Consola.imprimir("\tAlquiler 1: " + this.alquiler1 + "\n\tAlquiler 2: " + this.alquiler2 + "\n\tAlquiler 3: " + this.alquiler3 + "\n\t Alquiler 4: " + this.alquiler4);
    }
}
