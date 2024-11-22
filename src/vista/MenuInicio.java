package vista;

import controlador.ControladorLector;
import controlador.ControladorMenuInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Clase que maneja el menu de inicio del programa.
 */
public class MenuInicio extends JPanel{
    private int fondoEscogido = 0;
    private ImageIcon fondo;
    private ImageIcon tituloJuego = new ImageIcon(getClass().getResource("/imagenes/tituloJuego.png"));
    private JButton butonJugar = new JButton("JUGAR");
    private JButton butonVisor = new JButton("VISOR");
    private JButton butonSalir = new JButton("SALIR");
    private ControladorMenuInicio controladorMenuInicio;

    public MenuInicio(ControladorMenuInicio controladorMenuInicio) {
        this.controladorMenuInicio = controladorMenuInicio;
        setPreferredSize(new Dimension(840, 560));
        generarFondo();
        cargarFondo();
        setLayout(null);
        butonJugar.setBounds(148, 560/2+10, 120,40);
        butonJugar.setBackground(Color.BLACK);
        butonJugar.setForeground(Color.WHITE);
        butonJugar.setFont(new Font("Arial", Font.BOLD, 20));
        butonJugar.setBorderPainted(false);
        butonJugar.setContentAreaFilled(false);
        butonJugar.setFocusable(false);
        butonJugar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
               controladorMenuInicio.actionPerformed(e);
               requestFocusInWindow();
          }
        });

        butonVisor.setBounds(148, 560/2 +80, 120,40);
        butonVisor.setBackground(Color.black);
        butonVisor.setForeground(Color.white);
        butonVisor.setFont(new Font("Arial", Font.BOLD, 20));
        butonVisor.setBorderPainted(false);
        butonVisor.setContentAreaFilled(false);
        butonVisor.setFocusable(false);
        butonVisor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
               controladorMenuInicio.actionPerformed(e);
               requestFocusInWindow();
            }
        });

        butonSalir.setBounds(148, 560/2 +150, 120,40);
        butonSalir.setBackground(Color.black);
        butonSalir.setForeground(Color.white);
        butonSalir.setFont(new Font("Arial", Font.BOLD, 20));
        butonSalir.setBorderPainted(false);
        butonSalir.setContentAreaFilled(false);
        butonSalir.setFocusable(false);
        butonSalir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
               controladorMenuInicio.actionPerformed(e);
               requestFocusInWindow();
            }
        });

        add(butonJugar);
        add(butonVisor);
        add(butonSalir);
    }

    public void generarFondo(){
        int contador= fondoEscogido + 1;
        this.fondoEscogido = contador;
    }

    public void cargarFondo(){
        if(fondoEscogido >= 0 && fondoEscogido <= 8){
            fondo = new ImageIcon(getClass().getResource("/imagenes/fondoMenu1.gif"));
        } else {
            fondo = new ImageIcon(getClass().getResource("/imagenes/fondoMenu2.gif"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.setColor(Color.DARK_GRAY.darker().darker());
            g.fillRect(0,0,getWidth()/2,getHeight());
            g.drawImage(fondo.getImage(), getWidth()/2, 0, this.getWidth()/2, this.getHeight(), this);
            g.drawImage(tituloJuego.getImage(),15, 60, 400,150,this);
        }
    }

    /**
     * Get del boton que lleva al modo lector.
     * @return Retorna el boton que lleva al modo lector.
     */
    public JButton getButonVisor() {
        return butonVisor;
    }

    /**
     * Get del boton que lleva al modo juego.
     * @return Retorna el boton que lleva al modo juego.
     */
    public JButton getButonJugar() {
        return butonJugar;
    }

    /**
     * Get del boton que cierra el programa.
     * @return Retorna el boton que cierra el programa.
     */
    public JButton getButonSalir() {
        return butonSalir;
    }
}
