import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {

    public static byte[] getDigest(String algorithm, byte[] buffer){
        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(buffer);
            return digest.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static void imprimirHexa(byte[] byteArray){
        String out = "";
        for (int i=0; i < byteArray.length; i++){
            if ((byteArray[i] & 0xff) <= 0xf){
                out += "0";
            }
            out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
        }
        System.out.println((out));
    }

    public static byte[] getDigestFile(String algorithm, String fileName){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance(algorithm);

            FileInputStream in = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];

            int length;
            while((length = in.read(buffer)) != -1){
                md.update(buffer, 0, length);
            }
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 

        return md.digest();
    }

    public boolean verificar(byte[] digest1, byte[] digest2){
        if (digest1.length != digest2.length) {
            return false;
        }
        for (int i = 0; i < digest1.length; i++) {
            if (digest1[i] != digest2[i]) {
                return false;
            }
        }
        return true;
    }
}

