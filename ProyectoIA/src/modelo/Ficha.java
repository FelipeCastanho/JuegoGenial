package modelo;

public class Ficha {
    private int fila;
    private int columna;
    private Ficha pareja;
    private int color; // El número depende del color, por ejemplo: 1 = Amarillo, 2 = Morado, 3 = Azul, 4 = Rojo
                // 5 = Naranja y 6 = Verde

    public Ficha() {
        fila = -1;
        columna = -1;
        pareja = null;
        color = 0;
    }

    public Ficha(int fila, int columna, Ficha pareja, int color) {
        this.fila = fila;
        this.columna = columna;
        this.pareja = pareja;
        this.color = color;
    }
    
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Ficha getPareja() {
        return pareja;
    }

    public void setPareja(Ficha pareja) {
        this.pareja = pareja;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
}
