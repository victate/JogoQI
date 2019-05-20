package figuras;

import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;

public class Face{
    private int qnt_vertices = 0;
    private Double[][] vertices;
    private Color preenchimento;

    protected Face(){}

    protected void setVertices(Double[][] pontos){
        this.vertices = pontos;
        this.qnt_vertices = this.vertices.length;
    }
    protected void setPreenchimento(Color preenchimento){
        this.preenchimento = preenchimento;
    }


    protected ArrayList<Shape> getShape(){
        ArrayList<Shape> sps = new ArrayList<Shape>();
        Point2D.Double[] shape_vertices = new Point2D.Double[this.qnt_vertices];

        for (int i = 0; i < this.qnt_vertices; i++) {
            shape_vertices[i] = new Point2D.Double(this.vertices[i][0], this.vertices[i][1]);
        }

        sps.add(new Line2D.Double(shape_vertices[this.qnt_vertices - 1], shape_vertices[0]));
        for (int i = 1; i < this.qnt_vertices; i++) {
            sps.add(new Line2D.Double(shape_vertices[i-1], shape_vertices[i]));
        }
        return sps;
    }

    protected Color getPreenchimento(){
        return this.preenchimento;
    }

    public ArrayList<Shape> transladar(Double[] distancia){
        Point2D.Double[] shape_vertices = new Point2D.Double[this.qnt_vertices];

        for (int i = 0; i < this.qnt_vertices; i++) {
            this.vertices[i][0] = vertices[i][0] + distancia[0];
            this.vertices[i][1] = vertices[i][1] + distancia[1];
            shape_vertices[i] = new Point2D.Double(this.vertices[i][0], this.vertices[i][1]);
        }
        return this.getShape();
    }

    public ArrayList<Shape> escala(Double escala){
        Point2D.Double[] shape_vertices = new Point2D.Double[this.qnt_vertices];
        Double[][] matriz_escala = {{escala, 0.0},
                                    {0.0, escala}};
                                  
         for (int i = 0; i < this.qnt_vertices; i++) {
            this.vertices[i][0] = this.vertices[i][0] * matriz_escala[0][0] + this.vertices[i][1] * matriz_escala[0][1];
            this.vertices[i][1] = this.vertices[i][0] * matriz_escala[1][0] + this.vertices[i][1] * matriz_escala[1][1];
            
            shape_vertices[i] = new Point2D.Double(this.vertices[i][0], this.vertices[i][1]);
        }
        return this.getShape();  
    }

    protected Path2D.Double getGeneralPath(){
        Path2D.Double face_gp = new Path2D.Double();

        face_gp.moveTo(this.vertices[0][0], this.vertices[0][1]);
        for (int i = 0; i < this.qnt_vertices; i++) {
            face_gp.lineTo(this.vertices[i][0], this.vertices[i][1]);
        }
        face_gp.lineTo(this.vertices[0][0], this.vertices[0][1]);
        face_gp.closePath();
        return face_gp;
    }

}