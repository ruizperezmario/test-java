/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java;

/**
 *
 * @author Mario
 */
public class Examinador extends Thread{
   

    EspacioComun miEspacio;
    long time;
    
    public Examinador(String nombreHilo, EspacioComun miEspacio, long timeStamp){

        this.miEspacio = miEspacio;  
        this.time = timeStamp;
        this.setName(nombreHilo);
    }
    
    public boolean esPrimo(int n){
        boolean esPrimo;
        
        /*Excepcion: 0 y 1 NO son primos*/
        if (n == 0 || n == 1){
            esPrimo = false;
            return esPrimo;
        }
        
        /*Excepción: 2 es primo.*/
        if (n == 2){
            esPrimo = true;
            return esPrimo; //FIn de la ejecución;
        }
        
        /*Primero determino si es par o no n. Si es par entonces no es primo*/
        double resto = n % 2;
        
        if (resto == 0){
            esPrimo = false;    //Al ser divisible por 2 significa que n es par, y si es par entonces no es Primo.                 
        }else{
            /*Regla Matematica: No es necesario probar todos los factores desde 2 hasta n - 1 sino que se puede probar 
             * desde 2 hasta la raíz cuadrada de n redondeada hacia arriba. 
             */
            double resultDouble = Math.sqrt(n);
            int limitFactores = (int) Math.ceil(resultDouble); //Redondea hacia arriba.       
            
            for (int i = 2; i <= limitFactores; i++){
                double restoTmp = n % i;
                if (restoTmp == 0){
                    esPrimo = false;
                    return esPrimo; //Fin de ejecución el numero n no es primo. 
                }
            }
            
            /*Si la ejecución ha avanzando hasta este punto signifca que el numero es primo*/
            esPrimo = true;
        }
        
        return esPrimo;
    }
    
    public boolean primoCircular(int n){
      
        int nCopia = n;
        boolean primo = esPrimo(n);
        
        if (primo){
            n = rotar(n);
            
            while(nCopia != n){
                primo = esPrimo(n);
                
                if (primo == false){
                    return primo;
                }else{
                    n = rotar(n);
                }
            }            
        }
        
        return primo;
    }
    
    public int rotar(int n){
        int numeroRotado = 0;
        String numeroString = Integer.toString(n);
        int[] array = new int[numeroString.length()];
        
        for (int i=0; i < numeroString.length(); i++){
            array[i] = Integer.parseInt("" + numeroString.charAt(i));
        }     
  
        array = rotarDerecha(array);
        
         String strRotado = "";         
       
        for (int i=0; i < array.length; i++){
             strRotado = strRotado + Integer.toString(array[i]);
        }
        numeroRotado = Integer.parseInt(strRotado);
       
        return numeroRotado;
    }

     public int[] rotarDerecha(int[] array){
         int ultimo = array[array.length - 1];
         
         for(int i = array.length - 1; i > 0; i--){
             array[i] = array[i-1];
         }
         
         array[0] = ultimo;
         return array;
     }
     
     public void run(){
         System.out.println("El hilo " + this.getName() + " comenzo en el tiempo: " + 
                 (System.currentTimeMillis() - this.time)  
					+ " milisegundos");
         
         while( miEspacio.sePuedeProcesar()){
             
                int analizar = miEspacio.incrementarContador();
                boolean result = primoCircular(analizar);

                if (result){
                    miEspacio.put(analizar);
                }
         }
         
        miEspacio.incrementarContadorHilos();   //Hilo finalizado.
        System.out.println("El hilo " + this.getName() + " HA TERMINADO DE PROCESAR EN EL TIEMPO: " 
						+ (System.currentTimeMillis() - this.time) 
						+ " milisegundos");
     }

}

