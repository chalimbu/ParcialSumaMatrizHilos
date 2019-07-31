/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnable;

import Utils.Validador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author hrecaman
 */
public class Cliente {
//1.VARIABLE
    static ObjectOutputStream salida;
    static ObjectInputStream entrada;
    //static ObjectInputStream entrada;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = null;
        BufferedReader y;
        String c;//ce;

        System.out.println("Conectando ..\n");
        //InetAddress 
        s = new Socket("localhost", 12345);
        System.out.println("Conectado a" + s.getInetAddress().getHostName());
        while (true) {
            y = new BufferedReader(new InputStreamReader(System.in));
            boolean separadoComa=false;
            String cadenas[];
            do{
            System.out.println("Introduzca el tamaño de la matriz a generar [0-inf],[0,inf]" + " ");
            c = y.readLine();
            cadenas=c.split(",");
            separadoComa=(cadenas.length==2);
            }while(!(separadoComa&&Validador.esNumeroPositivo(cadenas[0])&&Validador.esNumeroPositivo(cadenas[1])));
            
            //flujo salida
            salida = new ObjectOutputStream(s.getOutputStream());
            salida.writeObject(c);
            salida.flush();//vaciar bufer
            System.out.println("Dato enviado\n");
            //entrada
            entrada = new ObjectInputStream(s.getInputStream());
            String mensajeRecibido=(String) entrada.readObject();
            System.out.println(mensajeRecibido);

            if (mensajeRecibido.contains("[exit]")) {
                s.close();
                System.out.println("fin comunicacion");
                break;
            }

        }
// TODO code application logic here
    }

}