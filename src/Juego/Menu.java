package Juego;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Excepciones.Comprobacion;
import Gui.*;
import Excepciones.Error;

public class Menu {
    private ArrayList<Jugador> jugadores;
    private HashMap<Integer, Casilla> casillas;
    private Tablero tablero;
    private MenuGui mGui;
    private Turno turno;
    private int turn = 0;
    private Interfaz interfaz;


    public Menu(){
        this.jugadores = new ArrayList<>();
        this.casillas = new HashMap<>();
        this.turno = new Turno();
        File casillas = new File("casillas.txt");
        this.crearCasillas(casillas);
        this.tablero = new Tablero(this.casillas, this);


    }


    /*
    @file nombre del archivo con formato para calles--> nombre;color;precio;alquiler;incrementoXedificacion;costeEdificacion;hipoteca
                                         para estaciones--> nombre;precio;hipoteca;alquiler1;alquiler2;alquiler3;alquiler4
                                         para servicios--> nombre;precio;hipoteca;multiplicador1;multiplicador2
                                         para Salida--> recompensa
                                         para Sorpresa--> sorpresa
                                         para carcel--> visita/no visita
                                         para taxes--> impuesto
         Crea el tablero
     */
    private void crearCasillas(File casillas) {
        int posicion = -1;
        Casilla c = null;

        String asignacion;
        BufferedReader bufferLector;

        ArrayList<Orientacion> orientaciones = new ArrayList<>();
        orientaciones.add(0, Orientacion.abajo);
        orientaciones.add(1, Orientacion.izquierda);
        orientaciones.add(2, Orientacion.arriba);
        orientaciones.add(3, Orientacion.derecha);
        int j = -1;

        try {
            FileReader lector = new FileReader(casillas);
            bufferLector = new BufferedReader(lector);
            while ((asignacion = bufferLector.readLine()) != null) {
                posicion++;
                if(posicion % Error.maxFila == 0) {
                    j++;
                    switch (asignacion){
                        case "40000":
                            c = new Salida(Integer.parseInt(asignacion));
                            c.setPosicion(posicion);
                            break;
                        case "Parking":
                            c = new Parking();
                            c.setPosicion(posicion);
                            break;
                        case "visita":
                            c = new Carcel(true);
                            c.setPosicion(posicion);
                            break;
                        case "no visita":
                            c = new Carcel(false);
                            c.setPosicion(posicion);
                            break;
                    }
                }
                else if(posicion % Error.maxFila == 5){
                    String[] partes = asignacion.split(";");
                    c = new Estacion(partes[0], Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                    c.setPosicion(posicion);
                }else {
                    switch (orientaciones.get(j)) {
                        case abajo:
                            if (posicion % Error.maxFila == 2 || posicion % Error.maxFila == 7) {
                                c = new Sorpresa();
                                c.setPosicion(posicion);
                            } else if (posicion % Error.maxFila == 4) {
                                c = new Taxes(Float.parseFloat(asignacion));
                                c.setPosicion(posicion);
                            }else {
                                String[] partes = asignacion.split(";");
                                Color color = Comprobacion.color(partes[1]);
                                c = new Calle(partes[0], color, Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Float.parseFloat(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                                c.setPosicion(posicion);
                            }
                            break;
                        case izquierda:
                            if (posicion % Error.maxFila == 2) {
                                String[] partes = asignacion.split(";");
                                c = new Servicio(partes[0],Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
                                c.setPosicion(posicion);
                            } else if (posicion % Error.maxFila == 7) {
                                c = new Sorpresa();
                                c.setPosicion(posicion);
                            }else {
                                String[] partes = asignacion.split(";");
                                Color color = Comprobacion.color(partes[1]);
                                c = new Calle(partes[0], color, Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Float.parseFloat(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                                c.setPosicion(posicion);
                            }
                            break;
                        case arriba:
                            if (posicion % Error.maxFila == 2) {
                                c = new Sorpresa();
                                c.setPosicion(posicion);
                            } else if (posicion % Error.maxFila == 8) {
                                String[] partes = asignacion.split(";");
                                c = new Servicio(partes[0],Integer.parseInt(partes[1]), Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]));
                                c.setPosicion(posicion);
                            }else {
                                String[] partes = asignacion.split(";");
                                Color color = Comprobacion.color(partes[1]);
                                c = new Calle(partes[0], color, Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Float.parseFloat(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                                c.setPosicion(posicion);
                            }
                            break;
                        case derecha:
                            if (posicion % Error.maxFila == 3 || posicion % Error.maxFila == 6) {
                                c = new Sorpresa();
                                c.setPosicion(posicion);
                            } else if (posicion % Error.maxFila == 8) {
                                c = new Taxes(Float.parseFloat(asignacion));
                                c.setPosicion(posicion);
                            }else {
                                String[] partes = asignacion.split(";");
                                Color color = Comprobacion.color(partes[1]);
                                c = new Calle(partes[0], color, Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Float.parseFloat(partes[4]), Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                                c.setPosicion(posicion);
                            }
                            break;
                    }
                }
                this.casillas.put(posicion, c);
            }
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }

    }

    /*
    crea los jugadores y asigna el primer turno en funcion del orden en el que se asignen los jugadores al array
     */
    public ArrayList<Jugador> crearJugadores(ArrayList<String> jugadores){
        Random ran = new Random();
        Jugador j = null;
        int x = 0;
        ArrayList<Integer> repes = new ArrayList<>();
        if(jugadores.size() > 0)
            x = ran.nextInt(jugadores.size());

        for(int i = 0; i < jugadores.size(); i++){
            while(repes.contains(x))
                x = ran.nextInt(jugadores.size());

            repes.add(x);
            j = new Jugador(jugadores.get(x));
            this.jugadores.add(j);


        }
        turno.setJugador(this.jugadores.get(0));

        this.mGui = tablero.getMenuGui();
        this.interfaz = new Interfaz(mGui);

        for(Casilla c: casillas.values())
            c.setInter(interfaz);

        return this.jugadores;
    }

    /*
    Se ejecuta al pulsar el bComprar. Transmite la llamada a la propiedad en cuestion
     */
    public void comprar(){
        if(this.casillas.get(turno.getJugador().getPosicion()) instanceof Propiedad){
            ((Propiedad) this.casillas.get(turno.getJugador().getPosicion())).comprar(turno.getJugador());
        }else
            new VAviso(Error.noComprable);

    }

    /*
    Se ejecuta al pulsar el bEdificar. Transmite la llamada a la propiedad en cuestion
     */
    public void edificar(){
        if(this.casillas.get(turno.getJugador().getPosicion()) instanceof Calle){
            ((Calle) this.casillas.get(turno.getJugador().getPosicion())).edificar(turno.getJugador());
        }else
            new VAviso(Error.noEdificable);

    }

    /*
    Se ejecuta al pulsar el bHipotecar. Transmite la llamada a la propiedad en cuestion
     */
    public void hipotecar(){
        if(this.casillas.get(turno.getJugador().getPosicion()) instanceof Propiedad){
            ((Propiedad) this.casillas.get(turno.getJugador().getPosicion())).hipotecar(turno.getJugador());
        }else
            new VAviso(Error.noHipotecable);

    }

    /*
    Se ejecuta al pulsar el bTurno
     */
    public void acabarTurno(){
        if(turno.isOk()) {
            turn++;
            this.turno.turnoSig(jugadores.get(turn % jugadores.size()));

            try {
                java.util.concurrent.TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                System.out.println("No deberia llegar aqui");
            }

            mGui.limpiarMovi();

            //Comprobacion carcel
            if(turno.getJugador().isEncarcelado()){
                Carcel car = (Carcel)casillas.get(turno.getJugador().getPosicion());
                turno.tirar();
                car.comprobacion(turno.getJugador(), turno);
            }else {
                turno.tirar();

                mGui.actJugador(turno.getJugador());
                mGui.actTirada(turno.getTirada());

                turno.getJugador().setPosicion((turno.getJugador().getPosicion() + turno.getTirada()) % Error.maxCasillas);

                tablero.moverFicha(turno);

                mGui.actInfo(casillas.get(turno.getJugador().getPosicion()));


                comprobacionesTurno();
            }
        }else{
            new VAviso(Error.moroso);
            //Intentamos cobrar de nuevo
            Casilla c = casillas.get(turno.getJugador().getPosicion());
            if(c instanceof Propiedad) {
                ((Propiedad) c).cobrar(turno.getJugador(), turno);
                new VAviso("Cobrada queda la deuda, ya puedes terminar turno");
            }
            else if(c instanceof Taxes) {
                ((Taxes) c).cobrar(turno.getJugador());
                new VAviso("Cobrada queda la deuda, ya puedes terminar turno");
            }
        }

    }

    public void comprobacionesTurno(){
        Casilla c = casillas.get(turno.getJugador().getPosicion());

        Salida s = (Salida)casillas.get(0);
        s.recompensar(turno.getJugador(), turno);

        if(c instanceof Propiedad)
            ((Propiedad) c).cobrar(turno.getJugador(), turno);
        else if(c instanceof Taxes)
            ((Taxes) c).cobrar(turno.getJugador());
        else if(c instanceof Sorpresa)
            //Falta crear las sorpresas
            ;
        else if( c instanceof Carcel){
            if(!((Carcel) c).isVisita()){
                ((Carcel) c).encarcelar(turno.getJugador());
                tablero.moverFichaCarcel(turno);
                turno.getJugador().setEncarcelado(true);
            }
        }

    }



    public Turno getTurno() {
        return turno;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
