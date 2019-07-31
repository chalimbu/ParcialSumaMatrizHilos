/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

/**
 *
 * @author sebastian
 */
public class SumadorFilas extends Thread{
    int fila[];
    public int total;
    public SumadorFilas(int fila[]){
        this.fila=fila;    
    }
    
    @Override
    public void start(){
        int sumaTotal=0;
        for(int i=0;i<fila.length;i++){
            sumaTotal+=fila[i];
        }
        total=sumaTotal;
        System.out.println(total);
    }
    
    
    
}
