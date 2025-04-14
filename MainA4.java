import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

public class MainA4 {
    private final static String ALGORITMO = "RSA";
    public static void main(String[] args) {

        try {
            FileInputStream archivoLlave = new FileInputStream("llave_privada.key");
            ObjectInputStream oisLlave = new ObjectInputStream(archivoLlave);
            PrivateKey llavePrivada = (PrivateKey) oisLlave.readObject();
            oisLlave.close();

            // Recuperar el texto cifrado
            FileInputStream archivoTexto = new FileInputStream("mensaje_Cifrado.dat");
            ObjectInputStream oisTexto = new ObjectInputStream(archivoTexto);
            byte[] textoCifrado = (byte[]) oisTexto.readObject();
            oisTexto.close();

            byte[] mensajeDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, textoCifrado);
            System.out.print("Texto descifrado (bytes): ");
            imprimir(mensajeDescifrado);


            // Convertir byte[] a String
            String textoRecuperado = new String(mensajeDescifrado);
            System.out.println("Texto recuperado: " + textoRecuperado);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

}
