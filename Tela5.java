import javax.swing.*;
import java.util.*;
import figuras.QuadradoQuebrado;
import figuras.QuadradoCortado;
import figuras.Quadrado;
import figuras.Linha;
import figuras.Octaedro;
import figuras.OctaedroPizza;
import figuras.HexagonoPizza;
import figuras.Pentagono;
import figuras.Hexagono;
import figuras.Triangulo;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tela5 extends JPanel {

    int score = 0;
    JFrame janela;
    long start;
    long end;
    long duracao;

    public Tela5(JFrame janela, int score, long start){
        this.janela = janela;
        this.score = score;
        this.start = start;
        this.end = System.currentTimeMillis();
        this.duracao = (this.end - this.start)/1000;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2D = (Graphics2D) g;

        Font stringFont = new Font( "SansSerif", Font.PLAIN, 20);
        g.setFont(stringFont);
        g.drawString("Parabens! Voce terminou!", 125, 150);
        g.drawString("Pontuacao:"+score+"/4", 125, 170);
        g.drawString("Tempo:"+duracao+"s", 125, 190);
        
    }
}