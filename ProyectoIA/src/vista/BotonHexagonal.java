/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author Konita
 */
public class BotonHexagonal extends JButton{
    private Color colorFondo, colorPresionado;
    Shape figura; 
    
    public BotonHexagonal(Color fon, Color pre, ImageIcon img) {
        super();
        colorFondo = fon;
        colorPresionado = pre;
        setContentAreaFilled(false);
        setIcon(img);
    }
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(colorPresionado);
        } else {
            g.setColor(colorFondo);
        }
        
        int puntosx2[] = {25,50,50,25,0,0};
        int puntosy2[] = {0,13,31,50,31,13};
        //int puntosx2[] = {50,100,100,50,0,0};
        //int puntosy2[] = {0,26,73,100,73,26};
        
        g.fillPolygon(puntosx2, puntosy2, 6);
        
        super.paintComponent(g);
    }
    
    public boolean contains(int x, int y) {
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                        6);
        int puntosx[] = {50,100,0};
        int puntosy[] = {0,100,100};
        
        int puntosx2[] = {25,50,50,25,0,0};
        int puntosy2[] = {0,13,31,50,31,13};
        
        //System.out.println(puntosx[0]+"");
        
        polygon.moveTo(puntosx2[0], puntosy2[0]);

        for (int index = 1; index < puntosx2.length; index++) {
                polygon.lineTo(puntosx2 [index], puntosy2[index]);
        }
        
        figura = polygon;
        polygon.closePath();
        return figura.contains(x, y);
    }
}
