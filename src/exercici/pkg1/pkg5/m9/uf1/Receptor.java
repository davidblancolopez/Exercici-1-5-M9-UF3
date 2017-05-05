package exercici.pkg1.pkg5.m9.uf1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.Signature;

public class Receptor {

    
    
    public boolean validateSignature(String fitxer, byte[] signature, PublicKey pub) {
        boolean valid = false;
        FileInputStream fis;
        BufferedInputStream bis;
        byte[] buffer = new byte [1024];
        int mida;
        
        try {
            //Inicializamos el bufferedInput i el fileInput para realizar la lectura del fichero.
            fis = new FileInputStream(fitxer);
            bis = new BufferedInputStream(fis);
            
            //Inicializamos el signer para la firma.
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            
            //Recorremos el archivo.
            while(bis.available() != 0){
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida); //Li assignem a l’objecte firma les dades a firmar digitalment
            }
            
            //Tanquem el recurs.
            bis.close();
            
            //Es verifica la firma.
            valid = signer.verify(signature);
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return valid;
    }
    
    
    
    
}
