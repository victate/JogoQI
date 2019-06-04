package figuras;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.*;

import figuras.Point3D;
import figuras.Face;

public class Figura{
    
    private ArrayList<Face> faces = new ArrayList<Face>();
    private ArrayList<Point3D[]> pontos = new ArrayList<Point3D[]>();

    private double[][] coordenadas_homogeneas = {{1.0, 0.0, 0.0, 0.0},
                                                {0.0, 1.0, 0.0, 0.0},
                                                {0.0, 0.0, 1.0, 0.0},
                                                {0.0, 0.0, 0.0, 1.0}};

    public Figura(){}

    public void addFacePontos(Point3D[] pontos){
        this.pontos.add(pontos);
        addFace(pontos);
    }

    private void addFace(Point3D[] pontos){
        
        Face face = new Face(pontos);

        if(this.faces.size() > 0){
            if(pontos[0].getZ()>this.faces.get(0).getVertices().get(0).getZ()){
                Face aux = this.faces.get(0);
                this.faces.set(0, face);
                this.faces.add(aux);
            }
            else{
                this.faces.add(face);
            }
        }

        if(this.faces.size() > 1){
            for(int i=1; i<faces.get(0).getVertices().size(); i++){
                Point3D[] new_face = {faces.get(0).getVertices().get(i-1), faces.get(1).getVertices().get(i-1),
                                      faces.get(1).getVertices().get(i), faces.get(0).getVertices().get(i)};

                this.faces.add(new Face(new_face));
                Area area_face = new Area(getGeneralPathFace(this.faces.size()-1));
                area_face.intersect(new Area(getGeneralPathFace(this.faces.size()-2)));
                if(!area_face.isEmpty()){
                    if(this.faces.get(this.faces.size()-1).getVertices().get(0).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(0).getZ()||
                    this.faces.get(this.faces.size()-1).getVertices().get(1).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(1).getZ()|
                    this.faces.get(this.faces.size()-1).getVertices().get(2).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(0).getZ()||
                    this.faces.get(this.faces.size()-1).getVertices().get(3).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(1).getZ()){
                        
                        Face aux = this.faces.get(this.faces.size()-1);
                        this.faces.set(this.faces.size()-1, this.faces.get(this.faces.size()-2));
                        this.faces.set(this.faces.size()-2, aux);
                    } 
                }
            }
            Point3D[] new_face = {faces.get(0).getVertices().get(faces.get(0).getVertices().size()-1),
                                    faces.get(1).getVertices().get(faces.get(1).getVertices().size()-1),
                                    faces.get(1).getVertices().get(0),
                                    faces.get(0).getVertices().get(0)};
            this.faces.add(new Face(new_face));
            Area area_face = new Area(getGeneralPathFace(this.faces.size()-1));
            area_face.intersect(new Area(getGeneralPathFace(this.faces.size()-2)));
            if(!area_face.isEmpty()){
                if(this.faces.get(this.faces.size()-1).getVertices().get(0).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(0).getZ()||
                this.faces.get(this.faces.size()-1).getVertices().get(1).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(1).getZ()|
                this.faces.get(this.faces.size()-1).getVertices().get(2).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(0).getZ()||
                this.faces.get(this.faces.size()-1).getVertices().get(3).getZ()<this.faces.get(this.faces.size()-2).getVertices().get(1).getZ()){

                    Face aux = this.faces.get(this.faces.size()-1);
                    this.faces.set(this.faces.size()-1, this.faces.get(this.faces.size()-2));
                    this.faces.set(this.faces.size()-2, aux);
                }
                 
            }
        }
        else{
            this.faces.add(face);
        }
    }

    private void reset_coordHomogeneas(){
        for(int i=0; i<this.coordenadas_homogeneas.length; i++){
            for(int j=0; j<this.coordenadas_homogeneas.length; j++){
                if(j==i){
                    this.coordenadas_homogeneas[i][j] = 1;
                }else{
                    this.coordenadas_homogeneas[i][j] = 0;
                }
            }             
        }
    }

    public void transladar(double x, double y, double z){
        this.coordenadas_homogeneas[0][3] = x;
        this.coordenadas_homogeneas[1][3] = y;
        this.coordenadas_homogeneas[2][3] = z;

        novas_coordenadas();
    }

    public void escala(double x, double y, double z){
        this.coordenadas_homogeneas[0][0] = x;
        this.coordenadas_homogeneas[1][1] = y;
        this.coordenadas_homogeneas[2][2] = z;

        novas_coordenadas();
    }

    public void reflexao(int x, int y, int z){
        if(x==1){
            this.coordenadas_homogeneas[0][0] = this.coordenadas_homogeneas[0][0]*-1;
        }
        if(y==1){
            this.coordenadas_homogeneas[1][1] = this.coordenadas_homogeneas[1][1]*-1;
        }
        if(z==1){
            this.coordenadas_homogeneas[2][2] = this.coordenadas_homogeneas[2][2]*-1;
        }

        novas_coordenadas();
    }

    public void rotacao(double x, double y, double z){
        if(z!=0.0){
            this.coordenadas_homogeneas[0][0] = Math.cos(z);
            this.coordenadas_homogeneas[0][1] = -Math.sin(z);
            this.coordenadas_homogeneas[1][0] = Math.sin(z);
            this.coordenadas_homogeneas[1][1] = Math.cos(z);
        }else{
            if(x!=0.0){
                this.coordenadas_homogeneas[1][1] = Math.cos(x);
                this.coordenadas_homogeneas[1][2] = -Math.sin(x);
                this.coordenadas_homogeneas[2][1] = Math.sin(x);
                this.coordenadas_homogeneas[2][2] = Math.cos(x);
            }else{
                if(y!=0.0){
                    this.coordenadas_homogeneas[0][0] = Math.cos(y);
                    this.coordenadas_homogeneas[0][2] = Math.sin(y);
                    this.coordenadas_homogeneas[2][0] = -Math.sin(y);
                    this.coordenadas_homogeneas[2][2] = Math.cos(y);
                }
            }
        }
        novas_coordenadas();
    }

    public void projecao_isometrica(){
        this.rotacao(0.0, 2*Math.PI/3, 0.0);
        this.rotacao(2*Math.PI/3, 0.0, 0.0);
        novas_coordenadas();
    }

    protected void imprimir_matriz(double[][] matriz){
        for(int i=0; i<matriz.length; i++){
            System.out.print("[");
            for(int j=0; j<matriz.length; j++){
                    System.out.print(matriz[i][j]+", ");
            }
            System.out.print("]\n");           
        }
    }

    private void novas_coordenadas(){
        this.faces = new ArrayList<Face>();

        for(Point3D[] pontos : this.pontos){
            for(Point3D ponto : pontos){
                double[] matriz_temp = {0.0, 0.0, 0.0, 0.0};
                
                for(int i = 0; i<this.coordenadas_homogeneas.length; i++){
                    matriz_temp[i] =  ponto.getX() * this.coordenadas_homogeneas[i][0]
                                    + ponto.getY() * this.coordenadas_homogeneas[i][1]
                                    + ponto.getZ() * this.coordenadas_homogeneas[i][2]
                                    + 1.0 * this.coordenadas_homogeneas[i][3];
                }

                ponto.setX(matriz_temp[0]/matriz_temp[3]);
                ponto.setY(matriz_temp[1]/matriz_temp[3]);
                ponto.setZ(matriz_temp[2]/matriz_temp[3]);
            }

            this.addFace(pontos);
        }        
        reset_coordHomogeneas();
    }
    

    public int getNumFaces(){
        return this.faces.size();
    }

    private ArrayList<Point3D> getFace(int i){
        return faces.get(i).getVertices();
    }

    public GeneralPath getGeneralPath(){
        GeneralPath path = new GeneralPath();

        path.moveTo(faces.get(0).getVertices().get(0).getX(), faces.get(0).getVertices().get(0).getY());
        for (Face face : faces) {
            for(Point3D ponto : face.getVertices()){
                path.lineTo(ponto.getX(), ponto.getY());
            }
            path.lineTo(face.getVertices().get(0).getX(), face.getVertices().get(0).getY());
        }
        path.closePath();
        return path;
    }

    public GeneralPath getGeneralPathFace(int i){
        GeneralPath path = new GeneralPath();

        path.moveTo(faces.get(i).getVertices().get(0).getX(), faces.get(i).getVertices().get(0).getY());
        for(Point3D ponto : faces.get(i).getVertices()){
            path.lineTo(ponto.getX(), ponto.getY());
        }
        path.lineTo(faces.get(i).getVertices().get(0).getX(), faces.get(i).getVertices().get(0).getY());
        path.closePath();
        return path;
    }
    
} 