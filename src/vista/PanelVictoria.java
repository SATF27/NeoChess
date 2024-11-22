package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que simula ser una pantalla de victoria en el tablero.
 */
public class PanelVictoria extends JPanel {
    private boolean victoriaBlancas, victoriaNegras;

    /**
     * Constructor de la clase.
     * @param victoriaBlancas Booleano que indica si ganaron las blancas.
     * @param victoriaNegras Booleano que indica si ganaron las negras.
     */
    public PanelVictoria(boolean victoriaBlancas, boolean victoriaNegras) {
        this.victoriaBlancas = victoriaBlancas;
        this.victoriaNegras = victoriaNegras;
        setPreferredSize(new Dimension(560, 560));
        setLayout(null);
        setOpaque(false);
    }

    /**
     * Sobreescritura del metodo paintComponent de la clase JPanel.
     *
     * Dibuja un mensaje de victoria y un rectangulo con un color con opacidad baja
     * que sirve de fondo.
     * @param g Objeto que dibuja cosas en el panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color (15, 15, 15, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        if (victoriaBlancas) {
            g.setFont(new Font("Comic sans MS", Font.BOLD, 30));
            g.drawString("Fichas blancas ganan", 135, 290);
        }
        else if (victoriaNegras) {
            g.setFont(new Font("Comic sans MS", Font.BOLD, 30));
            g.drawString("Fichas negras ganan", 135, 290);
        }
    }
}
