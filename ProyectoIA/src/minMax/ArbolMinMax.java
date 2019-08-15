package minMax;

import java.util.ArrayList;
import modelo.Ficha;

public class ArbolMinMax {
    Nodo raiz;
    ArrayList<Nodo> colaPrioridad;//Cola de prioridad por profundidad
    Nodo actual;
    int limiteProfundidad;
    int fila;
    int columna;
    Ficha fichaRespuesta;
    ArrayList<Ficha> fichasManoIA;
    ArrayList<Ficha> fichasManoJ;
    ArrayList<ArrayList<Ficha>> tablero;

    
    public ArbolMinMax(ArrayList<ArrayList<Ficha>> tablero, int limiteProfundidad,  ArrayList<Ficha> fichasManoIA, 
        ArrayList<Ficha> fichasManoJ, int puntosColorIA[], int puntosColorJ[]){
        raiz = new Nodo();
        raiz.setProfundidad(0);
        this.tablero = tablero;
        raiz.setPuntosColorIA(clonarPuntos(puntosColorIA));
        raiz.setPuntosColorJ(clonarPuntos(puntosColorJ));
        raiz.setTipoNodo(1);
        colaPrioridad = new ArrayList<Nodo>();
        colaPrioridad.add(raiz);
        this.limiteProfundidad = limiteProfundidad;
        this.fichasManoIA = fichasManoIA;
        this.fichasManoJ = fichasManoJ;
    }
    
     public void funcionPrincipal(){
        actual = colaPrioridad.remove(0);
        expandir(actual);//Expandir nodo
        while(colaPrioridad.size() > 0){
            actual = colaPrioridad.remove(0);
            expandir(actual);
        }
        //Recorrer los nodos que son hijos de la raiz pasando el valor a la raiz
        ArrayList<Nodo> nodosHijos = raiz.getHijos();
        for (int i = 0; i < nodosHijos.size(); i++) {
            raiz.actualizarPuntaje(nodosHijos.get(i).getPuntajeUtilidad(), nodosHijos.get(i).getFila(),
                    nodosHijos.get(i).getColumna(), nodosHijos.get(i).getFila2(),nodosHijos.get(i).getColumna2(), nodosHijos.get(i).getFicha());      
            //System.out.println(nodosHijos.get(i).getPuntajeUtilidad() + " Posiciones ("+nodosHijos.get(i).getFila()+" "+
            //       nodosHijos.get(i).getColumna()+") - ("+ nodosHijos.get(i).getFila2() + " " +nodosHijos.get(i).getColumna2()+") "+ nodosHijos.get(i).getFicha()+" Color ("+ 
            //               fichasManoIA.get(nodosHijos.get(i).getFicha()).getColor() + " "+ fichasManoIA.get(nodosHijos.get(i).getFicha()).getPareja().getColor()+")"); 
        }
    }   
    
    public int[] clonarPuntos(int puntos[]){
        int ps[] = new int[puntos.length];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = puntos[i];
        }
        return ps;
    }
    
    public void expandir(Nodo nodo){
        if(nodo.getProfundidad() == 0){
            //Se expanden las alternativas de MAX
            ArrayList<int[]> alternativas = posiblesPosiciones(tablero, -1,-1, -1, -1);
            for (int i = 0; i < alternativas.size(); i++) {
                for (int j = 0; j < fichasManoIA.size(); j++) {
                    //Se crea el nodo
                    Nodo nodoNuevo = new Nodo();
                    //Se actualiza la ficha1 en el nodo
                    nodoNuevo.setFicha(j);
                    nodoNuevo.setPuntosColorIA(clonarPuntos(raiz.getPuntosColorIA()));
                    nodoNuevo.setPuntosColorJ(clonarPuntos(raiz.getPuntosColorJ()));
                    
                    fichasManoIA.get(j).setFila(alternativas.get(i)[0]);
                    fichasManoIA.get(j).setColumna(alternativas.get(i)[1]);
                    fichasManoIA.get(j).getPareja().setFila(alternativas.get(i)[2]);
                    fichasManoIA.get(j).getPareja().setColumna(alternativas.get(i)[3]);
                    
                    int n = nodoNuevo.actualizarPuntajeFicha(fichasManoIA.get(j), tablero, -1, -1);
                    int n1 = nodoNuevo.actualizarPuntajeFicha( fichasManoIA.get(j).getPareja(), tablero, -1, -1);  
                    
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getColor(), n);
                    nodoNuevo.actualizarPuntajeIA(fichasManoIA.get(j).getPareja().getColor(), n1);
                    
                   
                    nodoNuevo.setFila(alternativas.get(i)[0]);
                    nodoNuevo.setColumna(alternativas.get(i)[1]);
                    nodoNuevo.setFila2(alternativas.get(i)[2]);
                    nodoNuevo.setColumna2(alternativas.get(i)[3]);
                    
                     //Se setea la ficha en el nodo (posicion en el arreglo de fichas en mano) y se setea la posicion de la ficha
                    //Se setea la profundidad
                    nodoNuevo.setProfundidad(1);
                    //Se actualiza puntero al padre
                    nodoNuevo.setPadre(raiz);
                    //Se setea el tipo de nodo
                    nodoNuevo.setTipoNodo(2);
                    //Se agreaga el nuevo hijo al padre
                    raiz.agregarHijo(nodoNuevo);
                    agregarNodo(nodoNuevo);
                    //System.out.println(colaPrioridad.size());   
                }
            } 
        }else if(nodo.getProfundidad() == 1){
            //System.out.println("1");
            //Se expanden las alternativas de MIN
            ArrayList<int[]> alternativas = posiblesPosiciones(tablero, nodo.getFila(), nodo.getColumna(), nodo.getFila2(), nodo.getColumna2());
            //System.out.println("Expandiendo hijo");
            //System.out.println(alternativas.size());
            //System.out.println(alternativas.size());
            for (int i = 0; i < alternativas.size(); i++) {
                for (int j = 0; j < fichasManoJ.size(); j++) {
                    //Se crea el nodo
                    //System.out.println("2");
                    Nodo nodoNuevo = new Nodo();  
                    nodoNuevo.setFichaJ(j);
                    nodoNuevo.setFicha(nodo.getFicha());
                     nodoNuevo.setProfundidad(2);
                    //Se actualiza el color y la posicion de la ficha
                    nodoNuevo.setFila(nodo.getFila());
                    nodoNuevo.setColumna(nodo.getColumna());
                    nodoNuevo.setFila2(nodo.getFila2());
                    nodoNuevo.setColumna2(nodo.getColumna2());
                    
                    nodoNuevo.setFilaJ(alternativas.get(i)[0]);
                    nodoNuevo.setColumnaJ(alternativas.get(i)[1]);
                    nodoNuevo.setFila2J(alternativas.get(i)[2]);
                    nodoNuevo.setColumna2J(alternativas.get(i)[3]);
                    
                     //Se crea copia del puntje y se actualiza la copia con el nuevo puntaje
                    nodoNuevo.setPuntosColorIA(clonarPuntos(nodo.getPuntosColorIA()));
                    nodoNuevo.setPuntosColorJ(clonarPuntos(nodo.getPuntosColorJ()));
                    
                    fichasManoJ.get(j).setFila(alternativas.get(i)[0]);
                    fichasManoJ.get(j).setColumna(alternativas.get(i)[1]);
                    fichasManoJ.get(j).getPareja().setFila(alternativas.get(i)[2]);
                    fichasManoJ.get(j).getPareja().setColumna(alternativas.get(i)[3]);
                    //System.out.println("3");
                    int n = nodoNuevo.actualizarPuntajeFicha(fichasManoJ.get(j), tablero, fichasManoIA.get(nodo.getFicha()).getColor(), fichasManoIA.get(nodo.getFicha()).getPareja().getColor());
                    //System.out.println("4");
                    int n1 = nodoNuevo.actualizarPuntajeFicha( fichasManoJ.get(j).getPareja(), tablero, fichasManoIA.get(nodo.getFicha()).getColor(), fichasManoIA.get(nodo.getFicha()).getPareja().getColor());
                    //System.out.println("4.5");
                    nodoNuevo.actualizarPuntajeJ(fichasManoJ.get(j).getColor(), n);
                    nodoNuevo.actualizarPuntajeJ(fichasManoJ.get(j).getPareja().getColor(), n1);
                    //System.out.println("5");
                    //Se setea la profundidad
                   
                    //Se actualiza puntero al padre
                    nodoNuevo.setPadre(nodo);
                    //Se setea el tipo de nodo
                    nodoNuevo.setTipoNodo(1);
                    
                    agregarNodo(nodoNuevo);
                }
            }
        }else{
            //System.out.println("Esto");
            //Se calcula la utilidad y se envia al padre
            double puntaje = nodo.calcularUtilidad();
            //System.out.println(puntaje);
            nodo.getPadre().actualizarPuntaje(puntaje, nodo.getFila(), nodo.getColumna(), nodo.getFila2(),
                    nodo.getColumna2(), nodo.getFicha());
            //Pasar variable revisado a true
            //System.out.println(nodo.getFila() +" "+ nodo.getColumna());
            nodo.setRevisado(true);
        }
    }
    
    public void agregarNodo(Nodo nodo){
        boolean nodoIngresado = false;
        for (int i = 0; i < colaPrioridad.size() && !nodoIngresado; i++) {
            if(nodo.getProfundidad() > colaPrioridad.get(i).getProfundidad()){
                colaPrioridad.add(i, nodo);
                nodoIngresado = true;
            }
        }
        if(nodoIngresado == false){
            colaPrioridad.add(nodo);
        }
    }
                                                                                    
    public ArrayList<int[]> posiblesPosiciones(ArrayList<ArrayList<Ficha>> t, int fila, int columna,int fila2, int columna2) {
        ArrayList<int[]> respuesta = new ArrayList<int[]>();
        for(int i = 0; i < t.size(); i++){
            for (int j = 0; j < t.get(i).size(); j++) {
                if((fila != i || columna != j) && (fila2 != i || columna2 != j)){
                    if(t.get(i).get(j).getColor() == 0){
                        validarEspacio(t.get(i).get(j), t, respuesta, fila, columna,fila2, columna2);
                    }
                }
                
            }
        }
        return respuesta;
    }
    
    public void validarEspacio(Ficha ficha, ArrayList<ArrayList<Ficha>> tablero, ArrayList<int[]> respuesta, int f, int c,int f2, int c2){
        int fila = ficha.getFila();
        int columna = ficha.getColumna();
        if(fila <= 3){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    if((f != fila-1 || c != columna-1) && (f2 != fila-1 || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + (columna-1));
                        posiciones[2] = fila-1;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                     if(tablero.get(fila-1).get(columna).getColor() == 0){
                        if((f != fila-1 || c != columna) && (f2 != fila-1 || c2 != columna)){
                            int posiciones [] = new int[4];
                            //System.out.println("superior derecha "+ fila + " " + columna + " "+ (fila-1) + " " + columna);
                            posiciones[2] = fila-1;
                            posiciones[3] = columna;
                            posiciones[0] = fila;
                            posiciones[1] = columna;
                            respuesta.add(posiciones);
                        }
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    if((f != fila || c != columna-1) && (f2 != fila || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Izquierda " +fila + " " + columna + " " + fila + " " + (columna-1));
                        posiciones[2] = fila;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }   
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna).getColor() == 0){
                    if((f != fila+1 || c != columna) && (f2 != fila+1 || c2 != columna)){
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior izquierda "+ fila + " " + columna + " "+ (fila+1) + " " + columna);
                        posiciones[2] = fila+1;
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size() && columna < tablero.get(fila).size()){
                if(tablero.get(fila+1).get(columna+1).getColor() == 0){
                    if((f != fila+1 || c != columna+1) && (f2 != fila+1 || c2 != columna+1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior derecha " + fila + " " + columna + " "+ (fila+1) + " " + (columna+1));
                        posiciones[2] = fila+1;
                        posiciones[3] = columna+1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    if((f != fila || c != columna+1) && (f2 != fila || c2 != columna+1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Derecha "+ fila + " " + columna + " " + fila + " " + (columna+1));
                        posiciones[2] = fila;
                        posiciones[3] = columna+1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
        }
        else if(fila >= 5){
            //Revisar superior izquierda
            if(fila > 0 && columna >= 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0){
                    if((f != fila-1 || c != columna) && (f2 != fila-1 || c2 != columna)){
                        int posiciones [] = new int[4];
                        //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + columna);
                        posiciones[2] = fila-1;
                        posiciones[3] = columna;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar superior derecha
            if(fila > 0 && columna < tablero.get(fila).size()){
                if(columna+1 < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna+1).getColor() == 0){
                        if((f != fila-1 || c != columna+1) && (f2 != fila-1 || c2 != columna+1)){
                            int posiciones [] = new int[4];
                            //System.out.println("Superior derecha " + fila + " " + columna +" "+(fila-1) + " " + (columna+1));
                            posiciones[2] = fila-1;
                            posiciones[3] = columna+1;
                            posiciones[0] = fila;
                            posiciones[1] = columna;
                            respuesta.add(posiciones);
                        }
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    if((f != fila || c != columna-1) && (f2 != fila || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Izquierda " + fila + " " + columna +" "+fila + " " + (columna-1));
                        posiciones[2] = fila;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    if((f != fila+1 || c != columna-1) && (f2 != fila+1 || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior izquierda " + fila + " " + columna +" "+(fila+1) + " " + (columna-1));
                        posiciones[2] = fila+1;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
                        if((f != fila+1 || c != columna) && (f2 != fila+1 || c2 != columna)){
                            int posiciones [] = new int[4];
                            //System.out.println("Inferior derecha " + fila + " " + columna +" "+(fila+1) + " " + columna);
                            posiciones[2] = fila+1;
                            posiciones[3] = columna;
                            posiciones[0] = fila;
                            posiciones[1] = columna;
                            respuesta.add(posiciones);
                        }
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) {
                    if((f != fila || c != columna+1) && (f2 != fila || c2 != columna+1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Dereccha " + fila + " " + columna +" "+fila + " " + (columna+1));
                        posiciones[2] = fila;
                        posiciones[3] = columna+1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
        }
        else if(fila == 4){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0){
                    if((f != fila-1 || c != columna-1) && (f2 != fila-1 || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Superior izquierda " + fila + " " + columna +" "+(fila-1) + " " + (columna-1));
                        posiciones[2] = fila-1;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna).getColor() == 0){
                        if((f != fila-1 || c != columna) && (f2 != fila-1 || c2 != columna)){
                            int posiciones [] = new int[4];
                            //System.out.println("Superior derecha " + fila + " " + columna +" "+(fila-1) + " " + columna);
                            posiciones[2] = fila-1;
                            posiciones[3] = columna;
                            posiciones[0] = fila;
                            posiciones[1] = columna;
                            respuesta.add(posiciones);
                        }
                    }
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0){
                    if((f != fila || c != columna-1) && (f2 != fila || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Izquierda " + fila + " " + columna +" "+fila + " " + (columna-1));
                        posiciones[2] = fila;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0){
                    if((f != fila+1 || c != columna-1) && (f2 != fila+1 || c2 != columna-1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Inferior izquierda " + fila + " " + columna +" "+(fila+1) + " " + (columna-1));
                        posiciones[2] = fila+1;
                        posiciones[3] = columna-1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0){
                        if((f != fila+1 || c != columna) && (f2 != fila+1 || c2 != columna)){
                            int posiciones [] = new int[4];
                            //System.out.println("Inferior derecha " + fila + " " + columna +" "+(fila+1) + " " + columna);
                            posiciones[2] = fila+1;
                            posiciones[3] = columna;
                            posiciones[0] = fila;
                            posiciones[1] = columna;
                            respuesta.add(posiciones);
                        }
                    }
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0){
                    if((f != fila || c != columna+1) && (f2 != fila || c2 != columna+1)){
                        int posiciones [] = new int[4];
                        //System.out.println("Derecha " + fila + " " + columna +" "+ fila + " " + (columna+1));
                        posiciones[2] = fila;
                        posiciones[3] = columna+1;
                        posiciones[0] = fila;
                        posiciones[1] = columna;
                        respuesta.add(posiciones);
                    }
                }
            }
        }
    }
    
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArrayList<Nodo> getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(ArrayList<Nodo> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
    }

    public Nodo getActual() {
        return actual;
    }

    public void setActual(Nodo actual) {
        this.actual = actual;
    }

    public int getLimiteProfundidad() {
        return limiteProfundidad;
    }

    public void setLimiteProfundidad(int limiteProfundidad) {
        this.limiteProfundidad = limiteProfundidad;
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

    public Ficha getFichaRespuesta() {
        return fichaRespuesta;
    }

    public void setFichaRespuesta(Ficha fichaRespuesta) {
        this.fichaRespuesta = fichaRespuesta;
    }

}
