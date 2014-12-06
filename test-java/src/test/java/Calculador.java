/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java;

import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class Calculador {
       //limiteSuperiorAnalizar: Numeros a examinar si son primos circulares o no.
    public void calcularPrimosCirculares(int limiteSuperiorAnalizar, int cantHilos){    
        
        ArrayList<Examinador> listaHilos = new ArrayList();          
       
        int contadorHilos = 0;
        long initialTime;
        
        EspacioComun miEspacio = new EspacioComun(cantHilos, limiteSuperiorAnalizar);      
        Examinador exam;
        
        while (contadorHilos < cantHilos){
            
            initialTime = System.currentTimeMillis();
            exam = new Examinador("Hilo " + contadorHilos, miEspacio, initialTime);
            listaHilos.add(exam);
            listaHilos.get(contadorHilos).start();
            
            contadorHilos++;
        }
        
        ArrayList<Integer> listado = miEspacio.obtenerListado();
        
        for(int i=0; i < listado.size(); i++){
            System.out.println(listado.get(i));
        } 
         
        System.out.println("Cantidad de numeros primos circulares: " + listado.size());
    
    }
}
