import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Primitiva extends Loteria implements Registro {

    private static final String FICHERO_PRIMITIVA = "primitiva.txt";
    private static final int MAX_NUMEROS = 49;
    private static final int CANTIDAD_NUMEROS = 6;

    // Constructor, llamamos al padre con el nombre del sorteo
    public Primitiva() {
        super("Primitiva");
    }

    // Genera 6 numeros aleatorios del 1 al 49 sin repetir
    @Override
    public void generarBoleto() {
        numeros.clear();
        Random random = new Random();

        while (numeros.size() < CANTIDAD_NUMEROS) {
            int numero = random.nextInt(MAX_NUMEROS) + 1;
            if (!numeros.contains(numero)) {
                numeros.add(numero);
            }
        }

        ordenarBoleto();
    }

    // Ordena los 6 numeros de menor a mayor
    @Override
    public void ordenarBoleto() {
        Collections.sort(numeros);
    }

    // Muestra los numeros del boleto por consola con formato de dos digitos
    @Override
    public void mostrarBoleto() {
        System.out.println("Sorteo: " + nombreSorteo);
        System.out.print("Numeros: ");
        for (int i = 0; i < numeros.size(); i++) {
            System.out.printf("%02d", numeros.get(i));
            if (i < numeros.size() - 1) {
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    // Guarda el boleto en primitiva.txt sin borrar los anteriores
    @Override
    public void almacenar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHERO_PRIMITIVA, true))) {
            StringBuilder linea = new StringBuilder("Numeros: ");
            for (int i = 0; i < numeros.size(); i++) {
                linea.append(String.format("%02d", numeros.get(i)));
                if (i < numeros.size() - 1) linea.append(",");
            }
            writer.write(linea.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el boleto de Primitiva: " + e.getMessage());
        }
    }

    // Lee todos los boletos del fichero primitiva.txt y los muestra por pantalla
    @Override
    public void leer() {
        System.out.println("Boletos guardados de la Primitiva:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_PRIMITIVA))) {
            String linea;
            int contador = 1;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Boleto " + contador + ": " + linea);
                contador++;
            }
            if (contador == 1) {
                System.out.println("No hay boletos guardados todavia.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer los boletos de Primitiva: " + e.getMessage());
        }
    }
}