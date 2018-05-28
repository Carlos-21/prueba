/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author PC-Cenpro
 */
public class hilo extends Thread{
    private final String servidor = "www.peru-software.com";
    private final int puerto = 21;
    private final String usuario = "pp20172@peru-software.com";
    private final String clave = "fisi20172";
      long inicio;
     final int fin;

     //Prueba 1296496
    public hilo(long inicio, int fin, String name) {
        super(name);
        this.inicio = inicio;
        this.fin = fin;
    }
    
    @Override
    public void run(){
        FTPClient ftp = new FTPClient();
        try {
            boolean bandera2 = true;
            ftp.connect(servidor, puerto);
            ftp.login(usuario, clave);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            File down1 = new File("./commons-net-3.6-bin.zip");
            File down2 = new File("./"+getName()+".zip");
//            if(bandera2){
//                ftp.setRestartOffset(inicio);
//                System.out.println("Inicio : "+" "+getName()+" "+inicio);
//                System.out.println("Fin : "+" "+getName()+" "+fin);
//                bandera2=false;
//            }
            byte[] bytes = new byte[4096];
            if (down2.exists()) {
                OutputStream out1 = new BufferedOutputStream(new FileOutputStream(down2, true));
                ftp.setRestartOffset(fin-down2.length());
                InputStream inp1 = ftp.retrieveFileStream("./commons-net-3.6-bin.zip");
                System.out.println("Iniciando desde " + down1.length());
                int leido = -1;
                int total = 0;
                int total3 = 0; 
                boolean bandera = true;
                System.out.println("hola ");
                while ((leido = inp1.read(bytes)) != -1 && bandera) {
                    if (total + leido <= fin) {
                        out1.write(bytes, 0, leido);
                        total += leido;
                        total3=total;
                    } else {
                        leido = fin - total;
                        out1.write(bytes, 0, leido);
                        total3 = total+leido;
                        bandera = false;
                    }

                }
                System.out.println("Tamaño : "+getName()+" "+total3);
                out1.close();
                inp1.close();
            } else {
                OutputStream out1 = new BufferedOutputStream(new FileOutputStream(down2,true));
                 //if(inicio!=0){
                InputStream inp1 = ftp.retrieveFileStream("./commons-net-3.6-bin.zip");
                 long cantida = 1406280;
                    ftp.setRestartOffset(cantida);
                //}
                
//                System.out.println("Iniciando desde " +getName()+" "+ inicio);
                
               
                int leido = -1;
                int total = 0;
                int total3 = 0; 
                boolean bandera = true;
                System.out.println("Inicio : " +getName()+" "+ ftp.getRestartOffset());
                while ((leido = inp1.read(bytes)) != -1 && bandera) {
                    /*if (total + leido <= fin-inicio) {
                        out1.write(bytes, 0, leido);
                        total += leido;
                        total3=total;
                    } else {
//                        leido = (fin-inicio) - total;
//                        out1.write(bytes, 0, leido);
//                        total3 = total+leido;
                        bandera = false;                      
                    }*/
                    if(total+leido<=fin-inicio){
                        out1.write(bytes, 0, leido);
                        total+=leido;
                    }
                    else{
                        leido = (int) ((fin-inicio) - total);
                       out1.write(bytes, 0, leido);
                        bandera=false;
                    }
                }
                
                out1.close();
                inp1.close();
//                System.out.println("Resta : "+getName()+" "+(fin-inicio));
//                System.out.println("Finalizando : "+getName()+" "+fin);
                System.out.println("Tamaño : "+getName()+" "+down2.length());    
            }
        } catch (IOException ex) {
            Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
