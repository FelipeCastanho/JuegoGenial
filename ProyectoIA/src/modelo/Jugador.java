package modelo;

import java.util.ArrayList;

public class Jugador {
    int puntosColor[];
    ArrayList<Ficha> fichasActuales;
    String nombre = "";

    public Jugador() {
        puntosColor = new int[6];
        for(int i = 0; i < puntosColor.length; i++) puntosColor[i] = 0;
        fichasActuales = new ArrayList<Ficha>();
    }

    public Jugador(ArrayList<Ficha> fichasActuales) {
        puntosColor = new int[6];
        for(int i = 0; i < puntosColor.length; i++) puntosColor[i] = 0;
        this.fichasActuales = fichasActuales;
    }
    
    public Ficha mover(Ficha ficha1, int posiciones[]){
        ficha1.setFila(posiciones[0]);
        ficha1.setColumna(posiciones[1]);
        Ficha ficha2 = ficha1.getPareja();
        ficha2.setFila(posiciones[2]);
        ficha2.setColumna(posiciones[3]);
        fichasActuales.remove(ficha1);
        return ficha1;
    }

    public void agregarFicha(Ficha fichaNueva){
        if(fichaNueva!= null){
            fichasActuales.add(fichaNueva);
        }
    }

    public void actualizarPuntaje(int color, int puntaje){
        puntosColor[color-1] += puntaje;
        if(puntosColor[color-1] > 18)  puntosColor[color-1] = 18;
    }

    public int[] getPuntosColor() {
        return puntosColor;
    }

    public void setPuntosColor(int[] puntosColor) {
        this.puntosColor = puntosColor;
    }

    public ArrayList<Ficha> getFichasActuales() {
        return fichasActuales;
    }

    public void setFichasActuales(ArrayList<Ficha> fichasActuales) {
        this.fichasActuales = fichasActuales;
    }

    public String getNombre() {
       return nombre;
   }

   public void setNombre(String nombre) {
       this.nombre = nombre;
   }
}
