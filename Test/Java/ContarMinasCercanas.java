public class ContarMinasCercanas {
    int contarMinasCercanas(char[][] tablero, int fila, int columna) {
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
