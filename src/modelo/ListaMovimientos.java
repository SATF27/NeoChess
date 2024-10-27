package modelo;

import java.util.ArrayList;

public class ListaMovimientos {
    ArrayList<String> movimientos;
    int cantidadMovimientos;

    public ListaMovimientos() {
        movimientos = new ArrayList<>();
        cantidadMovimientos = 0;
    }

    // Añade un solo movimiento
    public void agregarMovimiento(String movimiento) {
        movimientos.add(movimiento);
        cantidadMovimientos++;
    }

    // Lee una lista de movimientos y los añade
    public void agregarMovimientos(String movimientos) {
        boolean soporte = true;
        String movimiento = "";
        for (int indice = 0; indice < movimientos.length(); indice++) {
            char c = movimientos.charAt(indice);
            if (c == '.') {
                indice++;
                while (true) {
                    movimiento += movimientos.charAt(indice);
                    indice++;
                    if (movimientos.charAt(indice) == ' ') {
                        agregarMovimiento(movimiento);
                        break;
                    }
                }
                indice++;
                movimiento = "";
                while (true) {
                    movimiento += movimientos.charAt(indice);
                    indice++;
                    if (movimientos.charAt(indice) == ' ') {
                        agregarMovimiento(movimiento);
                        break;
                    }
                }
                movimiento = "";
            }
        }
    }

    public String getMovimiento(int i) {
        return movimientos.get(i);
    }

    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public void mostrarMovimientos() {
        for (String movimiento : movimientos) {
            System.out.print(movimiento + " ");
        }
        System.out.println();
    }
}
