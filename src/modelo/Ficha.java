package modelo;

/**
 * Clase que maneja los datos de cada ficha en un tablero de ajedrez.
 *
 * Cuando una posicion del tablero esta vacia, los datos del objeto ficha
 * que se almacena en esa posicion del arreglo del tablero son TipoFicha.NADA
 * y ColorFicha.NADA.
 */
public class Ficha {
    private TipoFicha tipo;
    private ColorFicha colorFicha;
    private char posColumna, posFila;

    /**
     * Constructor de la clase.
     * @param tipo Tipo de ficha creada (peon, rey, reina, torre, caballo o alfil).
     * @param colorFicha Color de la ficha creada (blanco, negro)
     * @param posColumna Columna del tablero en la que se ubica la ficha (escrita en notacion de ajedrez)
     * @param posFila Fila del tablero en la que se ubica la ficha (escrita en notacion de ajedrez)
     */
    public Ficha (TipoFicha tipo, ColorFicha colorFicha, char posColumna, char posFila) {
        this.tipo = tipo;
        this.colorFicha = colorFicha;
        this.posColumna = posColumna;
        this.posFila = posFila;
    }

    /**
     * Devuelve el tipo de la ficha.
     * @return Retorna el tipo de la ficha.
     */
    public TipoFicha getTipo() {
        return tipo;
    }

    /**
     * Cambia el tipo de la ficha.
     * @param tipo Tipo de ficha al cual se cambiara.
     */
    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el color de la ficha.
     * @return retorna el color de la ficha.
     */
    public ColorFicha getColor() {
        return colorFicha;
    }

    /**
     * Cambia el color de la ficha.
     * @param colorFicha Color al cual se cambiara.
     */
    public void setColor(ColorFicha colorFicha) {
        this.colorFicha = colorFicha;
    }

    /**
     * Devuelve la columna en la que se encuentra.
     * @return Retorna el caracter que representa la columna en la que se encuentra la ficha
     *                  (en notacion de ajedrez).
     */
    public char getPosColumna() {
        return posColumna;
    }

    /**
     * Actualiza la columna en la que se encuentra la ficha. (Ejemplo: a->h)
     * @param posColumna Caracter que representa la columna en la que se encuentra la ficha
     *                   (en notacion de ajedrez).
     */
    public void setPosColumna(char posColumna) {
        this.posColumna = posColumna;
    }

    /**
     * Obtiene la fila en la que se encuentra la ficha.
     * @return Retorna el caracter que representa la fila en la que se encuentra la ficha
     *         (en notacion de ajedrez).
     */
    public char getPosFila() {
        return posFila;
    }

    /**
     * Actualiza la fila en la que se encuentra la ficha. (Ejemplo: 1->8)
     * @param posFila Caracter que representa la fila en la que se encuentra la ficha
     *                   (en notacion de ajedrez).
     */
    public void setPosFila(char posFila) {
        this.posFila = posFila;
    }
}
