package figuras;

import java.lang.Math;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;


public class Hexagono{
    Path2D path = new Path2D.Double();
    int v = 6; 

    Double[] p1 = {0.0,60.0};
    Double[] p2 = {30.0,45.0};                         
    Double[] p3 = {30.0,15.5};
    Double[] p4 = {0.0,0.0};
    Double[] p5 = {-30.0,15.5};
    Double[] p6 = {-30.0,45.0};
    Double[][] p = {p1, p2, p3, p4, p5, p6};

    ArrayList<Shape> sps =new ArrayList<Shape>();
    Point2D.Double[] points = new Point2D.Double[v];

    public Hexagono(){}

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
    
    public ArrayList<Shape> espelhar(){
        sps = new ArrayList<Shape>();
        Double[] espelhar = {-1.0,0.0
                            ,0.0,1.0};
        for (int i = 0; i < this.v; i++) {
            Double aux = (p[i][0] * espelhar[0]) + (p[i][1] * espelhar[1]);
            Double aux1 = (p[i][0] * espelhar[2]) + (p[i][1] * espelhar[3]);
            this.p[i][0] = aux+60;
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
            this.p[i][0] = aux+60;
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
        