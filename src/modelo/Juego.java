package modelo;

import java.util.ArrayList;

/**
 * Clase que maneja la logica de un juego de ajedrez;
 * como los movimientos, los turnos, las validaciones de los
 * movimientos, etc.
 */
public class Juego {
    private Tablero tablero;
    private ListaMovimientos movimientos;
    private boolean turnoNegro;
    private boolean turnoBlanco;
    private String ultimoMovimiento = "";
    private ArrayList<Ficha> fichasTomadas = new ArrayList<>();

    /**
     * Posiciones del arreglo en las que se ha realizado un movimiento.
     * Las posiciones anteriores representan desde donde viene la ficha
     * y las posiciones actuales representan a donde fue movida la ficha.
     */
    private int posIMovimientoAnterior = -1, posJMovimientoAnterior = -1,
            posIMovimientoActual = -1, posJMovimientoActual = -1;

    /**
     * Si el rey esta en jaque estas posiciones buscan la posicion del rey y las guardan.
     * <p>
     * Esto se usa para poder pasar estos datos a la interfaz del tablero y que se muestre un cuadro
     * rojo que representa al rey en jaque.
     */
    private int posIReyJaque = -1, posJReyJaque = -1;

    /**
     * Constructor de la clase.
     *
     * @param tablero     Referencia del objeto que contiene la informacion del tablero.
     * @param movimientos Referencia del objeto que contiene la lista de movimientos de una partida.
     */
    public Juego(Tablero tablero, ListaMovimientos movimientos) {
        this.tablero = tablero;
        this.movimientos = movimientos;
        turnoNegro = false;
        turnoBlanco = true;
    }

    /**
     * Cambia el turno en el cual el juego esta.
     */
    public void cambiarTurno() {
        if (turnoBlanco) {
            turnoNegro = true;
            turnoBlanco = false;
        } else {
            turnoBlanco = true;
            turnoNegro = false;
        }
    }

    /**
     * Interpreta un movimiento escrito en notacion de ajedrez
     * y llama al metodo que se encarga de realizar el movimiento
     * de la ficha o las fichas que corresponda.
     *
     * @param movimiento Movimento escrito en notacion de ajedrez.
     * @return Retorna false si el movimiento es incorrecto y true si no lo es.
     */
    public boolean moverFicha(String movimiento) {
        char primerCaracter = movimiento.charAt(0);
        if (ultimoMovimiento.contains("#")) {
            return false;
        }
        if (!ultimoMovimiento.isEmpty()) {
            if (ultimoMovimiento.contains("+")) {
                if (!movimiento.contains("K") && !movimiento.contains("x" +
                        String.valueOf(tablero.obtenerCharColumna(posJMovimientoActual)) +
                        String.valueOf(tablero.obtenerCharFila(posIMovimientoActual)))) {
                    return false;
                }
            }
        }
        if (turnoBlanco) {
            if (Character.isUpperCase(primerCaracter)) {
                if (movimiento.equals("O-O")) {
                    enroqueCorto(ColorFicha.BLANCO);
                } else if (movimiento.equals("O-O-O")) {
                    enroqueLargo(ColorFicha.BLANCO);
                } else if (primerCaracter == 'N') {
                    if (!moverCaballo(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                } else if (primerCaracter == 'B') {
                    if (!moverAlfil(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                } else if (primerCaracter == 'Q') {
                    if (!moverReina(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                } else if (primerCaracter == 'R') {
                    if (!moverTorre(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                } else if (primerCaracter == 'K') {
                    if (!moverRey(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }

                }
            }
            // Si la primera letra del movimiento no es mayuscula entonces lo que se movio es un peon
            else {
                if (movimiento.contains("=")) {
                    if (!coronacion(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                } else {
                    if (!moverPeon(movimiento, ColorFicha.BLANCO)) {
                        return false;
                    }
                }
            }
            cambiarTurno();
        } else if (turnoNegro) {
            if (Character.isUpperCase(primerCaracter)) {
                if (movimiento.equals("O-O")) {
                    enroqueCorto(ColorFicha.NEGRO);
                } else if (movimiento.equals("O-O-O")) {
                    enroqueLargo(ColorFicha.NEGRO);
                } else if (primerCaracter == 'N') {
                    if (!moverCaballo(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                } else if (primerCaracter == 'B') {
                    if (!moverAlfil(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                } else if (primerCaracter == 'Q') {
                    if (!moverReina(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                } else if (primerCaracter == 'R') {
                    if (!moverTorre(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                } else if (primerCaracter == 'K') {
                    if (!moverRey(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                }
            }

            // Si la primera letra del movimiento no es mayuscula entonces el movimiento es de un peon
            else {
                if (movimiento.contains("=")) {
                    if (!coronacion(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                } else {
                    if (!moverPeon(movimiento, ColorFicha.NEGRO)) {
                        return false;
                    }
                }
            }
            cambiarTurno();
        }
        if (movimiento.contains("+") || movimiento.contains("#")) {
            Ficha fichaRey;
            if (turnoBlanco) {
                fichaRey = tablero.encontrarRey(ColorFicha.BLANCO);
            } else {
                fichaRey = tablero.encontrarRey(ColorFicha.NEGRO);
            }
            posIReyJaque = tablero.obtenerPosFila(fichaRey.getPosFila());
            posJReyJaque = tablero.obtenerPosColumna(fichaRey.getPosColumna());
        } else {
            // Se reinician las posiciones del rey en jaque en la interfaz.
            // Esto es para que al pasar de movimiento ya no se muestre el cuadro rojo
            // que representa que el rey esta en jaque.
            posIReyJaque = -1;
            posJReyJaque = -1;
        }
        ultimoMovimiento = movimiento;
        return true;
    }

    /**
     * Metodo que se encarga de realizar el movimiento de un peon.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color del peon que se mueve.
     * @return Retorna true si el peon pudo moverse, y false si no.
     */
    public boolean moverPeon(String movimiento, ColorFicha colorFicha) {
        char primerCaracter = movimiento.charAt(0);
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');

        // Si el segundo caracter es un digito quiere decir que el movimiento del peon es simplemente un avance
        if (Character.isDigit(segundoCaracter)) { // Ejemplo: e6
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, segundoCaracter, colorFicha);
            if (fichaPeon == null) {
                return false;
            }
            // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
            if (tablero.getFicha(primerCaracter, segundoCaracter).getTipo() != TipoFicha.NADA) {
                return false;
            }
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
            tablero.setFicha(primerCaracter, segundoCaracter, fichaPeon);
            fichaPeon.setPosColumna(primerCaracter);
            fichaPeon.setPosFila(segundoCaracter);
            posIMovimientoActual = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
        }
        // El movimiento del peon es la toma de una ficha
        else if (segundoCaracter == 'x') { //Ejemplo: dxe6
            char columnaFichaTomada = movimiento.charAt(2); // Columna en donde se encuentra la ficha que fue tomada
            char filaFichaTomada = movimiento.charAt(3); // Fila en donde se encuentra la ficha que fue tomada
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, filaFichaTomada, colorFicha);
            if (fichaPeon == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaFichaTomada, filaFichaTomada).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaFichaTomada, filaFichaTomada));
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            tablero.setFicha(columnaFichaTomada, filaFichaTomada, fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
            tablero.setFicha(columnaFichaTomada, filaFichaTomada, fichaPeon);
            fichaPeon.setPosColumna(columnaFichaTomada);
            fichaPeon.setPosFila(filaFichaTomada);
            posIMovimientoActual = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
        }
        return true;
    }

    /**
     * Metodo que se encarga de realizar un enroque corto.
     *
     * @param colorFicha Color de las piezas del jugador que va a realizar en enroque.
     */
    public void enroqueCorto(ColorFicha colorFicha) {
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        if (colorFicha == ColorFicha.BLANCO) {
            Ficha fichaRey = new Ficha(TipoFicha.REY, ColorFicha.BLANCO, 'g', '1');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, ColorFicha.BLANCO, 'f', '1');
            tablero.setFicha('h', '1', fichaNula);
            tablero.setFicha('e', '1', fichaNula);
            tablero.setFicha('g', '1', fichaRey);
            tablero.setFicha('f', '1', fichaTorre);
            posIMovimientoAnterior = tablero.obtenerPosFila('1');
            posJMovimientoAnterior = tablero.obtenerPosColumna('e');
            posIMovimientoActual = tablero.obtenerPosFila('1');
            posJMovimientoActual = tablero.obtenerPosColumna('h');

        } else {
            Ficha fichaRey = new Ficha(TipoFicha.REY, ColorFicha.NEGRO, 'g', '8');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, ColorFicha.NEGRO, 'f', '8');
            tablero.setFicha('h', '8', fichaNula);
            tablero.setFicha('e', '8', fichaNula);
            tablero.setFicha('g', '8', fichaRey);
            tablero.setFicha('f', '8', fichaTorre);
            posIMovimientoAnterior = tablero.obtenerPosFila('8');
            posJMovimientoAnterior = tablero.obtenerPosColumna('e');
            posIMovimientoActual = tablero.obtenerPosFila('8');
            posJMovimientoActual = tablero.obtenerPosColumna('h');
        }
    }

    /**
     * Metodo que se encarga de realizar un enroque largo.
     *
     * @param colorFicha Color de las piezas del jugador que va a realizar en enroque.
     */
    public void enroqueLargo(ColorFicha colorFicha) {
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        if (colorFicha == ColorFicha.BLANCO) {
            Ficha fichaRey = new Ficha(TipoFicha.REY, ColorFicha.BLANCO, 'c', '1');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, ColorFicha.BLANCO, 'd', '1');
            tablero.setFicha('a', '1', fichaNula);
            tablero.setFicha('e', '1', fichaNula);
            tablero.setFicha('c', '1', fichaRey);
            tablero.setFicha('d', '1', fichaTorre);
            posIMovimientoAnterior = tablero.obtenerPosFila('1');
            posJMovimientoAnterior = tablero.obtenerPosColumna('a');
            posIMovimientoActual = tablero.obtenerPosFila('1');
            posJMovimientoActual = tablero.obtenerPosColumna('e');
        } else {
            Ficha fichaRey = new Ficha(TipoFicha.REY, ColorFicha.NEGRO, 'c', '8');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, ColorFicha.NEGRO, 'd', '8');
            tablero.setFicha('a', '8', fichaNula);
            tablero.setFicha('e', '8', fichaNula);
            tablero.setFicha('c', '8', fichaRey);
            tablero.setFicha('d', '8', fichaTorre);
            posIMovimientoAnterior = tablero.obtenerPosFila('8');
            posJMovimientoAnterior = tablero.obtenerPosColumna('a');
            posIMovimientoActual = tablero.obtenerPosFila('8');
            posJMovimientoActual = tablero.obtenerPosColumna('e');
        }
    }

    /**
     * Metodo que se encarga de realizar el movimiento de un caballo.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color del caballo que se mueve.
     * @return Retorna true si el caballo pudo moverse. Falso si no.
     */
    public boolean moverCaballo(String movimiento, ColorFicha colorFicha) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        Ficha fichaCaballo;
        if (segundoCaracter == 'x') { // Ejemplo: Nxc3
            char columnaMovimiento = movimiento.charAt(2); // caracter que indica a que columna del tablero se mueve el caballo
            char filaMovimiento = movimiento.charAt(3); // caracter que indica a que fila del tablero se mueve el caballo
            fichaCaballo = tablero.encontrarCaballo(columnaMovimiento, filaMovimiento, colorFicha);
            if (fichaCaballo == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
            tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaCaballo.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
            fichaCaballo.setPosColumna(columnaMovimiento);
            fichaCaballo.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaCaballo.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
        } else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') { // Ejemplo: Neb6 || N1b6
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaCaballo = tablero.encontrarCaballo(segundoCaracter, colorFicha);
                if (fichaCaballo == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
            } else if (tercerCaracter == 'x') { // Ejemplo: Nexb6
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaCaballo = tablero.encontrarCaballo(segundoCaracter, colorFicha);
                if (fichaCaballo == null) {
                    return false;
                }
                // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                    return false;
                }
                fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
            } else { // Ejemplo: Nc6
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaCaballo = tablero.encontrarCaballo(columnaMovimiento, filaMovimiento, colorFicha);
                if (fichaCaballo == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaCaballo.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaCaballo.getPosColumna());
            }
        }
        return true;
    }

    /**
     * Metodo que se encarga de realizar el movimiento de un alfil.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color del alfil que se mueve.
     * @return Retorna true si el alfil pudo moverse. False si no.
     */
    public boolean moverAlfil(String movimiento, ColorFicha colorFicha) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        Ficha fichaAlfil;

        if (segundoCaracter == 'x') { // Ejemplo: Bxc3
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaAlfil = tablero.encontrarAlfil(columnaMovimiento, filaMovimiento, colorFicha);
            if (fichaAlfil == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
            tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaAlfil.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
            fichaAlfil.setPosColumna(columnaMovimiento);
            fichaAlfil.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaAlfil.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
        } else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') { // Ejemplo: Bec6 || B1c7
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaAlfil = tablero.encontrarAlfil(segundoCaracter, colorFicha);
                if (fichaAlfil == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
            } else if (tercerCaracter == 'x') { // Ejemplo: Baxc3
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaAlfil = tablero.encontrarAlfil(segundoCaracter, colorFicha);
                if (fichaAlfil == null) {
                    return false;
                }
                // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                    return false;
                }
                fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
            } else { // Ejemplo: Ba2
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaAlfil = tablero.encontrarAlfil(columnaMovimiento, filaMovimiento, colorFicha);
                if (fichaAlfil == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaAlfil.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaAlfil.getPosColumna());
            }
        }
        return true;
    }

    /**
     * Metodo que se encarga de realizar el movimiento de una reina.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color de la reina que se mueve.
     * @return Retorna true si la reina pudo moverse. False si no.
     */
    public boolean moverReina(String movimiento, ColorFicha colorFicha) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        Ficha fichaReina;

        if (segundoCaracter == 'x') { // Ejemplo: Qxe2
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaReina = tablero.encontrarReina(columnaMovimiento, filaMovimiento, colorFicha);
            if (fichaReina == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
            tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaReina.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaReina.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
            fichaReina.setPosColumna(columnaMovimiento);
            fichaReina.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaReina.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaReina.getPosColumna());
        } else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') { // Ejemplo: Q1c2 || Qeh2
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaReina = tablero.encontrarReina(segundoCaracter, colorFicha);
                if (fichaReina == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaReina.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaReina.getPosColumna());
            } else if (tercerCaracter == 'x') { // Ejemplo: Qexb2
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaReina = tablero.encontrarReina(segundoCaracter, colorFicha);
                if (fichaReina == null) {
                    return false;
                }
                // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                    return false;
                }
                fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaReina.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaReina.getPosColumna());
            } else { // Ejemplo: Qa2
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaReina = tablero.encontrarReina(columnaMovimiento, filaMovimiento, colorFicha);
                if (fichaReina == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaReina.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaReina.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaReina.getPosColumna());
            }
        }
        return true;
    }

    /**
     * Metodo que se encarga de realizar el movimiento de una torre.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color de la torre que se mueve.
     * @return Retorna true si la torre pudo moverse. False si no.
     */
    public boolean moverTorre(String movimiento, ColorFicha colorFicha) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        Ficha fichaTorre;

        if (segundoCaracter == 'x') { // Ejemplo: Rxe2
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaTorre = tablero.encontrarTorre(columnaMovimiento, filaMovimiento, colorFicha);
            if (fichaTorre == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
            tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaTorre.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
            fichaTorre.setPosColumna(columnaMovimiento);
            fichaTorre.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaTorre.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
        } else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') { // Ejemplo: Rha6 || R1f5
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaTorre = tablero.encontrarTorre(segundoCaracter, colorFicha);
                if (fichaTorre == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
            } else if (tercerCaracter == 'x') { // Ejemplo: Rgxd5
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaTorre = tablero.encontrarTorre(segundoCaracter, colorFicha);
                if (fichaTorre == null) {
                    return false;
                }
                // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                    return false;
                }
                fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
            } else { // Ejemplo: Ra1
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaTorre = tablero.encontrarTorre(columnaMovimiento, filaMovimiento, colorFicha);
                if (fichaTorre == null) {
                    return false;
                }
                // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
                if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                    return false;
                }
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                posIMovimientoAnterior = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoAnterior = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
                posIMovimientoActual = tablero.obtenerPosFila(fichaTorre.getPosFila());
                posJMovimientoActual = tablero.obtenerPosColumna(fichaTorre.getPosColumna());
            }
        }
        return true;
    }

    /**
     * Metodo que se encarga de realizar el movimiento del rey.
     *
     * @param movimiento Movimiento ingresado en notacion de ajedrez.
     * @param colorFicha Color del rey que se mueve.
     * @return Retorna true si el rey pudo moverse. False si no.
     */
    public boolean moverRey(String movimiento, ColorFicha colorFicha) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        Ficha fichaRey;

        if (segundoCaracter == 'x') { //Ejemplo: Kxf2
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaRey = tablero.encontrarRey(colorFicha);
            if (fichaRey == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaMovimiento, filaMovimiento));
            tablero.setFicha(fichaRey.getPosColumna(), fichaRey.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaRey.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaRey.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaRey);
            fichaRey.setPosColumna(columnaMovimiento);
            fichaRey.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaRey.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaRey.getPosColumna());
        } else { // Ejemplo Ka2
            char columnaMovimiento = segundoCaracter;
            char filaMovimiento = movimiento.charAt(2);
            fichaRey = tablero.encontrarRey(colorFicha);
            if (fichaRey == null) {
                return false;
            }
            // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
            if (tablero.getFicha(columnaMovimiento, filaMovimiento).getTipo() != TipoFicha.NADA) {
                return false;
            }
            tablero.setFicha(fichaRey.getPosColumna(), fichaRey.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaRey.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaRey.getPosColumna());
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaRey);
            fichaRey.setPosColumna(columnaMovimiento);
            fichaRey.setPosFila(filaMovimiento);
            posIMovimientoActual = tablero.obtenerPosFila(fichaRey.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaRey.getPosColumna());
        }
        return true;
    }

    /**
     * Metodo que contiene la logica de la coronacion de un pen
     * en el tablero.
     *
     * @param movimiento Movimiento escrito en notacion de ajedrez.
     * @param colorFicha Color del peon que se coronara.
     * @return True si el peon se pudo coronar. False si no.
     */
    public boolean coronacion(String movimiento, ColorFicha colorFicha) {
        char primerCaracter = movimiento.charAt(0);
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
        char caracterFichaCoronada = ' ';
        for (int i = 0; i < movimiento.length(); i++) {
            if (movimiento.charAt(i) == '=') {
                caracterFichaCoronada = movimiento.charAt(i + 1);
            }
        }
        // Si el segundo caracter es un digito quiere decir que el movimiento del peon es simplemente un avance
        // hacia la coronacion
        if (Character.isDigit(segundoCaracter)) {
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, segundoCaracter, colorFicha);
            if (fichaPeon == null) {
                return false;
            }
            // En el movimiento indicado hay una pieza la cual no fue indicada que deba ser tomada.
            if (tablero.getFicha(primerCaracter, segundoCaracter).getTipo() != TipoFicha.NADA) {
                return false;
            }
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaPeon.getPosColumna());

            if (caracterFichaCoronada == 'N') {
                fichaPeon = new Ficha(TipoFicha.CABALLO, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'B') {
                fichaPeon = new Ficha(TipoFicha.ALFIL, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'R') {
                fichaPeon = new Ficha(TipoFicha.TORRE, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'Q') {
                fichaPeon = new Ficha(TipoFicha.REINA, colorFicha, primerCaracter, segundoCaracter);
            }
            tablero.setFicha(primerCaracter, segundoCaracter, fichaPeon);
            fichaPeon.setPosColumna(primerCaracter);
            fichaPeon.setPosFila(segundoCaracter);
            posIMovimientoActual = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
        }
        // El movimiento del peon es la toma de una ficha
        else if (segundoCaracter == 'x') {
            char columnaFichaTomada = movimiento.charAt(2); // Columna en donde se encuentra la ficha que fue tomada
            char filaFichaTomada = movimiento.charAt(3); // Fila en donde se encuentra la ficha que fue tomada
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, filaFichaTomada, colorFicha);
            if (fichaPeon == null) {
                return false;
            }
            // Se indico que se va a tomar una pieza en una posicion en la que no hay ninguna
            if (tablero.getFicha(columnaFichaTomada, filaFichaTomada).getTipo() == TipoFicha.NADA) {
                return false;
            }
            fichasTomadas.add(tablero.getFicha(columnaFichaTomada, filaFichaTomada));
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            posIMovimientoAnterior = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoAnterior = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
            if (caracterFichaCoronada == 'N') {
                fichaPeon = new Ficha(TipoFicha.CABALLO, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'B') {
                fichaPeon = new Ficha(TipoFicha.ALFIL, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'R') {
                fichaPeon = new Ficha(TipoFicha.TORRE, colorFicha, primerCaracter, segundoCaracter);
            } else if (caracterFichaCoronada == 'Q') {
                fichaPeon = new Ficha(TipoFicha.REINA, colorFicha, primerCaracter, segundoCaracter);
            }
            tablero.setFicha(columnaFichaTomada, filaFichaTomada, fichaPeon);
            fichaPeon.setPosColumna(columnaFichaTomada);
            fichaPeon.setPosFila(filaFichaTomada);
            posIMovimientoActual = tablero.obtenerPosFila(fichaPeon.getPosFila());
            posJMovimientoActual = tablero.obtenerPosColumna(fichaPeon.getPosColumna());
        }
        return true;
    }

    /**
     * Dada una lista de movimientos, analiza la partida hasta el turno indicado
     * de la lista.
     *
     * @param movimientos Lista de movimientos que se ingresa.
     * @param ultimoTurno Turno hasta el que se analiza la lista de movimientos.
     */
    public void analizarPartida(ListaMovimientos movimientos, int ultimoTurno) {
        tablero.iniciarTablero();
        ultimoMovimiento = "";
        fichasTomadas.clear();
        turnoBlanco = true;
        turnoNegro = false;
        posJMovimientoActual = -1;
        posJMovimientoAnterior = -1;
        posIMovimientoActual = -1;
        posIMovimientoAnterior = -1;
        if (ultimoTurno == 0) {
            return;
        }
        for (int i = 0; i < ultimoTurno; i++) {
            if (!moverFicha(movimientos.getMovimiento(i))) {
                System.out.println("La partida no se pudo analizar correctamente.");
                System.out.println("Hay uno o mas movimientos erroneos en la partida.");
            }
        }
    }

    /**
     * Reinicia un juego de ajedrez y devuelve los datos que maneja
     * la clase a su estado inicial.
     */
    public void reiniciarPartida() {
        turnoNegro = false;
        turnoBlanco = true;
        ultimoMovimiento = "";
        fichasTomadas.clear();
        tablero.iniciarTablero();
    }

    /**
     * Devuelve el tablero de ajedrez con el que trabaja la clase.
     * @return Retorna el arreglo de fichas que representa al tablero de ajedrez.
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Devuelve la posicion en la fila inicial del ultimo movimiento realizado en el juego.
     * @return Retorna la posicion en la fila del arreglo de donde fue parte el ultimo movimiento.
     */
    public int getPosIMovimientoAnterior() {
        return posIMovimientoAnterior;
    }

    /**
     * Devuelve la posicion en la columna inicial del ultimo movimiento realizado en el juego.
     * @return Retorna la posicion en la columna del arreglo de donde fue parte el ultimo movimiento.
     */
    public int getPosJMovimientoAnterior() {
        return posJMovimientoAnterior;
    }

    /**
     * Devuelve la posicion en la fila final del ultimo movimiento realizado en el juego.
     * @return Retorna la posicion en la fila del arreglo de donde hacia donde se dirigio el ultimo movimiento.
     */
    public int getPosIMovimientoActual() {
        return posIMovimientoActual;
    }

    /**
     * Devuelve la posicion en la columna final del ultimo movimiento realizado en el juego.
     * @return Retorna la posicion en la columna del arreglo de donde hacia donde se dirigio el ultimo movimiento.
     */
    public int getPosJMovimientoActual() {
        return posJMovimientoActual;
    }

    /**
     * Devuelve la posicion en la fila en la que se encuentra un rey en jaque (cuando lo hay).
     * @return Retorna la posicion en la fila del arreglo en la que se encuentra un rey en jaque.
     */
    public int getPosIReyJaque() {
        return posIReyJaque;
    }

    /**
     * Devuelve la posicion en la columna en la que se encuentra un rey en jaque (cuando lo hay).
     * @return Retorna la posicion en la columna del arreglo en la que se encuentra un rey en jaque.
     */
    public int getPosJReyJaque() {
        return posJReyJaque;
    }

    /**
     * Devuelve la coleccion de fichas tomadas en una partida (util para mostrarlas en la vista del modo juego)
     * @return Retorna el arrayList que contiene las fichas tomadas en una partida.
     */
    public ArrayList<Ficha> getFichasTomadas() {
        return fichasTomadas;
    }

    /**
     * Devuelve un booleano que indica si es el turno del blanco en la partida.
     * @return Retorna el booleano que indica si es el turno del blanco en la partida.
     */
    public boolean isTurnoBlanco() {
        return turnoBlanco;
    }
}