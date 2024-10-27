package modelo;

import java.util.List;

public class Juego {
    Tablero tablero;
    ListaMovimientos movimientos;
    boolean turnoNegro;
    boolean turnoBlanco;

    public Juego(Tablero tablero, ListaMovimientos movimientos) {
        this.tablero = tablero;
        this.movimientos = movimientos;
        turnoNegro = false;
        turnoBlanco = true;
    }


    /*public Ficha encontrarFicha(String caracterFicha, String posColumna, String posFila) {
        if (turnoBlanco) {

        }
        else if (turnoNegro) {

        }

    }*/

    public void cambiarTurno() {
        if (turnoBlanco) {
            turnoNegro = true;
            turnoBlanco = false;
        }
        else {
            turnoBlanco = true;
            turnoNegro = false;
        }
    }

    public void moverFicha(String movimiento) {
        char primerCaracter = movimiento.charAt(0);
        if (turnoBlanco) {
            if (Character.isUpperCase(primerCaracter)) {
                if (movimiento.equals("O-O")) {
                    enroqueCorto(Color.BLANCO);
                }
                else if (movimiento.equals("O-O-O")) {
                    enroqueLargo(Color.BLANCO);
                }
                else if (primerCaracter == 'N') {
                    moverCaballo(movimiento, Color.BLANCO);
                }
                else if (primerCaracter == 'B') {
                    moverAlfil(movimiento, Color.BLANCO);
                }
                else if (primerCaracter == 'Q') {
                    moverReina(movimiento, Color.BLANCO);
                }
                else if (primerCaracter == 'R') {
                    moverTorre(movimiento, Color.BLANCO);
                }
                else if (primerCaracter == 'K') {
                    moverRey(movimiento, Color.BLANCO);
                }
            }
            // Si la primera letra del movimiento no es mayuscula entonces lo que se movio es un peon
            else {
                if (movimiento.contains("=")) {
                    coronacion(movimiento, Color.BLANCO);
                }
                else {
                    moverPeon(movimiento, Color.BLANCO);
                }
            }
            cambiarTurno();
        }
        else if (turnoNegro) {
            if (Character.isUpperCase(primerCaracter)) {
                if (movimiento.equals("O-O")) {
                    enroqueCorto(Color.NEGRO);
                }
                else if (movimiento.equals("O-O-O")) {
                    enroqueLargo(Color.NEGRO);
                }
                else if (primerCaracter == 'N') {
                    moverCaballo(movimiento, Color.NEGRO);
                }
                else if (primerCaracter == 'B') {
                    moverAlfil(movimiento, Color.NEGRO);
                }
                else if (primerCaracter == 'Q') {
                    moverReina(movimiento, Color.NEGRO);
                }
                else if (primerCaracter == 'R') {
                    moverTorre(movimiento, Color.NEGRO);
                }
                else if (primerCaracter == 'K') {
                    moverRey(movimiento, Color.NEGRO);
                }
            }

            // Si la primera letra del movimiento no es mayuscula entonces el movimiento es de un peon
            else {
                if (movimiento.contains("=")) {
                    coronacion(movimiento, Color.NEGRO);
                }
                else {
                    moverPeon(movimiento, Color.NEGRO);
                }

            }
            movimientos.agregarMovimiento(movimiento);
            cambiarTurno();
        }
    }

    public void moverPeon(String movimiento, Color color) {
        char primerCaracter = movimiento.charAt(0);
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');

        // Si el segundo caracter es un digito quiere decir que el movimiento del peon es simplemente un avance
        if (Character.isDigit(segundoCaracter)) {
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, color);
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            tablero.setFicha(primerCaracter, segundoCaracter, fichaPeon);
            fichaPeon.setPosColumna(primerCaracter);
            fichaPeon.setPosFila(segundoCaracter);
        }
        // El movimiento del peon es la toma de una ficha
        else if (segundoCaracter == 'x') {
            char columnaFichaTomada = movimiento.charAt(2); // Columna en donde se encuentra la ficha que fue tomada
            char filaFichaTomada = movimiento.charAt(3); // Fila en donde se encuentra la ficha que fue tomada
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, color);
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            tablero.setFicha(columnaFichaTomada, filaFichaTomada, fichaPeon);
            fichaPeon.setPosColumna(columnaFichaTomada);
            fichaPeon.setPosFila(filaFichaTomada);
        }
    }

    public void enroqueCorto(Color color) {
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        if (color == Color.BLANCO) {
            Ficha fichaRey = new Ficha(TipoFicha.REY, Color.BLANCO, 'g', '1');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, Color.BLANCO, 'f', '1');
            tablero.setFicha('h', '1', fichaNula);
            tablero.setFicha('e', '1', fichaNula);
            tablero.setFicha('g', '1', fichaRey);
            tablero.setFicha('f', '1', fichaTorre);
        }
        else {
            Ficha fichaRey = new Ficha(TipoFicha.REY, Color.NEGRO, 'g', '8');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, Color.NEGRO, 'f', '8');
            tablero.setFicha('h', '8', fichaNula);
            tablero.setFicha('e', '8', fichaNula);
            tablero.setFicha('g', '8', fichaRey);
            tablero.setFicha('f', '8', fichaTorre);
        }
    }

    public void enroqueLargo(Color color) {
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        if (color == Color.BLANCO) {
            Ficha fichaRey = new Ficha(TipoFicha.REY, Color.BLANCO, 'c', '1');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, Color.BLANCO, 'd', '1');
            tablero.setFicha('a', '1', fichaNula);
            tablero.setFicha('e', '1', fichaNula);
            tablero.setFicha('c', '1', fichaRey);
            tablero.setFicha('d', '1', fichaTorre);
        }
        else {
            Ficha fichaRey = new Ficha(TipoFicha.REY, Color.NEGRO, 'c', '8');
            Ficha fichaTorre = new Ficha(TipoFicha.TORRE, Color.NEGRO, 'd', '8');
            tablero.setFicha('a', '8', fichaNula);
            tablero.setFicha('e', '8', fichaNula);
            tablero.setFicha('c', '8', fichaRey);
            tablero.setFicha('d', '8', fichaTorre);
        }
    }

    public void moverCaballo(String movimiento, Color color) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        Ficha fichaCaballo;
        if (segundoCaracter == 'x') {
            char columnaMovimiento = movimiento.charAt(2); // caracter que indica a que columna del tablero se mueve el caballo
            char filaMovimiento = movimiento.charAt(3); // caracter que indica a que fila del tablero se mueve el caballo
            fichaCaballo = tablero.encontrarCaballo(columnaMovimiento, filaMovimiento, color);
            tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
            fichaCaballo.setPosColumna(columnaMovimiento);
            fichaCaballo.setPosFila(filaMovimiento);
        }
        else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') {
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaCaballo = tablero.encontrarCaballo(segundoCaracter, color);
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
            }
            else if (tercerCaracter == 'x') {
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaCaballo = tablero.encontrarCaballo(segundoCaracter, color);
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
            }
            else {
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaCaballo = tablero.encontrarCaballo(columnaMovimiento, filaMovimiento, color);
                tablero.setFicha(fichaCaballo.getPosColumna(), fichaCaballo.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaCaballo);
                fichaCaballo.setPosColumna(columnaMovimiento);
                fichaCaballo.setPosFila(filaMovimiento);
            }
        }
    }

    public void moverAlfil(String movimiento, Color color) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        Ficha fichaAlfil;

        if (segundoCaracter == 'x') {
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaAlfil = tablero.encontrarAlfil(columnaMovimiento, filaMovimiento, color);
            tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
            fichaAlfil.setPosColumna(columnaMovimiento);
            fichaAlfil.setPosFila(filaMovimiento);
        }
        else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') {
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaAlfil = tablero.encontrarAlfil(segundoCaracter, color);
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
            }
            else if (tercerCaracter == 'x') {
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaAlfil = tablero.encontrarAlfil(segundoCaracter, color);
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
            }
            else {
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaAlfil = tablero.encontrarAlfil(columnaMovimiento, filaMovimiento, color);
                tablero.setFicha(fichaAlfil.getPosColumna(), fichaAlfil.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaAlfil);
                fichaAlfil.setPosColumna(columnaMovimiento);
                fichaAlfil.setPosFila(filaMovimiento);
            }
        }
    }

    public void moverReina(String movimiento, Color color) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        Ficha fichaReina;

        if (segundoCaracter == 'x') {
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaReina = tablero.encontrarReina(columnaMovimiento, filaMovimiento, color);
            tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
            fichaReina.setPosColumna(columnaMovimiento);
            fichaReina.setPosFila(filaMovimiento);
        }
        else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') {
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaReina = tablero.encontrarReina(segundoCaracter, color);
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
            }
            else if (tercerCaracter == 'x') {
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaReina = tablero.encontrarReina(segundoCaracter, color);
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
            }
            else {
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaReina = tablero.encontrarReina(columnaMovimiento, filaMovimiento, color);
                tablero.setFicha(fichaReina.getPosColumna(), fichaReina.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaReina);
                fichaReina.setPosColumna(columnaMovimiento);
                fichaReina.setPosFila(filaMovimiento);
            }
        }
    }

    public void moverTorre(String movimiento, Color color) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        Ficha fichaTorre;

        if (segundoCaracter == 'x') {
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaTorre = tablero.encontrarTorre(columnaMovimiento, filaMovimiento, color);
            tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
            fichaTorre.setPosColumna(columnaMovimiento);
            fichaTorre.setPosFila(filaMovimiento);
        }
        else {
            char tercerCaracter = movimiento.charAt(2);
            if (Character.isLowerCase(tercerCaracter) && tercerCaracter != 'x') {
                char columnaMovimiento = tercerCaracter;
                char filaMovimiento = movimiento.charAt(3);
                fichaTorre = tablero.encontrarTorre(segundoCaracter, color);
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
            }
            else if (tercerCaracter == 'x') {
                char columnaMovimiento = movimiento.charAt(3);
                char filaMovimiento = movimiento.charAt(4);
                fichaTorre = tablero.encontrarTorre(segundoCaracter, color);
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
            }
            else {
                char columnaMovimiento = segundoCaracter;
                char filaMovimiento = movimiento.charAt(2);
                fichaTorre = tablero.encontrarTorre(columnaMovimiento, filaMovimiento, color);
                tablero.setFicha(fichaTorre.getPosColumna(), fichaTorre.getPosFila(), fichaNula);
                tablero.setFicha(columnaMovimiento, filaMovimiento, fichaTorre);
                fichaTorre.setPosColumna(columnaMovimiento);
                fichaTorre.setPosFila(filaMovimiento);
            }
        }
    }

    public void moverRey(String movimiento, Color color) {
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        Ficha fichaRey;

        if (segundoCaracter == 'x') {
            char columnaMovimiento = movimiento.charAt(2);
            char filaMovimiento = movimiento.charAt(3);
            fichaRey = tablero.encontrarRey(color);
            tablero.setFicha(fichaRey.getPosColumna(), fichaRey.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaRey);
            fichaRey.setPosColumna(columnaMovimiento);
            fichaRey.setPosFila(filaMovimiento);
        }
        else {
            char columnaMovimiento = segundoCaracter;
            char filaMovimiento = movimiento.charAt(2);
            fichaRey = tablero.encontrarRey(color);
            tablero.setFicha(fichaRey.getPosColumna(), fichaRey.getPosFila(), fichaNula);
            tablero.setFicha(columnaMovimiento, filaMovimiento, fichaRey);
            fichaRey.setPosColumna(columnaMovimiento);
            fichaRey.setPosFila(filaMovimiento);
        }
    }

    public void coronacion(String movimiento, Color color) {
        char primerCaracter = movimiento.charAt(0);
        char segundoCaracter = movimiento.charAt(1);
        Ficha fichaNula = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
        char caracterFichaCoronada = ' ';
        for (int i = 0; i < movimiento.length(); i++) {
            if (movimiento.charAt(i) == '=') {
                caracterFichaCoronada = movimiento.charAt(i + 1);
            }
        }
        // Si el segundo caracter es un digito quiere decir que el movimiento del peon es simplemente un avance
        // hacia la coronacion
        if (Character.isDigit(segundoCaracter)) {
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, color);
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            if (caracterFichaCoronada == 'N') {
                fichaPeon = new Ficha (TipoFicha.CABALLO, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'B') {
                fichaPeon = new Ficha (TipoFicha.ALFIL, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'R') {
                fichaPeon = new Ficha (TipoFicha.TORRE, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'Q') {
                fichaPeon = new Ficha (TipoFicha.REINA, color, primerCaracter, segundoCaracter);
            }
            tablero.setFicha(primerCaracter, segundoCaracter, fichaPeon);
            fichaPeon.setPosColumna(primerCaracter);
            fichaPeon.setPosFila(segundoCaracter);
        }
        // El movimiento del peon es la toma de una ficha
        else if (segundoCaracter == 'x') {
            char columnaFichaTomada = movimiento.charAt(2); // Columna en donde se encuentra la ficha que fue tomada
            char filaFichaTomada = movimiento.charAt(3); // Fila en donde se encuentra la ficha que fue tomada
            Ficha fichaPeon = tablero.encontrarPeon(primerCaracter, color);
            tablero.setFicha(fichaPeon.getPosColumna(), fichaPeon.getPosFila(), fichaNula);
            if (caracterFichaCoronada == 'N') {
                fichaPeon = new Ficha (TipoFicha.CABALLO, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'B') {
                fichaPeon = new Ficha (TipoFicha.ALFIL, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'R') {
                fichaPeon = new Ficha (TipoFicha.TORRE, color, primerCaracter, segundoCaracter);
            }
            else if (caracterFichaCoronada == 'Q') {
                fichaPeon = new Ficha (TipoFicha.REINA, color, primerCaracter, segundoCaracter);
            }
            tablero.setFicha(columnaFichaTomada, filaFichaTomada, fichaPeon);
            fichaPeon.setPosColumna(columnaFichaTomada);
            fichaPeon.setPosFila(filaFichaTomada);
        }
    }

    public void analizarPartida(ListaMovimientos movimientos, int ultimoTurno) {
        tablero.iniciarTablero();
        turnoBlanco = true;
        turnoNegro = false;
        for (int i = 0; i < ultimoTurno; i++) {
            // System.out.print(movimientos.getMovimiento(i) + " ");
            moverFicha(movimientos.getMovimiento(i));
        }
    }
}
