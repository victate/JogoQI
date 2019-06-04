package figuras;

import java.awt.*;
import java.util.*;

import jdk.nashorn.internal.ir.ReturnNode;

public class Face{
    private ArrayList<Point3D> vertices = new ArrayList<Point3D>();
    private Color preenchimento;

    public Face(ArrayList<Point3D> pontos){
        this.vertices = pontos;
    }

    public Face(Point3D[] pontos){
        for(Point3D ponto : pontos){
            this.vertices.add(ponto);
        }
    }

    protected void setPreenchimento(Color preenchimento){
        this.preenchimento = preenchimento;
    }

    protected Color getPreenchimento(){
        return this.preenchimento;
    }

    protected ArrayList<Point3D> getVertices(){
        return this.vertices;
    }

}