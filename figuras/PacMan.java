package figuras;

import figuras.Figura;
import java.awt.geom.GeneralPath;
import figuras.Point3D;

public class PacMan{
    Figura pacman  = new Figura();

    Point3D p11 = new Point3D(  0.0,  0.0, 0.0);
    Point3D p12 = new Point3D( 30.0, 20.0, 0.0);                         
    Point3D p13 = new Point3D(  0.0, 37.5, 0.0);
    Point3D p14 = new Point3D( 30.0, 55.0, 0.0);
    Point3D p15 = new Point3D(  0.0, 75.0, 0.0);
    Point3D p16 = new Point3D(-30.0, 55.0, 0.0);
    Point3D p17 = new Point3D(-30.0, 20.0, 0.0);

    Point3D p21 = new Point3D(p11.getX(), p11.getY(), 5.0);
    Point3D p22 = new Point3D(p12.getX(), p12.getY(), 5.0);                         
    Point3D p23 = new Point3D(p13.getX(), p13.getY(), 5.0);
    Point3D p24 = new Point3D(p14.getX(), p14.getY(), 5.0);
    Point3D p25 = new Point3D(p15.getX(), p15.getY(), 5.0);
    Point3D p26 = new Point3D(p16.getX(), p16.getY(), 5.0);
    Point3D p27 = new Point3D(p17.getX(), p17.getY(), 5.0);

    Point3D[][] vertices_faces = {{p11, p12, p13, p14, p15, p16, p17}, {p21, p22, p23, p24, p25, p26, p27}};

    public PacMan(){
        for(Point3D[] vertices : vertices_faces){
            for(Point3D ponto : vertices){
                ponto.setX(ponto.getX());
                ponto.setY(ponto.getY());
                ponto.setZ(ponto.getZ());
            }
            pacman.addFacePontos(vertices);
        }
    }

    public void transladar(double x, double y, double z){
        pacman.transladar(x, y, z);
    }

    public void escala(double x, double y, double z){
        pacman.escala(x, y, z);
    }

    public void rotacao(double x, double y, double z){
        pacman.rotacao(x, y, z);
    }

    public GeneralPath getPath(){
        return pacman.getGeneralPath();
    }

    public GeneralPath getPathFace(int i){
        return pacman.getGeneralPathFace(i);
    }

    public int getNumFaces(){
        return pacman.getNumFaces();
    }

    public void reflexao(int x, int y, int z){
        pacman.reflexao(x, y, z);
    }

    public void projecao_isometrica(){
        pacman.projecao_isometrica();
    }

} 