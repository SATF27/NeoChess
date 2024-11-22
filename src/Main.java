import controlador.ControladorJuego;
import controlador.ControladorLector;
import controlador.ControladorMenuInicio;
import modelo.*;
import utilidades.ArchivoPgn;
import vista.*;

public class Main {
    public static void main(String[] args) {
        ListaMovimientos movimientosLector = new ListaMovimientos();
        ListaMovimientos movimientosJuego = new ListaMovimientos();
        Tablero tableroLector = new Tablero();
        Tablero tableroJuego = new Tablero();
        Juego juegoLector = new Juego(tableroLector, movimientosLector);
        Juego juegoModoJuego = new Juego(tableroJuego, movimientosJuego);
        PanelTablero panelTableroLector = new PanelTablero(tableroLector);
        PanelTablero panelTableroJuego = new PanelTablero(tableroJuego);
        ArchivoPgn archivoPgn = new ArchivoPgn();
        ControladorLector controladorLector = new ControladorLector(juegoLector, panelTableroLector, movimientosLector, archivoPgn);
        ControladorJuego controladorJuego = new ControladorJuego(juegoModoJuego, panelTableroJuego, movimientosJuego, archivoPgn);
        ControladorMenuInicio controladorMenuInicio = new ControladorMenuInicio();
        VistaModoLector vistaModoLector = new VistaModoLector(panelTableroLector,controladorLector, archivoPgn);
        VistaModoJuego vistaModoJuego = new VistaModoJuego(panelTableroJuego, controladorJuego);
        MenuInicio menuInicio = new MenuInicio(controladorMenuInicio);
        FramePrograma framePrograma = new FramePrograma(vistaModoLector, vistaModoJuego, menuInicio);
        controladorLector.setVistaModoLector(vistaModoLector);
        controladorLector.setFramePrograma(framePrograma);
        controladorJuego.setVistaModoJuego(vistaModoJuego);
        controladorJuego.setFramePrograma(framePrograma);
        controladorMenuInicio.setFramePrograma(framePrograma);
    }
}