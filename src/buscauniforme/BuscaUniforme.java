/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscauniforme;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Lucas
 */
public class BuscaUniforme {

    private final int MAX_VERTS = 16;
    private Vertex vertexList[];
    private double adjMat[][];
    private int nVerts;

// ------------------------------------------------------------
    public BuscaUniforme() {
        vertexList = new Vertex[MAX_VERTS];

        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int y = 0; y < MAX_VERTS; y++) {
            for (int x = 0; x < MAX_VERTS; x++) {
                adjMat[x][y] = 0;
            }
        }

    }
// ------------------------------------------------------------

    public void addVertex(String lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }
// ------------------------------------------------------------

    public void addEdge(int start, int end, double value) {
        adjMat[start][end] = value;

    }
// ------------------------------------------------------------

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }
// -------------------------------------------------------------

    public void CustoUniforme(int start, int end) {
        vertexList[0].wasVisited = true;
        ArrayList<caminho> fila = new ArrayList<>();
        fila.add(new caminho(start, 0));
        displayVertex(0);

        int v2;

        while (!fila.isEmpty()) {
            int v1 = fila.get(0).getVertice();
            double v = fila.get(0).getValor();
            fila.remove(0);

            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                if (v2 == end) {
                    System.out.println("Chegou");
                } else {
                    vertexList[v2].dad = v1;
                    v = v + adjMat[v1][v2];
                    fila.add(new caminho(v2, v));
                }

            }

            Collections.sort(fila);
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
}
