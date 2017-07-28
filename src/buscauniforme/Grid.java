package buscauniforme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Grid extends JPanel {
    private List<Point> pontos = new ArrayList<>(15);

    private static class Line {

        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<Line> linhas = new LinkedList<Line>();

    public Grid() {
        setSize(900, 720);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point fillCell : pontos) {
            int cellX = (fillCell.x);
            int cellY = (fillCell.y);
            g.setColor(Color.red);
            g.fillRoundRect(cellX, cellY, 10, 10, 10, 10);
        }
        for (Line linha : linhas) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(Color.red);
            g2d.setStroke(new BasicStroke(3.0f));
            g2d.draw(new Line2D.Double(linha.x1 + 5, linha.y1 + 5, linha.x2 + 5, linha.y2 + 5));
        }
        g.dispose();
    }

    public void limpar() {
        getGraphics().dispose();
    }

    public void inserirRotas() {
        for (int i = 0; i < pontos.size() - 1; i++) {
            linhas.add(new Line(pontos.get(i).x, pontos.get(i).y, pontos.get(i + 1).x, pontos.get(i + 1).y, Color.red));

        }
    }

    public void leituraResultado() {
        try {
            
            BufferedReader myBuffer = new BufferedReader(new InputStreamReader(new FileInputStream("resultado.txt")));

            // loop que lê e imprime todas as linhas do arquivo
            String pt = myBuffer.readLine();
            while (pt != null) {
                if (pt != null) {
                    String[] valores = pt.split(";");
                    this.pontos.add(new Point(Integer.parseInt(valores[0]), Integer.parseInt(valores[1])));
                }
                pt = myBuffer.readLine();

            }

            myBuffer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "não encontrou");
        }

    }

    public void init() {
        leituraResultado();
        inserirRotas();
    }

    /* Teste da classe
    public static void main(String args[]) throws InterruptedException {

        Grid g = new Grid();

        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.pack();
        janela.setSize(900, 720);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.add(g);
        g.init();
    }
*/
}
