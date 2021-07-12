package Juego;

public class Dado {
    public static int tirar(){
        return (int)Math.floor(Math.random() * 6 + 1);
    }
}
