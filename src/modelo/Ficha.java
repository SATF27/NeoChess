package modelo;

public class Ficha {
    private TipoFicha tipo;
    private Color color;
    private char posColumna, posFila;

    public Ficha (TipoFicha tipo, Color color, char posColumna, char posFila) {
        this.tipo = tipo;
        this.color = color;
        this.posColumna = posColumna;
        this.posFila = posFila;
    }

    public TipoFicha getTipo() {
        return tipo;
    }

    public void setTipo(TipoFicha tipo) {
        this.tipo = tipo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public char getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(char posColumna) {
        this.posColumna = posColumna;
    }

    public char getPosFila() {
        return posFila;
    }

    public void setPosFila(char posFila) {
        this.posFila = posFila;
    }
}
