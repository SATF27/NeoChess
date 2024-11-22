package vista;

import controlador.ControladorJuego;
import modelo.ColorFicha;
import modelo.Ficha;
import modelo.TipoFicha;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Esta clase es un JLayeredPane que maneja la vista que del
 * modo de juego para 2 jugadores del programa
 */
public class VistaModoJuego extends JLayeredPane {
    private PanelTablero panelTablero;
    private JButton botonCargarPartida;
    private JButton botonRotarTablero;
    private JButton botonAnteriorMovimiento;
    private JButton botonGuardarPartida;
    private JButton botonReiniciarPartida;
    private ControladorJuego controladorJuego;
    private JTextField entradaMovimientos;
    private boolean turnoBlancas = true, turnoNegras = false;
    private boolean victoria = false;
    private ArrayList<Ficha> fichasTomadas;
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
    private PanelVictoria panelVictoria;

    /**
     * Constructor de la clase.
     * @param panelTablero Panel que muestra el tablero en pantalla.
     * @param controladorJuego Instancia del controlador usada para enviar la entrada de eventos directamente desde la vista.
     */
    public VistaModoJuego(PanelTablero panelTablero, ControladorJuego controladorJuego) {
        this.panelTablero = panelTablero;
        botonCargarPartida = new JButton("Cargar partida");
        botonRotarTablero = new JButton("Rotar tablero");
        botonAnteriorMovimiento = new JButton("Anterior movimiento");
        botonGuardarPartida = new JButton("Guardar partida");
        botonReiniciarPartida = new JButton("Reiniciar partida");
        this.controladorJuego = controladorJuego;
        Border borde = BorderFactory.createLineBorder(new Color(51,51,51), 2);
        Color colorBotones = new Color(37, 37, 37);
        setPreferredSize(new Dimension(840, 560));
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                controladorJuego.keyListener(e);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
        setLayout(null);

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

        // Diseño y dimensiones del boton guardar partida
        botonGuardarPartida.setBounds(576, 250, 248, 20);
        botonGuardarPartida.setBackground(colorBotones);
        botonGuardarPartida.setForeground(Color.white);
        botonGuardarPartida.setBorderPainted(false);
        botonGuardarPartida.setFocusable(false);
        botonGuardarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorJuego.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonGuardarPartida);

        // Diseño y dimensiones del boton cargar partida
        botonCargarPartida.setBounds(576, 300, 248, 20);
        botonCargarPartida.setBackground(colorBotones);
        botonCargarPartida.setForeground(Color.white);
        botonCargarPartida.setBorderPainted(false);
        botonCargarPartida.setFocusable(false);
        botonCargarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorJuego.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonCargarPartida);

        // Diseño y dimensiones del boton reiniciar partida
        botonReiniciarPartida.setBounds(576, 350, 248, 20);
        botonReiniciarPartida.setBackground(colorBotones);
        botonReiniciarPartida.setForeground(Color.white);
        botonReiniciarPartida.setBorderPainted(false);
        botonReiniciarPartida.setFocusable(false);
        botonReiniciarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorJuego.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonReiniciarPartida);

        // Diseño y dimensiones del boton retroceder movimiento
        botonAnteriorMovimiento.setBounds(576, 400, 248, 20);
        botonAnteriorMovimiento.setBackground(colorBotones);
        botonAnteriorMovimiento.setForeground(Color.white);
        botonAnteriorMovimiento.setBorderPainted(false);
        botonAnteriorMovimiento.setFocusable(false);
        botonAnteriorMovimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorJuego.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonAnteriorMovimiento);

        // Diseño y dimensiones del boton de rotar tablero
        botonRotarTablero.setBounds(576, 450, 248, 20);
        botonRotarTablero.setBackground(colorBotones);
        botonRotarTablero.setForeground(Color.white);
        botonRotarTablero.setBorderPainted(false);
        botonRotarTablero.setFocusable(false);
        botonRotarTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorJuego.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonRotarTablero);

        entradaMovimientos = new JTextField();
        entradaMovimientos.setBackground(colorBotones);
        entradaMovimientos.setForeground(Color.darkGray);
        entradaMovimientos.setText("Escribe un movimiento");
        entradaMovimientos.setFont(new Font("Arial", Font.PLAIN, 15));
        entradaMovimientos.setBounds(576, 500, 248, 20);
        entradaMovimientos.setBorder(borde);
        entradaMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        entradaMovimientos.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controladorJuego.keyListener(e);
                    entradaMovimientos.setText("Escribe un movimiento");
                    entradaMovimientos.setForeground(Color.darkGray);
                    requestFocusInWindow();
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
        entradaMovimientos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                entradaMovimientos.setText("");
                entradaMovimientos.setForeground(Color.white);
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        add(entradaMovimientos);

        add(panelTablero, Integer.valueOf(1));
        panelTablero.setBounds(0, 0, 560, 560);
    }

    /**
     * Get del boton de cargar archivo.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonCargarPartida() {
        return botonCargarPartida;
    }

    /**
     * Get del boton de retroceder movimiento.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonAnteriorMovimiento() {
        return botonAnteriorMovimiento;
    }

    /**
     * Get del boton de rotar tablero.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonRotarTablero() {
        return botonRotarTablero;
    }

    /**
     * Get del boton de guardar partida.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonGuardarPartida() {
        return botonGuardarPartida;
    }

    /**
     * Get del boton de guardar partida.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonReiniciarPartida() {
        return botonReiniciarPartida;
    }

    /**
     * Get del textfield de entrada de movimientos.
     * Se usa para obtener su texto en el controlador.
     * @return Retorna el textfield de entrada de movimientos.
     */
    public JTextField getEntradaMovimientos() {
        return entradaMovimientos;
    }

    /**
     * Sobreescritra del metodo que dibuja graficos en la clase JPanel.
     *
     * Dibuja un rectangulo que sirve como fondo de los botones en esta vista.
     *
     * @param g El objeto que permite dibujar utilizando el metodo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(18, 18, 18));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(37, 37, 37));
        g.fillRect(576, 20, 248, 60);
        if (fichasTomadas != null) {
            int anchoCuadroFichas = 30, altoCuadroFichas = 30;
            int j = 0;
            for (int i = 0; i < fichasTomadas.size(); i++) {
                if (fichasTomadas.get(i).getColor() == ColorFicha.BLANCO) {
                    if (fichasTomadas.get(i).getTipo() == TipoFicha.PEON) {
                        g.drawImage(imagenPeonBlanco, j * anchoCuadroFichas/2 + 580, 20, anchoCuadroFichas, altoCuadroFichas, this);
                    } else if (fichasTomadas.get(i).getTipo() == TipoFicha.CABALLO) {
                        g.drawImage(imagenCaballoBlanco, j * anchoCuadroFichas/2 + 580, 20, anchoCuadroFichas, altoCuadroFichas, this);
                    } else if (fichasTomadas.get(i).getTipo() == TipoFicha.ALFIL) {
                        g.drawImage(imagenAlfilBlanco, j * anchoCuadroFichas/2 + 580, 20, anchoCuadroFichas, altoCuadroFichas, this);
                    } else if (fichasTomadas.get(i).getTipo() == TipoFicha.TORRE) {
                        g.drawImage(imagenTorreBlanco, j * anchoCuadroFichas/2 + 580, 20, anchoCuadroFichas, altoCuadroFichas, this);
                    } else if (fichasTomadas.get(i).getTipo() == TipoFicha.REINA) {
                        g.drawImage(imagenReinaBlanco, j * anchoCuadroFichas/2 + 580, 20, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    j++;
                }
            }
            j = 0;
            for (int i = 0; i < fichasTomadas.size(); i++) {
                if (fichasTomadas.get(i).getColor() == ColorFicha.NEGRO) {
                    if (fichasTomadas.get(i).getTipo() == TipoFicha.PEON) {
                        g.drawImage(imagenPeonNegro, j*anchoCuadroFichas/2 + 580, 50, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    else if (fichasTomadas.get(i).getTipo() == TipoFicha.CABALLO) {
                        g.drawImage(imagenCaballoNegro, j*anchoCuadroFichas/2 + 580, 50, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    else if (fichasTomadas.get(i).getTipo() == TipoFicha.ALFIL) {
                        g.drawImage(imagenAlfilNegro, j*anchoCuadroFichas/2 + 580, 50, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    else if (fichasTomadas.get(i).getTipo() == TipoFicha.TORRE) {
                        g.drawImage(imagenTorreNegro, j*anchoCuadroFichas/2 + 580, 50, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    else if (fichasTomadas.get(i).getTipo() == TipoFicha.REINA) {
                        g.drawImage(imagenReinaNegro, j*anchoCuadroFichas/2 + 580, 50, anchoCuadroFichas, altoCuadroFichas, this);
                    }
                    j++;
                }
            }
        }
        if (turnoBlancas) {
            g.setColor(Color.WHITE);
            g.fillOval(610, 90, 20, 20);
            g.setFont(new Font("Comic sans MS", Font.BOLD, 20));
            g.drawString("Turno blancas", 650, 107);

        }
        else if (turnoNegras) {
            g.setColor(Color.BLACK);
            g.fillOval(610, 90, 20, 20);
            g.setFont(new Font("Comic sans MS", Font.BOLD, 20));
            g.drawString("Turno negras", 650, 107);
        }
    }


    /**
     * Actualiza la vista para que se muestren los cambios realizados.
     */
    public void actualizarVista() {
        repaint();
        panelTablero.repaint();
        revalidate();
    }


    /**
     * Muestra un mensaje que indica que un movimiento fue ingresado erroneamente
     * cuando el programa detecta que un movimiento no fue hecho como se deberia.
     */
    public void mostrarMensajeMalMovimiento() {
        JOptionPane.showMessageDialog(null, "¡Movimiento incorrecto!");
    }

    /**
     * Obtiene un arreglo de fichas tomadas en el juego.
     * @param fichasTomadas Arreglo de fichas tomadas en el juego.
     */
    public void setFichasTomadas(ArrayList<Ficha> fichasTomadas) {
        this.fichasTomadas = fichasTomadas;
    }

    /**
     * Le indica a la vista que en el juego es el turno de las blancas.
     */
    public void turnoBlancas() {
        turnoBlancas = true;
        turnoNegras = false;
    }

    /**
     * Le indica a la vista que en el juego es el turno de las negras.
     */
    public void turnoNegras() {
        turnoNegras = true;
        turnoBlancas = false;
    }

    /**
     * Le indica la vista que algun jugador ha ganado.
     *
     * La vista decide que pantalla de victoria mostrar (para las blancas o para
     * las negras) dependiendo del turno en pantalla.
     */
    public void mostrarPantallaVictoria() {
        if (getParent() != null && getParent().isAncestorOf(panelVictoria)) {
            return;
        }
        if (turnoBlancas) {
            panelVictoria = new PanelVictoria(false, true);
        }
        else {
            panelVictoria = new PanelVictoria(true, false);
        }
        panelVictoria.setBounds(0, 0, 560, 560);
        add(panelVictoria, Integer.valueOf(2));
    }

    /**
     * Remueve la pantalla de victoria en la vista.
     */
    public void removerPantallaVictoria() {
        if (getParent() != null && getParent().isAncestorOf(panelVictoria)) {
            remove(panelVictoria);
            repaint();
            revalidate();
        }
    }
}
