/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscauniforme;



/**
 *
 * @author Lucas
 */
public class Transitions {
    private String start;
    private String end;
    private double value;
    private int tempo;
    
    public Transitions(){
    }
    
    public Transitions(String inicio, String fim, int tempo, double valor){
        this.start = inicio;
        this.end = fim;
        this.value = valor;
        this.tempo = tempo;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public double getValue() {
        return value;
    }

    public int getTempo() {
        return tempo;
    }
    
    
    
    
    
}
