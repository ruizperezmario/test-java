/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mario
 */
public class EspacioComun {
        
        ArrayList<Integer> listaPrimosCirculares = new ArrayList<Integer>(); 
        
        boolean valueSet = false;
        int contadorNumeros;
        
        int contadorHilos = 0;
        int cantHilos;
        int cantMaxNumero;
        
        public EspacioComun(int cantHilos, int cantMaxNumero){
            this.cantHilos = cantHilos;
            contadorNumeros = 0;
            this.cantMaxNumero = cantMaxNumero;
        }
        
        synchronized int incrementarContador(){
            contadorNumeros = contadorNumeros + 1;
            return contadorNumeros;
        }
        
        synchronized void put(int n) {
               listaPrimosCirculares.add(n);    
        }
        
        synchronized boolean sePuedeProcesar(){
            if (contadorNumeros < cantMaxNumero)
               return true;
            else
                return false;
        }
        
         
        synchronized void incrementarContadorHilos(){
            contadorHilos++;
            if (contadorHilos == cantHilos) {
               notify(); 
            }
        }
        
        
        
        synchronized ArrayList<Integer> obtenerListado() {
           if (contadorHilos != cantHilos) 
                       try { wait(); } catch(InterruptedException e){};
            notify();
            
           Collections.sort(listaPrimosCirculares);
           return listaPrimosCirculares;  
        }

}
