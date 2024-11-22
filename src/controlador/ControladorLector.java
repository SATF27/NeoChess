package controlador;

import modelo.*;
import utilidades.ArchivoPgn;
import utilidades.Sonido;
import vista.PanelTablero;
import vista.VistaModoLector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Controlador {
    private Juego juego;
    private Tablero tablero;
    private Sonido efectoMover, efectoCaptura, efectoEnroque;
    private PanelTablero panelTablero;
    private VistaModoLector vistaModoLector;
    private ListaMovimientos listaMovimientos;
    private ArchivoPgn archivoPgn;
    private Timer autoplay;
    private int turnoEnPantalla = 0;

    public Controlador(Juego juego, PanelTablero panelTablero, ListaMovimientos listaMovimientos, ArchivoPgn archivoPgn) {
        this.juego = juego;
        this.panelTablero = panelTablero;
        tablero = juego.getTablero();
        efectoMover = new Sonido();
        efectoMover.cargarSonido("/sonidos/Mover.wav");
        efectoCaptura = new Sonido();
        efectoCaptura.cargarSonido("/sonidos/Captura.wav");
        efectoEnroque = new Sonido();
        efectoEnroque.cargarSonido("/sonidos/Enroque.wav");
        this.archivoPgn = archivoPgn;
        this.listaMovimientos = listaMovimientos;
        autoplay = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turnoEnPantalla <= listaMovimientos.getCantidadMovimientos()) {
                    avanzarMovimiento();
                    vistaModoLector.resaltarMovimiento(turnoEnPantalla);
                    vistaModoLector.actualizarVista();
                }
            }
        });
    }

    public void iniciarAutoplay() {
        autoplay.start();
    }

    public void detenerAutoplay() {
        autoplay.stop();
    }

    public void hacerMovimiento() {
        String movimiento;
        System.out.print("Ingresa un movimiento: ");
        Scanner sc = new Scanner(System.in);
        movimiento = sc.nextLine();
        juego.moverFicha(movimiento);
        if (movimiento.contains("x")) {
            efectoCaptura.reproducir();
        }
        else if (movimiento.equals("O-O") || movimiento.equals("O-O-O")) {
            efectoEnroque.reproducir();
        }
        else {
            efectoMover.reproducir();
        }

        panelTablero.actualizarPanel();
    }

    /**
     * Avanza al movimiento siguiente en una partida cargada.
     */
    public void avanzarMovimiento() {
        if (turnoEnPantalla < listaMovimientos.getCantidadMovimientos()) {
            turnoEnPantalla++;
            if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("x")) {
                efectoCaptura.reproducir();
            }
            else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O") || listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O-O")) {
                efectoEnroque.reproducir();
            }
            else {
                efectoMover.reproducir();
            }
            juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                    juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
        }
    }

    /**
     * Avanza al anterior movimiento de una partida cargada.
     */
    public void retrocederMovimiento() {
        if (turnoEnPantalla > 0) {
            turnoEnPantalla--;
            if (turnoEnPantalla > 0) {
                if (listaMovimientos.getMovimiento(turnoEnPantalla).contains("x")) {
                    efectoCaptura.reproducir();
                }
                else if (listaMovimientos.getMovimiento(turnoEnPantalla).equals("O-O") || listaMovimientos.getMovimiento(turnoEnPantalla).equals("O-O-O")) {
                    efectoEnroque.reproducir();
                }
                else {
                    efectoMover.reproducir();
                }
            }
            juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                    juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
        }
    }

    /**
     * Recibe un evento del teclado y decide como actuar en consecuencia.
     *
     * No es un metodo KeyListener definido en ninguna biblioteca. Es un metodo
     * creado para simular un KeyListener pero recibe eventos manualmente.
     * @param e Evento de teclado recibido.
     */
    public void keyListener(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            avanzarMovimiento();
            vistaModoLector.resaltarMovimiento(turnoEnPantalla);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            retrocederMovimiento();
            vistaModoLector.resaltarMovimiento(turnoEnPantalla);
        }
        vistaModoLector.actualizarVista();
    }

    /**
     * Este metodo se encarga de manejar la entrada de eventos por botones en el programa.
     * Aunque su nombre sea el mismo, en realidad no es el metodo actionPerformed de la
     * interface ActionListener.
     *
     * Este metodo esta creado para recibir la entrada de eventos directamente de manera externa
     * manualmente. Se le coloco el mismo nombre que el de la interface ActionListener para
     * hacer mas sencilla la comprension de su funcionamiento.
     * @param e Es el evento que se ingresa.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaModoLector.getBotonAvanzar()) {
            avanzarMovimiento();
            vistaModoLector.resaltarMovimiento(turnoEnPantalla);
        }
        if (e.getSource() == vistaModoLector.getBotonRetroceder()) {
            retrocederMovimiento();
            vistaModoLector.resaltarMovimiento(turnoEnPantalla);
        }
        if (e.getSource() == vistaModoLector.getBotonInicio()) {
            turnoEnPantalla = 0;
            juego.reiniciarPartida();
        }
        if (e.getSource() == vistaModoLector.getBotonFinal()) {
            turnoEnPantalla = listaMovimientos.getCantidadMovimientos();
            juego.analizarPartida(listaMovimientos,  listaMovimientos.getCantidadMovimientos());
            vistaModoLector.resaltarMovimiento(turnoEnPantalla);
        }
        if (e.getSource() == vistaModoLector.getBotonPausa()) {
            detenerAutoplay();
        }
        if (e.getSource() == vistaModoLector.getBotonReproducir()) {
            iniciarAutoplay();
        }
        if (e.getSource() == vistaModoLector.getBotonRotarTablero()) {
            tablero.rotarTablero();
        }
        if (e.getSource() == vistaModoLector.getBotonCargarArchivo()) {
            tablero.iniciarTablero();
            turnoEnPantalla = 0;
            archivoPgn.cargarArchivo();
            if (archivoPgn.getArchivo() != null) {
                archivoPgn.leerArchivo();
                listaMovimientos.agregarMovimientos(archivoPgn.getMovimientos());
                vistaModoLector.mostrarMovimientos();
            }
            else {
                System.out.println("Archivo no cargado");
            }
        }
        vistaModoLector.actualizarVista();
    }

    /**
     * Set de la vista del modo lector.
     * @param vistaModoLector Layeredpane que representa la vista del modo lector del programa.
     */
    public void setVistaModoLector(VistaModoLector vistaModoLector) {
        this.vistaModoLector = vistaModoLector;
    }
}
