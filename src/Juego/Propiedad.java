package Juego;

import Excepciones.Comprobacion;
import Excepciones.ExcepcionNumero;
import Excepciones.ExcepcionString;


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

                Consola.imprimir("La propiedad \"" + this.nombre + "\" ha sido adquirida por " + this.precio + "€");
            }
        }else{
            Consola.imprimir("La propiedad ya tiene un titular: " + this.titular.getNombre());
            Consola.imprimir("Quereis llegar a un acuerdo?[s/n]");
            String respuesta = "";
            while(!ok) {
                respuesta = Consola.leer();
                try{
                    ok = Comprobacion.sino(respuesta);
                }catch(ExcepcionString e) {
                    Consola.imprimir(e.getMessage());
                }
            }

            if(respuesta.equals("s")) {
                if(this.hipotecada = false)
                    this.vender(jugador);
                else
                    Consola.imprimir("Lola Mento pero la propiedad está hipotecada... No se puede vender");
            }
        }
    }

    public void vender(Jugador comprador){
        boolean ok = false;
        int oferta = 0;
        String respuesta = "";
        Consola.imprimir("Hora de las negociaciones...");
        Consola.imprimir("Cual es tu oferta " + comprador.getNombre() + "?");

        while(!ok) {
            try {
                oferta = Comprobacion.valorValido(Consola.leer());
                Comprobacion.saldoSuficiente(oferta, comprador.getDinero());
                ok = true;
            } catch (ExcepcionNumero e){
                Consola.imprimir(e.getMessage());
            }
        }

        ok = false;
        Consola.imprimir(this.titular.getNombre() + ", aceptas la oferta?[s/n]");
        while(!ok) {
            respuesta = Consola.leer();
            try{
                ok = Comprobacion.sino(respuesta);
            }catch(ExcepcionString e) {
                Consola.imprimir(e.getMessage());
            }
        }
        if(respuesta.equals("n"))
            Consola.imprimir("Bueno pues nada, continuamos");
        else{
            if(this instanceof Calle)
                ((Calle) this).setEdificios(0);
            this.titular.getPropiedades().remove(this.getNombre());
            this.titular.setDinero(this.titular.getDinero() + oferta);
            this.titular = comprador;
            comprador.getPropiedades().put(this.getNombre(), this);
            comprador.setDinero(comprador.getDinero() -  oferta);

            Consola.imprimir(comprador.getNombre() + " ha adquirido \"" + this.getNombre() + "\" por el modico precio de " + oferta);
        }

    }

    public void hipotecar(Jugador jugador){
        if(this.titular.equals(jugador)) {
            this.hipotecada = true;
            this.titular.setDinero(this.getTitular().getDinero() + this.hipoteca);

            Consola.imprimir("Propiedad hipotecada, deberás pagar su precio original para recuperarla");
        }else
            Consola.imprimir("No eres el dueño de esta propiedad");
    }

    public void deshipotecar(Jugador jugador){
        if(this.titular.equals(jugador)){
            try {
                Comprobacion.saldoSuficiente(this.precio, jugador.getDinero());
                Consola.imprimir("Propiedad deshipotecada, ya puedes disfrutar de sus ventajas");
            } catch (ExcepcionNumero e){
                Consola.imprimir(e.getMessage());
            }
        }else
            Consola.imprimir("No eres el dueño de esta propiedad");
    }

    public void describir(){
        super.describir();
        Consola.imprimir("\tPropiedad: " + this.nombre + "\n\tTitular: " + this.titular + "\n\tPrecio: " + this.precio );
    }
}
