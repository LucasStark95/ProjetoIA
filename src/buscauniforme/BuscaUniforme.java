/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscauniforme;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class BuscaUniforme {

    private final int MAX_VERTS = 17;
    private Vertex vertexList[];
    private double adjMat[][];
    private int nVerts;
    private ArrayList<Transitions> valores;
    public String caminho;

// ------------------------------------------------------------
    public BuscaUniforme() {
        this.valores = new ArrayList<Transitions>();
        vertexList = new Vertex[MAX_VERTS];

        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int y = 0; y < MAX_VERTS; y++) {
            for (int x = 0; x < MAX_VERTS; x++) {
                adjMat[x][y] = 0;
            }
        }

        carregar();

    }
// ------------------------------------------------------------

    public void addVertex(String lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }
// ------------------------------------------------------------

    public void addEdge(int start, int end, double value) {
        adjMat[start][end] = value;
        adjMat[end][start] = value;
        
    }
// ------------------------------------------------------------

    public void CustoUniforme(int start, int end) {
        vertexList[0].wasVisited = true;
        ArrayList<caminho> fila = new ArrayList<>();
        fila.add(new caminho(start, 0));
        int fim = 0;

        int v2;
        if (start == end) {
            JOptionPane.showMessageDialog(null, "Estados Iguais");

        } else {

            while (!fila.isEmpty()) {
                int v1 = fila.get(0).getVertice();
                double v = fila.get(0).getValor();
                fila.remove(0);

                while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                    vertexList[v2].wasVisited = true;
                    if (v2 == end) {
                        System.out.println("Achou");
                        //retrocesso(v1, end);
                    } else {
                        fim = v2;
                        vertexList[v2].dad = v1;
                        double valor = v + adjMat[v1][v2];
                        fila.add(new caminho(v2, valor));
                    }

                }

                Collections.sort(fila);
            }
            if(fim != end){

            }

        }

        for (int j = 0; j < nVerts; j++) {
            vertexList[j].wasVisited = false;
            vertexList[j].setDad(-1);
        }
    }
// ------------------------------------------------------------

// ------------------------------------------------------------- 
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] != 0 && vertexList[j].wasVisited == false) {
                return j;
            }
        }
        return -1;
    }
// ------------------------------------------------------------

    private void retrocesso(int vertice, int chegada) {
        caminho = "";

        ArrayList<Vertex> List = new ArrayList<>();
        List.add(vertexList[vertice]);
        int valor = vertexList[vertice].dad;
        while (valor != -1) {
            List.add(vertexList[valor]);
            valor = vertexList[valor].dad;
        }

        for (int j = List.size() - 1; j >= 0; j--) {
            caminho = caminho + " " + List.get(j).label + " ->";
        }

        caminho = caminho + " " + vertexList[chegada].label+" \n";
        double value = 0;
        double tempo = 0;
        for (int j = List.size() - 1; j >= 0; j--) {
            String destino = vertexList[j].label;
            int start = vertexList[j].dad;
            if (start != -1) {
                String origem = vertexList[start].label;
                for (Transitions t : valores) {
                    if ((t.getStart().equalsIgnoreCase(origem) && t.getEnd().equalsIgnoreCase(destino)) 
                            || (t.getStart().equalsIgnoreCase(destino) && t.getEnd().equalsIgnoreCase(origem))) {
                        value += t.getValue();
                        tempo += t.getTempo();
                    }
                }
            }
        }
        for (Transitions t : valores) {
            if ((t.getStart().equalsIgnoreCase(vertexList[vertice].label) && t.getEnd().equalsIgnoreCase(vertexList[chegada].label)) 
                    || (t.getStart().equalsIgnoreCase(vertexList[chegada].label) && t.getEnd().equalsIgnoreCase(vertexList[vertice].label))) {
                value += t.getValue();
                tempo += t.getTempo();
            }
        }
        caminho +="Tempo: " + tempo + " Valor: " + value;

    }

    private void carregar() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src\\Arquivos\\valores.txt"), "ISO-8859-1"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String rota[] = new String[4];
                rota = linha.split(";");
                int time = Integer.parseInt(rota[2]);
                double value = Double.parseDouble(rota[3]);
                valores.add(new Transitions(rota[0], rota[1], time, value));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
}
