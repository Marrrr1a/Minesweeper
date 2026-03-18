package minesweeper;

public class minesweeperMainV2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        MinefieldGeneratorV2 newField = new MinefieldGeneratorV2();
        newField.generateMinefield(50,50,10);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Время выполнения: " + duration);
    }
}