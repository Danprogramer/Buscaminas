import java.util.Scanner;

public class Buscaminas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int filas = 5;
        int columnas = 5;
        int numMinas = 5;

        Tablero tablero = new TableroImpl(filas, columnas, numMinas);

        boolean juegoActivo = true;

        while (juegoActivo) {
            tablero.imprimir();

            System.out.print("Ingrese fila y columna (ejemplo: 1 2): ");
            int fila = scanner.nextInt();
            int columna = scanner.nextInt();

            if (tablero.esMovimientoValido(fila, columna)) {
                if (tablero.esVictoria()) {
                    juegoActivo = false;
                    System.out.println("¡Felicidades! Has ganado.");
                } else {
                    tablero.descubrirCasilla(fila, columna);

                    if (!tablero.esMovimientoValido(fila, columna)) {
                        juegoActivo = false;
                        System.out.println("¡Has golpeado una mina! Fin del juego.");
                    }
                }
            } else {
                System.out.println("Movimiento no válido. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
}
