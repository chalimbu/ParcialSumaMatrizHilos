/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author sebastian
 */
public class Matriz {
    public int[][] matriz1;
    public int matriz2[][];
    
    /**
     * el cliente envia el tamaño x y y que comparte ambas matricez y estas se 
     * llenan aleatoriamente
     * @param x columna de la matrices
     * @param y filas de las matricez
     */
    public Matriz(int x,int y){
        matriz1=new int[x][y];
        matriz2=new int[x][y];
        for(int i=0;i<matriz1.length;i++){
            for(int j=0;j<matriz1[0].length;j++){
                matriz1[i][j]=(int)(100*Math.random());
                matriz2[i][j]=(int)(100*Math.random());
            }
        }
        System.out.println(getMatriz(0));
        System.out.println(getMatriz(1));
    }
    
    public String getMatriz(int numeroMatriz){
        int matriz[][];
        String Contenido="";
        if(numeroMatriz==0){
            matriz=matriz1;
        }else{
            matriz=matriz2;
            //System.out.println("daas"+matriz[0][0]);
        }
        
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[0].length;j++){
                Contenido+=matriz[i][j]+",";
            }
            Contenido+="\n";
        }
        return Contenido;
    }
}
