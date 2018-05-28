/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author PC-Cenpro
 */
public class prueba {
    private final String servidor = "www.peru-software.com";
    private final int puerto = 21;
    private final String usuario = "pp20172@peru-software.com";
    private final String clave = "fisi20172";
    
    public String valores(int total, int cantidad1, int cantidad2){
        FTPFile archivo = mostrarNombres();
        int pos = archivo.getName().indexOf('.');
        int cantidad = (int) (archivo.getSize()/3);
        total = (int) archivo.getSize();
        cantidad1 = cantidad;
        cantidad2 = cantidad + cantidad + 1;
        System.out.println("valores : "+total+" - "+cantidad1+" - "+cantidad2);
        return archivo.getName().substring(0, pos);
    }
    public FTPFile mostrarNombres(){
        FTPFile[] archivos = listarArchivos();
//        for (FTPFile file : archivos) {
//            int pos = file.getName().indexOf('.');
//            String cadena = file.getName().substring(0, pos);
//            System.out.println("Nombre : "+cadena+"Parte");
//            System.out.println("Tamaño total : "+file.getSize());
//            int cantidad = (int) (file.getSize()/3);
//            int tot = (int) (file.getSize());
//            System.out.println("Tamaño 1 : "+" 0 "+" - "+cantidad);
//            int cantidad2 = cantidad+cantidad+1;
//            System.out.println("Tamaño 2 : "+(cantidad+1)+" - "+(cantidad2));
//            System.out.println("Tamaño 3 : "+(cantidad2+1)+" - "+(tot-cantidad2-1));
//        }
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
    
    public void unir() throws IOException{
        try {
            File down1 = new File("./commons-net-3.6.part001.zip");
            File down2 = new File("./commons-net-3.6.part002.zip");
            File down3 = new File("./commons-net-3.6.part003.zip");
            File archivo = new File("./commons-net-3.6-bin.zip");
            byte[] bytes = new byte[4096];
            int leido = -1;
            OutputStream streamLocal = new BufferedOutputStream(new FileOutputStream(archivo,true));
            OutputStream streamLocal2 = new BufferedOutputStream(new FileOutputStream(archivo,true));
            OutputStream streamLocal3 = new BufferedOutputStream(new FileOutputStream(archivo,true));
            InputStream input1 = new BufferedInputStream(new FileInputStream(down1));
            InputStream input2 = new BufferedInputStream(new FileInputStream(down2));
            InputStream input3 = new BufferedInputStream(new FileInputStream(down3));
            while ((leido = input1.read(bytes)) != -1) {
                System.out.println("hola 1");
                streamLocal.write(bytes, 0, leido);
            }
            input1.close();
            streamLocal.close();
            while ((leido = input2.read(bytes)) != -1) {
                System.out.println("hola 2");
                streamLocal2.write(bytes, 0, leido);
            }
            input2.close();
            streamLocal2.close();
            while ((leido = input3.read(bytes)) != -1) {
                System.out.println("hola 3");
                streamLocal3.write(bytes, 0, leido);
            }
            input3.close();
            streamLocal3.close();
            System.out.println("Tamaño : "+archivo.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void copia (String ficheroOriginal, String ficheroCopia, long pos)
	{
		try
		{
                        // Se abre el fichero original para lectura
			FileInputStream fileInput = new FileInputStream(ficheroOriginal);
			BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
			
			// Se abre el fichero donde se hará la copia
			FileOutputStream fileOutput = new FileOutputStream (ficheroCopia,true);
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutput);
                        
                        //Random
                        //RandomAccessFile F = new  RandomAccessFile(ficheroCopia,"rw");          

			
			// Bucle para leer de un fichero y escribir en el otro.
			byte [] array = new byte[4096];
			int leidos = bufferedInput.read(array);
                        //F.seek(F.length());
			while (leidos > 0)
			{
                                //F.write(array,0,leidos);
                            
				//bufferedOutput.write(array,0,leidos);
				leidos=bufferedInput.read(array,0,leidos);
			}

			// Cierre de los ficheros
			bufferedInput.close();
                        //F.close();
			bufferedOutput.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
