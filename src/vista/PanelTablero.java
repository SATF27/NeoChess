package vista;

import modelo.*;
import modelo.Color;
import modelo.TipoFicha;
import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {
    Tablero tablero;
    Image imagenTablero;
    Image imagenPeonBlanco;
    Image imagenPeonNegro;
    Image imagenCaballoBlanco;
    Image imagenCaballoNegro;
    Image imagenAlfilBlanco;
    Image imagenAlfilNegro;
    Image imagenTorreBlanco;
    Image imagenTorreNegro;
    Image imagenReinaBlanco;
    Image imagenReinaNegro;
    Image imagenReyNegro;
    Image imagenReyBlanco;

    public PanelTablero(Tablero tablero) {
        this.tablero = tablero;
        setPreferredSize(new Dimension(560, 560));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/imagenes/Tablero.png"));
        imagenTablero = imageIcon.getImage();
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagenTablero, 0, 0, 560, 560, this);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero.getFicha(i, j).getTipo() == TipoFicha.PEON &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenPeonBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.PEON &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenPeonNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.CABALLO &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenCaballoBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.CABALLO &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenCaballoNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.ALFIL &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenAlfilBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.ALFIL &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenAlfilNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.TORRE &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenTorreBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.TORRE &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenTorreNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REINA &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenReinaBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REINA &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenReinaNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REY &&
                        tablero.getFicha(i, j).getColor() == Color.BLANCO) {
                    g.drawImage(imagenReyBlanco, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.REY &&
                        tablero.getFicha(i, j).getColor() == Color.NEGRO) {
                    g.drawImage(imagenReyNegro, j * 70, i * 70, 70, 70, this);
                }
                else if (tablero.getFicha(i, j).getTipo() == TipoFicha.NADA) {
                    // Nada xd
                }
            }
        }
    }

    public void actualizarPanel() {
        repaint();
    }
}

