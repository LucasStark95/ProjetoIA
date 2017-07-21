package buscauniforme;

public class caminho implements Comparable<caminho> {

    private int vertice;
    private double valor;

    public caminho(int vertice, double valor) {
        this.vertice = vertice;
        this.valor = valor;
    }

    public caminho() {
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(caminho ca) {
        if (this.valor < ca.valor) {
            return -1;
        }
        if (this.valor > ca.valor) {
            return 1;
        }
        return 0;
    }

}
