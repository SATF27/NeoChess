package modelo;

import java.util.ArrayList;

/**
 * Clase que maneja la lista de movimientos de una partida
 * y la logica para manipular y agregar movimientos a la lista.
 */
public class ListaMovimientos {
    ArrayList<String> movimientos;

    public ListaMovimientos() {
        movimientos = new ArrayList<>();
    }

    /**
     * Agrega un movimiento al arraylist de la clase.
     *
     * @param movimiento String del movimiento de ajedrez ingresado.
     */
    public void agregarMovimiento(String movimiento) {
        movimientos.add(movimiento);
    }

    /**
     * Lee una lista de movimientos en formato PGN, la interpreta
     * y agrega cada movimiento al arraylist de la clase.
     *
     * @param movimientos String de movimientos en formato PGN.
     */
    public void agregarMovimientos(String movimientos) {
        // Se quitan los movimientos ya ingresados en el arraylist
        this.movimientos.clear();
        // Agrega un espacio entre el punto que esta al lado de un turno y el movimiento.
        // Esto es solo para que a la hora de mostrar los movimientos en la interfaz sea un tanto mas "estetico"
        StringBuilder stringBuilder = new StringBuilder(movimientos);
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '.') {
                stringBuilder.insert(i + 1, ' ');
            }
        }
        movimientos = stringBuilder.toString();
        String[] arregloMovimientos = movimientos.split(" ");
        for (String movimiento : arregloMovimientos) {
            if (!movimiento.contains(".") && !movimiento.contains("/") && !movimiento.contains("0") && !movimiento.isEmpty() &&
                !movimiento.contains("{") && !movimiento.contains("}")) {
                agregarMovimiento(movimiento);
            }
        }
    }

    /**
     * Devuelve el movimiento almacenado en el indice i del arraylist.
     * @param i Indice del movimiento buscado en el arraylist
     * @return Retorna el movimiento almacenado en el indice i del arraylist.
     */
    public String getMovimiento(int i) {
        return movimientos.get(i);
    }

    /**
     * Devuelve la cantidad de movimientos en la lista.
     * @return Retorna la cantidad de movimientos en la lista.
     */
    public int getCantidadMovimientos() {
        return movimientos.size();
    }

    /**
     * Remueve el ultimo movimiento de la lista de movimientos.
     *
     * Util para quitar movimientos erroneos realizados accidentalmente que el programa no valida
     * y que puedan causar bugs en una partida del modo juego.
     */
    public void eliminarUltimoMovimiento() {
        if (!movimientos.isEmpty()) {
            movimientos.remove(movimientos.size() - 1);
        }
    }

    /**
     * Muestra en la consola los movimientos almacenados en el arraylist.
     *
     * Este metodo se usa principalmente para hacer pruebas en el programa.
     */
    public void mostrarMovimientos() {
        for (String movimiento : movimientos) {
            System.out.print(movimiento + " ");
        }
        System.out.println();
    }

    public void limpiarLista() {
        movimientos.clear();
    }
}
