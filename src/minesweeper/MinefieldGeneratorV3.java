package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class MinefieldGeneratorV3 {

    private static final char MINE = '*';
    private static final char EMPTY_CELL = '█';


    public char[][] generateMinefield(int width, int height, int minesCount) {
        parametersCheck(width,height,minesCount);
        char[][] mineField = fieldCreation(width, height);
        minesRandomizer(mineField,minesCount);
        addNumbers(mineField);
        printTheField(mineField);

        return mineField;
    }

    /**
     * Метод для проверки допустимости введённых параметров
     */
    private void parametersCheck(int width, int height, int minesCount) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Ширина и высота поля не могут быть меньше 1");
        } else if (minesCount >= width * height) {
            throw new IllegalArgumentException("Количество мин на поле не должно быть больше/равно количеству клеток поля");
        }
    }

    /**
     * Метод для создания пустого поля по указанным ранее параметрам
     * @return newField - возвращает созданное поле
     */
    private char[][] fieldCreation(int width, int height) {
        char[][] newField = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newField[y][x] = EMPTY_CELL;
            }
        }
        return newField;
    }

    /**
     * Метод для расстановки мин в случайных клетках созданного поля
     */
    private void minesRandomizer(char[][] newField, int minesCount) {
        Random random = new Random();
        int height = newField.length;
        int width = newField[0].length;
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

            newField[y][x] = MINE;
        }
    }

    /**
     * Метод для добавления на поле цифр (либо символа '█') в зависимости от количества мин в радиусе 1 клетки от текущей
     */
    private void addNumbers (char [][] newField){

        for (int y = 0; y < newField.length; y++) {
            for (int x = 0; x < newField[y].length; x++) {
                if (newField[y][x] != MinefieldGeneratorV3.MINE) {
                    int counter = counterOfMinesAround(newField, y, x);
                    newField[y][x] = (char) (counter + '0');
                    if (counter == 0) {
                        newField[y][x] = MinefieldGeneratorV3.EMPTY_CELL;
                    }
                }
            }
        }
    }

    /**
     * Метод для вывода поля в консоль
     */
    private void printTheField (char [][] printableField){
        for (int y = 0; y < printableField.length; y++) {
            for (int x = 0; x < printableField[y].length; x++) {
                System.out.print(printableField[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
    * Метод для подсчёта мин в радиусе 1 клетки от текущей
    * @return counter - возвращает количество мин вокруг клетки
    */
    private int counterOfMinesAround ( char[][] mineField, int currentRow, int currentColumn) {
        int height = mineField.length;
        int width = mineField[0].length;
        int counter = 0;

        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                int row = currentRow + m;
                int column = currentColumn + n;

                if (row >= 0 && row < height && column >= 0 && column < width && mineField[row][column] == MinefieldGeneratorV3.MINE) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
