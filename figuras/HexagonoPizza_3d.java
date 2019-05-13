package figuras;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;

public class HexagonoPizza_3d{
    Path2D path = new Path2D.Double();
    int f1v = 7; 
    int f2v = 7; 

    Double[] p11 = {0.0, 0.0};
    Double[] p12 = {30.0, 20.0};                         
    Double[] p13 = {0.0, 37.5};
    Double[] p14 = {30.0, 55.0};
    Double[] p15 = {0.0, 75.0};
    Double[] p16 = {-30.0, 55.0};
    Double[] p17 = {-30.0,  20.0};

    Double[] p21 = {-5.0, -5.0};
    Double[] p22 = {25.0, 15.0};                         
    Double[] p23 = {-5.0, 32.5};
    Double[] p24 = {25.0, 50.0};
    Double[] p25 = {-5.0, 70.0};
    Double[] p26 = {-35.0, 50.0};
    Double[] p27 = {-35.0, 15.0};

    Double[][] face1= {p11, p12, p13, p14, p15, p16, p17};
    Double[][] face2= {p21, p22, p23, p24, p25, p26, p27};

    ArrayList<Shape> sps =new ArrayList<Shape>();
    Point2D.Double[] pointsf1 = new Point2D.Double[f1v];
    Point2D.Double[] pointsf2 = new Point2D.Double[f2v];

    public HexagonoPizza_3d(){}
    
    public ArrayList<Shape> transladar(Double[] transl1){
        sps =new ArrayList<Shape>();

        //face1
        for (int i = 0; i < this.f1v; i++) {
            this.face1[i][0] = face1[i][0] + transl1[0]+30;
            this.face1[i][1] = face1[i][1] + transl1[1];
            pointsf1[i] = new Point2D.Double(face1[i][0], face1[i][1]);
        }
         for (int i = 1; i < this.f1v; i++) {
            sps.add(new Line2D.Double(pointsf1[i-1], pointsf1[i]));
        }
        sps.add(new Line2D.Double(pointsf1[f1v-1], pointsf1[0]));

        //face2
        for (int i = 0; i < this.f2v; i++) {
            this.face2[i][0] = face2[i][0] + transl1[0]+30;
            this.face2[i][1] = face2[i][1] + transl1[1];
            pointsf2[i] = new Point2D.Double(face2[i][0], face2[i][1]);
        }
         for (int i = 1; i < this.f2v; i++) {
            sps.add(new Line2D.Double(pointsf2[i-1], pointsf2[i]));
        }
        sps.add(new Line2D.Double(pointsf2[f2v-1], pointsf2[0]));

        //face1 - face2
        for (int i = 0; i < this.f1v; i++) {
            sps.add(new Line2D.Double(pointsf1[i], pointsf2[i]));
        }
        return sps;    
    }

    public ArrayList<Shape> Escala(Double vlr){
        
        sps = new ArrayList<Shape>();
        Double[][] escala = {{vlr,0.0},
                            {0.0,vlr}};
        
        //face1                            
         for (int i = 0; i < this.f1v; i++) {
            Double aux = (face1[i][0] * escala[0][0]) + (face1[i][1] * escala[0][1]);
            Double aux1 = (face1[i][0] * escala[1][0]) + (face1[i][1] * escala[1][1]);
            this.face1[i][0] = aux;
            this.face1[i][1] = aux1;
            
            pointsf1[i] = new Point2D.Double(face1[i][0], face1[i][1]);
        }
         for (int i = 1; i < this.f1v; i++) {
            sps.add(new Line2D.Double(pointsf1[i-1], pointsf1[i]));
        }
        sps.add(new Line2D.Double(pointsf1[f1v-1], pointsf1[0]));

        //face2                            
        for (int i = 0; i < this.f2v; i++) {
            Double aux = (face2[i][0] * escala[0][0]) + (face2[i][1] * escala[0][1]);
            Double aux1 = (face2[i][0] * escala[1][0]) + (face2[i][1] * escala[1][1]);
            this.face2[i][0] = aux;
            this.face2[i][1] = aux1;
            
            pointsf2[i] = new Point2D.Double(face2[i][0], face2[i][1]);
        }
            for (int i = 1; i < this.f2v; i++) {
            sps.add(new Line2D.Double(pointsf2[i-1], pointsf2[i]));
        }
        sps.add(new Line2D.Double(pointsf2[f2v-1], pointsf2[0]));

        return sps;    
    }

    public ArrayList<Shape> espelhar(){
        sps = new ArrayList<Shape>();
        Double[] espelhar = {-1.0,0.0
                            ,0.0,1.0};

        //face1
        for (int i = 0; i < this.f1v; i++) {
            Double aux = (face1[i][0] * espelhar[0]) + (face1[i][1] * espelhar[1]);
            Double aux1 = (face1[i][0] * espelhar[2]) + (face1[i][1] * espelhar[3]);
            this.face1[i][0] = aux;
            this.face1[i][1] = aux1;
            pointsf1[i] = new Point2D.Double(face1[i][0], face1[i][1]);
        }
        for (int i = 1; i < this.f1v; i++) {
            sps.add(new Line2D.Double(pointsf1[i-1], pointsf1[i]));
        }
        sps.add(new Line2D.Double(pointsf1[f1v-1], pointsf1[0]));

        //face2
        for (int i = 0; i < this.f2v; i++) {
            Double aux = (face2[i][0] * espelhar[0]) + (face2[i][1] * espelhar[1]);
            Double aux1 = (face2[i][0] * espelhar[2]) + (face2[i][1] * espelhar[3]);
            this.face2[i][0] = aux;
            this.face2[i][1] = aux1;
            pointsf2[i] = new Point2D.Double(face2[i][0], face2[i][1]);
        }
        for (int i = 1; i < this.f2v; i++) {
            sps.add(new Line2D.Double(pointsf2[i-1], pointsf2[i]));
        }
        sps.add(new Line2D.Double(pointsf2[f1v-1], pointsf2[0]));
        return sps;
    }


    public ArrayList<Shape> girar(Double x){
        sps = new ArrayList<Shape>();
        Double[] espelhar = {Math.cos(x), - Math.sin(x)
                            ,Math.sin(x),   Math.cos(x)};

        //face1                            
        for (int i = 0; i < this.f1v; i++) {
            Double aux = (face1[i][0] * espelhar[0]) + (face1[i][1] * espelhar[1]);
            Double aux1 = (face1[i][0] * espelhar[2]) + (face1[i][1] * espelhar[3]);
            this.face1[i][0] = aux+100;
            this.face1[i][1] = aux1+100;
            pointsf1[i] = new Point2D.Double(face1[i][0], face1[i][1]);
        }
        for (int i = 1; i < this.f1v; i++) {
            sps.add(new Line2D.Double(pointsf1[i-1], pointsf1[i]));
        }
        sps.add(new Line2D.Double(pointsf1[f1v-1], pointsf1[0]));

        //face2
        for (int i = 0; i < this.f2v; i++) {
            Double aux = (face2[i][0] * espelhar[0]) + (face2[i][1] * espelhar[1]);
            Double aux1 = (face2[i][0] * espelhar[2]) + (face2[i][1] * espelhar[3]);
            this.face2[i][0] = aux+100;
            this.face2[i][1] = aux1+100;
            pointsf2[i] = new Point2D.Double(face2[i][0], face2[i][1]);
        }
        for (int i = 1; i < this.f2v; i++) {
            sps.add(new Line2D.Double(pointsf2[i-1], pointsf2[i]));
        }
        sps.add(new Line2D.Double(pointsf2[f2v-1], pointsf2[0]));

        return sps; 
    }
} 