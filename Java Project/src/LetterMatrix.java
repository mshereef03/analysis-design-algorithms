import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class LetterMatrix {
    private Map<Pair<Character, Character>, Double> matrix;
    private List<Character> indices;

    public LetterMatrix(List<Character> indices) {
        this.indices = indices;
        this.matrix = new HashMap<>();

        // Initialize the matrix with default values (e.g., 0.0)
        for (Character row : indices) {
            for (Character col : indices) {
                matrix.put(new Pair<>(row, col), 0.0);
            }
        }
    }

    public void setValue(Character row, Character col, Double value) {
        if (indices.contains(row) && indices.contains(col)) {
            matrix.put(new Pair<>(row, col), value);
        } else {
            throw new IllegalArgumentException("Row or Column letter is invalid.");
        }
    }

    public Double getValue(Character row, Character col) {
        Pair<Character, Character> key = new Pair<>(row, col);
        if (matrix.containsKey(key)) {
            return matrix.get(key);
        } else {
            throw new IllegalArgumentException("Row or Column letter is invalid or not set.");
        }
    }

    // Inner Pair class to represent the keys of the matrix
    public static class Pair<L, R> {
        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }

        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return left.equals(pair.getLeft()) && right.equals(pair.getRight());
        }
    }

    // Main method for demonstration
    public static void main(String[] args) {
        // Example of using dynamic indices
        List<Character> dynamicIndices = Arrays.asList('A', 'B', 'C', 'D'); // Dynamic indices for Java 8 // Dynamic indices
        LetterMatrix matrix = new LetterMatrix(dynamicIndices);

        // Setting and getting values
        matrix.setValue('A', 'B', 1.5);
        System.out.println("Value at (A, B): " + matrix.getValue('A', 'B'));
    }
}

