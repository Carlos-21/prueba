/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba3;

import prueba1.prueba;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author CARLOS
 */
public class Archivo {
    private final String servidor = "www.peru-software.com";
    private final int puerto = 21;
    private final String usuario = "pp20172@peru-software.com";
    private final String clave = "fisi20172";
    
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
            
            //Random
            RandomAccessFile F = new  RandomAccessFile(ficheroCopia,"rw");   
            
            // Bucle para leer de un fichero y escribir en el otro.
            byte[] array = new byte[4096];
            int leidos = bufferedInput.read(array);
            
            F.seek(F.length());
            
            while (leidos > 0) {
                //bufferedOutput.write(array,0,leidos);
                F.write(array,0,leidos);
                leidos = bufferedInput.read(array, 0, leidos);
            }

            // Cierre de los ficheros
            bufferedInput.close();
            //bufferedOutput.close();
            F.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FTPFile mostrarNombres(){
        FTPFile[] archivos = listarArchivos();
        return archivos[6];
    }
    
    public FTPFile[] listarArchivos() {
        FTPClient ftpCliente = new FTPClient();
        FTPFile[] arreglo = null;
        try {
            ftpCliente.connect(servidor, puerto);
            ftpCliente.login(usuario, clave);
            ftpCliente.enterLocalPassiveMode();
            FTPFile[] files = ftpCliente.listFiles();
            arreglo = inicializarArreglo(arreglo, files);
            return arreglo;
        } catch (IOException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ftpCliente.isConnected()) {
                try {
                    ftpCliente.logout();
                    ftpCliente.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return arreglo;
    }
    
    public FTPFile[] inicializarArreglo(FTPFile[] arreglo, FTPFile[] files) {
        int cantidad = 0;
        for (FTPFile file : files) {
            if (file.isFile() && !(file.getName().charAt(0) == '.')) {
                cantidad++;
            }
        }
        int i = 0;
        arreglo = new FTPFile[cantidad];
        for (FTPFile file : files) {
            if (file.isFile() && !(file.getName().charAt(0) == '.')) {
                arreglo[i] = file;
                i++;
            }
        }
        return arreglo;
    }
}
