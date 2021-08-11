package Juego;

import java.util.HashMap;
import java.util.Iterator;


public class Jugador {
    private String nombre;
    private float dinero;
    private HashMap<String, Propiedad> propiedades;
    private boolean salirCarcel;
    private boolean encarcelado;
    private int posicion;
    final float dineroInicial = 300000;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.dinero = this.dineroInicial;
        this.propiedades = new HashMap<>();
        this.salirCarcel = false;
        this.encarcelado = false;
        this.posicion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public HashMap<String, Propiedad> getPropiedades() {
        return propiedades;
    }

    public boolean isSalirCarcel() {
        return salirCarcel;
    }

    public void setSalirCarcel(boolean salirCarcel) {
        this.salirCarcel = salirCarcel;
    }

    public boolean isEncarcelado() {
        return encarcelado;
    }

    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void retirarse(){
        this.posicion = 0;
        this.dinero = 0;
        Iterator<Propiedad> propiedades = this.propiedades.values().iterator();

        while (propiedades.hasNext()){
            Propiedad p = propiedades.next();

            p.setTitular(null);
            p.setHipotecada(false);
            this.propiedades.remove(p.getNombre());
            if( p instanceof Calle)
                ((Calle) p).setEdificios(0);
        }
    }
}
