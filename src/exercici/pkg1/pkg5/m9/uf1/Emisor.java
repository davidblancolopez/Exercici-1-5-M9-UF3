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
    public KeyPair generaClaus() throws NoSuchAlgorithmException {
        KeyPair key;
        
        KeyPairGenerator KeyGenerator = KeyPairGenerator.getInstance("RSA");
        KeyGenerator.initialize(2048);
        key = KeyGenerator.genKeyPair();
        
        return key;
    }

    /**
     * Metodo que realiza la firma en el documento.
     * Le llega un array de bytes, la clave privada y el nombre del archivo.
     * 
     * @param data
     * @param priv
     * @return 
     */
    public byte[] signData(String fitxer, PrivateKey priv) {
        byte[] signature = null;
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
            signer.initSign(priv); //Inicialitzem la firma digital a partir de l ’algorisme utilitzat
            
            //Recorremos el archivo.
            while(bis.available() != 0){
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida); //Li assignem a l’objecte firma les dades a firmar digitalment
            }
            
            //Se cierra el recurso.
            bis.close();
            signature = signer.sign(); //Finalment generem la firma.
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

}
