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
        // Se pide al usuario que ingrese el numero de fila y columna.
            System.out.print("Ingrese fila y columna (ejemplo: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();
        // Si escoge un valor valido comprueba la casilla y en caso de ser una mina se termina el juago, si no se descubre dicha casilla.
            if (esMovimientoValido(tablero, fila, columna)) {
                if (tablero[fila][columna] == 'X') {
                    juegoActivo = false;
                    System.out.println("¡Has golpeado una mina! Fin del juego.");
                } else {
                    descubrirCasilla(tablero, tableroVisible, fila, columna);
                    if (esVictoria(tableroVisible, numMinas)) {
                        juegoActivo = false;
                        System.out.println("¡Felicidades! Has ganado."); // si se han descubierto todas las celdas menos las 5 minas es victoria.
                    }
                }
            } else {
                System.out.println("Movimiento no válido. Inténtalo de nuevo."); // mensaje que se enseña por pantalla si la celda introducida es incorrecta.
            }
        }

        scanner.close();
    }
    // Inicializa el tablero con un carácter específico (espacio en blanco por defecto).
    private static char[][] inicializarTablero(int filas, int columnas) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }
        return tablero;
    }
    // Inicializa el tablero con un carácter específico (utilizado para el tablero visible).
    private static char[][] inicializarTablero(int filas, int columnas, char valorInicial) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = valorInicial;
            }
        }
        return tablero;
    }
    // Coloca las minas aleatoriamente en el tablero.
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
    // Imprime el estado actual del tablero visible.
    private static void imprimirTablero(char[][] tablero) {
        System.out.println("Tablero:");
        for (char[] fila : tablero) {
            for (char celda : fila) {
                System.out.print(celda + "| |");
            }
            System.out.println();

        }
    }
    // Verifica si un movimiento (fila, columna) es válido en el tablero.
    private static boolean esMovimientoValido(char[][] tablero, int fila, int columna) {
        int filas = tablero.length;
        int columnas = tablero[0].length;

        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }
    // Descubre una casilla en el tablero visible, revelando su contenido.
    private static void descubrirCasilla(char[][] tablero, char[][] tableroVisible, int fila, int columna) {
        if (tableroVisible[fila][columna] == ' ') {
            int minasCercanas = contarMinasCercanas(tablero, fila, columna);
            tableroVisible[fila][columna] = (char) (minasCercanas + '0');
        }
    }
    // Cuenta el número de minas cercanas a una casilla en el tablero.
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
    // Verifica si se ha alcanzado la condición de victoria.
    private static boolean esVictoria(char[][] tableroVisible, int numMinas) {
        int casillasDescubiertas = 0;
        int filas = tableroVisible.length;
        int columnas = tableroVisible[0].length;

        for (char[] chars : tableroVisible) {
            for (int j = 0; j < columnas; j++) {
                if (chars[j] != ' ' && chars[j] != 'X') {
                    casillasDescubiertas++;
                }
            }
        }

        return casillasDescubiertas == (filas * columnas - numMinas);
    }
}