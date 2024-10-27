package vista;

import controlador.Controlador;
import modelo.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FramePrograma extends JFrame {
    private PanelTablero panelTablero;
    private Controlador controlador;

    public FramePrograma(PanelTablero panelTablero, Controlador controlador) {
        super("PGNChessReader");
        Image iconoPrograma = new ImageIcon(getClass().getResource("/imagenes/IconoPrograma.png")).getImage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(iconoPrograma);
        this.panelTablero = panelTablero;
        this.controlador = controlador;
        add(panelTablero);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                controlador.keyListener(e);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
}
