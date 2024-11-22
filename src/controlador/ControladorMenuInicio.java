package controlador;

import vista.FramePrograma;

import java.awt.event.ActionEvent;

/**
 * Controlador que maneja las interacciones entre el menu de inicio y el frame del programa.
 */
public class ControladorMenuInicio {
    private FramePrograma framePrograma;

    /**
     * Este metodo se encarga de manejar la entrada de eventos por botones en el programa.
     * Aunque su nombre sea el mismo, en realidad no es el metodo actionPerformed de la
     * interface ActionListener.
     * <p>
     * Este metodo esta creado para recibir la entrada de eventos directamente de manera externa
     * manualmente. Se le coloco el mismo nombre que el de la interface ActionListener para
     * hacer mas sencilla la comprension de su funcionamiento.
     *
     * @param e Es el evento que se ingresa.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == framePrograma.getMenuInicio().getButonJugar()) {
            framePrograma.cargarVistaModoJuego();
        }

        if(e.getSource() == framePrograma.getMenuInicio().getButonVisor()){
            framePrograma.cargarVistaModoLector();
        }

        if(e.getSource() == framePrograma.getMenuInicio().getButonSalir()){
            System.exit(0);
        }
    }

    public void setFramePrograma(FramePrograma framePrograma) {
        this.framePrograma = framePrograma;
    }
}
