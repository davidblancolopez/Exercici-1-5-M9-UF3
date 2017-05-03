package exercici.pkg1.pkg5.m9.uf1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;

public class Emisor {

    private KeyPair keyPair;

    /**
     * Metodo que genera las claves que se utilizaran para cifrar y descifrar
     * los mensajes. Crea un KeyPairGenerator, lo inicializa y lo mete en la
     * variable keyPair.
     *
     * @throws NoSuchAlgorithmException
     */
    public void generaClaus() throws NoSuchAlgorithmException {
        KeyPairGenerator KeyGenerator = KeyPairGenerator.getInstance("RSA");
        KeyGenerator.initialize(2048);
        keyPair = KeyGenerator.genKeyPair();
    }

    /**
     * 
     * @param data
     * @param priv
     * @return 
     */
    public byte[] signData(byte[] data, PrivateKey priv, File fitxer) {
        byte[] signature = null;
        FileInputStream fis;
        BufferedInputStream bis;
        byte[] buffer = new byte [1024];
        int mida;
        
        try {
            fis = new FileInputStream(fitxer);
            bis = new BufferedInputStream(fis);
            
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(priv); //Inicialitzem la firma digital a partir de l ’algorisme utilitzat
            
            while(bis.available() != 0){
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida);
            }
            
            
            signer.update(data); //Li assignem a l’objecte firma les dades a firmar digitalment
            signature = signer.sign(); //Finalment generem la firma.
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

}
