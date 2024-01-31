import java.util.Random;

public class TableroImpl implements Tablero {
    private char[][] tablero;
    private char[][] tableroVisible;
    private int filas;
    private int columnas;
    private int numMinas;

    public TableroImpl(int filas, int columnas, int numMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numMinas = numMinas;
        this.tablero = inicializarTablero(filas, columnas);
        this.tableroVisible = inicializarTablero(filas, columnas, ' ');
        colocarMinas(numMinas);
    }

    // Implementación de métodos de la interfaz Tablero

    @Override
    public void imprimir() {
        System.out.println("Tablero:");
        for (char[] fila : tableroVisible) {
            for (char celda : fila) {
                System.out.print(celda + "|");
            }
            System.out.println();
        }
    }

    @Override
    public boolean esMovimientoValido(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    @Override
    public void descubrirCasilla(int fila, int columna) {
        if (tableroVisible[fila][columna] == ' ') {
            int minasCercanas = contarMinasCercanas(tablero, fila, columna);
            tableroVisible[fila][columna] = (minasCercanas > 0) ? (char) (minasCercanas + '0') : ' ';
        }
    }

    @Override
    public boolean esVictoria() {
        int casillasDescubiertas = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tableroVisible[i][j] != ' ' && tableroVisible[i][j] != 'X') {
                    casillasDescubiertas++;
                }
            }
        }

        return casillasDescubiertas == (filas * columnas - numMinas);
    }

    // Métodos privados

    private char[][] inicializarTablero(int filas, int columnas) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = ' ';
            }
        }
        return tablero;
    }

    private char[][] inicializarTablero(int filas, int columnas, char valorInicial) {
        char[][] tablero = new char[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = valorInicial;
            }
        }
        return tablero;
    }

    private void colocarMinas(int numMinas) {
        Random random = new Random();

        int minasColocadas = 0;
        while (minasColocadas < numMinas) {
            int fila = random.nextInt(filas);
            int columna = random.nextInt(columnas);

            if (tablero[fila][columna] != 'X') {
                tablero[fila][columna] = 'X';
                minasColocadas++;
            }
        }
    }

    private int contarMinasCercanas(char[][] tablero, int fila, int columna) {
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
}


