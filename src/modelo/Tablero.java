package modelo;

/**
 * Clase que maneja los datos de un tablero de ajedrez.
 * y la logica para buscar una ficha indicada a partir de la posicion de un movimiento.
 */
public class Tablero {
    private Ficha[][] tablero;
    private boolean rotado = false;

    /**
     * Constructor de la clase.
     *
     * Crea un arreglo del tablero de ajedrez y lo inicializa.
     */
    public Tablero() {
        tablero = new Ficha[8][8];
        iniciarTablero();
    }

    /**
     * Construye la posicion incial del tablero.
     * Dentro tiene dos bucle for que recorren posicion por posicion al
     * tablero y dependiendo de la posicion en la que se encuentra en una iteracion i j
     * coloca la pieza que va en tal posicion.
     *
     * Si el tablero esta rotado, al finalizar de inicializar el tablero llama al metodo que rota
     * el tablero.
     */
    public void iniciarTablero() {
        char posColummna = 'a', posFila = '8';
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Ficha fichaAux = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
                if (i == 0) {
                    fichaAux.setColor(ColorFicha.NEGRO);
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
                    fichaAux.setColor(ColorFicha.NEGRO);
                    fichaAux.setTipo(TipoFicha.PEON);
                    fichaAux.setPosColumna(posColummna);
                    fichaAux.setPosFila(posFila);
                    tablero[i][j] = fichaAux;
                }
                else if (i > 1 && i < 6) {
                    fichaAux.setTipo(TipoFicha.NADA);
                    fichaAux.setColor(ColorFicha.NADA);
                    tablero[i][j] = fichaAux;
                }
                else if (i == 6) {
                    fichaAux.setColor(ColorFicha.BLANCO);
                    fichaAux.setTipo(TipoFicha.PEON);
                    fichaAux.setPosColumna(posColummna);
                    fichaAux.setPosFila(posFila);
                    tablero[i][j] = fichaAux;
                }
                else if (i == 7) {
                    fichaAux.setColor(ColorFicha.BLANCO);
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
        if (rotado) {
            rotarTablero();
            rotado = true;
        }
    }

    /**
     * Convierte el caracter ingresado en notacion de algebra de ajedrez
     * al equivalente de la posicion de la fila del arreglo que se utiliza
     * como representacion del tablero.
     *
     * El retorno cambia dependiendo si el tablero esta rotado o no.
     * @param caracterFila Caracter que representa la fila del tablero en notacion de ajedrez.
     *                     Ejemplo: 3.
     * @return Devuelve el equivalente del caracter en notacion de ajedrez a la posicion de la fila del arreglo.
     *         Retorna 0 en caso tal de un ingreso erroneo.
     */
    public int obtenerPosFila(char caracterFila) {
        if (!rotado) {
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
                case '8':
                    return 0;
                default:
                    return -1;
            }
        }
        else {
            switch (caracterFila) {
                case '1':
                    return 0;
                case '2':
                    return 1;
                case '3':
                    return 2;
                case '4':
                    return 3;
                case '5':
                    return 4;
                case '6':
                    return 5;
                case '7':
                    return 6;
                case '8':
                    return 7;
                default:
                    return -1;
            }
        }
    }

    /**
     * Segun la fila indicada del arreglo que representa al tablero de ajedrez
     * devuelve el equivalente al caracter que representa tal fila en notacion
     * de algebra de ajedrez.
     *
     * El retorno cambia dependiendo si el tablero esta rotado o no.
     * @param i Fila del arreglo del tablero de ajedrez.
     * @return Caracter que representa la fila indicada en notacion de algebra de ajedrez.
     *         Retorna 0 en caso tal de un ingreso erroneo.
     */
    public char obtenerCharFila(int i) {
        if (!rotado) {
            switch (i) {
                case 0:
                    return '8';
                case 1:
                    return '7';
                case 2:
                    return '6';
                case 3:
                    return '5';
                case 4:
                    return '4';
                case 5:
                    return '3';
                case 6:
                    return '2';
                case 7:
                    return '1';
            }
            return '0';
        }
        else {
            switch (i) {
                case 0:
                    return '1';
                case 1:
                    return '2';
                case 2:
                    return '3';
                case 3:
                    return '4';
                case 4:
                    return '5';
                case 5:
                    return '6';
                case 6:
                    return '7';
                case 7:
                    return '8';
            }
            return '0';
        }
    }

    /**
     * Segun la columna indicada del arreglo que representa al tablero de ajedrez
     * devuelve el equivalente al caracter que representa tal columna en notacion
     * de algebra de ajedrez.
     *
     * El retorno cambia dependiendo si el tablero esta rotado o no.
     * @param j Columna del arreglo del tablero de ajedrez.
     * @return Caracter que representa la columna indicada en notacion de algebra de ajedrez.
     *         Retorna x en caso tal de no encontrarla.
     */
    public char obtenerCharColumna(int j) {
        if (!rotado) {
            switch (j) {
                case 0:
                    return 'a';
                case 1:
                    return 'b';
                case 2:
                    return 'c';
                case 3:
                    return 'd';
                case 4:
                    return 'e';
                case 5:
                    return 'f';
                case 6:
                    return 'g';
                case 7:
                    return 'h';
            }
            return 'x';
        }
        else {
            switch (j) {
                case 0:
                    return 'h';
                case 1:
                    return 'g';
                case 2:
                    return 'f';
                case 3:
                    return 'e';
                case 4:
                    return 'd';
                case 5:
                    return 'c';
                case 6:
                    return 'b';
                case 7:
                    return 'a';
            }
            return 'x';
        }
    }

    /**
     * Convierte el caracter ingresado en notacion de algebra de ajedrez al equivalente
     * de la posicion de la columna del arreglo que se utiliza como representacion del tablero.
     *
     * El retorno cambia dependiendo si el tablero esta rotado o no.
     * @param caracterColumna Caracter que representa la columna del tablero en notacion de ajedrez.
     *                        Ejemplo: g
     * @return Devuelve el equivalente del caracter en notacion de ajedrez a la posicion de la columna del arreglo.
     *         Retorna 0 en caso tal de un ingreso erroneo.
     */
    public int obtenerPosColumna(char caracterColumna) {
        if (!rotado) {
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
                    return -1;
            }
        }
        else {
            switch (caracterColumna) {
                case 'a':
                    return 7;
                case 'b':
                    return 6;
                case 'c':
                    return 5;
                case 'd':
                    return 4;
                case 'e':
                    return 3;
                case 'f':
                    return 2;
                case 'g':
                    return 1;
                case 'h':
                    return 0;
                default:
                    return -1;
            }
        }
    }

    /**
     * Obtiene la ficha que se encuentra en la posicion i j del
     * arreglo que representa al tablero de ajedrez.
     *
     * @param i Fila en donde se encuentra la ficha buscada.
     * @param j Columna en donde se encuentra la ficha buscada.
     * @return Retorna la ficha que se encuentra en la posicion indicada del arreglo.
     */
    public Ficha getFicha(int i, int j) {
        return tablero[i][j];
    }

    /**
     * Obtiene la ficha que se encuentra en la posicion del tablero
     * indicada en notacion de ajedrez.
     *
     * @param caracterFila Caracter que representa la fila en donde se encuentra la ficha buscada.
     * @param caracterColumna Caracter que representa la columna en donde se encuentra la ficha buscada.
     * @return Retorna la ficha que se encuentra en la posicion indicada del arreglo.
     */
    public Ficha getFicha(char caracterColumna, char caracterFila) {
        int columna = obtenerPosColumna(caracterColumna), fila = obtenerPosFila(caracterFila);
        return tablero[fila][columna];
    }

    /**
     * Cambia la ficha en el tablero en la posicion indicada por la ficha
     * que fue pasada como argumento del metodo.
     * @param posColumna Columna del tablero en donde se colocara la ficha.
     * @param posFila Fila del tablero en donde se colocara la ficha.
     * @param ficha Ficha que se desea colocar en la posicion indicada.
     */
    public void setFicha(char posColumna, char posFila, Ficha ficha) {
        int i = obtenerPosFila(posFila), j = obtenerPosColumna(posColumna);
        tablero[i][j] = ficha;
    }

    /**
     * Se encarga de buscar al peon que realizara un movimiento indicado.
     * El algoritmo para encontrarlo cambia dependiendo de la rotacion del tablero.
     * @param posColumna Columna en la que se movera el peon.
     * @param colorFicha Color del peon que se movera.
     * @return Retorna el peon que es apto para realizar el movimiento indicado.
     */
    public Ficha encontrarPeon(char posColumna, char posFila, ColorFicha colorFicha) {
        int j = obtenerPosColumna(posColumna), i = obtenerPosFila(posFila);
        if (!rotado) {
            if (colorFicha == ColorFicha.BLANCO) {
                if (tablero[i+1][j].getTipo() == TipoFicha.PEON && tablero[i+1][j].getColor() == ColorFicha.BLANCO) {
                    return tablero[i+1][j];
                }
                else if (tablero[i+1][j].getTipo() != TipoFicha.NADA) {
                    return null;
                }
                if (i + 2 == 6 && tablero[i+2][j].getTipo() == TipoFicha.PEON && tablero[i+2][j].getColor() == ColorFicha.BLANCO) {
                    return tablero[i+2][j];
                }
            }
            else if (colorFicha == ColorFicha.NEGRO) {
                if (tablero[i-1][j].getTipo() == TipoFicha.PEON && tablero[i-1][j].getColor() == ColorFicha.NEGRO) {
                    return tablero[i-1][j];
                }
                else if (tablero[i-1][j].getTipo() != TipoFicha.NADA) {
                    return null;
                }
                if (i - 2 == 1 && tablero[i-2][j].getTipo() == TipoFicha.PEON && tablero[i-2][j].getColor() == ColorFicha.NEGRO) {
                    return tablero[i-2][j];
                }
            }
        }
        else {
            if (colorFicha == ColorFicha.BLANCO) {
                if (tablero[i-1][j].getTipo() == TipoFicha.PEON && tablero[i-1][j].getColor() == ColorFicha.BLANCO) {
                    return tablero[i-1][j];
                }
                else if (tablero[i-1][j].getTipo() != TipoFicha.NADA) {
                    return null;
                }
                if (i - 2 == 1 && tablero[i-2][j].getTipo() == TipoFicha.PEON && tablero[i-2][j].getColor() == ColorFicha.BLANCO) {
                    return tablero[i-2][j];
                }
            }
            else if (colorFicha == ColorFicha.NEGRO) {
                if (tablero[i+1][j].getTipo() == TipoFicha.PEON && tablero[i+1][j].getColor() == ColorFicha.NEGRO) {
                    return tablero[i+1][j];
                }
                else if (tablero[i+1][j].getTipo() != TipoFicha.NADA) {
                    return null;
                }
                if (i + 2 == 6 && tablero[i+2][j].getTipo() == TipoFicha.PEON && tablero[i+2][j].getColor() == ColorFicha.NEGRO) {
                    return tablero[i+2][j];
                }
            }
        }
        return null;
    }

    /**
     * Se encarga de buscar al caballo que realizara un movimiento indicado.
     * @param columnaMovimiento Columna a la que se movera el caballo.
     * @param filaMovimiento Fila a la que se movera el caballo.
     * @param colorFicha Color del caballo que se movera.
     * @return Retorna el caballo que es apto para realizar el movimiento indicado.
     */
    public Ficha encontrarCaballo(char columnaMovimiento, char filaMovimiento, ColorFicha colorFicha) {
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
                if (tablero[i2][j].getTipo() == TipoFicha.CABALLO && tablero[i2][j].getColor() == colorFicha) {
                    return tablero[i2][j];
                }
            }
            columnaPosible = columnaMovimiento;
            filaPosible = filaMovimiento;

        }
        return null;
    }

    /**
     * Se encarga de buscar al caballo que realizara un movimiento indicado conociendo
     * la posicion en la fila o en la columna en donde se encuentra.
     * @param filaOColDondeEsta Caracter que representa la fila o columna en la que se encuentra
     *                          el caballo buscado.
     * @param colorFicha Color del caballo que se movera.
     * @return Retorna el caballo que es apto para realizar el movimiento indicado.
     */
    public Ficha encontrarCaballo(char filaOColDondeEsta, ColorFicha colorFicha) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.CABALLO && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.CABALLO && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    /**
     * Se encarga de buscar al alfil que realizara un movimiento indicado.
     * @param columnaMovimiento Columna a la que se movera el alfil.
     * @param filaMovimiento Fila a la que se movera el alfil.
     * @param colorFicha Color del alfil que se movera.
     * @return Retorna el alfil que es apto para realizar el movimiento indicado.
     */
    public Ficha encontrarAlfil(char columnaMovimiento, char filaMovimiento, ColorFicha colorFicha) {
        int posFila = obtenerPosFila(filaMovimiento), posCol = obtenerPosColumna(columnaMovimiento);

        int y = posCol + 1;
        if (y < 8) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.ALFIL && tablero[x][y].getColor() == colorFicha) {
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

    /**
     * Se encarga de buscar al alfil que realizara un movimiento indicado conociendo
     * la posicion en la fila o en la columna en donde se encuentra.
     * @param filaOColDondeEsta Caracter que representa la fila o columna en la que se encuentra
     *                          el alfil buscado.
     * @param colorFicha Color del alfil que se movera.
     * @return Retorna el alfil que es apto para realizar el movimiento indicado.
     */
    public Ficha encontrarAlfil(char filaOColDondeEsta, ColorFicha colorFicha) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.ALFIL && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.ALFIL && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    /**
     * Se encarga de buscar a la reina que realizara un movimiento indicado.
     * @param columnaMovimiento Columna a la que se movera la reina.
     * @param filaMovimiento Fila a la que se movera la reina.
     * @param colorFicha Color de la reina que se movera.
     * @return Retorna la reina que es apta para realizar el movimiento indicado.
     */
    public Ficha encontrarReina(char columnaMovimiento, char filaMovimiento, ColorFicha colorFicha) {
        int posFila = obtenerPosFila(filaMovimiento), posCol = obtenerPosColumna(columnaMovimiento);
        for (int j = posCol + 1; j < 8; j++) {
            if (tablero[posFila][j].getTipo() == TipoFicha.REINA &&  tablero[posFila][j].getColor() == colorFicha) {
                return tablero[posFila][j];
            }
            else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int j = posCol - 1; j >= 0; j--) {
            if (tablero[posFila][j].getTipo() == TipoFicha.REINA &&  tablero[posFila][j].getColor() == colorFicha) {
                return tablero[posFila][j];
            }
            else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int i = posFila + 1; i < 8; i++) {
            if (tablero[i][posCol].getTipo() == TipoFicha.REINA &&  tablero[i][posCol].getColor() == colorFicha) {
                return tablero[i][posCol];
            }
            else if (tablero[i][posCol].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        for (int i = posFila - 1; i >= 0; i--) {
            if (tablero[i][posCol].getTipo() == TipoFicha.REINA &&  tablero[i][posCol].getColor() == colorFicha) {
                return tablero[i][posCol];
            }
            else if (tablero[i][posCol].getTipo() != TipoFicha.NADA) {
                break;
            }
        }
        int y = posCol + 1;
        if (y < 8) {
            for (int x = posFila + 1; x < 8; x++) {
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == colorFicha) {
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
                if (tablero[x][y].getTipo() == TipoFicha.REINA &&  tablero[x][y].getColor() == colorFicha) {
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

    /**
     * Se encarga de buscar a la reina que realizara un movimiento indicado conociendo
     * la posicion en la fila o en la columna en donde se encuentra.
     * @param filaOColDondeEsta Caracter que representa la fila o columna en la que se encuentra
     *                          la reina buscada.
     * @param colorFicha Color de la reina que se movera.
     * @return Retorna la reina que es apta para realizar el movimiento indicado.
     */
    public Ficha encontrarReina(char filaOColDondeEsta, ColorFicha colorFicha) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.REINA && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.REINA && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    /**
     * Se encarga de buscar a la torre que realizara un movimiento indicado.
     * @param columnaMovimiento Columna a la que se movera la torre.
     * @param filaMovimiento Fila a la que se movera la torre.
     * @param colorFicha Color de la torre que se movera.
     * @return Retorna la reina que es apta para realizar el movimiento indicado.
     */
    public Ficha encontrarTorre(char columnaMovimiento, char filaMovimiento, ColorFicha colorFicha) {
        int posColumna = obtenerPosColumna(columnaMovimiento), posFila = obtenerPosFila(filaMovimiento);
        if (colorFicha == colorFicha.BLANCO) {
            for (int i = posFila + 1; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int i = posFila - 1; i >= 0; i--) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna + 1; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna - 1; j >= 0; j--) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
        }
        else {
            for (int i = posFila + 1; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int i = posFila - 1; i >= 0; i--) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
                else if (tablero[i][posColumna].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna + 1; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
            for (int j = posColumna - 1; j >= 0; j--) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
                else if (tablero[posFila][j].getTipo() != TipoFicha.NADA) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * Se encarga de buscar a la torre que realizara un movimiento indicado conociendo
     * la posicion en la fila o en la columna en donde se encuentra.
     * @param filaOColDondeEsta Caracter que representa la fila o columna en la que se encuentra
     *                          la torre buscada.
     * @param colorFicha Color de la torre que se movera.
     * @return Retorna la torre que es apta para realizar el movimiento indicado.
     */
    public Ficha encontrarTorre(char filaOColDondeEsta, ColorFicha colorFicha) {
        if (Character.isDigit(filaOColDondeEsta)) {
            int posFila = obtenerPosFila(filaOColDondeEsta);
            for (int j = 0; j < 8; j++) {
                if (tablero[posFila][j].getTipo() == TipoFicha.TORRE && tablero[posFila][j].getColor() == colorFicha) {
                    return tablero[posFila][j];
                }
            }
        }
        else {
            int posColumna = obtenerPosColumna(filaOColDondeEsta);
            for (int i = 0; i < 8; i++) {
                if (tablero[i][posColumna].getTipo() == TipoFicha.TORRE && tablero[i][posColumna].getColor() == colorFicha) {
                    return tablero[i][posColumna];
                }
            }
        }
        return null;
    }

    /**
     * Metodo que busca al rey que se va a mover a la posicion indicada en un movimiento.
     * @param colorFicha Color del rey que se busca.
     * @param colMovimiento Columna en la que se va a ejecutar el movimiento indicado.
     * @param filaMovimiento  Fila en la que se va a ejecutar el movimiento indicado.
     * @return Referencia de la ficha del rey que esta en el tablero. Retorna nulo si no encuentra un rey
     *          que pueda moverse a esa posicion.
     */
    public Ficha encontrarRey(ColorFicha colorFicha, char colMovimiento, char filaMovimiento) {
        int posColArray = obtenerPosColumna(colMovimiento), posFilaArray = obtenerPosFila(filaMovimiento);
        if (posColArray > 0 && posColArray < 7 && posFilaArray > 0 && posFilaArray < 7) {
            if (tablero[posFilaArray-1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray-1];
            }
            if (tablero[posFilaArray][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray-1];
            }
            if (tablero[posFilaArray+1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray-1];
            }
            if (tablero[posFilaArray-1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray];
            }
            if (tablero[posFilaArray+1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray];
            }
            if (tablero[posFilaArray-1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray+1];
            }
            if (tablero[posFilaArray][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray+1];
            }
            if (tablero[posFilaArray+1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray+1];
            }
        }
        else if (posColArray > 0 && posColArray < 7 && posFilaArray == 0) {
            if (tablero[posFilaArray][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray-1];
            }
            if (tablero[posFilaArray+1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray-1];
            }
            if (tablero[posFilaArray+1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray];
            }
            if (tablero[posFilaArray][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray+1];
            }
            if (tablero[posFilaArray+1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray+1];
            }
        }
        else if (posColArray > 0 && posColArray < 7 && posFilaArray == 7) {
            if (tablero[posFilaArray-1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray-1];
            }
            if (tablero[posFilaArray][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray-1];
            }
            if (tablero[posFilaArray-1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray];
            }
            if (tablero[posFilaArray-1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray+1];
            }
            if (tablero[posFilaArray][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray+1];
            }
        }
        else if (posFilaArray > 0 && posFilaArray < 7 && posColArray == 0) {
            if (tablero[posFilaArray-1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray];
            }
            if (tablero[posFilaArray+1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray];
            }
            if (tablero[posFilaArray-1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray+1];
            }
            if (tablero[posFilaArray][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray+1];
            }
            if (tablero[posFilaArray+1][posColArray+1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray+1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray+1];
            }
        }
        else if (posFilaArray > 0 && posFilaArray < 7 && posColArray == 7) {
            if (tablero[posFilaArray-1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray-1];
            }
            if (tablero[posFilaArray][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray][posColArray-1];
            }
            if (tablero[posFilaArray+1][posColArray-1].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray-1].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray-1];
            }
            if (tablero[posFilaArray-1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray-1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray-1][posColArray];
            }
            if (tablero[posFilaArray+1][posColArray].getTipo() == TipoFicha.REY && tablero[posFilaArray+1][posColArray].getColor() == colorFicha) {
                return tablero[posFilaArray+1][posColArray];
            }
        }
        return null;
    }

    /**
     * Metodo que busca a un rey en el tablero basandose solo en el color del rey buscado.
     * @param colorFicha Color del rey que se busca.
     * @return Referencia de la ficha del rey que esta en el tablero. Retorna nulo si no encuentra un rey.
     */
    public Ficha encontrarRey(ColorFicha colorFicha) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].getTipo() == TipoFicha.REY && tablero[i][j].getColor() == colorFicha) {
                    return tablero[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Contiene el algoritmo para rotar el tablero de ajedrez.
     */
    public void rotarTablero() {
        Ficha fichaMovimiento1;
        Ficha fichaMovimiento2;
        // Rota el tablero verticalmente
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                fichaMovimiento1 = tablero[i][j];
                char columnaMovimiento1 = obtenerCharColumna(j), filaMovimiento1 = obtenerCharFila(i);
                char columnaMovimiento2 = 'a', filaMovimiento2 = '1';
                fichaMovimiento2 = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
                if (i == 0) {
                    fichaMovimiento2 = tablero[7][j];
                    filaMovimiento2 = obtenerCharFila(7);
                    columnaMovimiento2 = obtenerCharColumna(j);
                }
                else if (i == 1) {
                    fichaMovimiento2 = tablero[6][j];
                    filaMovimiento2 = obtenerCharFila(6);
                    columnaMovimiento2 = obtenerCharColumna(j);
                }
                else if (i == 2) {
                    fichaMovimiento2 = tablero[5][j];
                    filaMovimiento2 = obtenerCharFila(5);
                    columnaMovimiento2 = obtenerCharColumna(j);
                }
                else if (i == 3) {
                    fichaMovimiento2 = tablero[4][j];
                    filaMovimiento2 = obtenerCharFila(4);
                    columnaMovimiento2 = obtenerCharColumna(j);
                }
                fichaMovimiento1.setPosColumna(columnaMovimiento2);
                fichaMovimiento1.setPosFila(filaMovimiento2);
                fichaMovimiento2.setPosColumna(columnaMovimiento1);
                fichaMovimiento2.setPosFila(filaMovimiento1);
                setFicha(columnaMovimiento2, filaMovimiento2, fichaMovimiento1);
                setFicha(columnaMovimiento1, filaMovimiento1, fichaMovimiento2);
            }
        }


        // Rota el tablero horizontalmente
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                fichaMovimiento1 = tablero[i][j];
                char columnaMovimiento1 = obtenerCharColumna(j), filaMovimiento1 = obtenerCharFila(i);
                char columnaMovimiento2 = 'a', filaMovimiento2 = '1';
                fichaMovimiento2 = new Ficha(TipoFicha.NADA, ColorFicha.NADA, '.', '.');
                if (j == 0) {
                    fichaMovimiento2 = tablero[i][7];
                    filaMovimiento2 = obtenerCharFila(i);
                    columnaMovimiento2 = obtenerCharColumna(7);
                }
                else if (j == 1) {
                    fichaMovimiento2 = tablero[i][6];
                    filaMovimiento2 = obtenerCharFila(i);
                    columnaMovimiento2 = obtenerCharColumna(6);
                }
                else if (j == 2) {
                    fichaMovimiento2 = tablero[i][5];
                    filaMovimiento2 = obtenerCharFila(i);
                    columnaMovimiento2 = obtenerCharColumna(5);
                }
                else if (j == 3) {
                    fichaMovimiento2 = tablero[i][4];
                    filaMovimiento2 = obtenerCharFila(i);
                    columnaMovimiento2 = obtenerCharColumna(4);
                }
                fichaMovimiento1.setPosColumna(columnaMovimiento2);
                fichaMovimiento1.setPosFila(filaMovimiento2);
                fichaMovimiento2.setPosColumna(columnaMovimiento1);
                fichaMovimiento2.setPosFila(filaMovimiento1);
                setFicha(columnaMovimiento2, filaMovimiento2, fichaMovimiento1);
                setFicha(columnaMovimiento1, filaMovimiento1, fichaMovimiento2);
            }
        }

        if (!rotado) {
            rotado = true;
        }
        else {
            rotado = false;
        }
    }
}
