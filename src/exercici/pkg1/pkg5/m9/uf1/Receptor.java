package exercici.pkg1.pkg5.m9.uf1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.Signature;

public class Receptor {

    
    
    public boolean validateSignature(byte[] data, byte[] signature, PublicKey pub, String fitxer) {
        boolean isValid = false;
        FileInputStream fis;
        BufferedInputStream bis;
        byte[] buffer = new byte [1024];
        int mida;
        
        try {
            fis = new FileInputStream(fitxer);
            bis = new BufferedInputStream(fis);
            
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            
            
            signer.update(data);
            isValid = signer.verify(signature);
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return isValid;
    }
    
    
    
    
}
