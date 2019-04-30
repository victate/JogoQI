import javax.swing.*;
import java.util.*;
import figuras.QuadradoQuebrado;
import figuras.QuadradoCortado;
import figuras.OctaedroPizza;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tela1 extends JPanel {

    Double[][] transl1 = {{ 100.0, 20.0 }, { 200.0, 20.0 }, {300.0, 20.0}};
    Double[][] transl2 = {{ 100.0, 100.0 }, { 200.0, 100.0 }, {300.0, 100.0}};
    Double[][] transl3 = {{ 100.0, 180.0 }, { 200.0, 180.0 }, {300.0, 180.0}};
    Double[][] transl4 = {{ 100.0, 380.0 }, { 200.0, 380.0 }, {300.0, 380.0}};
    int score = 0;
    JFrame janela;
    long start;

    public Tela1(JFrame janela, int score, long start){
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
                QuadradoQuebrado obj1 = new QuadradoQuebrado();
                QuadradoQuebrado obj2 = new QuadradoQuebrado();
                QuadradoQuebrado obj3 = new QuadradoQuebrado();
                OctaedroPizza obj4 = new OctaedroPizza();
                OctaedroPizza obj5 = new OctaedroPizza();
                OctaedroPizza obj6 = new OctaedroPizza();

                QuadradoCortado obj7 = new QuadradoCortado();
                QuadradoCortado obj8 = new QuadradoCortado();
                
                s.addAll(obj1.transladar(transl1[0]));
                obj2.espelhar();
                s.addAll(obj2.transladar(transl1[1]));
                obj3.girar(Math.PI/2);
                s.addAll(obj3.transladar(transl1[2]));
                s.addAll(obj4.transladar(transl2[0]));
                obj5.espelhar();
                s.addAll(obj5.transladar(transl2[1]));
                obj6.girar(Math.PI/2);
                s.addAll(obj6.transladar(transl2[2]));

                s.addAll(obj7.transladar(transl3[0]));
                obj8.espelhar();
                s.addAll(obj8.transladar(transl3[1]));

                QuadradoCortado s1 = new QuadradoCortado();
                QuadradoCortado s2 = new QuadradoCortado();
                QuadradoCortado s3 = new QuadradoCortado();
                s.addAll(s1.transladar(transl4[0]));
                s2.girar(Math.PI);
                Double[] aux = {transl4[1][0], transl4[1][1]+60.0};
                s.addAll(s2.transladar(aux));
                s3.girar(Math.PI/2);
                s.addAll(s3.transladar(transl4[2]));
                
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
                    if(x>200 && x<300){
                        score += 1;
                    }
                    g2D.dispose();
                    janela.getContentPane().removeAll();
                    Thread threadJ = new Thread() {
                        public void run() {
                            Tela2 prox = new Tela2(janela, score, start);
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


    public static void main(String [] args){
        JFrame janela = new JFrame();

        JButton bt_start = new JButton();
        bt_start.setText("Iniciar QUIZ.");
        bt_start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.remove(bt_start);

                Thread threadJ = new Thread() {
                    public void run() {
                        long start = System.currentTimeMillis();
                        Tela1 prox = new Tela1(janela, 0, start);
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
        });
    
        janela.add(bt_start);
        janela.setSize(500,500);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

    }
}





