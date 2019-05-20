package figuras;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.*;

public class HexPizza_3d_faces{
    
    ArrayList<Face> faces = new ArrayList<Face>();
    ArrayList<Double[][]> vertices_faces = new ArrayList<Double[][]>();

    int qnt_faces = 9;
    Double hipotenusa_ladoA = 5.0;
    Double hipotenusa_ladoB = 30.0;
    
    Double cateto_oposto = Math.asin(Math.PI/6)*hipotenusa_ladoA; //PI/6==30°
    Double cateto_adjacente = Math.acos(Math.PI/6)*hipotenusa_ladoA; //PI/6==30°

    Double cateto_opostoB = Math.asin(Math.PI/6)*hipotenusa_ladoB; //PI/6==30°
    Double cateto_adjacenteB = Math.acos(Math.PI/6)*hipotenusa_ladoB; //PI/6==30°

    Double[] p11 = {0.0-cateto_adjacenteB, 0.0-cateto_opostoB};
    Double[] p12 = {30.0-cateto_adjacenteB, 20.0-cateto_opostoB};                         
    Double[] p13 = {0.0-cateto_adjacenteB, 37.5-cateto_opostoB};
    Double[] p14 = {30.0-cateto_adjacenteB, 55.0-cateto_opostoB};
    Double[] p15 = {0.0-cateto_adjacenteB, 75.0-cateto_opostoB};
    Double[] p16 = {-30.0-cateto_adjacenteB, 55.0-cateto_opostoB};
    Double[] p17 = {-30.0-cateto_adjacenteB,  20.0-cateto_opostoB};

    Double[] p21 = {p11[0]+cateto_adjacente, p11[1]+cateto_oposto};
    Double[] p22 = {p12[0]+cateto_adjacente, p12[1]+cateto_oposto};                         
    Double[] p23 = {p13[0]+cateto_adjacente, p13[1]+cateto_oposto};
    Double[] p24 = {p14[0]+cateto_adjacente, p14[1]+cateto_oposto};
    Double[] p25 = {p15[0]+cateto_adjacente, p15[1]+cateto_oposto};
    Double[] p26 = {p16[0]+cateto_adjacente, p16[1]+cateto_oposto};
    Double[] p27 = {p17[0]+cateto_adjacente, p17[1]+cateto_oposto};

    Double[][] vertices_f1 = {p11, p12, p13, p14, p15, p16, p17};
    Double[][] vertices_f2 = {p21, p22, p23, p24, p25, p26, p27};
    Double[][] vertices_f3 = {p11, p21, p22, p12};
    Double[][] vertices_f4 = {p12, p22, p23, p13};
    Double[][] vertices_f5 = {p13, p23, p24, p14};
    Double[][] vertices_f6 = {p14, p24, p25, p15};
    Double[][] vertices_f7 = {p15, p25, p26, p16};
    Double[][] vertices_f8 = {p16, p26, p27, p17};
    Double[][] vertices_f9 = {p17, p27, p21, p11};


    public HexPizza_3d_faces(){

        this.vertices_faces.add(vertices_f1);
        this.vertices_faces.add(vertices_f2);
        this.vertices_faces.add(vertices_f3);
        this.vertices_faces.add(vertices_f4);
        this.vertices_faces.add(vertices_f5);
        this.vertices_faces.add(vertices_f6);
        this.vertices_faces.add(vertices_f7);
        this.vertices_faces.add(vertices_f8);
        this.vertices_faces.add(vertices_f9);


        for(int i=0; i<this.qnt_faces; i++){
            Face face = new Face();
            face.setVertices(vertices_faces.get(i));
            face.setPreenchimento(Color.GREEN);
            this.faces.add(face);
        }
    }

    public int getNumFaces(){
        return this.qnt_faces;
    }

    public ArrayList<Shape> getShape(){

        ArrayList<Shape> sps = new ArrayList<Shape>();

        for(int i=0; i<this.qnt_faces; i++){
            Face face = this.faces.get(i);
            sps.addAll(face.getShape());
        }
        return sps;
    }

    public ArrayList<Shape> transladar(Double[] distancia){

        for(int i=0; i<2; i++){
            Face face = this.faces.get(i);
            face.transladar(distancia);
        }
        return this.getShape();
    }

    public ArrayList<Shape> escala(Double escala){

        for(int i=0; i<2; i++){
            Face face = this.faces.get(i);
            face.escala(escala);
        }
        return this.getShape();
    }

    public Color getCorFace(int i){
        return this.faces.get(i).getPreenchimento();
    }

    public Path2D.Double getFace(int i){
        return this.faces.get(i).getGeneralPath();
    }

} 