package figuras;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;

public class HexagonoPizza{
    Path2D path = new Path2D.Double();
    int v = 7; 

    Double[] p1 = {0.0, 0.0};
    Double[] p2 = {30.0, 20.0};                         
    Double[] p3 = {0.0, 37.5};
    Double[] p4 = {30.0, 55.0};
    Double[] p5 = {0.0, 75.0};
    Double[] p6 = {-30.0, 55.0};
    Double[] p7 = {-30.0,  20.0};
    Double[][] p= {p1, p2, p3, p4, p5, p6, p7};

    ArrayList<Shape> sps =new ArrayList<Shape>();
    Point2D.Double[] points = new Point2D.Double[v];

    public HexagonoPizza(){}
    
    public ArrayList<Shape> Escala(Double vlr){
        Double[][] escala = {{vlr,0.0},
                            {0.0,vlr}};
        sps = new ArrayList<Shape>();
         for (int i = 0; i < this.v; i++) {
            Double aux = (p[i][0] * escala[0][0]) + (p[i][1] * escala[0][1]);
            Double aux1 = (p[i][0] * escala[1][0]) + (p[i][1] * escala[1][1]);
            this.p[i][0] = aux;
            this.p[i][1] = aux1;
            
            points[i] = new Point2D.Double(p[i][0], p[i][1]);
        }
         for (int i = 1; i < this.v; i++) {
            sps.add(new Line2D.Double(points[i-1], points[i]));
        }
        sps.add(new Line2D.Double(points[v-1], points[0]));
        return sps;    
    }

    public ArrayList<Shape> transladar(Double[] transl1){
        sps =new ArrayList<Shape>();
         for (int i = 0; i < this.v; i++) {
            this.p[i][0] = p[i][0] + transl1[0]+30;
            this.p[i][1] = p[i][1] + transl1[1];
            points[i] = new Point2D.Double(p[i][0], p[i][1]);
        }
         for (int i = 1; i < this.v; i++) {
            sps.add(new Line2D.Double(points[i-1], points[i]));
        }
        sps.add(new Line2D.Double(points[v-1], points[0]));
        return sps;    
    }
    public ArrayList<Shape> espelhar(){
        sps =new ArrayList<Shape>();
        Double[] espelhar = {-1.0,0.0
                            ,0.0,1.0};
        for (int i = 0; i < this.v; i++) {
            Double aux = (p[i][0] * espelhar[0]) + (p[i][1] * espelhar[1]);
            Double aux1 = (p[i][0] * espelhar[2]) + (p[i][1] * espelhar[3]);
            this.p[i][0] = aux+50;
            this.p[i][1] = aux1;
            points[i] = new Point2D.Double(p[i][0], p[i][1]);
        }
        for (int i = 1; i < this.v; i++) {
            sps.add(new Line2D.Double(points[i-1], points[i]));
        }
        sps.add(new Line2D.Double(points[v-1], points[0]));
        return sps;  
    }

    public ArrayList<Shape> girar(Double x){
        sps =new ArrayList<Shape>();
        Double[] espelhar = {Math.cos(x), - Math.sin(x)
                            ,Math.sin(x),   Math.cos(x)};
        for (int i = 0; i < this.v; i++) {
            Double aux = (p[i][0] * espelhar[0]) + (p[i][1] * espelhar[1]);
            Double aux1 = (p[i][0] * espelhar[2]) + (p[i][1] * espelhar[3]);
            this.p[i][0] = aux + 50;
            this.p[i][1] = aux1;
            points[i] = new Point2D.Double(p[i][0], p[i][1]);
        }
        for (int i = 1; i < this.v; i++) {
            sps.add(new Line2D.Double(points[i-1], points[i]));
        }
        sps.add(new Line2D.Double(points[v-1], points[0]));
        return sps;  
    }
} 