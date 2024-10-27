package modelo;

public class Tablero {
    private Ficha[][] tablero;


    public Tablero() {
        tablero = new Ficha[8][8];
        iniciarTablero();
    }

    public void iniciarTablero() {
        char posColummna = 'a', posFila = '8';

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Ficha fichaAux = new Ficha(TipoFicha.NADA, Color.NADA, '.', '.');
                if (i == 0) {
                    fichaAux.setColor(Color.NEGRO);
                    if (j == 0 || j == 7) {
                        fichaAux.setTipo(TipoFicha.TORRE);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 1 || j == 6) {
                        fichaAux.setTipo(TipoFicha.CABALLO);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 2 || j == 5) {
                        fichaAux.setTipo(TipoFicha.ALFIL);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 3) {
                        fichaAux.setTipo(TipoFicha.REINA);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 4) {
                        fichaAux.setTipo(TipoFicha.REY);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                }
                else if (i == 1) {
                    fichaAux.setColor(Color.NEGRO);
                    fichaAux.setTipo(TipoFicha.PEON);
                    fichaAux.setPosColumna(posColummna);
                    fichaAux.setPosFila(posFila);
                    tablero[i][j] = fichaAux;
                }
                else if (i > 1 && i < 6) {
                    fichaAux.setTipo(TipoFicha.NADA);
                    fichaAux.setColor(Color.NADA);
                    tablero[i][j] = fichaAux;
                }
                else if (i == 6) {
                    fichaAux.setColor(Color.BLANCO);
                    fichaAux.setTipo(TipoFicha.PEON);
                    fichaAux.setPosColumna(posColummna);
                    fichaAux.setPosFila(posFila);
                    tablero[i][j] = fichaAux;
                }
                else if (i == 7) {
                    fichaAux.setColor(Color.BLANCO);
                    if (j == 0 || j == 7) {
                        fichaAux.setTipo(TipoFicha.TORRE);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 1 || j == 6) {
                        fichaAux.setTipo(TipoFicha.CABALLO);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 2 || j == 5) {
                        fichaAux.setTipo(TipoFicha.ALFIL);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 3) {
                        fichaAux.setTipo(TipoFicha.REINA);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                    else if (j == 4) {
                        fichaAux.setTipo(TipoFicha.REY);
                        fichaAux.setPosColumna(posColummna);
                        fichaAux.setPosFila(posFila);
                        tablero[i][j] = fichaAux;
                    }
                }
                posColummna++;
            }
            posColummna = 'a';
            posFila--;
        }
    }

    // Convierte el caracter ingresado en notacion de algebra de ajedrez al equivalente de la posicion de la fila
    // del arreglo que se utiliza como representacion del tablero
    public int obtenerPosFila(char caracterFila) {
        switch (caracterFila) {
            case '1':
                return 7;
            case '2':
                return 6;
            case '3':
                return 5;
            case '4':
                return 4;
            case '5':
                return 3;
            case '6':
                return 2;
            case '7':
                return 1;
            default:
                return 0;
        }

    }


    // Convierte el caracter ingresado en notacion de algebra de ajedrez al equivalente de la posicion de la columna
    // del arreglo que se utiliza como representacion del tablero
    public int obtenerPosColumna(char caracterColumna) {
        switch (caracterColumna) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return 0;
        }
    }

    // Devuelve la ficha que se encuentra en una posicion del arreglo especificada en la notacion tipica de programacion
    public Ficha getFicha(int i, int j) {
        return tablero[i][j];
    }

    public void setFicha(char posColumna, char posFila, Ficha ficha) {
        int i = obtenerPosFila(posFila), j = obtenerPosColumna(posColumna);
        tablero[i][j] = ficha;
    }

    // Busca el peon que realizara un movimiento indicado
    public Ficha encontrarPeon(char posColumna, Color color) {
        int j = obtenerPosColumna(posColumna);
        if (color == Color.BLANCO) {
            for (int i = 0; i < 8; i++) {
                if (tablero[i][j].getTipo() == TipoFicha.PEON && tablero[i][j].getColor() == Color.BLANCO) {
                    return tablero[i][j];
                }
            }
        }
        else if (color == Color.NEGRO) {
            for (int i = 7; i >= 0; i--) {
                if (tablero[i][j].getTipo() == TipoFicha.PEON && tablero[i][j].getColor() == Color.NEGRO) {
                    return tablero[i][j];
                }
            }
        }
        return null;
    }

    public Ficha encontrarCaballo(char columnaMovimiento, char filaMovimiento, Color color) {
        // Son las variables que se encargan de buscar posicion por posicion los lugares en los que podria
        // estar el caballo que se va a mover
        char columnaPosible = columnaMovimiento, filaPosible = filaMovimiento;
        // Bucle que itera la cantidad de posiciones posibles en las que podria estar el caballo buscado (8 posibles)
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    columnaPosible += 2;
                    filaPosible++;
                    break;
                case 1:
                    columnaPosible++;
                    filaPosible += 2;
                    break;
                case 2:
                    columnaPosible--;
                    filaPosible += 2;
                    break;
                case 3:
                    columnaPosible -= 2;
                    filaPosible++;
                    break;
                case 4:
                    columnaPosible -= 2;
                    filaPosible--;
                    break;
                case 5:
                    columnaPosible--;
                    filaPosible -= 2;
                    break;
                case 6:
                    columnaPosible++;
                    filaPosible -= 2;
                    break;
                case 7:
                    columnaPosible += 2;
                    filaPosible--;
                    break;
                default:
                    break;
            }

            if (columnaPosible >= 'a' && columnaPosible <= 'h' && filaPosible >= '1' && filaPosible <= '8') {
                int i2 = obtenerPosFila(filaPosible), j = obtenerPosColumna(columnaPosible);
                if (tablero[i2][j].getTipo() == TipoFicha.CABALLO && tablero[i2][j].getColor() == color) {
                    return tablero[i2][j];
                }
            }
            columnaPosible = columnaMovimiento;
            filaPosible = filaMovimiento;

        }
        return null;
    }

    public Ficha encontrarCaballo(char filaOColDondeEsta, Color color) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.CABALLO && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.CABALLO && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    public Ficha encontrarAlfil(char columnaMovimiento, char filaMovimiento, Color color) {
        int posFila = obtenerPosFila(filaMovimiento), posCol = obtenerPosColumna(columnaMovimiento);

        int y = posCol + 1;
        if (y < 8) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y < 7) {
                    y++;
                }
            }
        }
        y = posCol + 1;
        if (y < 8) {
            for (int x = posFila - 1; x >= 0; x--) {
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y < 7) {
                    y++;
                }
            }
        }
        y = posCol - 1;
        if (y >= 0) {
            for (int x = posFila - 1; x >= 0; x--) {
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y > 0) {
                    y--;
                }
            }
        }
        y = posCol - 1;
        if (y >= 0) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y > 0) {
                    y--;
                }
            }
        }
        return null;
    }

    public Ficha encontrarAlfil(char filaOColDondeEsta, Color color) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.ALFIL && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.ALFIL && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    public Ficha encontrarReina(char columnaMovimiento, char filaMovimiento, Color color) {
        int posFila = obtenerPosFila(filaMovimiento), posCol = obtenerPosColumna(columnaMovimiento);
        for (int j = posCol + 1; j < 8; j++) {
            if (tablero[posFila][j].getTipo() == TipoFicha.REINA &&  tablero[posFila][j].getColor() == color) {
                return tablero[posFila][j];
            }
            else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int j = posCol - 1; j >= 0; j--) {
            if (tablero[posFila][j].getTipo() == TipoFicha.REINA &&  tablero[posFila][j].getColor() == color) {
                return tablero[posFila][j];
            }
            else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int i = posFila + 1; i < 8; i++) {
            if (tablero[i][posCol].getTipo() == TipoFicha.REINA &&  tablero[i][posCol].getColor() == color) {
                return tablero[i][posCol];
            }
            else if (tablero[i][posCol].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int i = posFila - 1; i >= 0; i--) {
            if (tablero[i][posCol].getTipo() == TipoFicha.REINA &&  tablero[i][posCol].getColor() == color) {
                return tablero[i][posCol];
            }
            else if (tablero[i][posCol].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        int y = posCol + 1;
        if (y < 8) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y < 7) {
                    y++;
                }
            }
        }

        y = posCol + 1;
        if (y < 8) {
            for (int x = posFila - 1; x >= 0; x--) {
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y < 7) {
                    y++;
                }
            }
        }

        y = posCol - 1;
        if (y >= 0) {
            for (int x = posFila - 1; x >= 0; x--) {
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y > 0) {
                    y--;
                }
            }
        }

        y = posCol - 1;
        if (y >= 0) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == color) {
                    return tablero[x][y];
                }
                else if (tablero[x][y].getTipo() != TipoFicha.NADA) {
                    break;
                }
                if (y > 0) {
                    y--;
                }
            }
        }

        return null;
    }

    public Ficha encontrarReina(char filaOColDondeEsta, Color color) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.REINA && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.REINA && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    public Ficha encontrarTorre(char columnaMovimiento, char filaMovimiento, Color color) {
        int posColumna = obtenerPosColumna(columnaMovimiento), posFila = obtenerPosFila(filaMovimiento);
        if (color == color.BLANCO) {
            for (int i = posFila + 1; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int i = posFila - 1; i >= 0; i--) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna + 1; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna - 1; j >= 0; j--) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
        }
        else {
            for (int i = posFila + 1; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int i = posFila - 1; i >= 0; i--) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna + 1; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna - 1; j >= 0; j--) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
        }
        return null;
    }

    public Ficha encontrarTorre(char filaOColDondeEsta, Color color) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == color) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == color) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    public Ficha encontrarRey(Color color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getTipo() == TipoFicha.REY && tablero[i][j].getColor() == color) {
                    return tablero[i][j];
                }
            }
        }
        return null;
    }
}
