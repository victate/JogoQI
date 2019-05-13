import javax.swing.JPanel;
import java.awt.Shape;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics2D;
import figuras.HexagonoPizza_3d;
import java.awt.event.*;
import javax.swing.JFrame;

public class Menu extends JPanel {

    JFrame janela;
    Menu(JFrame janela){
        this.janela = janela;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2D = (Graphics2D) g;
        ArrayList<Shape> s =new ArrayList<Shape>();
        Double[] trsld = {200.0, 100.0};
        
        Thread thread = new Thread() {
            public void run() {
                HexagonoPizza_3d img = new HexagonoPizza_3d();
                img.Escala(3.0);
                img.espelhar();
                img.girar(Math.PI*2/3);
                s.addAll(img.transladar(trsld));
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
    }


    public static void main(String [] args){
        JFrame janela = new JFrame();
        JPanel panel = new JPanel();
        Box box = Box.createVerticalBox();


        JButton bt_start = new JButton();
        bt_start.setText("Iniciar QUIZ.");
        bt_start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(bt_start);
                janela.remove(panel);
                janela.getContentPane().removeAll();

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


        Thread threadInit = new Thread() {
            public void run() {
                Menu img = new Menu(janela);
                box.add(img);
            }
        };
        try{
            threadInit.start();
            threadInit.join(500);
        }catch (InterruptedException e1){
            System.out.println("Main thread Interrupted");
        }
  
        panel.add(bt_start);
        janela.add(box, BorderLayout.CENTER);
        janela.add(panel, BorderLayout.PAGE_END);
        janela.setSize(500,500);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        

    }
}