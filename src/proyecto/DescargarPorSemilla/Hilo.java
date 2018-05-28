/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.DescargarPorSemilla;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author CARLOS
 */
public class Hilo extends Thread{
    private final String servidor = "www.peru-software.com";
    private final int puerto = 21;
    private final String usuario = "pp20172@peru-software.com";
    private final String clave = "fisi20172";
    private long inicio;
    private long fin;
    private final String directorio = "D:\\Programación Paralela\\FTPSemilla\\Archivo Descargado";

    public Hilo(String nombre) {
        super(nombre);
    }

    public long getInicio() {
        return inicio;
    }

    public void setInicio(long inicio) {
        this.inicio = inicio;
    }

    public long getFin() {
        return fin;
    }

    public void setFin(long fin) {
        this.fin = fin;
    }
    
    @Override
    public void run(){
        FTPClient ftp = new FTPClient();
        try{
            ftp.connect(servidor, puerto);
            ftp.login(usuario, clave);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setRestartOffset(getInicio());
            
            byte[] buffer = new byte[4096];
            int total;
            
            File archivo = new File(directorio + getName());
            RandomAccessFile F = new  RandomAccessFile(archivo,"rw");           
            
            
            if (archivo.exists()) {
                ftp.setRestartOffset(getInicio() + archivo.length());
                F.seek(getInicio() + archivo.length());
                total = (int) archivo.length();
            } else {
                ftp.setRestartOffset(getInicio());
                F.seek(getInicio());
                total = 0;
            }
            
            InputStream input1 = ftp.retrieveFileStream(getName());
            int leido = input1.read(buffer);
           
            boolean bandera = true;
            
            while(leido != -1 && bandera){
                if((total + leido) <= (getFin() - getInicio())){
                    F.write(buffer, 0, leido);
                    total += leido;
                }
                else{
                    int aux = (int) ((total + leido) - (getFin() - getInicio()));
                    leido -= aux;
                    total += leido;
                    F.write(buffer, 0, leido);
                    bandera = false;
                }
                leido = input1.read(buffer);
            }
            input1.close();
            F.close();
        }catch(IOException ex){
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
