import javax.swing.*;
import java.util.*;
import figuras.Quadrado;
import figuras.Linha;
import figuras.HexagonoPizza;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tela3 extends JPanel {

    Double[][] transl1 = {{ 100.0, 20.0 }, { 200.0, 20.0 }, {300.0, 20.0}};
    Double[][] transl2 = {{ 100.0, 120.0 }, { 200.0, 120.0 }, {300.0, 120.0}};
    Double[][] transl3 = {{ 100.0, 200.0 }, { 200.0, 200.0 }, {300.0, 200.0}};
    Double[][] transl4 = {{ 100.0, 400.0 }, { 200.0, 400.0 }, {300.0, 400.0}};
    int score = 0;
    JFrame janela;
    long start;

    public Tela3(JFrame janela, int score, long start){
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
                Quadrado obj1 = new Quadrado();
                Quadrado obj2 = new Quadrado();
                Quadrado obj3 = new Quadrado();
                Linha obj4 = new Linha();
                Quadrado obj5 = new Quadrado();
                Quadrado obj6 = new Quadrado();
                HexagonoPizza obj7 = new HexagonoPizza();
                HexagonoPizza obj8 = new HexagonoPizza();
                
                obj1.Escala(0.8);
                obj1.girar(Math.PI/4);
                s.addAll(obj1.transladar(transl1[0]));

                obj2.Escala(0.8);
                s.addAll(obj2.transladar(transl1[1]));
                
                obj3.Escala(1.2);
                Double[] aux = {transl1[2][0], transl1[2][1]+20.0};
                s.addAll(obj3.transladar(transl1[2]));

                aux[0] = transl2[0][0];
                aux[1] = transl2[0][1];
                s.addAll(obj4.transladar(aux));

                s.addAll(obj5.transladar(transl2[1]));
                
                obj6.Escala(0.5);
                aux[0] = transl2[2][0]+20.0;
                aux[1] = transl2[2][1]+20.0;
                s.addAll(obj6.transladar(aux));

                obj7.Escala(0.8);
                obj7.girar(Math.PI/4);
                aux[0] = transl3[0][0]-20.0;
                aux[1] = transl3[0][1]+10.0;
                s.addAll(obj7.transladar(aux));
                
                obj8.Escala(0.8);
                aux[0] = transl3[1][0];
                aux[1] = transl3[1][1];
                s.addAll(obj8.transladar(aux));

                HexagonoPizza s1 = new HexagonoPizza();
                Quadrado s2 = new Quadrado();
                HexagonoPizza s3 = new HexagonoPizza();

                s1.Escala(1.0);
                aux[0] = transl4[0][0];
                aux[1] = transl4[0][1]-35;
                s.addAll(s1.transladar(aux));

                aux[0] = transl4[1][0];
                aux[1] = transl4[1][1]-30;
                s.addAll(s2.transladar(aux));

                aux[0] = transl4[2][0];
                aux[1] = transl4[2][1]-20.0;
                s3.Escala(0.4);
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
                
                if(y>400){
                    if(x<200){
                        score += 1;
                    }
                    g2D.dispose();
                    janela.getContentPane().removeAll();
                    System.out.println(score);
                    Thread threadJ = new Thread() {
                        public void run() {
                            Tela4 prox = new Tela4(janela, score, start);
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