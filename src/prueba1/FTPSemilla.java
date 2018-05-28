/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC-Cenpro
 */
public class FTPSemilla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        prueba p = new prueba();
        int cantidad1=703140;
        int cantidad2=1406280;
        int total=2109420;
        p.unir();
//        p.copia("./commons-net-3.6.part001.zip","./commons-net-3.6-bin.zip",1);
//        p.copia("./commons-net-3.6.part002.zip","./commons-net-3.6-bin.zip",700516);
//        p.copia("./commons-net-3.6.part003.zip","./commons-net-3.6-bin.zip",1405696);
       String nombre = p.valores(total, cantidad1, cantidad2);
//        
          //hilo h1 = new hilo(0, cantidad1, nombre+".6.part001");
////        hilo h2 = new hilo(cantidad1, cantidad2, nombre+".6.part002");
//        long canti = 703140;
//        long c = 1406280;
//        //hilo h2 = new hilo(canti, cantidad2, nombre+".6.part002");
////        //h2.inicio = canti;
//////        hilo h3 = new hilo(cantidad2, total, nombre+".6.part003");
//        hilo h3 = new hilo(c, total, nombre+".6.part003");
//        //h1.start();
//        //h2.start();
//        h3.start();
//        try {
//            h1.join();
//            h2.join();
//            h3.join();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(FTPSemilla.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
