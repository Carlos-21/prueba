/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.DecargarPorPartes;

/**
 *
 * @author CARLOS
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        String nombre = prueba3.Archivo.valores();
        String directorio = "D:\\Programación Paralela\\FTPSemilla\\Archivos Semillas";
        
        long inicio1 = 0;
        long fin1 = 703140;
        
        long inicio2 = 703140;
        long fin2 = 1406280;
        
        long inicio3 = 1406280;
        long fin3 = 2109420;
        
        Hilo h1 = new Hilo(nombre+"part001");
        h1.setInicio(inicio1);
        h1.setFin(fin1);
        
        Hilo h2 = new Hilo(nombre+"part002");
        h2.setInicio(inicio2);
        h2.setFin(fin2);
        
        Hilo h3 = new Hilo(nombre+"part003");
        h3.setInicio(inicio3);
        h3.setFin(fin3);
        
        h1.start();
        h2.start();
        h3.start();
        
        h1.join();
        h2.join();
        h3.join();
        
        /*Archivo.copia(directorio + "./commons-net-3.6-binpart001.zip", directorio + "./commons-net-3.6-bin.zip");
        Archivo.copia(directorio + "./commons-net-3.6-binpart002.zip", directorio + "./commons-net-3.6-bin.zip");
        Archivo.copia(directorio + "./commons-net-3.6-binpart003.zip", directorio + "./commons-net-3.6-bin.zip");*/
    }
    
}
