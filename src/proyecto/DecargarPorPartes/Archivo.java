/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.DecargarPorPartes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author CARLOS
 */
public class Archivo {
    
    public static String valores(){
        return "commons-net-3.6-bin";
    }
    
    public static void copia(String ficheroOriginal, String ficheroCopia) {
        try {
            // Se abre el fichero original para lectura
            FileInputStream fileInput = new FileInputStream(ficheroOriginal);
            BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

            // Se abre el fichero donde se hará la copia
            FileOutputStream fileOutput = new FileOutputStream(ficheroCopia, true);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
            
            // Bucle para leer de un fichero y escribir en el otro.
            byte[] array = new byte[4096];
            int leidos = bufferedInput.read(array);
            
            while (leidos > 0) {
                bufferedOutput.write(array,0,leidos);
                leidos = bufferedInput.read(array, 0, leidos);
            }

            // Cierre de los ficheros
            bufferedInput.close();
            bufferedOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
