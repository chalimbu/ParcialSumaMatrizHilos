/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnable;


import Beans.Matriz;

import Hilos.SumadorFilas;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sebastian
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    
    public static SumadorFilas sumaFila[][];
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
    //objetos para el server tcp
    ObjectInputStream entrada;
    
    ServerSocket s=new ServerSocket(12345, 30000);
    Socket s1;
    
    
        // TODO code application logic here
   //     CampoBatalla cb=new CampoBatalla();
   //     GeneradorBarco gb[]=new GeneradorBarco[4];
   //     for(int i=0;i<gb.length;i++){
   //         gb[i]=new GeneradorBarco(cb);
   //         gb[i].start();
   //     }
   //     for(int i=0;i<gb.length;i++){//se hacen separados para hacer el paralelismo
   //         gb[i].join();
   //     }
   //     cb.imprimirCampo();
        
        while(true){
            System.out.println("Esperando cliente ..");
            //crear metodos con ayuda
           s1=esperarConexion(s);
           obtenerFlujos(s1);
    }
            //
     
    }
    
    private static Socket esperarConexion(ServerSocket s) throws IOException {
        //3. codigo
        Socket s1 = s.accept();
        System.out.println("Se ha conectado un cliente desde" + s1.getInetAddress());
        return s1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static void obtenerFlujos(Socket s1) throws IOException, ClassNotFoundException, InterruptedException {
        //4.codigo
        while (true) {
            //flujo entrada para leer socket
            ObjectInputStream entrada = new ObjectInputStream(s1.getInputStream());
            String cadena = (String) entrada.readObject();
            System.out.println("Dato Recibido" + cadena);
            String dimensiones[]=cadena.split(",");
            
            //DEVOLVERLE LOS DATOS AL CLIENTE
            EnviaRetroalimentacionAl(dimensiones,s1);
            

        }

        //thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private static void EnviaRetroalimentacionAl(String mensaje[],Socket s1) throws IOException, InterruptedException{
         final int numeroFilas=Integer.parseInt(mensaje[0]);
         final int numeroColumnas=Integer.parseInt(mensaje[1]);
        Matriz miMatriz=new Matriz(numeroFilas,numeroColumnas);
        sumaFila=new SumadorFilas[numeroFilas][2];

        //Realizo las sumas para la primera y segunda matriz
        for(int i=0;i<numeroFilas;i++){
            sumaFila[i][0]=new SumadorFilas(miMatriz.matriz1[i]);
            sumaFila[i][1]=new SumadorFilas(miMatriz.matriz2[i]);
            sumaFila[i][0].start();
            sumaFila[i][1].start();
        }
        
        
        //realizo los join para saber que las sumas acabaron
        for(int i=0;i<numeroFilas;i++){
            sumaFila[i][0].join();
            sumaFila[i][1].join();
        }
        
        
        ObjectOutputStream salida = new ObjectOutputStream(s1.getOutputStream());
        salida.writeObject("Matriz 1:\n"+miMatriz.getMatriz(0)+"\n Matriz 2 \n"+miMatriz.getMatriz(1)+
                "\n"+getSumaFilas(0)+"\n"+getSumaFilas(1)+"\n"+getSumaTotal()+"[exit]");    
        salida.flush();//vaciar bufer
        System.out.println("Dato enviado\n");
                 
    }
     
     public static String getSumaFilas(int matriz){
         String Contenido="Suma filas M"+(matriz+1);
         for(int i=0;i<sumaFila.length;i++){
             Contenido+="\n"+sumaFila[i][matriz].total;
         }
         return Contenido;
     }
     
     public static String getSumaTotal(){
         int total=0;
         String contenido="Sumatorial Total ";
         for(int i=0;i<sumaFila.length;i++){
             total+=sumaFila[i][0].total;
             total+=sumaFila[i][1].total;
         }
         return contenido+total;
     }

}
