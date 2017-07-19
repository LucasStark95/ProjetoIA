package buscauniforme;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/**
 * Created by John Hed on 13/07/2017.
 */
public class Main extends JPanel {

    private List<Point> pontos = new ArrayList<>(15);

    private JFrame janela;
    private JPanel painel;
    private JLabel mapa;
    
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point fillCell : pontos) {
            int cellX = (fillCell.x);
            int cellY = (fillCell.y);
            g.setColor(Color.RED);
            g.fillRoundRect(cellX, cellY, 10, 10, 10, 10);
        }
        for (Line linha : linhas) {
            g.setColor(Color.RED);
            g.drawLine(linha.x1 + 5, linha.y1 + 5, linha.x2 + 5, linha.y2 + 5);
        }
    }

    private void preparaJanela() {
        janela = new JFrame("Rotas de Aviões");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.pack();
        janela.setSize(900, 720);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        
        painelMapa();
        adcionaRotas();
        


    }

    // outros metodos prepara...
    private void painelMapa() {
        painel = new JPanel();
        ImageIcon img = new ImageIcon("src//Imagem//mapa.png");
        mapa = new JLabel(img);
        mapa.setLayout(new FlowLayout());
        painel.add(mapa, BorderLayout.NORTH);
        janela.add(painel, BorderLayout.CENTER);
        janela.pack();
        janela.setVisible(true);
    }

    private void adcionaRotas() {
        Main grid = new Main();
        grid.setSize(900, 720);
        grid.setBackground(new Color(0, 0, 0, 0));
        painel.add(grid);
        leitura();
        for (Point ponto : pontos) {
            grid.MarcaPonto(ponto.x, ponto.y);
        }

    }

    public void MarcaPonto(int x, int y) {
        pontos.add(new Point(x, y));
        repaint();
    }

    public void MarcaLinha(int x1, int y1, int x2, int y2) {
        linhas.add(new Line(x1, y1, x2, y2, Color.red));
        repaint();
    }

    public static void main(String args[]) {
        Main n = new Main();
        n.preparaJanela();
    }

    public void leitura() {
        try {
            BufferedReader myBuffer = new BufferedReader(new InputStreamReader
                (new FileInputStream("src//Arquivos//resultado.txt")));

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
            System.out.println("Erro");
        }

    }

}
