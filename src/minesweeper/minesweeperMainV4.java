package minesweeper;

public class minesweeperMainV4 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        MinefieldGeneratorV4 generation = new MinefieldGeneratorV4();
        Cell [][] newField = generation.generateMinefield(6,6,35,5,5);

        generation.printTheField(newField);
        System.out.println("-------------------");

        generation.flagCell(4,4);
        generation.printTheField(newField);
        System.out.println("-------------------");

        generation.revealCell(5,5);
        generation.printTheField(newField);
        System.out.println("-------------------");

        generation.revealCell(4,4);
        generation.printTheField(newField);
        System.out.println("-------------------");


        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Время выполнения: " + duration + " наносекунд");
    }
}