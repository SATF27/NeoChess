package controlador;

import modelo.Juego;
import modelo.ListaMovimientos;
import modelo.Tablero;
import utilidades.ArchivoPgn;
import utilidades.Sonido;
import vista.FramePrograma;
import vista.PanelTablero;
import vista.VistaModoJuego;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ControladorJuego {
    private Juego juego;
    private Tablero tablero;
    private Sonido efectoMover, efectoCaptura, efectoEnroque, efectoJaque, efectoJaqueMate;
    private PanelTablero panelTablero;
    private VistaModoJuego vistaModoJuego;
    private ListaMovimientos listaMovimientos;
    private ArchivoPgn archivoPgn;
    private FramePrograma framePrograma;


    /**
     * Constructor de la clase.
     *
     * @param juego            Objeto que maneja la logica del juego de ajedrez.
     * @param panelTablero     Panel que muestra el tablero de ajedrez.
     * @param listaMovimientos Lista de movimientos hechos en un juego.
     * @param archivoPgn       Objeto que maneja la logica para guardar, cargar y leer archivos PGN.
     */
    public ControladorJuego(Juego juego, PanelTablero panelTablero, ListaMovimientos listaMovimientos, ArchivoPgn archivoPgn) {
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
    }

    /**
     * Verifica si un movimiento ingresado cumple con la notacion
     * de ajedrez.
     *
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
     * Recibe un movimiento, lo valida, lo realiza y ejecuta un sonido
     * dependiendo del movimiento ejecutado.
     *
     * @param movimiento Texto del movimiento ingresado.
     */
    public void hacerMovimiento(String movimiento) {
        if (!validarFormatoMovimiento(movimiento)) {
            vistaModoJuego.mostrarMensajeMalMovimiento();
            return;
        }
        if (!juego.moverFicha(movimiento)) {
            vistaModoJuego.mostrarMensajeMalMovimiento();
            return;
        }
        if (movimiento.contains("+")) {
            efectoJaque.reproducir();
        } else if (movimiento.contains("#")) {
            efectoJaqueMate.reproducir();
        }
        if (movimiento.contains("x")) {
            efectoCaptura.reproducir();
        } else if (movimiento.equals("O-O") || movimiento.equals("O-O-O")) {
            efectoEnroque.reproducir();
        } else {
            efectoMover.reproducir();
        }
        // Si el rey esta en jaque, los atributos del objeto juego posIReyJaque y posJReyJaque
        // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
        // del rey que muestra que esta en jaque.
        panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());

        // Si se ha realizado un movimiento, los atributos del objeto juego de posIMovimientoAnterior, posJMovimientoAnterior,
        // posIMovimientoAcual y posJMovimientoActual seran diferentes de -1 lo que al pasarlos al panel tablero
        // hara que se muestren 2 cuadros amarillos que representan el movimiento realizado.
        panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());

        if (juego.isTurnoBlanco()) {
            vistaModoJuego.turnoBlancas();
        } else {
            vistaModoJuego.turnoNegras();
        }

        if (movimiento.contains("#")) {
            vistaModoJuego.mostrarPantallaVictoria();
        }
        vistaModoJuego.setFichasTomadas(juego.getFichasTomadas());
        vistaModoJuego.actualizarVista();
        listaMovimientos.agregarMovimiento(movimiento);
    }


    /**
     * Avanza al anterior movimiento de una partida cargada.
     */
    public void retrocederMovimiento() {
        listaMovimientos.eliminarUltimoMovimiento();
        if (listaMovimientos.getCantidadMovimientos() == 0) {
            efectoMover.reproducir();
        } else {
            if (listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).contains("+")) {
                efectoJaque.reproducir();
            } else if (listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).contains("#")) {
                efectoJaqueMate.reproducir();
            } else if (listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).contains("x")) {
                efectoCaptura.reproducir();
            } else if (listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).equals("O-O") || listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).equals("O-O-O")) {
                efectoEnroque.reproducir();
            } else {
                efectoMover.reproducir();
            }
        }
        juego.analizarPartida(listaMovimientos, listaMovimientos.getCantidadMovimientos());
        if (juego.isTurnoBlanco()) {
            vistaModoJuego.turnoBlancas();
        } else {
            vistaModoJuego.turnoNegras();
        }
        // Si el rey esta en jaque, los atributos del objeto juego posIReyJaque y posJReyJaque
        // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
        // del rey que muestra que esta en jaque.
        panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());

        // Si se ha realizado un movimiento, los atributos del objeto juego de posIMovimientoAnterior, posJMovimientoAnterior,
        // posIMovimientoAcual y posJMovimientoActual seran diferentes de -1 lo que al pasarlos al panel tablero
        // hara que se muestren 2 cuadros amarillos que representan el movimiento realizado.
        panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
        vistaModoJuego.setFichasTomadas(juego.getFichasTomadas());
        vistaModoJuego.removerPantallaVictoria();
        vistaModoJuego.actualizarVista();
    }

    /**
     * Recibe un evento del teclado y decide como actuar en consecuencia.
     * <p>
     * No es un metodo KeyListener definido en ninguna biblioteca. Es un metodo
     * creado para simular un KeyListener pero recibe eventos manualmente.
     *
     * @param e Evento de teclado recibido.
     */
    public void keyListener(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            hacerMovimiento(vistaModoJuego.getEntradaMovimientos().getText());
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            framePrograma.cargarMenuInicio();
            return;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            retrocederMovimiento();
        }
        vistaModoJuego.actualizarVista();
    }

    /**
     * Este metodo se encarga de manejar la entrada de eventos por botones en el programa.
     * Aunque su nombre sea el mismo, en realidad no es el metodo actionPerformed de la
     * interface ActionListener.
     * <p>
     * Este metodo esta creado para recibir la entrada de eventos directamente de manera externa
     * manualmente. Se le coloco el mismo nombre que el de la interface ActionListener para
     * hacer mas sencilla la comprension de su funcionamiento.
     *
     * @param e Es el evento que se ingresa.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaModoJuego.getBotonAnteriorMovimiento()) {
            retrocederMovimiento();
        }

        if (e.getSource() == vistaModoJuego.getBotonRotarTablero()) {
            tablero.rotarTablero();
            juego.analizarPartida(listaMovimientos, listaMovimientos.getCantidadMovimientos());
            panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                    juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
            panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());
        }
        if (e.getSource() == vistaModoJuego.getBotonCargarPartida()) {
            archivoPgn.cargarArchivo();
            if (archivoPgn.getArchivo() != null) {
                tablero.iniciarTablero();
                archivoPgn.leerArchivo();
                listaMovimientos.agregarMovimientos(archivoPgn.getMovimientos());
                juego.analizarPartida(listaMovimientos, listaMovimientos.getCantidadMovimientos());
                if (juego.isTurnoBlanco()) {
                    vistaModoJuego.turnoBlancas();
                } else {
                    vistaModoJuego.turnoNegras();
                }
                vistaModoJuego.setFichasTomadas(juego.getFichasTomadas());
                // Si el rey esta en jaque en el ultimo movimiento, los atributos del objeto juego posIReyJaque y posJReyJaque
                // seran diferentes de -1, lo que mostrara en la interfaz un cuadro rojo en la posicion
                // del rey que muestra que esta en jaque.
                panelTablero.setPosicionesReyJaque(juego.getPosIReyJaque(), juego.getPosJReyJaque());
                panelTablero.setPosicionesMovimiento(juego.getPosIMovimientoAnterior(), juego.getPosJMovimientoAnterior(),
                        juego.getPosIMovimientoActual(), juego.getPosJMovimientoActual());
                if (listaMovimientos.getMovimiento(listaMovimientos.getCantidadMovimientos() - 1).contains("#")) {
                    vistaModoJuego.mostrarPantallaVictoria();
                } else {
                    vistaModoJuego.removerPantallaVictoria();
                }
                vistaModoJuego.actualizarVista();
            } else {
                System.out.println("Archivo no cargado");
            }
        }
        if (e.getSource() == vistaModoJuego.getBotonReiniciarPartida()) {
            juego.reiniciarPartida();
            panelTablero.reiniciarPosicionesMovimiento();
            panelTablero.reiniciarPosicionesReyJaque();
            vistaModoJuego.turnoBlancas();
            listaMovimientos.limpiarLista();
            vistaModoJuego.setFichasTomadas(juego.getFichasTomadas());
            vistaModoJuego.removerPantallaVictoria();
            vistaModoJuego.actualizarVista();
        }
        if (e.getSource() == vistaModoJuego.getBotonGuardarPartida()) {
            archivoPgn.guardarPartida(listaMovimientos);
        }
        vistaModoJuego.actualizarVista();
    }

    /**
     * Set de la vista del modo juego.
     *
     * @param vistaModoJuego Layeredpane que representa la vista del modo juego del programa.
     */
    public void setVistaModoJuego(VistaModoJuego vistaModoJuego) {
        this.vistaModoJuego = vistaModoJuego;
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
