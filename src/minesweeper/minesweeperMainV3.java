package minesweeper;

public class minesweeperMainV3 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        MinefieldGeneratorV3 newField = new MinefieldGeneratorV3();
        newField.generateMinefield(50,50,10);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Время выполнения: " + duration);
    }
}