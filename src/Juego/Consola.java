package Juego;

import java.util.Scanner;

public abstract class Consola {

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    public static String leer() {
        System.out.print("$> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
