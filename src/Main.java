import controlador.Controlador;
import modelo.*;
import vista.*;

public class Main {
    public static void main(String[] args) {
        ListaMovimientos movimientos = new ListaMovimientos();
        Tablero tablero = new Tablero();
        PanelTablero panelTablero = new PanelTablero(tablero);
        Juego juego = new Juego(tablero, movimientos);
        Controlador controlador = new Controlador(juego, panelTablero, new ListaMovimientos());
        FramePrograma framePrograma = new FramePrograma(panelTablero, controlador);
        while (true) {
            controlador.hacerMovimiento();
        }
    }
}