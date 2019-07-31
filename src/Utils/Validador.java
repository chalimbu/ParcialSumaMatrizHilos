/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author sebastian
 */
public class Validador {
    public static boolean esNumeroPositivo(String number){
        try{
            int numero=Integer.parseInt(number);
            return (numero>=0);
        }catch(Exception e){
            return false;
        }
    }
    
}
