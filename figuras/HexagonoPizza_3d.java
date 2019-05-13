package figuras;

import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;

public class HexagonoPizza_3d{
    Path2D path = new Path2D.Double();
    int f1v = 7; 
    int f2v = f1v;
    Double hipotenusa = 5.0;
    Double cateto_oposto = Math.asin(Math.PI/6)*hipotenusa; //PI/6==30°
    Double cateto_adjacente = Math.acos(Math.PI/6)*hipotenusa; //PI/6==30°

    Double[] p11 = {0.0, 0.0};
    Double[] p12 = {30.0, 20.0};                         
    Double[] p13 = {0.0, 37.5};
    Double[] p14 = {30.0, 55.0};
    Double[] p15 = {0.0, 75.0};
    Double[] p16 = {-30.0, 55.0};
    Double[] p17 = {-30.0,  20.0};

    Double[] p21 = {p11[0]+cateto_adjacente, p11[1]+cateto_oposto};
    Double[] p22 = {p12[0]+cateto_adjacente, p12[1]+cateto_oposto};                         
    Double[] p23 = {p13[0]+cateto_adjacente, p13[1]+cateto_oposto};
    Double[] p24 = {p14[0]+cateto_adjacente, p14[1]+cateto_oposto};
    Double[] p25 = {p15[0]+cateto_adjacente, p15[1]+cateto_oposto};
    Double[] p26 = {p16[0]+cateto_adjacente, p16[1]+cateto_oposto};
    Double[] p27 = {p17[0]+cateto_adjacente, p17[1]+cateto_oposto};    

    Double[][] face1= {p11, p12, p13, p14, p15, p16, p17};
    Double[][] face2= {p21, p22, p23, p24, p25, p26, p27};

    ArrayList<Shape> sps =new ArrayList<Shape>();
    Point2D.Double[] pointsf1 = new Point2D.Double[f1v];
    Point2D.Double[] pointsf2 = new Point2D.Double[f2v];

    public HexagonoPizza_3d(){}
    
    public ArrayList<Shape> transladar(Double[] transl1){
        sps = new ArrayList<Shape>();

        System.out.print(cateto_oposto+"\n");
        System.out.print(cateto_adjacente);

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