import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        borrarFicheroSiExiste("euromillon.txt");
        borrarFicheroSiExiste("primitiva.txt");

        System.out.println("PRUEBAS DEL SISTEMA DE LOTERIA");
        System.out.println();

        probarEuromillon();
        probarPrimitiva();
        probarExcepciones();
        probarGettersYSetters();
        probar200BoletosEuromillon();
        probar200BoletosPrimitiva();
    }

    // Prueba basica del Euromillon: generamos boletos, los guardamos y los leemos
    static void probarEuromillon() {
        System.out.println("PRUEBA EUROMILLON");
        System.out.println();

        Euromillon euro = new Euromillon();

        euro.generarBoleto();
        System.out.println("Boleto generado:");
        euro.mostrarBoleto();
        System.out.println();

        System.out.println("Guardando boleto en fichero...");
        euro.almacenar();
        System.out.println("Boleto guardado correctamente.");
        System.out.println();

        euro.generarBoleto();
        System.out.println("Segundo boleto generado:");
        euro.mostrarBoleto();
        euro.almacenar();
        System.out.println();

        System.out.println("Leyendo boletos desde el fichero:");
        euro.leer();
        System.out.println();
    }

    // Prueba basica de la Primitiva, igual que la de arriba pero con este sorteo
    static void probarPrimitiva() {
        System.out.println("PRUEBA PRIMITIVA");
        System.out.println();

        Primitiva prim = new Primitiva();

        prim.generarBoleto();
        System.out.println("Boleto generado:");
        prim.mostrarBoleto();
        System.out.println();

        System.out.println("Guardando boleto en fichero...");
        prim.almacenar();
        System.out.println("Boleto guardado correctamente.");
        System.out.println();

        prim.generarBoleto();
        System.out.println("Segundo boleto generado:");
        prim.mostrarBoleto();
        prim.almacenar();
        System.out.println();

        System.out.println("Leyendo boletos desde el fichero:");
        prim.leer();
        System.out.println();
    }

    // Probamos que las excepciones saltan cuando les pasamos datos incorrectos
    static void probarExcepciones() {
        System.out.println("PRUEBA DE EXCEPCIONES");
        System.out.println();

        System.out.println("Intentando poner nombre nulo al sorteo...");
        try {
            Euromillon euro = new Euromillon();
            euro.setNombreSorteo(null);
            System.out.println("ERROR: No se lanzo la excepcion esperada");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Intentando poner nombre vacio al sorteo...");
        try {
            Primitiva prim = new Primitiva();
            prim.setNombreSorteo("   ");
            System.out.println("ERROR: No se lanzo la excepcion esperada");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Intentando poner lista de numeros nula...");
        try {
            Euromillon euro = new Euromillon();
            euro.setNumeros(null);
            System.out.println("ERROR: No se lanzo la excepcion esperada");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }
        System.out.println();

        System.out.println("Intentando poner estrellas fuera de rango (estrella = 15)...");
        try {
            Euromillon euro = new Euromillon();
            ArrayList<Integer> estrellasInvalidas = new ArrayList<>();
            estrellasInvalidas.add(15);
            estrellasInvalidas.add(3);
            euro.setEstrellas(estrellasInvalidas);
            System.out.println("ERROR: No se lanzo la excepcion esperada");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }
        System.out.println();

        // Borramos el fichero para simular que no existe y ver que lo gestiona bien
        System.out.println("Intentando leer un fichero que no existe...");
        borrarFicheroSiExiste("primitiva.txt");
        Primitiva prim = new Primitiva();
        prim.leer();
        System.out.println();

        System.out.println("Intentando poner lista de estrellas nula...");
        try {
            Euromillon euro = new Euromillon();
            euro.setEstrellas(null);
            System.out.println("ERROR: No se lanzo la excepcion esperada");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }
        System.out.println();
    }

    // Comprobamos que los getters y setters funcionan bien con valores correctos
    static void probarGettersYSetters() {
        System.out.println("PRUEBA DE GETTERS Y SETTERS");
        System.out.println();

        Euromillon euro = new Euromillon();
        euro.generarBoleto();
        System.out.println("Nombre del sorteo (getter): " + euro.getNombreSorteo());
        euro.setNombreSorteo("EuroMillones");
        System.out.println("Nombre cambiado a: " + euro.getNombreSorteo());
        System.out.println("Numeros del boleto (getter): " + euro.getNumeros());
        System.out.println("Estrellas del boleto (getter): " + euro.getEstrellas());
        System.out.println();

        Primitiva prim = new Primitiva();
        prim.generarBoleto();
        System.out.println("Nombre del sorteo Primitiva: " + prim.getNombreSorteo());
        System.out.println("Numeros del boleto Primitiva: " + prim.getNumeros());

        ArrayList<Integer> nuevosNumeros = new ArrayList<>();
        nuevosNumeros.add(1);
        nuevosNumeros.add(2);
        nuevosNumeros.add(3);
        nuevosNumeros.add(4);
        nuevosNumeros.add(5);
        nuevosNumeros.add(6);
        prim.setNumeros(nuevosNumeros);
        System.out.println("Numeros cambiados con setter: " + prim.getNumeros());
        System.out.println();
    }

    // Genera 200 boletos del Euromillon, los guarda y busca el numero menos usado.
    // Usamos un HashMap para contar cuantas veces aparece cada numero.
    static void probar200BoletosEuromillon() {
        System.out.println("PRUEBA 200 BOLETOS EUROMILLON");
        System.out.println();

        borrarFicheroSiExiste("euromillon.txt");
        Euromillon euro = new Euromillon();

        HashMap<Integer, Integer> contadorNumeros = new HashMap<>();
        for (int i = 1; i <= 50; i++) {
            contadorNumeros.put(i, 0);
        }

        System.out.println("Generando 200 boletos de Euromillon...");
        for (int i = 0; i < 200; i++) {
            euro.generarBoleto();
            euro.almacenar();
            for (int numero : euro.getNumeros()) {
                contadorNumeros.put(numero, contadorNumeros.get(numero) + 1);
            }
        }

        System.out.println("200 boletos generados y guardados en euromillon.txt");
        System.out.println();

        int numeroMenosUsado = buscarNumeroMenosUsado(contadorNumeros);
        int vecesAparecido = contadorNumeros.get(numeroMenosUsado);
        System.out.println("El numero menos utilizado en los 200 boletos de Euromillon es: "
                + String.format("%02d", numeroMenosUsado)
                + " (ha aparecido " + vecesAparecido + " veces)");
        System.out.println();

        System.out.println("Los 5 numeros menos frecuentes de Euromillon:");
        mostrar5MenosFrecuentes(contadorNumeros, 50);
        System.out.println();
    }

    // Igual que el metodo anterior pero para la Primitiva con rango 1-49
    static void probar200BoletosPrimitiva() {
        System.out.println("PRUEBA 200 BOLETOS PRIMITIVA");
        System.out.println();

        borrarFicheroSiExiste("primitiva.txt");
        Primitiva prim = new Primitiva();

        HashMap<Integer, Integer> contadorNumeros = new HashMap<>();
        for (int i = 1; i <= 49; i++) {
            contadorNumeros.put(i, 0);
        }

        System.out.println("Generando 200 boletos de Primitiva...");
        for (int i = 0; i < 200; i++) {
            prim.generarBoleto();
            prim.almacenar();
            for (int numero : prim.getNumeros()) {
                contadorNumeros.put(numero, contadorNumeros.get(numero) + 1);
            }
        }

        System.out.println("200 boletos generados y guardados en primitiva.txt");
        System.out.println();

        int numeroMenosUsado = buscarNumeroMenosUsado(contadorNumeros);
        int vecesAparecido = contadorNumeros.get(numeroMenosUsado);
        System.out.println("El numero menos utilizado en los 200 boletos de Primitiva es: "
                + String.format("%02d", numeroMenosUsado)
                + " (ha aparecido " + vecesAparecido + " veces)");
        System.out.println();

        System.out.println("Los 5 numeros menos frecuentes de Primitiva:");
        mostrar5MenosFrecuentes(contadorNumeros, 49);
        System.out.println();
    }

    // Recorre el HashMap y devuelve el numero con el contador mas bajo
    static int buscarNumeroMenosUsado(HashMap<Integer, Integer> contadores) {
        int numeroMenosUsado = -1;
        int minVeces = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entrada : contadores.entrySet()) {
            if (entrada.getValue() < minVeces) {
                minVeces = entrada.getValue();
                numeroMenosUsado = entrada.getKey();
            }
        }
        return numeroMenosUsado;
    }

    // Ordena los numeros por apariciones y muestra los 5 menos frecuentes
    static void mostrar5MenosFrecuentes(HashMap<Integer, Integer> contadores, int rangoMax) {
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= rangoMax; i++) {
            numeros.add(i);
        }
        numeros.sort((a, b) -> contadores.get(a) - contadores.get(b));
        for (int i = 0; i < 5 && i < numeros.size(); i++) {
            int num = numeros.get(i);
            System.out.println("  Numero " + String.format("%02d", num)
                    + " -> " + contadores.get(num) + " apariciones");
        }
    }

    // Borra el fichero si existe, lo usamos para limpiar antes de cada prueba
    static void borrarFicheroSiExiste(String nombreFichero) {
        File fichero = new File(nombreFichero);
        if (fichero.exists()) {
            fichero.delete();
        }
    }
}