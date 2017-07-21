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
public class Vertex {

    public String label;
    public boolean wasVisited;
    public int dad = -1;

    public Vertex(String lab) {
        label = lab;
        wasVisited = false;

    }

    public void setDad(int dad) {
        this.dad = dad;
    }

    public int getDad() {
        return this.dad;
    }

}
