package Juego;


import Excepciones.Comprobacion;
import Excepciones.ExcepcionCapitalista;

public class Servicio extends Propiedad {
    private float multiplicador1;
    private float multiplicador2;

    public Servicio(String nombre, int precio, int hipoteca, float multiplicador1, float multiplicador2) {
        super(nombre, precio, hipoteca);
        this.multiplicador1 = multiplicador1;
        this.multiplicador2 = multiplicador2;
    }

    public float getMultiplicador1() {
        return multiplicador1;
    }

    public float getMultiplicador2() {
        return multiplicador2;
    }

    public void cobrar(Jugador jugador, Turno turno) {
      try {
          turno.setTirada(Dado.tirar());
          Comprobacion.cobrable(this, jugador, turno);

          if(Comprobacion.propIguales(this) == 1) {
              float precio = (float) turno.getTirada() * this.multiplicador1;
              jugador.setDinero(jugador.getDinero() - precio);
              this.titular.setDinero(this.titular.getDinero() + precio);
          }else{
              float precio = (float) turno.getTirada() * this.multiplicador2;
              jugador.setDinero(jugador.getDinero() - precio);
              this.titular.setDinero(this.titular.getDinero() + precio);
          }

          getInter().actDinero(jugador.getDinero());
          getInter().mostrarInfo("Lo siento pero tu money baja a " + jugador.getDinero() + " €");
          getInter().mostrarMovi(this.titular.getNombre(), true, precio);
          turno.setOk(true);
      }catch (ExcepcionCapitalista e){
          Consola.imprimir((e.getMessage()));
      }

    }

    public void describir(){
        super.describir();
        Consola.imprimir("\tMultiplicador 1: " + this.multiplicador1 + "\n\t Multiplicador 2: " + this.multiplicador2);
    }

}
