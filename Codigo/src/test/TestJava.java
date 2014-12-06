/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Mario
 */
public class TestJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int cantNumeros = 1000000;
        int cantHilos = 10;
        Calculador calcu = new Calculador();
        calcu.calcularPrimosCirculares(cantNumeros, cantHilos);
    }
}
