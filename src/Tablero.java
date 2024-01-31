public interface Tablero {
    void imprimir();
    boolean esMovimientoValido(int fila, int columna);
    void descubrirCasilla(int fila, int columna);
    boolean esVictoria();
}
