# Hash Table Implementation

A Java implementation demonstrating three different collision resolution strategies for hash tables: Linear Probing, Quadratic Probing, and Chaining.

## Features

- **Linear Probing**: Resolves collisions by searching for the next available slot sequentially
- **Quadratic Probing**: Uses a quadratic function to find the next available slot
- **Chaining**: Handles collisions by maintaining linked lists at each index

## Operations Supported

- **Insert**: Add values to the hash table
- **Search**: Check if a value exists in the table
- **Delete**: Remove values (using lazy deletion for probing methods)
- **Display**: Print the current state of the hash table

## How to Run

1. Compile the program:
```bash
javac HashTable.java
```

2. Run the program:
```bash
java HashTable
```

3. Choose a collision handling method from the interactive menu:
   - Option 1: Linear Probing
   - Option 2: Quadratic Probing
   - Option 3: Chaining
   - Option 4: Exit

## Implementation Details

- Fixed table size: 10
- Hash function: `value % 10`
- Lazy deletion for open addressing methods (entries marked as deleted rather than removed)
- Dynamic linked lists for chaining method

## Example Output

The program demonstrates inserting values (10, 20, 30, 21, 31), searching for a value, and deleting it, showing the table state at each step.

## License

MIT License - see [LICENSE](LICENSE) file for details