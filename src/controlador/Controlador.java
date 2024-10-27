package controlador;

import modelo.*;
import utilidades.ArchivoPgn;
import utilidades.Sonido;
import vista.PanelTablero;

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Controlador {
    private Juego juego;
    private Sonido efectoMover, efectoCaptura, efectoEnroque;
    private PanelTablero panelTablero;
    private ListaMovimientos listaMovimientos;
    private ArchivoPgn archivoPgn;
    private int turnoEnPantalla = 0;

    public Controlador(Juego juego, PanelTablero panelTablero, ListaMovimientos listaMovimientos) {
        this.juego = juego;
        this.panelTablero = panelTablero;
        efectoMover = new Sonido();
        efectoMover.cargarSonido("/sonidos/Mover.wav");
        efectoCaptura = new Sonido();
        efectoCaptura.cargarSonido("/sonidos/Captura.wav");
        efectoEnroque = new Sonido();
        efectoEnroque.cargarSonido("/sonidos/Enroque.wav");
        archivoPgn = new ArchivoPgn();
        archivoPgn.leerArchivo();
        this.listaMovimientos = listaMovimientos;
        listaMovimientos.agregarMovimientos(archivoPgn.getMovimientos());
    }

    public void hacerMovimiento() {
        String movimiento;
        System.out.print("Ingresa un movimiento: ");
        Scanner sc = new Scanner(System.in);
        movimiento = sc.nextLine();
        juego.moverFicha(movimiento);
        if (movimiento.contains("x")) {
            efectoCaptura.reproducir();
        }
        else if (movimiento.equals("O-O") || movimiento.equals("O-O-O")) {
            efectoEnroque.reproducir();
        }
        else {
            efectoMover.reproducir();
        }

        panelTablero.actualizarPanel();
    }

    public void keyListener(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (turnoEnPantalla < listaMovimientos.getCantidadMovimientos()) {
                turnoEnPantalla++;
                    if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("x")) {
                    efectoCaptura.reproducir();
                }
                else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O") || listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O-O")) {
                    efectoEnroque.reproducir();
                }
                else {
                    efectoMover.reproducir();
                }
                juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (turnoEnPantalla > 0) {
                turnoEnPantalla--;
                if (turnoEnPantalla > 0) {
                    if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).contains("x")) {
                        efectoCaptura.reproducir();
                    }
                    else if (listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O") || listaMovimientos.getMovimiento(turnoEnPantalla - 1).equals("O-O-O")) {
                        efectoEnroque.reproducir();
                    }
                    else {
                        efectoMover.reproducir();
                    }
                }
                juego.analizarPartida(listaMovimientos, turnoEnPantalla);
            }
        }
        panelTablero.actualizarPanel();
    }
}
