import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Clase del sorteo Euromillon: 5 numeros del 1 al 50 y 2 estrellas del 1 al 12.
// Hereda de Loteria e implementa Registro para guardar y leer en fichero.
public class Euromillon extends Loteria implements Registro {

    // Lista para las estrellas, que son distintas a los numeros normales
    private ArrayList<Integer> estrellas;

    private static final String FICHERO_EUROMILLON = "euromillon.txt";
    private static final int MAX_NUMEROS = 50;
    private static final int MAX_ESTRELLAS = 12;
    private static final int CANTIDAD_NUMEROS = 5;
    private static final int CANTIDAD_ESTRELLAS = 2;

    // Constructor, llamamos al padre con el nombre y inicializamos la lista de estrellas
    public Euromillon() {
        super("Euromillon");
        this.estrellas = new ArrayList<>();
    }

    // Getter de las estrellas
    public ArrayList<Integer> getEstrellas() {
        return estrellas;
    }

    // Setter de estrellas con validacion de rango (1-12) y comprobacion de nulo
    public void setEstrellas(ArrayList<Integer> estrellas) {
        if (estrellas == null) {
            throw new IllegalArgumentException("La lista de estrellas no puede ser nula");
        }
        for (int estrella : estrellas) {
            if (estrella < 1 || estrella > MAX_ESTRELLAS) {
                throw new IllegalArgumentException("Las estrellas deben estar entre 1 y " + MAX_ESTRELLAS);
            }
        }
        this.estrellas = estrellas;
    }

    // Genera 5 numeros aleatorios del 1 al 50 y 2 estrellas del 1 al 12, sin repetir.
    // Usamos contains() para evitar duplicados antes de añadir cada numero.
    @Override
    public void generarBoleto() {
        numeros.clear();
        estrellas.clear();
        Random random = new Random();

        while (numeros.size() < CANTIDAD_NUMEROS) {
            int numero = random.nextInt(MAX_NUMEROS) + 1;
            if (!numeros.contains(numero)) {
                numeros.add(numero);
            }
        }

        while (estrellas.size() < CANTIDAD_ESTRELLAS) {
            int estrella = random.nextInt(MAX_ESTRELLAS) + 1;
            if (!estrellas.contains(estrella)) {
                estrellas.add(estrella);
            }
        }

        ordenarBoleto();
    }

    // Ordena los numeros y las estrellas de menor a mayor con Collections.sort()
    @Override
    public void ordenarBoleto() {
        Collections.sort(numeros);
        Collections.sort(estrellas);
    }

    // Muestra el boleto por consola con formato de dos digitos para cada numero
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
        System.out.print("   Estrellas: ");
        for (int i = 0; i < estrellas.size(); i++) {
            System.out.printf("%02d", estrellas.get(i));
            if (i < estrellas.size() - 1) {
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    // Guarda el boleto en euromillon.txt, con append true para no borrar los anteriores.
    // El try-with-resources cierra el fichero solo al terminar el bloque.
    @Override
    public void almacenar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHERO_EUROMILLON, true))) {
            StringBuilder linea = new StringBuilder("Numeros: ");
            for (int i = 0; i < numeros.size(); i++) {
                linea.append(String.format("%02d", numeros.get(i)));
                if (i < numeros.size() - 1) linea.append(",");
            }
            linea.append(" | Estrellas: ");
            for (int i = 0; i < estrellas.size(); i++) {
                linea.append(String.format("%02d", estrellas.get(i)));
                if (i < estrellas.size() - 1) linea.append(",");
            }
            writer.write(linea.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el boleto de Euromillon: " + e.getMessage());
        }
    }

    // Lee todos los boletos del fichero euromillon.txt y los muestra linea a linea
    @Override
    public void leer() {
        System.out.println("Boletos guardados del Euromillon:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_EUROMILLON))) {
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
            System.out.println("Error al leer los boletos de Euromillon: " + e.getMessage());
        }
    }
}