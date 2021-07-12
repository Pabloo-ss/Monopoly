package Juego;

import java.util.HashMap;

public class Carcel extends Casilla {
    private final int turnosMax = 3;
    private boolean visita;
    private HashMap<Jugador, Integer> presos;

    public Carcel(boolean visita) {
        this.visita = visita;
        this.presos = new HashMap<>();
    }

    public int getTurnosMax() {
        return turnosMax;
    }

    public boolean isVisita() {
        return visita;
    }

    public HashMap<Jugador, Integer> getPresos() {
        return presos;
    }

    public void encarcelar(Jugador j){
        this.presos.put(j, 0);
        j.setEncarcelado(true);
        //j.setPosicion();moverlo a la carcel
    }

    public void liberar(Jugador j){
        this.presos.remove(j);
        j.setEncarcelado(false);
    }
}
