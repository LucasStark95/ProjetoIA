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
public class Vertex  {
   public String label;
   public boolean wasVisited;
   private int x, y;
   public int dad = -1;

   public Vertex(String lab) 
      {
      label = lab;
      wasVisited = false;
      
      } 
   
   public Vertex(String lab, int x, int y) 
      {
      label = lab;
      wasVisited = false;
      this.x = x;
      this.y = y;
      } 


    public void setDad(int dad) {
        this.dad = dad;
    }
    
    public int getDad(){
        return this.dad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


   
    
    
}
