package Juego;

import Gui.MenuGui;

import java.util.HashMap;

public class Carcel extends Casilla {
    private final int turnosMax = 2;
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

        getInter().mostrarInfo("Pa la carcel maquina");
    }

    public void liberar(Jugador j){
        this.presos.remove(j);
        j.setEncarcelado(false);
        getInter().mostrarInfo("Quedas libre de la carcel granuja");
    }

    public void comprobacion(Jugador j, Turno t){
        if(presos.get(j) == turnosMax || t.getTirada() == 6){
            liberar(j);
        }
    }
}
