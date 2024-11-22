package vista;

import controlador.ControladorLector;
import utilidades.ArchivoPgn;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;

/**
 * Esta clase es un JLayeredPane que maneja la vista que del
 * modo lector de archivos pgn del programa
 */
public class VistaModoLector extends JLayeredPane {
    private PanelTablero panelTablero;
    private JButton botonRetroceder, botonAvanzar, botonInicio, botonFinal;
    private JButton botonCargarArchivo;
    private JButton botonPausa, botonReproducir;
    private JButton botonRotarTablero;
    private ControladorLector controladorLector;
    private ArchivoPgn archivoPgn;
    private JTextField entradaMovimientos;

    /**
     * Textarea que contiene los movimientos del arhivo PGN.
     */
    JTextArea textoMovimientos;

    /**
     * Scrollpane que muestra los movimientos en el programa.
     */
    JScrollPane visorMovimientos;

    /**
     * Constructor de la clase.
     * @param controladorLector Instancia del controlador usada para enviar la entrada de eventos directamente desde la vista.
     * @param archivoPgn Clase que maneja el procesamiento del archivo PGN que se ingrese al programa.
     */
    public VistaModoLector(PanelTablero panelTablero, ControladorLector controladorLector, ArchivoPgn archivoPgn) {
        this.panelTablero = panelTablero;
        botonRetroceder = new JButton();
        botonAvanzar = new JButton();
        botonInicio = new JButton();
        botonFinal = new JButton();
        botonCargarArchivo = new JButton("Cargar archivo");
        botonPausa = new JButton("Pausa");
        botonReproducir = new JButton("Reproducir");
        botonRotarTablero = new JButton("Rotar tablero");
        this.controladorLector = controladorLector;
        this.archivoPgn = archivoPgn;
        textoMovimientos = new JTextArea();
        Border borde = BorderFactory.createLineBorder(new Color(51,51,51), 2);
        Color colorBotones = new Color(37, 37, 37);
        setPreferredSize(new Dimension(840, 560));
        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                controladorLector.keyListener(e);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
        setLayout(null);
        ImageIcon icon;
        Image imagenSoporte;

        entradaMovimientos = new JTextField();
        entradaMovimientos.setBackground(colorBotones);
        entradaMovimientos.setForeground(Color.darkGray);
        entradaMovimientos.setText("Escribe un movimiento");
        entradaMovimientos.setFont(new Font("Arial", Font.PLAIN, 15));
        entradaMovimientos.setBounds(576, 300, 248, 20);
        entradaMovimientos.setBorder(borde);
        entradaMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        entradaMovimientos.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controladorLector.keyListener(e);
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


        // Diseño y dimensiones del visor de movimientos
        textoMovimientos.setEditable(false);
        textoMovimientos.setBackground(colorBotones);
        textoMovimientos.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textoMovimientos.setForeground(Color.white);
        visorMovimientos = new JScrollPane(textoMovimientos);
        visorMovimientos.setBounds(576, 20, 248, 250);
        visorMovimientos.setBorder(null);
        visorMovimientos.getVerticalScrollBar().setBackground(Color.BLACK);
        add(visorMovimientos);

        // Diseño y dimensiones del boton cargar archivo
        botonCargarArchivo.setBounds(576, 350, 248, 20);
        botonCargarArchivo.setBackground(colorBotones);
        botonCargarArchivo.setForeground(Color.white);
        botonCargarArchivo.setBorderPainted(false);
        botonCargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonCargarArchivo);

        // Diseño y dimensiones del boton movimiento del inicio
        botonInicio.setBounds(576, 400, 50, 20);
        botonInicio.setBackground(colorBotones);
        icon = new ImageIcon(getClass().getResource("/imagenes/BotonInicio.png"));
        imagenSoporte = icon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(imagenSoporte);
        botonInicio.setIcon(icon);
        botonInicio.setBorderPainted(false);
        botonInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonInicio);

        // Diseño y dimensiones del boton de retroceder
        botonRetroceder.setBounds(642, 400, 50, 20);
        botonRetroceder.setBackground(colorBotones);
        icon = new ImageIcon(getClass().getResource("/imagenes/BotonRetroceder.png"));
        imagenSoporte = icon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(imagenSoporte);
        botonRetroceder.setIcon(icon);
        botonRetroceder.setBorderPainted(false);
        botonRetroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonRetroceder);

        // Diseño y dimensiones del boton de avanzar
        botonAvanzar.setBounds(708, 400, 50, 20);
        botonAvanzar.setBackground(colorBotones);
        icon = new ImageIcon(getClass().getResource("/imagenes/BotonAvanzar.png"));
        imagenSoporte = icon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(imagenSoporte);
        botonAvanzar.setIcon(icon);
        botonAvanzar.setBorderPainted(false);
        botonAvanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonAvanzar);

        // Diseño y dimensiones del boton del movimiento final
        botonFinal.setBounds(774, 400, 50, 20);
        botonFinal.setBackground(colorBotones);
        icon = new ImageIcon(getClass().getResource("/imagenes/BotonFinal.png"));
        imagenSoporte = icon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(imagenSoporte);
        botonFinal.setIcon(icon);
        botonFinal.setBorderPainted(false);
        botonFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonFinal);

        // Diseño y dimensiones del boton de pausa
        botonPausa.setBounds(576, 450, 116, 20);
        botonPausa.setBackground(colorBotones);
        botonPausa.setForeground(Color.white);
        botonPausa.setBorderPainted(false);
        botonPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonPausa);

        // Diseño y dimensiones del boton de reproducir
        botonReproducir.setBounds(708, 450, 116, 20);
        botonReproducir.setBackground(colorBotones);
        botonReproducir.setForeground(Color.white);
        botonReproducir.setBorderPainted(false);
        botonReproducir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonReproducir);

        // Diseño y dimensiones del boton de rotar tablero
        botonRotarTablero.setBounds(576, 500, 248, 20);
        botonRotarTablero.setBackground(colorBotones);
        botonRotarTablero.setForeground(Color.white);
        botonRotarTablero.setBorderPainted(false);
        botonRotarTablero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLector.actionPerformed(e);
                requestFocusInWindow();
            }
        });
        add(botonRotarTablero);

        add(panelTablero, Integer.valueOf(1));
        panelTablero.setBounds(0, 0, 560, 560);
    }

    /**
     * Get del boton retroceder.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonRetroceder() {
        return botonRetroceder;
    }

    /**
     * Get del boton avanzar.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonAvanzar() {
        return botonAvanzar;
    }

    /**
     * Get del boton de inicio.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonInicio() {
        return botonInicio;
    }

    /**
     * Get del boton final.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonFinal() {
        return botonFinal;
    }

    /**
     * Get del boton de cargar archivo.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonCargarArchivo() {
        return botonCargarArchivo;
    }

    /**
     * Get del boton de pausa.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonPausa() {
        return botonPausa;
    }

    /**
     * Get del boton de reproducir.
     * Se usa para manejar la entrada de eventos de los botones.
     * @return Retorna el boton indicado.
     */
    public JButton getBotonReproducir() {
        return botonReproducir;
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
    }

    /**
     * Agrega el texto que contiene los movimientos con salto de linea del PGN al JTextArea de la vista si es que
     * ya el archivo fue leido.
     */
    public void mostrarMovimientos() {
        if (!archivoPgn.getMovimientosConSaltoDeLinea().isEmpty()) {
            textoMovimientos.setText(archivoPgn.getMovimientosConSaltoDeLinea());
        }
        else {
            System.out.println("No se han cargado movimientos para mostrar en el modo lector");
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
     * Le agrega un efecto de resaltado al texto que identifica al movimiento que se muestra en pantalla.
     * @param turnoMovimiento Turno del movimiento producido.
     */
    public void resaltarMovimiento(int turnoMovimiento) {
        try {
            Highlighter highlighter = textoMovimientos.getHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            highlighter.removeAllHighlights();
            int iteradorEspacios = 0;
            int index = -1;
            int endIndex = -1;

            for (int i = 0; i < textoMovimientos.getText().length(); i++) {
                if (index != -1 && (textoMovimientos.getText().charAt(i) == ' ' || textoMovimientos.getText().charAt(i) == '\n'
                || i == textoMovimientos.getText().length() - 1)) {
                    if (textoMovimientos.getText().charAt(i) == ' ' || textoMovimientos.getText().charAt(i) == '\n') {
                        endIndex = i;
                    }
                    else if (i == textoMovimientos.getText().length() - 1) {
                        endIndex = i + 1;
                    }
                    break;
                }
                else if (textoMovimientos.getText().charAt(i) == ' ' ) {
                    iteradorEspacios++;
                    if  (iteradorEspacios == turnoMovimiento) {
                        index = i + 1;
                    }
                }
            }

            if (index >= 0 && endIndex >= 0) {
                highlighter.addHighlight(index, endIndex, painter);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra un mensaje que indica que un movimiento fue ingresado erroneamente
     * cuando el programa detecta que un movimiento no fue hecho como se deberia.
     */
    public void mostrarMensajeMalMovimiento() {
        JOptionPane.showMessageDialog(null, "¡Movimiento incorrecto!");
    }
}
