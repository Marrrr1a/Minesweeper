package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class MinefieldGeneratorV4 {

    private Cell [][] field;


    public Cell [][] generateMinefield(int width, int height, int minesCount, int startX, int startY) {
        parametersCheck(width,height,minesCount);
        validateInput(width,height,startX,startY);
        field = fieldCreation(width, height);
        minesRandomizer (minesCount, startX, startY);
        addNumbers();

        return field;
    }

    /**
     * Метод для проверки допустимости введённых параметров поля и количества мин
     */
    private void parametersCheck(int width, int height, int minesCount) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Ширина и высота поля не могут быть меньше 1");
        } else if (minesCount >= width * height) {
            throw new IllegalArgumentException("Количество мин на поле не должно быть больше/равно количеству клеток поля");
        }
    }

    /**
     * Метод, который проверяет, не находятся ли координаты вне поля
     */
    private void validateInput(int width, int height, int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0){
            throw new IllegalArgumentException("Координаты объекта находятся вне поля");
        }
    }

    /**
     * Метод для создания пустого поля по указанным ранее параметрам
     * @return newField - возвращает созданное поле
     */
    private Cell [][] fieldCreation(int width, int height) {
        Cell [][] newField = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newField[y][x] = new Cell();
            }
        }
        return newField;
    }

    /**
     * Метод для расстановки мин в случайных клетках созданного поля (кроме стартовой позиции)
     */
    private void minesRandomizer(int minesCount, int startX, int startY) {
        Random random = new Random();
        int height = field.length;
        int width = field[0].length;
        int fieldSize = width * height;
        ArrayList <Integer> list = new ArrayList<>(fieldSize);
        for (int i = 0; i < fieldSize; i++) {
            list.add(i);
        }

        for (int i = 0; i < minesCount; i++) {
            int randomIndex = random.nextInt(list.size());
            int randomElement = list.remove(randomIndex);

            int y = randomElement / width;
            int x = randomElement % width;

            if (x == startX && y == startY){
                i--;
                continue;
            }
            field[y][x].isMine = true;
        }
    }

    /**
     * Метод для добавления на поле цифр в зависимости от количества мин в радиусе 1 клетки от текущей
     */
    private void addNumbers (){

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (!field[y][x].isMine) {
                    field[y][x].adjacentMines = counterOfMinesAround(y,x);
                }
            }
        }
    }

    /**
     * Метод для вывода поля в консоль
     */
    public void printTheField (Cell [][] field){
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (!field[y][x].isRevealed){
                    if (field[y][x].isFlagged){
                        System.out.print("⚑ ");
                    } else {
                        System.out.print("█ ");
                    }
                } else {
                    if (field[y][x].isMine){
                        System.out.print("* ");
                    } else {
                        System.out.print(field[y][x].adjacentMines + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
    * Метод для подсчёта мин в радиусе 1 клетки от текущей
    * @return counter - возвращает количество мин вокруг клетки
    */
    private int counterOfMinesAround (int currentRow, int currentColumn) {
        int counter = 0;

        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                int row = currentRow + m;
                int column = currentColumn + n;

                if (row >= 0 && row < field.length && column >= 0 && column < field[0].length && field[row][column].isMine) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Метод для добавления/удаления флага из клетки
     */
    public void flagCell (int x, int y){
        validateInput(field[0].length, field.length, x, y);
        field[x][y].isFlagged = !field[x][y].isFlagged;
    }

    /**
     * Метод для открытия клетки или целого поля
     */
    public void revealCell (int x, int y){
        validateInput(field[0].length, field.length, x, y);
        if (field[x][y].isRevealed){
            return;
        }

        if (field[x][y].isMine){
            revealWholeField();
        } else {
            field[x][y].isRevealed = true;
        }
    }

    /**
     * Метод, реализующий логику открытия целого поля
     */
    private void revealWholeField () {
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[0].length; x++) {
                field[x][y].isRevealed = true;
            }
        }
    }
}
