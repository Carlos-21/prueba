/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.DescargarPorSemilla;


/**
 *
 * @author CARLOS
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        String nombre = "./commons-net-3.6-bin.zip";
        
        //Medidas calculadas para los inicios y fines de los hilos
        long inicio1 = 0;
        long fin1 = 703140;
        
        long inicio2 = 703140;
        long fin2 = 1406280;
        
        long inicio3 = 1406280;
        long fin3 = 2109420;
        
        //Hilo 1
        Hilo h1 = new Hilo(nombre);
        h1.setInicio(inicio1);
        h1.setFin(fin1);
        
        //Hilo 2
        Hilo h2 = new Hilo(nombre);
        h2.setInicio(inicio2);
        h2.setFin(fin2);
        
        //Hilo 3
        Hilo h3 = new Hilo(nombre);
        h3.setInicio(inicio3);
        h3.setFin(fin3);
        
        //Lanzamiento de los hilos
        h1.start();
        h2.start();
        h3.start();
        
        h1.join();
        h2.join();
        h3.join();
        
    }
    
}
