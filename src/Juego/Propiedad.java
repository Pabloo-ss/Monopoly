package Juego;

import Excepciones.Comprobacion;
import Excepciones.ExcepcionNumero;
import Excepciones.ExcepcionString;
import Excepciones.Error;

import java.util.ArrayList;


public abstract class Propiedad extends Casilla {
    protected String nombre;
    protected Jugador titular;
    protected int precio;
    protected int hipoteca;
    protected boolean hipotecada;

    public Propiedad(String nombre, int precio, int hipoteca){
        this.nombre = nombre;
        this.titular = null;
        this.precio = precio;
        this.hipoteca = hipoteca;
        this.hipotecada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public Jugador getTitular() {
        return titular;
    }

    public void setTitular(Jugador titular) {
        this.titular = titular;
    }

    public boolean isHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public int getPrecio() {
        return precio;
    }

    public int getHipoteca() {
        return hipoteca;
    }

    public abstract void cobrar(Jugador jugador, Turno turno);

    public void comprar(Jugador jugador){
        boolean ok = false;
        if(this.titular == null){
            float saldo = jugador.getDinero();
            if (saldo >= this.precio){
                this.titular = jugador;
                jugador.setDinero(saldo - this.precio);
                jugador.getPropiedades().put(this.getNombre(), this);

                getInter().actDinero(jugador.getDinero());
                getInter().actPropiedades(new ArrayList<>(jugador.getPropiedades().values()));
                getInter().mostrarInfo("La propiedad \"" + this.nombre + "\" ha sido adquirida por " + this.precio + "€");
                getInter().mostrarMovi(Error.banca, true, precio);
            }
        }else{
            getInter().mostrarInfo("La propiedad ya tiene un titular: " + this.titular.getNombre());
            getInter().mostrarInfo("Quereis llegar a un acuerdo?[s/n]");
            String respuesta = "";
            while(!ok) {
                respuesta = getInter().leer();
                try{
                    ok = Comprobacion.sino(respuesta);
                }catch(ExcepcionString e) {
                    getInter().mostrarInfo(e.getMessage());
                }
            }

            if(respuesta.equals("s")) {
                if(this.hipotecada = false)
                    this.vender(jugador);
                else
                    getInter().mostrarInfo("Lola Mento pero la propiedad está hipotecada... No se puede vender");
            }
        }
    }

    public void vender(Jugador comprador){
        boolean ok = false;
        int oferta = 0;
        String respuesta = "";
        getInter().mostrarInfo("Hora de las negociaciones...");
        getInter().mostrarInfo("Cual es tu oferta " + comprador.getNombre() + "?");

        while(!ok) {
            try {
                oferta = Comprobacion.valorValido(getInter().leer());
                Comprobacion.saldoSuficiente(oferta, comprador.getDinero());
                ok = true;
            } catch (ExcepcionNumero e){
                getInter().mostrarInfo(e.getMessage());
            }
        }

        ok = false;
        getInter().mostrarInfo(this.titular.getNombre() + ", aceptas la oferta?[s/n]");
        while(!ok) {
            respuesta = getInter().leer();
            try{
                ok = Comprobacion.sino(respuesta);
            }catch(ExcepcionString e) {
                getInter().mostrarInfo(e.getMessage());
            }
        }
        if(respuesta.equals("n"))
            getInter().mostrarInfo("Bueno pues nada, continuamos");
        else{
            if(this instanceof Calle)
                ((Calle) this).setEdificios(0);
            this.titular.getPropiedades().remove(this.getNombre());
            this.titular.setDinero(this.titular.getDinero() + oferta);

            getInter().mostrarMovi(titular.getNombre(), true, oferta);

            this.titular = comprador;
            comprador.getPropiedades().put(this.getNombre(), this);
            comprador.setDinero(comprador.getDinero() -  oferta);

            getInter().mostrarInfo(comprador.getNombre() + " ha adquirido \"" + this.getNombre() + "\" por el modico precio de " + oferta);
            getInter().actDinero(comprador.getDinero());
            getInter().actPropiedades(new ArrayList<>(comprador.getPropiedades().values()));
        }

    }

    public void hipotecar(Jugador jugador){
        if(this.titular.equals(jugador)) {
            this.hipotecada = true;
            this.titular.setDinero(this.getTitular().getDinero() + this.hipoteca);

            getInter().mostrarInfo("Propiedad hipotecada, deberás pagar su precio original para recuperarla");
            getInter().actDinero(titular.getDinero());
            getInter().actHipoteca(getPosicion(), true);
        }else
            getInter().mostrarInfo("No eres el dueño de esta propiedad");
    }

    public void deshipotecar(Jugador jugador){
        if(this.titular.equals(jugador)){
            try {
                Comprobacion.saldoSuficiente(this.precio, jugador.getDinero());
                jugador.setDinero(jugador.getDinero() - precio);

                getInter().mostrarInfo("Propiedad deshipotecada, ya puedes disfrutar de sus ventajas");
                getInter().actDinero(jugador.getDinero());
                getInter().actHipoteca(getPosicion(), false);
            } catch (ExcepcionNumero e){
                getInter().mostrarInfo(e.getMessage());
            }
        }else
            getInter().mostrarInfo("No eres el dueño de esta propiedad");
    }

    public void describir(){
        super.describir();
        Consola.imprimir("\tPropiedad: " + this.nombre + "\n\tTitular: " + this.titular + "\n\tPrecio: " + this.precio );
    }
}
