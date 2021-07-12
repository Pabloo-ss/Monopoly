package Juego;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import Excepciones.Comprobacion;
import Gui.Orientacion;
import Gui.Tablero;
import Excepciones.Error;

public class Menu {
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private HashMap<Integer, Casilla> casillas = new HashMap<>();


    public Menu(){
        File casillas = new File("casillas.txt");
        crearCasillas(casillas);

        while(true){

        }
    }





    /*
    @file nombre del archivo con formato para calles--> nombre;color;precio;alquiler;incrementoXedificacion;costeEdificacion;hipoteca
                                         para estaciones--> nombre;precio;hipoteca;alquiler1;alquiler2;alquiler3;alquiler4
                                         para servicios--> nombre;precio;hipoteca;multiplicador1;multiplicador2
                                         para Salida--> recompensa
                                         para Sorpresa--> sorpresa
                                         para carcel--> visita/no visita
                                         para taxes--> impuesto
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
            new Tablero(this.casillas);
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }

    }
}
