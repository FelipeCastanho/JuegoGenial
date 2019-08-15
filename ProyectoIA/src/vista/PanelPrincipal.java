/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import modelo.Ficha;
import modelo.Jugador;
import modelo.Tablero;
/**
 *
 * @author Jennifer
 */
public class PanelPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form PanelPrincipal
     */
    ArrayList<ArrayList<BotonHexagonal>> hexagonos;
    ArrayList<BotonHexagonal> manoJugador;
    ArrayList<BotonHexagonal> manoIA;
    ArrayList<JButton> tableroJ1;
    ArrayList<JButton> tableroJ2;
    Controlador controlador;
    ImageIcon casillaVacia, fichaAmarilla, fichaRoja, fichaMorada, fichaAzul, fichaNaranja, fichaVerde;
    Image fondo;
    BotonHexagonal boton;
    Tablero tablero;
    Jugador j1;
    Jugador j2;
    public PanelPrincipal() {
        initComponents();
        //Inicializar imagenes
        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo9.jpg")).getImage();
        casillaVacia = new ImageIcon(getClass().getResource("/imagenes/hexagonoBase50.png"));
        fichaAmarilla = new ImageIcon(getClass().getResource("/imagenes/Amarillo50.png"));
        fichaRoja = new ImageIcon(getClass().getResource("/imagenes/Rojo50.png"));
        fichaMorada = new ImageIcon(getClass().getResource("/imagenes/Morado50.png"));
        fichaAzul = new ImageIcon(getClass().getResource("/imagenes/Azul50.png"));
        fichaNaranja = new ImageIcon(getClass().getResource("/imagenes/Naranja50.png"));
        fichaVerde = new ImageIcon(getClass().getResource("/imagenes/Verde50.png"));
        //Inicializar variables
        j1 = new Jugador();
        j2 = new Jugador();
        j1.setNombre("Jugador, Humano");
        j2.setNombre("IA");
        tablero = new Tablero(j1, j2);
        controlador = new Controlador(this);
        hexagonos = new ArrayList<ArrayList<BotonHexagonal>>();
        manoJugador = new ArrayList<BotonHexagonal>();
        manoIA = new ArrayList<BotonHexagonal>();
        tableroJ1 = new ArrayList<JButton>();
        tableroJ2 = new ArrayList<JButton>();

        JLabel label2 = new JLabel("Fichas Jugador");
        label2.setFont(new java.awt.Font("Tahoma", 0, 18));
        label2.setBounds(30, 435, 150, 25);
        this.add(label2);
 
        JLabel label4 = new JLabel("Fichas IA");
        label4.setFont(new java.awt.Font("Tahoma", 0, 18));
        label4.setBounds(620, 435, 150, 25);
        this.add(label4);
        
        crearTablero();
        tablero.iniciarJuego();
        dibujarFichasMano();
        iniciarlizarTablerosPuntuacion();
        actualizarTablerosPuntuacion();
    }
    
    public void paintComponent(Graphics g) {
    g.drawImage(fondo, 0, 0, null);
    }
    public ArrayList<ArrayList<BotonHexagonal>> getHexagonos(){
        return hexagonos;
    }
    public ArrayList<BotonHexagonal> getFichasMano(){
        return manoJugador;
    }
    public ArrayList<BotonHexagonal> getFichasIA(){
        return manoIA;
    }
    public ArrayList<ArrayList<Ficha>> getTablero(){
        return tablero.getTablero();
    }
    public Tablero getJuego(){
        return tablero;
    }
    public void crearTablero(){
        int x = 100;
        int y = 0;
        for (int i=0;i<9;i++ ) {
            if(i<5){
                ArrayList<BotonHexagonal> arreglo = new ArrayList<BotonHexagonal>();
                for (int j=0;j<i+5;j++) {
                    BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, casillaVacia);
                    b.setBounds(50+x, 50+y, 50 , 50);
                    b.addMouseListener(controlador);
                    arreglo.add(b);
                    this.add(b);
                    x+=52;
                }
                hexagonos.add(arreglo);
                y+=40;
                x=100-(25*(i+1));
                if(i==4) x+=50;
            }else{
                ArrayList<BotonHexagonal> arreglo = new ArrayList<BotonHexagonal>();
                for (int j=0;j<13-i;j++) {
                    BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, casillaVacia);
                    b.setBounds(50+x, 50+y, 50 , 50);
                    b.addMouseListener(controlador);
                    arreglo.add(b);
                    this.add(b);
                    x+=52;
                }
                hexagonos.add(arreglo);
                y+=40;
                x=0+(25*(i-3));
            }
        }
        hexagonos.get(0).get(0).setIcon(fichaAmarilla);
        hexagonos.get(0).get(4).setIcon(fichaMorada);
        hexagonos.get(4).get(0).setIcon(fichaAzul);
        hexagonos.get(4).get(8).setIcon(fichaRoja);
        hexagonos.get(8).get(0).setIcon(fichaNaranja);
        hexagonos.get(8).get(4).setIcon(fichaVerde);
    }
    public ArrayList<ArrayList<BotonHexagonal>> getBotones(){
        return hexagonos;
    }
    public void dibujarFichasMano(){
        for(int i = 0; i < manoJugador.size(); i++){
            this.remove(manoJugador.get(i));
            this.remove(manoIA.get(i));
        }
        manoJugador = new ArrayList<BotonHexagonal>();
        manoIA = new ArrayList<BotonHexagonal>();
        ArrayList<Ficha> fichas = j1.getFichasActuales();
        int x = 0;
        int y = 430;
        for (int i = 0; i < fichas.size(); i++) {
            BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, getImage(fichas.get(i).getColor()));
            b.setBounds(50+x, 50+y, 50 , 50);
            this.add(b);
            BotonHexagonal b1 = new BotonHexagonal(Color.red, Color.red, getImage(fichas.get(i).getPareja().getColor()));
            b1.setBounds(100+x, 50+y, 50 , 50);
            b1.addMouseListener(controlador);
            b.addMouseListener(controlador);
            manoJugador.add(b);
            manoJugador.add(b1);
            this.add(b1);
            x+=200;
            if(i == 2) {
                y+=70;
                x = 0;
            }
        }
        
        ArrayList<Ficha> fichasIA = j2.getFichasActuales();
        x = 590;
        y = 430;
        for (int i = 0; i < fichasIA.size(); i++) {
            BotonHexagonal b = new BotonHexagonal(Color.red, Color.red, getImage(fichasIA.get(i).getColor()));
            b.setBounds(50+x, 50+y, 50 , 50);
            this.add(b);
            BotonHexagonal b1 = new BotonHexagonal(Color.red, Color.red, getImage(fichasIA.get(i).getPareja().getColor()));
            b1.setBounds(100+x, 50+y, 50 , 50);
            manoIA.add(b);
            manoIA.add(b1);
            this.add(b1);
            x+=200;
            if(i == 2) {
                y+=70;
                x = 590;
            }
        }
        
        
       this.repaint();
    }
    public ImageIcon getImage(int color){
        if(color == 1) return fichaAmarilla;
        if(color == 2) return fichaMorada;
        if(color == 3) return fichaAzul;
        if(color == 4) return fichaRoja;
        if(color == 5) return fichaNaranja;
        if(color == 6) return fichaVerde;
        else return casillaVacia;
    }
    public void iniciarlizarTablerosPuntuacion(){
        int x = 610;
        int y = 50;
        
        JLabel l = new JLabel("Puntos Jugador");
        l.setBounds(x, y-30, 150, 25);
        l.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l);
        JLabel l2 = new JLabel("Puntos IA");
        l2.setBounds(x, y+170, 150, 25);
        l2.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l2);
        for(int j = 0; j < 2; j++){
           for(int i = 0; i < 19; i++){
                JLabel l1 = new JLabel(i+"");
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                l1.setBackground(Color.white);
                this.add(l1);
                x+=30;
            } 
            y+= 200;
            x=610;
        }      
        x = 610;
        y = 70;
        //Tablero jugador1
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 19; j++) {
                JLabel l1 = new JLabel();
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                this.add(l1);
                x+=30;
            }
            y+=20;
            x=610;
        }
        x = 610;
        y = 270;
        
        //Tablero jugador2
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 19; j++) {
                JLabel l1 = new JLabel();
                l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                l1.setBounds(x, y, 30, 20);
                this.add(l1);
                x+=30;
            }
            y+=20;
            x=610;
        }
        for(int i = 0; i < 2; i++){
            JButton b1 = new JButton();
            b1.setBackground(Color.YELLOW);   
            JButton b2 = new JButton();
            b2.setBackground(new Color(173,63, 174));
            JButton b3 = new JButton();
            b3.setBackground(Color.BLUE);
            JButton b4 = new JButton();
            b4.setBackground(Color.RED);
            JButton b5 = new JButton();
            b5.setBackground(Color.ORANGE);
            JButton b6 = new JButton();
            b6.setBackground(Color.GREEN);
            add(b1);
            add(b2);
            add(b3);
            add(b4);
            add(b5);
            add(b6);
            if(i == 0){
                tableroJ1.add(b1);
                tableroJ1.add(b2);
                tableroJ1.add(b3);
                tableroJ1.add(b4);
                tableroJ1.add(b5);
                tableroJ1.add(b6);
            }
            else{
                tableroJ2.add(b1);
                tableroJ2.add(b2);
                tableroJ2.add(b3);
                tableroJ2.add(b4);
                tableroJ2.add(b5);
                tableroJ2.add(b6);
            }
        }
        
        // El número depende del color, por ejemplo: 1 = Amarillo, 2 = Morado, 3 = Azul, 4 = Rojo
                // 5 = Naranja y 6 = Verde
    }
    public void actualizarTablerosPuntuacion(){
        int puntosJ1[] = j1.getPuntosColor();
        int puntosJ2[] = j2.getPuntosColor();
        int x = 610;
        int y = 70;
        
        for(int i = 0; i < puntosJ1.length; i++){
            tableroJ1.get(i).setBounds(x+(30*puntosJ1[i]), y+(20*i), 30, 20);
        }
        
        x = 610;
        y = 270;
        for(int i = 0; i < puntosJ1.length; i++){
            tableroJ2.get(i).setBounds(x+(30*puntosJ2[i]), y+(20*i), 30, 20);
        }
    }
    public ArrayList<Ficha> getFichasManoJugador(){
        return j1.getFichasActuales();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
