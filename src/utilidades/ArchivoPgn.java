package utilidades;

import java.io.*;

public class ArchivoPgn {
    private String contenido;
    private String movimientos;
    private File archivo;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public ArchivoPgn() {
        contenido = "";
        movimientos = "";
        archivo = new File("media/partida/partida.pgn");

    }

    public void leerArchivo() {
        try {
            fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea = bufferedReader.readLine();
            while (linea != null) {
                contenido += linea + "\n";
                char caracter = ' ';
                if (!linea.isEmpty()) {
                    caracter = linea.charAt(0);
                }
                if (caracter != '[') {
                    movimientos += linea + " ";
                }
                linea = bufferedReader.readLine();
            }
            movimientos = movimientos.substring(1);
        } catch (FileNotFoundException e) {
            System.out.println("Error: archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMovimientos() {
        return movimientos;
    }
}
