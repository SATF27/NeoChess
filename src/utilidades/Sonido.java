package utilidades;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

/**
 * Clase que contiene la logica del procesamiento de sonidos en el programa.
 */
public class Sonido {
    private Clip clip;

    /**
     * Carga un sonido desde la ruta indicada.
     * @param ruta Ruta en la que se encuentra el sonido que va a ser cargado.
     */
    public void cargarSonido(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reproduce el sonido que haya sido cargado.
     */
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Si el sonido que fue cargado esta detenido, lo reanuda
     * indefinidamente.
     */
    public void reanudar() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Detiene un sonido si esta siendo reproducido.
     */
    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Reproduce en bucle el sonido que haya sido cargado.
     */
    public void reproducirEnBucle() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Reproduce el sonido en bucle
        }
    }
}
