package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que maneja la informacion del frame que se muestra en pantalla.
 */
public class FramePrograma extends JFrame {
    private VistaModoLector vistaModoLector;
    private VistaModoJuego vistaModoJuego;
    private MenuInicio menuInicio;

    /**
     * Constructor de la clase.
     * @param vistaModoLector LayeredPane que maneja la logica para la vista del modo lector del programa.
     * @param vistaModoJuego LayeredPane que maneja la logica para la vista del modo juego del programa.
     * @param menuInicio LayeredPane que maneja la logica de la vista del menu de inicio del programa.
     */
    public FramePrograma(VistaModoLector vistaModoLector, VistaModoJuego vistaModoJuego, MenuInicio menuInicio) {
        super("NeoChess");
        Image iconoPrograma = new ImageIcon(getClass().getResource("/imagenes/IconoPrograma.png")).getImage();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(iconoPrograma);
        this.vistaModoLector = vistaModoLector;
        this.vistaModoJuego = vistaModoJuego;
        this.menuInicio = menuInicio;
        cargarMenuInicio();
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Pasa del menu de inicio al modo lector del programa.
     */
    public void cargarVistaModoLector() {
        getContentPane().removeAll();
        add(vistaModoLector);
        vistaModoLector.requestFocusInWindow();
        revalidate();
        repaint();
    }

    /**
     * Pasa del menu de inicio al modo juego del programa.
     */
    public void cargarVistaModoJuego() {
        getContentPane().removeAll();
        add(vistaModoJuego);
        vistaModoJuego.requestFocusInWindow();
        revalidate();
        repaint();
    }

    /**
     * Pasa de la vista del modo lector o del modo juego al menu de inicio.
     */
    public void cargarMenuInicio() {
        getContentPane().removeAll();
        menuInicio.generarFondo();
        menuInicio.cargarFondo();
        add(menuInicio);
        menuInicio.requestFocusInWindow();
        revalidate();
        repaint();
    }

    /**
     * Devuelve el menu de inicio del frame.
     *
     * Util para manejar las acciones de sus botones.
     * @return Retorna el LayeredPane que maneja la logica de la vista del menu de inicio.
     */
    public MenuInicio getMenuInicio() {
        return menuInicio;
    }
}
