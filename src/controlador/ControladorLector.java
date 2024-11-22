package controlador;

import modelo.*;
import utilidades.ArchivoPgn;
import utilidades.Sonido;
import vista.FramePrograma;
import vista.MenuInicio;
import vista.PanelTablero;
import vista.VistaModoLector;

import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;

public class ControladorLector {
    private Juego juego;
    private Tablero tablero;
    private Sonido efectoMover, efectoCaptura, efectoEnroque, efectoJaque, efectoJaqueMate;
    private PanelTablero panelTablero;
    private VistaModoLector vistaModoLector;
    private ListaMovimientos listaMovimientos;
    private ArchivoPgn archivoPgn;
    private Timer autoplay;
    private FramePrograma framePrograma;
    private int turnoEnPantalla = 0;
    /**
     * Constructor de la clase.
     *
     * @param juego            Objeto que maneja la logica del juego de ajedrez.
     * @param panelTablero     Panel que muestra el tablero de ajedrez.
     * @param listaMovimientos Lista de movimientos hechos en un juego.
     * @param archivoPgn       Objeto que maneja la logica para guardar, cargar y leer archivos PGN.
     */
    public ControladorLector(Juego juego, PanelTablero panelTablero, ListaMovimientos listaMovimientos, ArchivoPgn archivoPgn) {
        this.juego = juego;
        this.panelTablero = panelTablero;
        tablero = juego.getTablero();
        efectoMover = new Sonido();
        efectoMover.cargarSonido("/sonidos/Mover.wav");
        efectoCaptura = new Sonido();
        efectoCaptura.cargarSonido("/sonidos/Captura.wav");
        efectoEnroque = new Sonido();
        efectoEnroque.cargarSonido("/sonidos/Enroque.wav");
        efectoJaque = new Sonido();
        efectoJaque.cargarSonido("/sonidos/Jaque.wav");
        efectoJaqueMate = new Sonido();
        efectoJaqueMate.cargarSonido("/sonidos/JaqueMate.wav");
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

    /**
     * Inicia el timer que reproduce automaticamente una partida cargada.
     */
    public void iniciarAutoplay() {
        autoplay.start();
    }

    /**
     * Pausa el timer que reproduce una partida cargada.
     */
    public void detenerAutoplay() {
        autoplay.stop();
    }

    /**
     * Recibe un movimiento, lo valida, lo realiza y ejecuta un sonido
     * dependiendo del movimiento ejecutado.
     *
     * @param movimiento Texto del movimiento ingresado.
     */
    public void hacerMovimiento(String movimiento) {
        if (!validarFormatoMovimiento(movimiento)) {
            vistaModoLector.mostrarMensajeMalMovimiento();
            return;
        }
        if (!juego.moverFicha(movimiento)) {
           vistaModoLector.mostrarMensajeMalMovimiento();
           return;
        }
        if (movimiento.contains("+")) {
            efectoJaque.reproducir();
        }
        else if (movimiento.contains("#")) {
            efectoJaqueMate.reproducir();
        }
        if (movimiento.contains("x")) {
            efectoCaptura.reproducir();
        }
        else if (movimiento.equals("O-O") || movimiento.equals("O-O-O")) {
            efectoEnroque.reproducir();
        }

        else {
            efectoMover.reproducir();
        }
        // Si el rey en jaque, los atributos del objeto juego posIReyJaque y posJReyJaque
        // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
        // del rey que muestra que esta en jaque.
        panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());

        // Si se ha realizado un movimiento, los atributos del objeto juego de posIMovimientoAnterior, posJMovimientoAnterior,
        // posIMovimientoAcual y posJMovimientoActual seran diferentes de -1 lo que al pasarlos al panel tablero
        // hara que se muestren 2 cuadros amarillos que representan el movimiento realizado.
        panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());

        panelTablero.actualizarPanel();
    }

    /**
     * Verifica si un movimiento ingresado cumple con la notacion
     * de ajedrez.
     * @param movimiento Movimiento que se desea validar.
     * @return Retorna false si el movimiento es invalido, y true si no lo es.
     */
    public boolean validarFormatoMovimiento(String movimiento) {
        if (movimiento.length() == 1 || movimiento.length() > 7) {
            System.out.println(1);
            return false;
        }
        if (movimiento.contains(" ")) {
            System.out.println(2);
            return false;
        }
        if (!Character.isLetter(movimiento.charAt(0))) {
            System.out.println(3);
            return false;
        }
        if (movimiento.contains("0") || movimiento.contains("9")) {
            System.out.println(4);
            return false;
        }

        for (char c : movimiento.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '=' && c != '+' && c != '#' && c != '-') {
                System.out.println(5);
                return false;
            }
            if (Character.isLowerCase(c) && c > 'h' && c != 'x') {
                System.out.println(6);
                return false;
            }
            if (Character.isUpperCase(c) && c != 'Q' && c != 'R' && c != 'K' && c != 'B' && c != 'N' && c != 'O') {
                System.out.println(7);
                return false;
            }
        }
        return true;
    }

    /**
     * Avanza al movimiento siguiente en una partida cargada.
     */
    public void avanzarMovimiento() {
        if (turnoEnPantalla < listaMovimientos.getCantidadMovimientos()) {
            turnoEnPantalla++;
            if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("+")) {
                efectoJaque.reproducir();
            }
            else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("#")) {
                efectoJaqueMate.reproducir();
            }
            else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("x")) {
                efectoCaptura.reproducir();
            }
            else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O") || listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O-O")) {
                efectoEnroque.reproducir();
            }
            else {
                efectoMover.reproducir();
            }
            juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            // Si el rey en jaque, los atributos del objeto juego posIReyJaque y posJReyJaque
            // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
            // del rey que muestra que esta en jaque.
            panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());

            // Si se ha realizado un movimiento, los atributos del objeto juego de posIMovimientoAnterior, posJMovimientoAnterior,
            // posIMovimientoAcual y posJMovimientoActual seran diferentes de -1 lo que al pasarlos al panel tablero
            // hara que se muestren 2 cuadros amarillos que representan el movimiento realizado.
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
                if (listaMovimientos.getMovimiento(turnoEnPantalla).contains("+")) {
                    efectoJaque.reproducir();
                }
                else if (listaMovimientos.getMovimiento(turnoEnPantalla).contains("#")) {
                    efectoJaqueMate.reproducir();
                }
                else if (listaMovimientos.getMovimiento(turnoEnPantalla).contains("x")) {
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
            // Si el rey en jaque, los atributos del objeto juego posIReyJaque y posJReyJaque
            // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
            // del rey que muestra que esta en jaque.
            panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());

            // Si se ha realizado un movimiento, los atributos del objeto juego de posIMovimientoAnterior, posJMovimientoAnterior,
            // posIMovimientoAcual y posJMovimientoActual seran diferentes de -1 lo que al pasarlos al panel tablero
            // hara que se muestren 2 cuadros amarillos que representan el movimiento realizado.
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
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            hacerMovimiento(vistaModoLector.getEntradaMovimientos().getText());
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            framePrograma.cargarMenuInicio();
            return;
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
            panelTablero.reiniciarPosicionesMovimiento();
            panelTablero.reiniciarPosicionesReyJaque();
            vistaModoLector.resaltarMovimiento(0);
        }
        if (e.getSource() == vistaModoLector.getBotonFinal()) {
            turnoEnPantalla = listaMovimientos.getCantidadMovimientos();
            juego.analizarPartida(listaMovimientos,  listaMovimientos.getCantidadMovimientos());
            panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                    juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
            panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());
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
            juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                    juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
            panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());
        }
        if (e.getSource() == vistaModoLector.getBotonCargarArchivo()) {
            archivoPgn.cargarArchivo();
            if (archivoPgn.getArchivo() != null) {
                tablero.iniciarTablero();
                turnoEnPantalla = 0;
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

    /**
     * Agrega una referencia del frame del programa a la clase para poder cambiar entre el
     * menu del juego y el menu de inicio.
     * @param framePrograma Referencia del objeto que muestra el frame en pantalla.
     */
    public void setFramePrograma(FramePrograma framePrograma) {
        this.framePrograma = framePrograma;
    }
}
