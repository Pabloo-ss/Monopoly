package Juego;

public class Turno {
    private Jugador jugador;
    private boolean ok;
    private int tirada;

    public Turno(Jugador jugador){
        this.jugador = jugador;
        this.ok = true;
        this.tirada = 0;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getTirada() {
        return tirada;
    }

    public void setTirada(int tirada) {
        this.tirada = tirada;
    }

    public void turnoSig(Jugador j){
        this.jugador = j;
        this.ok = true;
        this.tirada = 0;
    }
}
