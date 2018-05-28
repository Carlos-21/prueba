/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;


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
            //File archivo = new File("./"+getName()+".zip");
            RandomAccessFile F = new  RandomAccessFile("./commons-net-3.6-bin.zip","rw");  
            byte[] buffer = new byte[4096];
            //OutputStream out1 = new BufferedOutputStream(new FileOutputStream(archivo));
            InputStream input1 = ftp.retrieveFileStream("./commons-net-3.6-bin.zip");
            ftp.setRestartOffset(getInicio());
            F.seek(getInicio()+1);
            int leido = input1.read(buffer,0,1000);
            int total = 0;
            boolean bandera = true;
            System.out.println("");
            System.out.println("inicio : "+getName()+" "+getInicio());
            System.out.println("fin : "+getName()+" "+getFin());
            System.out.println("incializacion : "+getName()+" "+ftp.getRestartOffset());
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
                leido = input1.read(buffer,0,1000);
            }
            input1.close();
            F.close();
            System.out.println("");
            //System.out.println("Tamaño del archivo "+getName()+" : "+F.length());
            System.out.println("");
        }catch(IOException ex){
            Logger.getLogger(prueba3.Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (ftp.isConnected()) {
                    ftp.logout();
                    ftp.disconnect();
                }
            } catch (IOException ex) {
                Logger.getLogger(prueba3.Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
