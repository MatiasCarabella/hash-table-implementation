import java.util.LinkedList;
import java.util.Scanner;

public class HashTable {
    // Inner class to represent an entry in the hash table
    private static class Entry {
        int value;
        boolean isDeleted; // Flag to indicate if the entry has been deleted

        public Entry(int value) {
            this.value = value;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return isDeleted ? "Deleted" : String.valueOf(value);
        }
    }

    // The table can contain entries or linked lists for chaining
    private Object[] table;
    private static final int SIZE = 10; // Fixed table size

    // Constructor to initialize the hash table
    public HashTable() {
        table = new Object[SIZE];
    }

    // Hash function that uses the table size (10)
    public int hash(int value) {
        return value % SIZE;
    }

    // Insertion using linear probing
    public void insertLinear(int value) {
        int index = hash(value);
        int originalIndex = index;
        // Find the next available position
        while (table[index] != null && !(table[index] instanceof LinkedList) && !((Entry) table[index]).isDeleted) {
            index = (index + 1) % SIZE;
            if (index == originalIndex) {
                System.out.println("Table is full, cannot insert value: " + value);
                return;
            }
        }
        table[index] = new Entry(value);
    }

    // Insertion using quadratic probing
    public void insertQuadratic(int value) {
        int index = hash(value);
        int originalIndex = index;
        int i = 1;
        // Find the next available position using quadratic sequence
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

    // Insertion using chaining (open hashing)
    public void insertChaining(int value) {
        int index = hash(value);
        // If there's no list at the index, create a new list
        if (table[index] == null) {
            table[index] = new LinkedList < Entry > ();
        }
        LinkedList < Entry > chain = (LinkedList < Entry > ) table[index];
        // Add the new value to the list
        chain.add(new Entry(value));
    }

    // Search using linear probing
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

    // Search using quadratic probing
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

    // Search using chaining (open hashing)
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

    // Deletion using linear probing
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

    // Deletion using quadratic probing
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

    // Deletion using chaining (open hashing)
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

    // Print the hash table contents
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
            System.out.println("\nChoose the collision handling method:");
            System.out.println("1. Linear Probing");
            System.out.println("2. Quadratic Probing");
            System.out.println("3. Chaining");
            System.out.println("4. Exit");
            System.out.print("Option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Method calls for Linear Probing
                    System.out.println("\nInserting using linear probing:");
                    hashTable.insertLinear(10);
                    hashTable.insertLinear(20);
                    hashTable.insertLinear(30);
                    hashTable.insertLinear(21);
                    hashTable.insertLinear(31);
                    hashTable.printTable();

                    System.out.println("Contains 20 (Linear): " + hashTable.containsLinear(20));
                    hashTable.removeLinear(20);
                    System.out.println("After removing 20 (Linear):");
                    hashTable.printTable();
                    break;
                case 2:
                    // Method calls for Quadratic Probing
                    hashTable = new HashTable(); // Reset the table for quadratic probing

                    System.out.println("\nInserting using quadratic probing:");
                    hashTable.insertQuadratic(10);
                    hashTable.insertQuadratic(20);
                    hashTable.insertQuadratic(30);
                    hashTable.insertQuadratic(21);
                    hashTable.insertQuadratic(31);
                    hashTable.printTable();

                    System.out.println("Contains 20 (Quadratic): " + hashTable.containsQuadratic(20));
                    hashTable.removeQuadratic(20);
                    System.out.println("After removing 20 (Quadratic):");
                    hashTable.printTable();
                    break;
                case 3:
                    // Method calls for Chaining
                    hashTable = new HashTable(); // Reset the table for chaining

                    System.out.println("\nInserting using chaining:");
                    hashTable.insertChaining(10);
                    hashTable.insertChaining(20);
                    hashTable.insertChaining(30);
                    hashTable.insertChaining(21);
                    hashTable.insertChaining(31);
                    hashTable.printTable();

                    System.out.println("Contains 20 (Chaining): " + hashTable.containsChaining(20));
                    hashTable.removeChaining(20);
                    System.out.println("After removing 20 (Chaining):");
                    hashTable.printTable();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (option != 4);
        scanner.close();
    }
}