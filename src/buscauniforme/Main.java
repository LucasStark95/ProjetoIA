package buscauniforme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 *
 */
public class Main {

    private BuscaUniforme Grafo = new BuscaUniforme();
    private JFrame janela;
    private JPanel painel;
    private JLabel mapa;
    private JComboBox<String> orig;
    private JComboBox<String> dest;
    private JButton btnBuscar;
    private JButton btnNovo;
    private Grid gridPontos = new Grid();



    private void preenche(int i) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        // abertura do arquivo
        try {
            BufferedReader myBuffer = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Arquivos/cidades.txt"),"ISO-8859-1"));

            // loop que lê e imprime todas as linhas do arquivo
            String cidade = myBuffer.readLine();

            while (cidade != null) {
                model.addElement(cidade);
                cidade = myBuffer.readLine();
            }

            myBuffer.close();
            switch (i) {
                case 1:
                    orig.setModel(model);
                    break;
                case 2:
                    dest.setModel(model);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }

    }

    private void popularGrafo() {
        try {
          
            BufferedReader brc = new BufferedReader(
                    new InputStreamReader(
                            getClass().getResourceAsStream("/Arquivos/cidades.txt"),"ISO-8859-1"));
                            
            String cid = brc.readLine();
            
            while (cid != null) {
                Grafo.addVertex(cid);
                cid = brc.readLine();
                
            }
            
            brc.close();

            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    getClass().getResourceAsStream("/Arquivos/rotas.txt")));
            String linha;
            while ((linha = br.readLine()) != null) {
                String rota[] = new String[3];
                rota = linha.split(";");
                int start = Integer.parseInt(rota[0]);
                int end = Integer.parseInt(rota[1]);
                double value = Double.parseDouble(rota[2]);

                
                Grafo.addEdge(start, end, value);
                
            }
            
            br.close();

        } catch (Exception e) {

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
        janela.setBackground(Color.GRAY);
        painelMapa();
    }
    
    public void init(){
        preparaJanela();
    }

    // outros metodos prepara...
    private void painelMapa() {
        painel = new JPanel();
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagem/mapa.png"));
        mapa = new JLabel(img);
        mapa.setLayout(new FlowLayout());
        JPanel menu = new JPanel();
        btnBuscar = new JButton("Buscar");
        btnNovo = new JButton("Nova Busca");
        btnNovo.setEnabled(false);
        orig = new JComboBox<String>();
        dest = new JComboBox<String>();
        popularGrafo();
        preenche(1);
        preenche(2);
        menu.setSize(300, 300);
        menu.setVisible(true);
        menu.setBackground(Color.GRAY);
        menu.add(orig);
        menu.add(dest);
        menu.add(btnBuscar);
        menu.add(btnNovo);
        painel.setBackground(Color.GRAY);
        painel.add(mapa, BorderLayout.NORTH);
        janela.add(painel, BorderLayout.CENTER);
        janela.add(menu, BorderLayout.NORTH);
        janela.pack();
        janela.setVisible(true);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int start = orig.getSelectedIndex();
                int end = dest.getSelectedIndex();
                Grafo.CustoUniforme(start, end);
                if(!Grafo.caminhoFinal.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, " " + Grafo.caminhoFinal);
                }

                gridPontos.init();
                mapa.add(gridPontos, BorderLayout.CENTER);
                mapa.setVisible(true);
                mapa.paint(gridPontos.getGraphics());
                btnNovo.setEnabled(true);
                btnBuscar.setEnabled(false);
                orig.setEnabled(false);
                dest.setEnabled(false);

            }
        });
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Main n = new Main();
                n.preparaJanela();
            }
        });
    }


    public static void main(String args[]) {
        Main n = new Main();
        n.init();

    }

}
