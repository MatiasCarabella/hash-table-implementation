import java.util.LinkedList;
import java.util.Scanner;

public class HashTable {
    // Clase interna para representar una entrada en la tabla hash
    private static class Entry {
        int value;
        boolean isDeleted; // Indicador para saber si la entrada ha sido eliminada

        public Entry(int value) {
            this.value = value;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return isDeleted ? "Deleted" : String.valueOf(value);
        }
    }

    // La tabla puede contener entradas o listas enlazadas para encadenamiento
    private Object[] table;
    private static final int SIZE = 10; // Tamaño fijo de la tabla

    // Constructor para inicializar la tabla hash
    public HashTable() {
        table = new Object[SIZE];
    }

    // Función hash que usa el tamaño de la tabla (10)
    public int hash(int value) {
        return value % SIZE;
    }

    // Inserción usando sondeo lineal
    public void insertLinear(int value) {
        int index = hash(value);
        int originalIndex = index;
        // Buscar la siguiente posición disponible
        while (table[index] != null && !(table[index] instanceof LinkedList) && !((Entry) table[index]).isDeleted) {
            index = (index + 1) % SIZE;
            if (index == originalIndex) {
                System.out.println("Table is full, cannot insert value: " + value);
                return;
            }
        }
        table[index] = new Entry(value);
    }

    // Inserción usando sondeo cuadrático
    public void insertQuadratic(int value) {
        int index = hash(value);
        int originalIndex = index;
        int i = 1;
        // Buscar la siguiente posición disponible usando secuencia cuadrática
        while (table[index] != null && !(table[index] instanceof LinkedList) && !((Entry) table[index]).isDeleted) {
            index = (originalIndex + i * i) % SIZE;
            i++;
            if (i == SIZE) {
                System.out.println("Table is full, cannot insert value: " + value);
                return;
            }
        }
        table[index] = new Entry(value);
    }

    // Inserción usando encadenamiento (hashing abierto)
    public void insertChaining(int value) {
        int index = hash(value);
        // Si no hay lista en el índice, crear una nueva lista
        if (table[index] == null) {
            table[index] = new LinkedList < Entry > ();
        }
        LinkedList < Entry > chain = (LinkedList < Entry > ) table[index];
        // Añadir el nuevo valor a la lista
        chain.add(new Entry(value));
    }

    // Búsqueda usando sondeo lineal
    public boolean containsLinear(int value) {
        int index = hash(value);
        int originalIndex = index;
        while (table[index] != null && !(table[index] instanceof LinkedList)) {
            if (((Entry) table[index]).value == value && !((Entry) table[index]).isDeleted) {
                return true;
            }
            index = (index + 1) % SIZE;
            if (index == originalIndex) {
                break;
            }
        }
        return false;
    }

    // Búsqueda usando sondeo cuadrático
    public boolean containsQuadratic(int value) {
        int index = hash(value);
        int originalIndex = index;
        int i = 1;
        while (table[index] != null && !(table[index] instanceof LinkedList)) {
            if (((Entry) table[index]).value == value && !((Entry) table[index]).isDeleted) {
                return true;
            }
            index = (originalIndex + i * i) % SIZE;
            i++;
            if (i == SIZE) {
                break;
            }
        }
        return false;
    }

    // Búsqueda usando encadenamiento (hashing abierto)
    public boolean containsChaining(int value) {
        int index = hash(value);
        LinkedList < Entry > chain = (LinkedList < Entry > ) table[index];
        if (chain != null) {
            for (Entry entry: chain) {
                if (entry.value == value && !entry.isDeleted) {
                    return true;
                }
            }
        }
        return false;
    }

    // Eliminación usando sondeo lineal
    public void removeLinear(int value) {
        int index = hash(value);
        int originalIndex = index;
        while (table[index] != null && !(table[index] instanceof LinkedList)) {
            if (((Entry) table[index]).value == value && !((Entry) table[index]).isDeleted) {
                ((Entry) table[index]).isDeleted = true;
                return;
            }
            index = (index + 1) % SIZE;
            if (index == originalIndex) {
                break;
            }
        }
    }

    // Eliminación usando sondeo cuadrático
    public void removeQuadratic(int value) {
        int index = hash(value);
        int originalIndex = index;
        int i = 1;
        while (table[index] != null && !(table[index] instanceof LinkedList)) {
            if (((Entry) table[index]).value == value && !((Entry) table[index]).isDeleted) {
                ((Entry) table[index]).isDeleted = true;
                return;
            }
            index = (originalIndex + i * i) % SIZE;
            i++;
            if (i == SIZE) {
                break;
            }
        }
    }

    // Eliminación usando encadenamiento (hashing abierto)
    public void removeChaining(int value) {
        int index = hash(value);
        LinkedList < Entry > chain = (LinkedList < Entry > ) table[index];
        if (chain != null) {
            for (Entry entry: chain) {
                if (entry.value == value && !entry.isDeleted) {
                    entry.isDeleted = true;
                    return;
                }
            }
        }
    }

    // Imprimir el contenido de la tabla hash
    public void printTable() {
        for (int i = 0; i < SIZE; i++) {
            if (table[i] instanceof LinkedList) {
                LinkedList < Entry > chain = (LinkedList < Entry > ) table[i];
                System.out.print("Index " + i + ": ");
                for (Entry entry: chain) {
                    System.out.print(entry + ", ");
                }
                System.out.println("null");
            } else {
                System.out.println("Index " + i + ": " + table[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable();
        int option;

        do {
            System.out.println("\nElige el método de manejo de colisiones:");
            System.out.println("1. Sondeo Lineal");
            System.out.println("2. Sondeo Cuadrático");
            System.out.println("3. Encadenamiento");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Llamadas a métodos para Sondeo Lineal
                    System.out.println("\nInsertando usando sondeo lineal:");
                    hashTable.insertLinear(10);
                    hashTable.insertLinear(20);
                    hashTable.insertLinear(30);
                    hashTable.insertLinear(21);
                    hashTable.insertLinear(31);
                    hashTable.printTable();

                    System.out.println("Contiene 20 (Lineal): " + hashTable.containsLinear(20));
                    hashTable.removeLinear(20);
                    System.out.println("Después de eliminar 20 (Lineal):");
                    hashTable.printTable();
                    break;
                case 2:
                    // Llamadas a métodos para Sondeo Cuadrático
                    hashTable = new HashTable(); // Reiniciar la tabla para sondeo cuadrático

                    System.out.println("\nInsertando usando sondeo cuadrático:");
                    hashTable.insertQuadratic(10);
                    hashTable.insertQuadratic(20);
                    hashTable.insertQuadratic(30);
                    hashTable.insertQuadratic(21);
                    hashTable.insertQuadratic(31);
                    hashTable.printTable();

                    System.out.println("Contiene 20 (Cuadrático): " + hashTable.containsQuadratic(20));
                    hashTable.removeQuadratic(20);
                    System.out.println("Después de eliminar 20 (Cuadrático):");
                    hashTable.printTable();
                    break;
                case 3:
                    // Llamadas a métodos para Encadenamiento
                    hashTable = new HashTable(); // Reiniciar la tabla para encadenamiento

                    System.out.println("\nInsertando usando encadenamiento:");
                    hashTable.insertChaining(10);
                    hashTable.insertChaining(20);
                    hashTable.insertChaining(30);
                    hashTable.insertChaining(21);
                    hashTable.insertChaining(31);
                    hashTable.printTable();

                    System.out.println("Contiene 20 (Encadenamiento): " + hashTable.containsChaining(20));
                    hashTable.removeChaining(20);
                    System.out.println("Después de eliminar 20 (Encadenamiento):");
                    hashTable.printTable();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (option != 4);
        scanner.close();
    }
}