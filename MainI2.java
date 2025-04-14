import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainI2 {
    public static void main(String[] args) {   
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre del archivo: ");
            String nombreArchivo = scanner.nextLine();
            System.out.print("Ingrese el numero de la opcion para calcular el Digest del archivo:\n1. MD5 \n2. SHA-1 \n");
            Integer opcion = Integer.parseInt(scanner.nextLine());

            // Imprimir texto original
            System.out.println("Nombre del archivo: " + nombreArchivo);

            if(opcion == 1){
                // Calcular digest con MD5
                byte[] digest = Digest.getDigestFile("MD5", nombreArchivo);
                System.out.print("Digest MD5 obtenido: ");
                Digest.imprimirHexa(digest);
            } else if(opcion == 2){
                // Calcular digest con SHA-1
                byte[] digest = Digest.getDigestFile("SHA-1", nombreArchivo);
                System.out.print("Digest SHA-1 obtenido: ");
                Digest.imprimirHexa(digest);
            } else {
                System.out.println("Opcion no valida");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
