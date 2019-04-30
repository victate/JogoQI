import javax.swing.*;
import java.util.*;
import figuras.QuadradoQuebrado;
import figuras.QuadradoCortado;
import figuras.Linha;
import figuras.Octaedro;
import figuras.OctaedroPizza;
import figuras.Pentagono;
import figuras.Hexagono;
import figuras.Triangulo;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tela2 extends JPanel {

    Double[][] transl1 = {{ 100.0, 20.0 }, { 200.0, 20.0 }, {300.0, 20.0}};
    Double[][] transl2 = {{ 100.0, 100.0 }, { 200.0, 100.0 }, {300.0, 100.0}};
    Double[][] transl3 = {{ 100.0, 180.0 }, { 200.0, 180.0 }, {300.0, 180.0}};
    Double[][] transl4 = {{ 100.0, 380.0 }, { 200.0, 380.0 }, {300.0, 380.0}};
    int score = 0;
    JFrame janela;
    long start;

    public Tela2(JFrame janela, int score, long start){
        this.janela = janela;
        this.score = score;
        this.start = start;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2D = (Graphics2D) g;
        Thread thread;
        ArrayList<Shape> s =new ArrayList<Shape>();

        thread = new Thread() {
            public void run() {
                Pentagono obj1 = new Pentagono();
                Triangulo obj2 = new Triangulo();
                Octaedro obj3 = new Octaedro();
                Pentagono obj4 = new Pentagono();
                Triangulo obj5 = new Triangulo();
                Linha obj6 = new Linha();

                Hexagono obj7 = new Hexagono();
                Triangulo obj8 = new Triangulo();
                
                s.addAll(obj1.transladar(transl1[0]));

                obj2.girar(-Math.PI/2);
                Double[] aux = {transl1[1][0]-60, transl1[1][1]+60};
                s.addAll(obj2.transladar(aux));

                s.addAll(obj3.transladar(transl1[2]));

                s.addAll(obj4.transladar(transl2[0]));

                obj5.girar(Math.PI/2);
                s.addAll(obj5.transladar(transl2[1]));
                
                s.addAll(obj6.transladar(transl2[2]));

                s.addAll(obj7.transladar(transl3[0]));
                obj8.girar(Math.PI/2);
                s.addAll(obj8.transladar(transl3[1]));

                Linha s1 = new Linha();
                Pentagono s2 = new Pentagono();
                Triangulo s3 = new Triangulo();
                s.addAll(s1.transladar(transl4[0]));
                s.addAll(s2.transladar(transl4[1]));
                s3.girar(-Math.PI/2);
                aux[0] = transl4[2][0]-60;
                aux[1] = transl4[2][1]+60.0;
                s.addAll(s3.transladar(aux));
               
                
            }
        };   
        try{
            thread.start();
            thread.join(500);
        }catch (InterruptedException e){
            System.out.println("Main thread Interrupted");
        }

        for(int j=0; j<s.size(); j++){
            g2D.draw(s.get(j));
        }

        janela.addMouseListener(new MouseListener(){
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                if(y>380){
                    if(x>300){
                        score += 1;
                    }
                    g2D.dispose();
                    janela.getContentPane().removeAll();
                    
                    System.out.println(score);
                    Thread threadJ = new Thread() {
                        public void run() {
                            Tela3 prox = new Tela3(janela, score, start);
                            janela.add(prox);
                        }
                    };   
                    try{
                        threadJ.start();
                        threadJ.join(500);
                    }catch (InterruptedException e1){
                        System.out.println("Main thread Interrupted");
                    }                        
                    janela.repaint();
                    janela.setVisible(true);

                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
        
            @Override
            public void mouseExited(MouseEvent e) {}
        
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseClicked(MouseEvent e) {}
        });
    }
}