package vista;

import modelo.*;
import modelo.ColorFicha;
import modelo.TipoFicha;
import javax.swing.*;
import java.awt.*;

/**
 * Panel que maneja la lógica para mostrar el tablero de ajedrez
 * y los movimientos en pantalla.
 */
public class PanelTablero extends JPanel {
    private Tablero tablero;
    private Image imagenPeonBlanco;
    private Image imagenPeonNegro;
    private Image imagenCaballoBlanco;
    private Image imagenCaballoNegro;
    private Image imagenAlfilBlanco;
    private Image imagenAlfilNegro;
    private Image imagenTorreBlanco;
    private Image imagenTorreNegro;
    private Image imagenReinaBlanco;
    private Image imagenReinaNegro;
    private Image imagenReyNegro;
    private Image imagenReyBlanco;

    /**
     * Posiciones del arreglo en las que se ha realizado un movimiento.
     * Las posiciones anteriores representan desde donde viene la ficha
     * y las posiciones actuales representan a donde fue movida la ficha.
     */
    private int posIMovimientoAnterior = -1, posJMovimientoAnterior = -1,
            posIMovimientoActual = -1, posJMovimientoActual = -1;
    /**
     * Si el rey esta en jaque estas posiciones guardan la posicion en la que se
     * encuentra el rey en jaque. Luego en el metodo paintComponent pinta un
     * cuadro rojo en la posicion del tablero donde el rey esta en jaque.
     *
     * Si no esta en jaque estas posiciones pasan a reiniciarse al valor -1 con el cual
     * la interfaz no muestra nada.
     */
    private int posIReyJaque = -1, posJReyJaque = -1;

    /**
     * Constructor de la clase
     * @param tablero Arreglo de la clase Ficha que contiene la informacion del tablero de ajedrez.
     */
    public PanelTablero(Tablero tablero) {
        this.tablero = tablero;
        setPreferredSize(new Dimension(560, 560));
        ImageIcon imageIcon;
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/PeonBlanco.png"));
        imagenPeonBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/PeonNegro.png"));
        imagenPeonNegro = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/CaballoBlanco.png"));
        imagenCaballoBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/CaballoNegro.png"));
        imagenCaballoNegro = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/AlfilBlanco.png"));
        imagenAlfilBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/AlfilNegro.png"));
        imagenAlfilNegro = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/TorreBlanco.png"));
        imagenTorreBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/TorreNegro.png"));
        imagenTorreNegro = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/ReinaBlanco.png"));
        imagenReinaBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/ReinaNegro.png"));
        imagenReinaNegro = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/ReyBlanco.png"));
        imagenReyBlanco = imageIcon.getImage();
        imageIcon = new ImageIcon(getClass().getResource("/imagenes/ReyNegro.png"));
        imagenReyNegro = imageIcon.getImage();
        setBackground(java.awt.Color.DARK_GRAY);
    }

    /**
     * Reescritura del metodo paintComponent de la clase JPanel.
     *
     * Dibuja el tablero y las fichas en el panel, ademas de pintar los cuadrados en los que
     * una pieza fue movida.
     *
     * @param g Objeto que permite dibujar en el metodo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Se pintan los cuadros del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j) % 2 == 0) {
                    g.setColor(new java.awt.Color(220, 220, 220));
                }
                else {
                    g.setColor(new java.awt.Color(171, 171, 171));
                }
                g.fillRect(i*70, j*70, 70, 70);
            }
        }

        // Produce el efecto de pieza movida cuando un movimiento es realizado.
        // Dibuja un cuadrado amarillo desde la posicion en que la pieza se ha movido hasta la
        // posicion a la que se movio.
        if (posIMovimientoAnterior != -1 && posJMovimientoAnterior != -1 && posJMovimientoActual != -1 &&
                posIMovimientoActual != -1) {
            g.setColor(new java.awt.Color(255, 226, 19, 150));
            g.fillRect(posJMovimientoAnterior*70, posIMovimientoAnterior*70, 70, 70);
            g.setColor(new java.awt.Color(255, 226, 19, 255));
            g.fillRect(posJMovimientoActual*70, posIMovimientoActual*70, 70, 70);
        }

        if (posIReyJaque != -1 && posJReyJaque != -1) {
            g.setColor(java.awt.Color.red);
            g.fillRect(posJReyJaque*70, posIReyJaque*70, 70, 70);
        }
        // Se dibujan las piezas en el tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero.getFicha(i, j).getTipo() == TipoFicha.PEON &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenPeonBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.PEON &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenPeonNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.CABALLO &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenCaballoBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.CABALLO &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenCaballoNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.ALFIL &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenAlfilBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.ALFIL &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenAlfilNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.TORRE &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenTorreBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.TORRE &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenTorreNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REINA &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenReinaBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REINA &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenReinaNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REY &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.BLANCO) {
                    g.drawImage(imagenReyBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REY &&
                        tablero.getFicha(i, j).getColor() == ColorFicha.NEGRO) {
                    g.drawImage(imagenReyNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.NADA) {
                    // Nada xd
                }
            }
        }
    }

    /**
     * Establece las posiciones en el array en las que se ha producido un movimiento.
     * @param posIMovimientoAnterior Posicion i del array del cual parte el movimiento.
     * @param posJMovimientoAnterior Posicion j del array del cual parte el movimiento.
     * @param posIMovimientoActual Posicion i del array en el cual se situa el movimiento.
     * @param posJMovimientoActual Posicion j del array en el cual se situa el movimiento.
     */
    public void setPosicionesMovimiento(int posIMovimientoAnterior, int posJMovimientoAnterior,
                                        int posIMovimientoActual, int posJMovimientoActual) {
        this.posIMovimientoAnterior = posIMovimientoAnterior;
        this.posJMovimientoAnterior = posJMovimientoAnterior;
        this.posIMovimientoActual = posIMovimientoActual;
        this.posJMovimientoActual = posJMovimientoActual;
    }

    public void setPosicionesReyJaque(int posIReyJaque, int posJReyJaque) {
        this.posJReyJaque = posJReyJaque;
        this.posIReyJaque = posIReyJaque;
    }

    /**
     * Reinicia las posiciones en las cuales se muestra el ultimo movimiento hecho en el tablero
     * a los valores en los cuales el metodo que dibuja el tablero no muestra nada.
     */
    public void reiniciarPosicionesMovimiento() {
        this.posIMovimientoAnterior = -1;
        this.posJMovimientoAnterior = -1;
        this.posIMovimientoActual = -1;
        this.posJMovimientoActual = -1;
    }


    /**
     * Reinicia las posicion en la cual se muestra el cuadro rojo el tablero cuando el rey esta en jaque
     * a los valores en los cuales el metodo que dibuja el tablero no muestra nada en tal posicion.
     */
    public void reiniciarPosicionesReyJaque() {
        this.posIReyJaque = -1; this.posJReyJaque = -1;
    }

    /**
     * Repinta el panel para que se vean los cambios hechos en el.
     */
    public void actualizarPanel() {
        repaint();
    }
}

