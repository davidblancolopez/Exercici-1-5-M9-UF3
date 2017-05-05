
package exercici.pkg1.pkg5.m9.uf1;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class Exercici15M9UF1 {

    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Emisor em = new Emisor();
        Receptor re = new Receptor();
        
        KeyPair key;
        byte[]contingut;
        
        //Creem les claus.
        key = em.generaClaus();
        
        //Posem el contingut del fitxer a un array amb la firma.
        contingut = em.signData("carta.txt", key.getPrivate());
        
        //Mostrem si la firma es correcte amb aquesta comprovació
        System.out.println(re.validateSignature("carta.txt", contingut, key.getPublic()));
    }
    
}
