import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Testing {
    @Test
    @DisplayName("Testing")

    public void testContarMinasCercanas() {
        // Crear una instancia de la clase Buscaminas (reemplaza "Buscaminas" con el nombre de tu clase)
        ContarMinasCercanas buscaminas = new ContarMinasCercanas();

        // Configurar un tablero de ejemplo con minas y casillas vacías
        char[][] tablero = {
                {' ', 'X', ' ', ' ', ' '},
                {'X', ' ', ' ', 'X', ' '},
                {' ', ' ', ' ', ' ', 'X'},
                {' ', 'X', ' ', ' ', ' '},
                {'X', ' ', ' ', 'X', ' '}
        };

        // Verificar el conteo de minas para diversas casillas
        assertEquals(4, buscaminas.contarMinasCercanas (tablero, 0, 0));
        assertEquals(3, buscaminas.contarMinasCercanas (tablero, 1, 1));
        assertEquals(2, buscaminas.contarMinasCercanas (tablero, 2, 2));
        assertEquals(1, buscaminas.contarMinasCercanas (tablero, 3, 3));
        assertEquals(0, buscaminas.contarMinasCercanas (tablero, 4, 4));
    }
}