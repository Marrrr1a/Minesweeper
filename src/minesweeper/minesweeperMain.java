package minesweeper;

public class minesweeperMain {
    public static void main(String[] args) {
        minefieldGenerator newField = new minefieldGenerator();
        newField.generateMinefield(10,10,20);
    }
}