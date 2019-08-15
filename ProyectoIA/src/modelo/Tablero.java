package modelo;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Random;
import modelo.Ficha;

public class Tablero{
    Jugador j1,j2;
    ArrayList<ArrayList<Ficha>> tablero;
    boolean gameOver;
    boolean turno;
    ArrayList<Ficha> bolsaFichas;
    ArrayList<Ficha> fichasEnTablero;

    public Tablero(Jugador j1, Jugador j2){
        this.j1 = j1;
        this.j2 = j2;
        gameOver = false;
        turno = false;
        bolsaFichas = new ArrayList<Ficha>();
        tablero = new ArrayList<ArrayList<Ficha>>();
        fichasEnTablero = new ArrayList<Ficha>();
        for (int i = 0; i < 9; i++) {
          tablero.add(new ArrayList<Ficha>());
        }
        for (int i=0;i<9;i++ ) {
            if(i<5){
                for (int j=0;j<i+5;j++) {
                    tablero.get(i).add(new Ficha(i,j,null,0));
                }
            }else{
                for (int j=0;j<13-i;j++) {
                    tablero.get(i).add(new Ficha(i,j,null,0));

                }
            }
        }
        tablero.get(0).get(0).setColor(1);
        tablero.get(0).get(4).setColor(2);
        tablero.get(4).get(0).setColor(3);
        tablero.get(4).get(8).setColor(4);
        tablero.get(8).get(0).setColor(5);
        tablero.get(8).get(4).setColor(6);
    }

  	//Funciones:
  	//Funcion principal (se manejan los turnos y la jugada)
    public void iniciarJuego(){
        llenarBolsa();
        inicializarManos();  
    }
    
    //Inicializar manos de los jugadores
    public void inicializarManos(){
        for(int i = 0; i < 6; i++){
            j1.agregarFicha(entregarFichaBolsa());
            j2.agregarFicha(entregarFichaBolsa());
        }
    }
    //Validar gameover
    public void validarGameOver(){
        boolean respuesta = false;
        for(int i = 0; i < tablero.size() && !respuesta; i++){
            for(int j = 0; j < tablero.get(i).size() && !respuesta; j++){
                if(tablero.get(i).get(j).getColor() == 0){
                    if(validarEspacio(tablero.get(i).get(j))) respuesta = true;
                }
            }
        }
        gameOver = !respuesta;
    }
    
    public void agregarFichasAlTablero(Ficha ficha){
        fichasEnTablero.add(ficha);
    }
    
    public void cambiarTurno(){
        turno = !turno;
    }
    
    public boolean validarEspacio(Ficha ficha){
        int fila = ficha.getFila();
        int columna = ficha.getColumna();
        if(fila <=3){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0) return true;
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
                }
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna+1 < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna+1).getColor() == 0) return true;
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) return true;
            }
        }
        if(fila >= 5){
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){
                if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna+1 < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna+1).getColor() == 0) return true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) return true;
            }
        }
        else{
            //Revisar superior izquierda
            if(fila > 0 && columna > 0){    
                if(tablero.get(fila-1).get(columna-1).getColor()==0) return true;
            }
            //Revisar superior derecha
            if(fila > 0){
                if(columna < tablero.get(fila-1).size()){
                    if(tablero.get(fila-1).get(columna).getColor() == 0) return true;
                }
            }
            //Revisar Izquierda
            if(columna > 0){
                if(tablero.get(fila).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior izquierda
            if(fila+1 < tablero.size() && columna > 0){
                if(tablero.get(fila+1).get(columna-1).getColor() == 0) return true;
            }
            //Revisar inferior derecha
            if(fila+1 < tablero.size()){
                if(columna < tablero.get(fila+1).size()){
                    if(tablero.get(fila+1).get(columna).getColor() == 0) return true;
                }
            }
            //Revisar  derecha
            if(columna+1 < tablero.get(fila).size()){
                if(tablero.get(fila).get(columna+1).getColor() == 0) return true;
            }
        }
        return false;
    }

    //Obtener jugador ganador
    public Jugador jugadorGanador(){
        Jugador ganador = new Jugador();
        if(gameOver){
            int puntajeJugador1[] = j1.getPuntosColor();
            int puntajeTotalJ1 = Integer.MAX_VALUE;
            int puntajeJugador2[] = j2.getPuntosColor();
            int puntajeTotalJ2 = Integer.MAX_VALUE;

            for(int i = 0; i < puntajeJugador1.length;i++){
                if(!(puntajeTotalJ1 < puntajeJugador1[i])){
                   puntajeTotalJ1 = puntajeJugador1[i];
                }
                if(!(puntajeTotalJ2 < puntajeJugador2[i])){
                   puntajeTotalJ2 = puntajeJugador2[i];
                }
            }
            if(puntajeTotalJ1 == puntajeTotalJ2){
                ganador = null;
            }else if(puntajeTotalJ1 > puntajeTotalJ2){
                ganador = j1;
            }else{
                ganador = j2;
            }
        }
        return ganador;
    }

  //Setters y getters
    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public Jugador getJ2() {
        return j2;
    }

    public void setJ2(Jugador j2) {
        this.j2 = j2;
    }

    public ArrayList<ArrayList<Ficha>> getTablero() {
        return tablero;
    }

    public void setTablero(ArrayList<ArrayList<Ficha>> tablero) {
        this.tablero = tablero;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public ArrayList<Ficha> getBolsaFichas() {
        return bolsaFichas;
    }

    public void setBolsaFichas(ArrayList<Ficha> bolsaFichas) {
        this.bolsaFichas = bolsaFichas;
    }

     //Funcion que permite seleccionar una ficha dentro de las fichas que tiene el jugador en la mano (le entra el jugador y retorna la ficha seleccionada)
    

    //Funcion que permite obtener las fichas que tiene en la mano un jugador
    public ArrayList<Ficha> getFichasJugador(int j){
        if(j == 1) return j1.getFichasActuales();
        else return j2.getFichasActuales();
    }
    
    //Funcion que permite obtener los puntos de un jugador
    public int[] getPuntosJugador(int j){
        if(j == 1) return j1.getPuntosColor();
        else return j2.getPuntosColor();
    }
    
    //Funcion que permite que un jugador ingrese las posiciones a mover (Es la que se va a llamar desde la vista, ingresa x1,y1,x2,y2) (cuando haya vista, esto va en la vista)

    //Funcion que entregue, de la bolsa de fichas, una nueva ficha a un jugador
    public Ficha entregarFichaBolsa(){
        Random rand = new Random(); 
        Ficha ficha = bolsaFichas.get((int)(rand.nextDouble()*bolsaFichas.size()));
        bolsaFichas.remove(ficha);
        return ficha;
    }
    
    //Funcion que llene la bolsa de fichas segun la distribucion que hay en el pdf
    // El número depende del color, por ejemplo: 1 = Amarillo, 2 = Morado, 3 = Azul, 4 = Rojo
    // 5 = Naranja y 6 = Verde
    public void llenarBolsa(){
        // Fichas del mismo simbolo
        for(int i = 0; i < 5; i++){  
            bolsaFichas.add(auxCrearPareja(1,1));//Amarillas 
            bolsaFichas.add(auxCrearPareja(2,2));//Moradas
            bolsaFichas.add(auxCrearPareja(3,3)); //Azules
            bolsaFichas.add(auxCrearPareja(4,4));//Rojas 
            bolsaFichas.add(auxCrearPareja(5,5));//Naranjas
            bolsaFichas.add(auxCrearPareja(6,6));//Verdes
        }
        for(int i = 0; i < 6; i++){
            //Rojo y otro color
            bolsaFichas.add(auxCrearPareja(4,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(4,2));//Morada
            bolsaFichas.add(auxCrearPareja(4,3)); //Azul
            bolsaFichas.add(auxCrearPareja(4,5));//Naranja
            bolsaFichas.add(auxCrearPareja(4,6));//Verde
            //Azul y otro color
            bolsaFichas.add(auxCrearPareja(3,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(3,2));//Morada
            bolsaFichas.add(auxCrearPareja(3,5));//Naranja
            bolsaFichas.add(auxCrearPareja(3,6));//Verde
            //Verde y otro color
            bolsaFichas.add(auxCrearPareja(6,1));//Amarilla
            bolsaFichas.add(auxCrearPareja(6,2));//Morada
            bolsaFichas.add(auxCrearPareja(6,5));//Naranja
            //Amarillo y otro color
            bolsaFichas.add(auxCrearPareja(1,2));//Morada
            bolsaFichas.add(auxCrearPareja(1,5));//Naranja
            //Naranja y morado
            bolsaFichas.add(auxCrearPareja(2,5));//Naranja
        }
    }

    public Ficha auxCrearPareja(int color1, int color2){
        Ficha ficha1 = new Ficha(-1,-1,null, color1);
        Ficha ficha2 = new Ficha(-1,-1,ficha1, color2);
        ficha1.setPareja(ficha2);
        return ficha1;
    }

    public int calcularPuntaje(Ficha fichaJugada) {
        int fila = fichaJugada.getFila();
        int columna = fichaJugada.getColumna();
        int puntos = 0;
        boolean fin = false;
        int filaAux = 0;
        int columnaAux = 0;
        for(int i = 0; i < 6; i++){
            filaAux = fila;
            columnaAux = columna;
            fin = false;
            while(!fin){//0 = superior izquierda, 1 = superior derecha, 2 = derecha, 3 = inferior derecha, 4 = inferior izquierda, 5 = izquierda
                if(filaAux <= 3){
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar inferior derecha
                    else if(i == 3 && filaAux+1 < tablero.size()){
                        if(columnaAux+1 < tablero.get(filaAux+1).size()){
                            if(tablero.get(filaAux+1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                columnaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else if(filaAux >= 5){
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux >= 0){
                        if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                            filaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar superior derecha 
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux+1 < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux+1).getColor() == fichaJugada.getColor()){
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
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
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
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }else{
                    //Revisar superior izquierda
                    if(i == 0 && filaAux-1 >= 0 && columnaAux-1>=0){
                        if(tablero.get(filaAux-1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            puntos++;
                            filaAux--;
                            columnaAux--;
                        }
                        else fin = true;                        
                    }
                    //Revisar superior derecha
                    else if(i == 1 && filaAux-1 >= 0){
                        if(columnaAux < tablero.get(filaAux-1).size()){
                            if(tablero.get(filaAux-1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux--;
                                puntos++;
                            }
                            else fin = true; 
                        }
                        else fin = true;
                    }
                    //Revisar izquierda
                    else if(i == 5 && columnaAux-1 >= 0){
                        if(tablero.get(filaAux).get(columnaAux-1).getColor() == fichaJugada.getColor()){
                            columnaAux--;
                            puntos++;
                        }
                        else fin = true;
                    }
                    //Revisar inferior izquierda
                    else if(i == 4 && filaAux+1 < tablero.size()){
                        if(columnaAux-1 >= 0){
                            if(tablero.get(filaAux+1).get(columnaAux-1).getColor() == fichaJugada.getColor()){
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
                            if(tablero.get(filaAux+1).get(columnaAux).getColor() == fichaJugada.getColor()){
                                filaAux++;
                                puntos++;
                            }
                            else fin = true;
                        }
                        else fin = true;
                    }
                    //Revisar derecha
                    else if(i == 2 && columnaAux+1 < tablero.get(filaAux).size()){
                        if(tablero.get(filaAux).get(columnaAux+1).getColor() == fichaJugada.getColor()){
                            columnaAux++;
                            puntos++;
                        }
                        else fin = true;
                    }
                    else fin = true;
                }
            }
        }
        //0 = superior izquierda, 1 = superior derecha, 2 = derecha, 3 = inferior derecha, 4 = inferior izquierda, 5 = izquierda
        //System.out.println("Ficha: "+fichaJugada.getFila() + "-"+ fichaJugada.getColumna()+" puntos:"+puntos);
        return puntos;
    }

    public void actualizarTablero(Ficha fichaJugada) {
        tablero.get(fichaJugada.getFila()).get(fichaJugada.getColumna()).setColor(fichaJugada.getColor());
        tablero.get(fichaJugada.getFila()).get(fichaJugada.getColumna()).setPareja(fichaJugada.getPareja());
        tablero.get(fichaJugada.getPareja().getFila()).get(fichaJugada.getPareja().getColumna()).setColor(fichaJugada.getPareja().getColor());
        tablero.get(fichaJugada.getPareja().getFila()).get(fichaJugada.getPareja().getColumna()).setPareja(fichaJugada);
    }
    
    public void prueba(Ficha f){
        System.out.println(calcularPuntaje(f));
    }
    
   /* public static void main(String args[]){
        Tablero t = new Tablero(null, null);
        Ficha f = new Ficha(10, 0, null, 4);
        t.prueba(f);
        
    }*/
}