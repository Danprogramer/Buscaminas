import java.util.Random;
import java.util.Scanner;

public class Buscaminas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int filas = 5; // Número de filas del tablero
        int columnas = 5; // Número de columnas del tablero
        int numMinas = 5; // Número de minas en el tablero

        char[][] tablero = inicializarTablero(filas, columnas);
        colocarMinas(tablero, numMinas);
        char[][] tableroVisible = inicializarTablero(filas, columnas, ' ');

        boolean juegoActivo = true;

        while (juegoActivo) {
            imprimirTablero(tableroVisible);

            System.out.print("Ingrese fila y columna (ejemplo: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (esMovimientoValido(tablero, fila, columna)) {
                if (tablero[fila][columna] == 'X') {
                    juegoActivo = false;
                    System.out.println("¡Has golpeado una mina! Fin del juego.");
                } else {
                    descubrirCasilla(tablero, tableroVisible, fila, columna);
                    if (esVictoria(tableroVisible, numMinas)) {
                        juegoActivo = false;
                        System.out.println("¡Felicidades! Has ganado.");
                    }
                }
            } else {
                System.out.println("Movimiento no válido. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }

    private static char[][] inicializarTablero(int filas, int columnas) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }
        return tablero;
    }

    private static char[][] inicializarTablero(int filas, int columnas, char valorInicial) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = valorInicial;
            }
        }
        return tablero;
    }

    private static void colocarMinas(char[][] tablero, int numMinas) {
        Random random = new Random();
        int filas = tablero.length;
        int columnas = tablero[0].length;

        for (int i = 0; i < numMinas; i++) {
            int fila, columna;
            do {
                fila = random.nextInt(filas);
                columna = random.nextInt(columnas);
            } while (tablero[fila][columna] == 'X');

            tablero[fila][columna] = 'X';
        }
    }

    private static void imprimirTablero(char[][] tablero) {
        System.out.println("Tablero:");
        for (char[] fila : tablero) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    private static boolean esMovimientoValido(char[][] tablero, int fila, int columna) {
        int filas = tablero.length;
        int columnas = tablero[0].length;

        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    private static void descubrirCasilla(char[][] tablero, char[][] tableroVisible, int fila, int columna) {
        if (tableroVisible[fila][columna] == ' ') {
            int minasCercanas = contarMinasCercanas(tablero, fila, columna);
            tableroVisible[fila][columna] = (char) (minasCercanas + '0');
        }
    }

    private static int contarMinasCercanas(char[][] tablero, int fila, int columna) {
        int minasCercanas = 0;
        int filas = tablero.length;
        int columnas = tablero[0].length;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas && tablero[i][j] == 'X') {
                    minasCercanas++;
                }
            }
        }

        return minasCercanas;
    }

    private static boolean esVictoria(char[][] tableroVisible, int numMinas) {
        int casillasDescubiertas = 0;
        int filas = tableroVisible.length;
        int columnas = tableroVisible[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tableroVisible[i][j] != ' ' && tableroVisible[i][j] != 'X') {
                    casillasDescubiertas++;
                }
            }
        }

        return casillasDescubiertas == (filas * columnas - numMinas);
    }
}