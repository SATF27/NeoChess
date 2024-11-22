package utilidades;

import modelo.ListaMovimientos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Clase que maneja la informacion, procesamiento, lectura y carga de archivos PGN en el programa.
 */
public class ArchivoPgn {
    private String contenido;

    /**
     * String que almacena los movimientos en una sola linea sin saltos de linea.
     *
     * Se usa para escanear los movimientos mas facilmente y almacenarlos despues en el
     * arraylist que los contiene individualmente.
     */
    private String movimientos;

    /**
     * String que almacena los movimientos tal cual estan definidos en el archivo pgn.
     * O sea, contiene los saltos de linea del archivo.
     *
     * Se usa para mostrar los movimientos en un textfield en la vista del modo lector del programa.
     */
    private String movimientosConSaltoDeLinea;
    private File archivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    /**
     * Constructor de la clase.
     *
     * Solo inicializa los strings que guardan el contenido de un PGN leido en texto.
     */
    public ArchivoPgn() {
        contenido = "";
        movimientos = "";
        movimientosConSaltoDeLinea = "";
    }

    /**
     * Abre una ventana de seleccion de archivo, y guarda el archivo que has seleccionado
     * en el atributo "archivo" de la clase.
     *
     * Solo permite seleccionar archivos del tipo PGN.
     */
    public void cargarArchivo() {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("*.pgn", "pgn");
        jfc.setFileFilter(fileNameExtensionFilter);
        int seleccion = jfc.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            archivo = jfc.getSelectedFile();
        }
    }

    /**
     * Lee el archivo PGN, interpreta sus datos, y guarda los movimientos en 2 strings.
     * Un string contiene los movimientos tal cual aparecen en el PGN. Dicho string se utiliza
     * para luego tokenizar los movimientos y almacenarlo en un arraylist de movimientos.
     *
     * El otro string contiene los movimientos con saltos de linea. Este string se utiliza para
     * mostrarlo en la interfaz del modo lector del programa.
     */
    public void leerArchivo() {
        try {
            fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea = bufferedReader.readLine();
            movimientos = "";
            movimientosConSaltoDeLinea = "";
            while (linea != null) {
                contenido += linea + "\n";


                char caracter = ' ';
                if (!linea.isEmpty()) {
                    caracter = linea.charAt(0);
                }
                if (caracter != '[') {
                    movimientos += linea + " ";

                    //Mejorar la logica de esto*
                    int index = 0;
                    int caracterFinalDelTurno = 0;
                    int caracterInicialDelTurno = 0;
                    for (int i = 0; i < linea.length(); i++) {
                        char c = linea.toCharArray()[i];
                        if (c == '.') {
                            for (int k = index; k >= 0; k--) {
                                if (linea.charAt(k) == ' ') {
                                    caracterInicialDelTurno = k + 1;
                                    break;
                                }
                                else if (k == 0) {
                                    caracterInicialDelTurno = k;
                                    break;
                                }
                            }
                            int espacios = 0;
                            for (int j = caracterInicialDelTurno; j < linea.length(); j++) {
                                if (linea.charAt(j) == ' ' || j == linea.length() - 1) {
                                    espacios++;
                                    if (j == linea.length() - 1) {
                                        caracterFinalDelTurno = j + 1;
                                        movimientosConSaltoDeLinea += linea.substring(caracterInicialDelTurno, caracterFinalDelTurno) +  "\n";
                                    }
                                    else if (espacios == 2) {
                                        caracterFinalDelTurno = j;
                                        movimientosConSaltoDeLinea += linea.substring(caracterInicialDelTurno, caracterFinalDelTurno) +  "\n";
                                        break;
                                    }
                                }
                            }
                        }
                        index++;
                    }

                }
                linea = bufferedReader.readLine();
            }
            // El algoritmo de interpretar los movimientos y guardarlos siempre detecta primero un salto de linea.
            // Esta linea de codigo remueve ese salto.
            movimientos = movimientos.substring(1);

            // El algoritmo de interpretar los movimientos con salto de linea y guardarlos siempre detecta a lo
            // ultimo un salto de linea. Esta linea de codigo remueve ese salto.
            movimientosConSaltoDeLinea = movimientosConSaltoDeLinea.substring(0, movimientosConSaltoDeLinea.length() - 1);

            // Agrega un espacio entre el punto que esta al lado de un turno y el movimiento.
            // Esto es solo para que a la hora de mostrar los movimientos en la interfaz sea un tanto mas "estetico"
            StringBuilder stringBuilder = new StringBuilder(movimientosConSaltoDeLinea);
            for (int i = 0; i < stringBuilder.length(); i++) {
                if (stringBuilder.charAt(i) == '.') {
                    if (stringBuilder.charAt(i+1) != ' ') {
                        stringBuilder.insert(i+1, ' ');
                    }
                }
            }
            movimientosConSaltoDeLinea = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            System.out.println("Error: archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter del string que guarda los movimientos en una linea completa sin saltos de linea.
     * @return String de movimientos sin saltos de linea.
     */
    public String getMovimientos() {
        return movimientos;
    }

    /**
     * Getter del string que guarda los movimientos con saltos de linea.
     * @return String de movimientos con saltos de linea.
     */
    public String getMovimientosConSaltoDeLinea() { return movimientosConSaltoDeLinea; }

    /**
     * Getter del archivo PGN.
     * @return Retorna el archivo PGN cargado en el programa.
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * Guarda una partida a partir de una lista de movimientos.
     * @param movimientos Lista de movimientos de la cual generar el archivo PGN.
     */
    public void guardarPartida(ListaMovimientos movimientos) {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar partida");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.pgn", "pgn");
        jfc.setFileFilter(filtro);
        jfc.setApproveButtonText("Guardar archivo");
        int seleccion = jfc.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            archivo = jfc.getSelectedFile();
            if (!archivo.getName().endsWith(".pgn")) {
                archivo = new File(archivo.getAbsolutePath() + ".pgn");
            }

            try (FileWriter fileWriter = new FileWriter(archivo)) {
                String movimientosEnFormatoPgn = "";
                int turnoQueGuarda = 1;
                for (int i = 0; i < movimientos.getCantidadMovimientos(); i++) {
                    if (i % 2 == 0) {
                        movimientosEnFormatoPgn += turnoQueGuarda + "." + movimientos.getMovimiento(i) + " ";
                        turnoQueGuarda++;
                    }
                    else {
                        movimientosEnFormatoPgn += movimientos.getMovimiento(i) + " ";
                        if (turnoQueGuarda % 10 == 0) {
                            movimientosEnFormatoPgn += "\n";
                        }
                    }
                }
                fileWriter.write(movimientosEnFormatoPgn);
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
