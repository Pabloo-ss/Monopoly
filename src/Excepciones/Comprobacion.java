package Excepciones;

import Juego.*;

import java.awt.*;
import java.util.Iterator;

public class Comprobacion {

    public static boolean sino(String res) throws ExcepcionString {
        if(res.equals("s") || res.equals("n"))
            return true;
        throw new ExcepcionString(Error.sino);
    }

    public static int valorValido(String res) throws ExcepcionNumero{
        try{
            int numero = Integer.parseInt(res);
            if(numero > 0)
                return numero;
            else
                throw new ExcepcionNumero(Error.numNegativo);
            } catch (NumberFormatException e){
                throw new ExcepcionNumero(Error.formatoNum);
            }
    }

    public static void saldoSuficiente(int oferta, float saldo) throws ExcepcionNumero{
        if(saldo < (float)oferta)
            throw new ExcepcionNumero(Error.saldoInsuficiente);
    }

    public static void cobrable(Propiedad prop, Jugador j, Turno turno) throws ExcepcionCapitalista {
        if (!prop.getTitular().equals(j) && prop.getTitular() != null) {
            if (prop.isHipotecada())
                throw new ExcepcionCapitalista("Lo siento " + j.getNombre() + " pero esta no la cobras que esta hipotecada ");
            else {//Se cobra
                if (prop instanceof Servicio) {
                    Servicio ser = (Servicio) prop;
                    if (Comprobacion.propIguales(ser) == 1) {
                        if (j.getDinero() < (float) turno.getTirada() * ser.getMultiplicador1()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)turno.getTirada() * ser.getMultiplicador1() - j.getDinero()) + "€");
                        }
                    }else {
                        if (j.getDinero() < (float) turno.getTirada() * ser.getMultiplicador2()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)turno.getTirada() * ser.getMultiplicador2() - j.getDinero()) + "€");
                        }
                    }
                }if(prop instanceof Estacion){
                    Estacion est = (Estacion) prop;
                    if (Comprobacion.propIguales(est) == 1) {
                        if (j.getDinero() < (float) est.getPrecio() * est.getAlquiler1()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)est.getPrecio() * est.getAlquiler1() - j.getDinero()) + "€");
                        }
                    }else if (Comprobacion.propIguales(est) == 2) {
                        if (j.getDinero() < (float) est.getPrecio() * est.getAlquiler2()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)est.getPrecio() * est.getAlquiler2() - j.getDinero()) + "€");
                        }
                    }else if (Comprobacion.propIguales(est) == 3) {
                        if (j.getDinero() < (float) est.getPrecio() * est.getAlquiler3()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)est.getPrecio() * est.getAlquiler3() - j.getDinero()) + "€");
                        }
                    }else{
                        if (j.getDinero() < (float) est.getPrecio() * est.getAlquiler4()) {
                            turno.setOk(false);

                            throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)est.getPrecio() * est.getAlquiler4() - j.getDinero()) + "€");
                        }
                    }
                }if(prop instanceof Calle){
                    Calle calle = (Calle) prop;

                    if(j.getDinero() < (float) calle.getAlquiler() * (calle.getIncremento() * calle.getEdificios())){
                        turno.setOk(false);

                        throw new ExcepcionCapitalista(Error.moroso+". Todavia te faltan " + ((float)calle.getPrecio() * (1 + calle.getIncremento() * calle.getEdificios()) - j.getDinero()) + "€");
                    }
                }
            }
        }
    }

    public static int propIguales(Propiedad p){
        Jugador dueño = p.getTitular();
        int res = 0;

        Iterator<Propiedad> propiedades = p.getTitular().getPropiedades().values().iterator();
        while(propiedades.hasNext()){
            Propiedad prop = propiedades.next();
            if(prop instanceof Servicio)
                res++;
            if(prop instanceof Estacion)
                res++;
            if(prop instanceof Calle){
                Calle calle = (Calle) prop;
                Calle calleP = (Calle) p;
                if(calle.getColor().equals(calleP.getColor()))
                    res++;
            }
        }
        return res;
    }

    public static void edificable(Calle p) throws ExcepcionCapitalista{
        Jugador j = p.getTitular();
        int num = 0;


        if(p.getEdificios() == 4)
            throw new ExcepcionCapitalista(Error.maxEdificios);

        Iterator<Propiedad> propiedades = p.getTitular().getPropiedades().values().iterator();
        while(propiedades.hasNext()){
            Propiedad pit = propiedades.next();

            if(pit instanceof Calle){
                if(((Calle) pit).getColor().equals(p.getColor()))
                    num++;
            }
        }
        if((p.getColor().equals(Color.PINK) || p.getColor().equals(Color.MAGENTA)) && num != 2)
            throw new ExcepcionCapitalista(Error.todaviaNoEdif);
        else if(!(p.getColor().equals(Color.PINK) || p.getColor().equals(Color.MAGENTA)) && num != 3)
            throw new ExcepcionCapitalista(Error.todaviaNoEdif);
    }

    public static Color color(String col){
        Color res = null;
        switch (col){
            case "rosa":
                res = Color.PINK;
                break;
            case "cian":
                res = Color.CYAN;
            break;
            case "azul":
                res = Color.BLUE;
            break;
            case "naranja":
                res = new Color(245, 154, 18);
            break;
            case "marron":
                res = new Color(129,65,0);
            break;
            case "verde":
                res = Color.GREEN;
            break;
            case "rojo":
                res = Color.RED;
            break;
            case "morado":
                res = new Color(170, 5, 229);
            break;
        }
        return res;
    }
}
