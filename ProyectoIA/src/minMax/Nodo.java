package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class Nodo {
    int profundidad;
    Nodo padre;
    ArrayList<Nodo> hijos;
    double puntajeUtilidad;
    int tipoNodo; //Min o Max
    int ficha;
    int fila;
    int columna;
    int fila2;
    int columna2;
    int fichaJ;
    int filaJ;
    int columnaJ;
    int fila2J;
    int columna2J;
    int puntosColorIA[];
    int puntosColorJ[];
    boolean revisado;
    int contador = 0;
    
    
    public Nodo(){
        profundidad = 0;
        padre = null;
        hijos = new ArrayList<Nodo>();
        puntajeUtilidad = 0;
        tipoNodo = 0; //Nodo no inicializado con tipo, max es 1 y min es 2
        revisado = false; 
        fila = -1;
        columna = -1;
        fila2 = -1;
        columna2 = -1;
        ficha = -1;
        filaJ = -1;
        columnaJ = -1;
        fila2J = -1;
        columna2J = -1;
        fichaJ = -1;
    }
    
    public void actualizarPuntaje(double puntaje, int fila, int columna, int fila2, int columna2, int ficha){
        contador++;
        if(tipoNodo == 1){
            if(puntaje > puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.fila2 = fila2;
                this.columna2 = columna2;
                this.ficha = ficha;
            }
        }else{
            if(puntaje < puntajeUtilidad){
                puntajeUtilidad = puntaje;
                this.fila = fila;
                this.columna = columna;
                this.fila2 = fila2;
                this.columna2 = columna2;
                this.ficha = ficha;
            }
        }
    }
    
    public double calcularUtilidad() {
        double utilidadSuma = 0;
        double utilidad = 0;
        int menorIA = 19;
        int menorJ = 19;
        int cantidadMenorIA = 0;
        int cantidadMenorJ = 0;
        for (int i = 0; i < puntosColorIA.length; i++) {
            utilidadSuma += puntosColorIA[i];
            utilidadSuma -= puntosColorJ[i];
            if(menorIA > puntosColorIA[i]) {
                menorIA = puntosColorIA[i];
                cantidadMenorIA = 1;
            }
            else if(menorIA == puntosColorIA[i]){
                cantidadMenorIA++;
            }
            if(menorJ > puntosColorJ[i]){
                menorJ = puntosColorJ[i];
                cantidadMenorJ = 1;
            }
            else if(menorJ == puntosColorJ[i]){
                cantidadMenorJ++;
            }
        }        
        double aux1 = menorIA+10 ;
        aux1 /= cantidadMenorIA;
        aux1 *= 2;
        double aux2 = menorJ+10;
        aux2 /= cantidadMenorJ;
        aux2 *= 2;
        double aux3 = utilidadSuma;
        aux3 /= 100;
        utilidad =  aux1 - aux2 + aux3;
        puntajeUtilidad = utilidad;
        return utilidad;
    }

    public int actualizarPuntajeFicha(Ficha fichaJugada, ArrayList<ArrayList<Ficha>> tablero, int color, int color2) {
        int fila = fichaJugada.getFila();
        int columna = fichaJugada.getColumna();
        int puntos = 0;
        boolean fin = false;
        int filaAux = 0;
        int columnaAux = 0;
        /*if(profundidad == 2){
            System.out.println(fila + " "+ columna);
            System.out.println(this.fila + " "+this.columna);
            System.out.println(this.fila2 + " "+this.columna2);
        }*/
        
        for(int i = 0; i < 6; i++){
            filaAux = fila;
            columnaAux = columna;  
            fin = false;
            //if(profundidad == 2) System.out.println("Lado revisado = "+i);
            while(!fin){//0 = superior izquierda, 1 = superior derecha, 2 = derecha, 3 = inferior derecha, 4 = inferior izquierda, 5 = izquierda
                //System.out.println(filaAux);
                //if(profundidad == 2) System.out.println("Auxiliar "+filaAux + " " + columnaAux);
                if(filaAux <= 3){  
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        //if(profundidad == 2)System.out.println("Superior izquierda");
                        if((filaAux-1) == this.fila && (columnaAux-1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                filaAux--;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if((filaAux-1) == this.fila2 && (columnaAux-1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                filaAux--;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        //if(profundidad == 2)System.out.println("Superior derecha");
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if((filaAux-1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }
                            else if((filaAux-1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }
                            else if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        //if(profundidad == 2)System.out.println(" izquierda");
                        if((filaAux) == this.fila && (columnaAux-1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if((filaAux) == this.fila2 && (columnaAux-1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        //if(profundidad == 2)System.out.println("inferior izquierda");
                        if(columnaAux < tablero.get(filaAux+1).size()){ 
                            if((filaAux+1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }
                            else if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        //if(profundidad == 2)System.out.println("inferior  derecha");
                        if(columnaAux+1 < tablero.get(filaAux+1).size()){
                            if((filaAux+1) == this.fila && (columnaAux+1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux+1) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux++;
                                }
                                else fin = true;
                            }
                            else if(tablero.get(filaAux+1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){//Errrrrrrrrrrrrrrrrrrroooooooooooooorrrrrrr
                        //if(profundidad == 2) System.out.println("derecha");
                        if((filaAux) == this.fila && (columnaAux+1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                columnaAux++;
                            }
                            else fin = true;
                        }
                        else if((filaAux) == this.fila2 && (columnaAux+1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                columnaAux++;
                            }
                            else fin = true;
                        }else if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else if(filaAux >= 5){
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux >= 0){
                        if((filaAux-1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }
                            else if((filaAux-1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar superior derecha 
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux+1 < tablero.get(filaAux-1).size()){
                            if((filaAux-1) == this.fila && (columnaAux+1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                    columnaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux-1) == this.fila2 && (columnaAux+1) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                    columnaAux++;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux-1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                columnaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if((filaAux) == this.fila && (columnaAux-1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if((filaAux) == this.fila2 && (columnaAux-1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }else if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if((filaAux+1) == this.fila && (columnaAux-1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux--;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux-1) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux--;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux--;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if((filaAux+1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if((filaAux) == this.fila && (columnaAux+1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    columnaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux) == this.fila2 && (columnaAux+1) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    columnaAux++;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                columnaAux++;
                                puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else{
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        if((filaAux-1) == this.fila && (columnaAux-1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                filaAux--;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if((filaAux-1) == this.fila2 && (columnaAux-1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                filaAux--;
                                columnaAux--;
                            }
                            else fin = true;
                        }else if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if((filaAux-1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }
                            else if((filaAux-1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux--;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if((filaAux) == this.fila && (columnaAux-1) == this.columna){
                            if(color == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }
                        else if((filaAux) == this.fila2 && (columnaAux-1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                columnaAux--;
                            }
                            else fin = true;
                        }else if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if((filaAux+1) == this.fila && (columnaAux-1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux--;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux-1) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                    columnaAux--;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux--;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if((filaAux+1) == this.fila && (columnaAux) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }
                            else if((filaAux+1) == this.fila2 && (columnaAux) == this.columna2){
                                if(color2 == fichaJugada.getColor()){
                                    puntos++;
                                    filaAux++;
                                }
                                else fin = true;
                            }else if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if((filaAux) == this.fila && (columnaAux+1) == this.columna){
                                if(color == fichaJugada.getColor()){
                                    puntos++;
                                    columnaAux++;
                                }
                                else fin = true;
                        }
                        else if((filaAux) == this.fila2 && (columnaAux+1) == this.columna2){
                            if(color2 == fichaJugada.getColor()){
                                puntos++;
                                columnaAux++;
                            }
                            else fin = true;
                        }else if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }
            }
        }
        return puntos;
    }
    
    public int getFichaJ() {
        return fichaJ;
    }

    public void setFichaJ(int fichaJ) {
        this.fichaJ = fichaJ;
    }

    public int getFilaJ() {
        return filaJ;
    }

    public void setFilaJ(int filaJ) {
        this.filaJ = filaJ;
    }

    public int getColumnaJ() {
        return columnaJ;
    }

    public void setColumnaJ(int columnaJ) {
        this.columnaJ = columnaJ;
    }

    public int getFila2J() {
        return fila2J;
    }

    public void setFila2J(int fila2J) {
        this.fila2J = fila2J;
    }

    public int getColumna2J() {
        return columna2J;
    }

    public void setColumna2J(int columna2J) {
        this.columna2J = columna2J;
    }
    
    public void actualizarPuntajeIA(int color, int puntaje){
        puntosColorIA[color-1] += puntaje;
        if(puntosColorIA[color-1] > 18)  puntosColorIA[color-1] = 18;
    }
    public void actualizarPuntajeJ(int color, int puntaje){
        puntosColorJ[color-1] += puntaje;
        if(puntosColorJ[color-1] > 18)  puntosColorJ[color-1] = 18;
    }
    
    public int getFila2() {
        return fila2;
    }

    public void setFila2(int fila2) {
        this.fila2 = fila2;
    }

    public int getColumna2() {
        return columna2;
    }

    public void setColumna2(int columna2) {
        this.columna2 = columna2;
    }

    public int[] getPuntosColorIA() {
        return puntosColorIA;
    }

    public void setPuntosColorIA(int[] puntosColorIA) {
        this.puntosColorIA = puntosColorIA;
    }

    public int[] getPuntosColorJ() {
        return puntosColorJ;
    }

    public void setPuntosColorJ(int[] puntosColorJ) {
        this.puntosColorJ = puntosColorJ;
    }
    
    public void agregarHijo(Nodo hijo){
        hijos.add(hijo);
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
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

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }
    
    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    public double getPuntajeUtilidad() {
        return puntajeUtilidad;
    }

    public void setPuntajeUtilidad(double puntajeUtilidad) {
        this.puntajeUtilidad = puntajeUtilidad;
    }

    public int getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(int tipoNodo) {
        if(tipoNodo == 1){
            puntajeUtilidad = -400;
        }else{
            puntajeUtilidad = 400;
        }
        this.tipoNodo = tipoNodo;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

}
