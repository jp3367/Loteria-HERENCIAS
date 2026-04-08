import java.util.ArrayList;


public abstract class Loteria {

    // Nombre del sorteo, protected para que las clases hijas lo puedan usar
    protected String nombreSorteo;

    // Lista con los numeros del boleto, tambien protected por lo mismo
    protected ArrayList<Integer> numeros;

    // Constructor que recibe el nombre del sorteo e inicializa la lista vacia
    public Loteria(String nombreSorteo) {
        this.nombreSorteo = nombreSorteo;
        this.numeros = new ArrayList<>();
    }

    // Getter del nombre
    public String getNombreSorteo() {
        return nombreSorteo;
    }

    // Setter del nombre, comprobamos que no sea nulo ni vacio
    public void setNombreSorteo(String nombreSorteo) {
        if (nombreSorteo == null || nombreSorteo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del sorteo no puede estar vacio");
        }
        this.nombreSorteo = nombreSorteo;
    }

    // Getter de la lista de numeros
    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    // Setter de la lista, comprobamos que no sea nula
    public void setNumeros(ArrayList<Integer> numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("La lista de numeros no puede ser nula");
        }
        this.numeros = numeros;
    }

    // Metodo abstracto para generar los numeros aleatorios del boleto
    public abstract void generarBoleto();

    // Metodo abstracto para ordenar los numeros de menor a mayor
    public abstract void ordenarBoleto();

    // Metodo abstracto para mostrar el boleto por consola
    public abstract void mostrarBoleto();
}