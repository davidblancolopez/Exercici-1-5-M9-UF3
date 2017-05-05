
package exercici.pkg1.pkg5.m9.uf1;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class Exercici15M9UF1 {

    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Emisor em = new Emisor();
        Receptor re = new Receptor();
        
        KeyPair key;
        byte[]contingut;
        
        key = em.generaClaus();
        
        contingut = em.signData("carta.txt", key.getPrivate());
        
        System.out.println(re.validateSignature("carta2.txt", contingut, key.getPublic()));
    }
    
}
