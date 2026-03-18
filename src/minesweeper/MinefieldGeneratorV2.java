package minesweeper;

import java.util.Random;

public class MinefieldGeneratorV2 {

    private static final char MINE = '*';
    private static final char EMPTY_CELL = '█';


    public char[][] generateMinefield(int width, int height, int minesCount) {
        parametersCheck(width,height,minesCount);
        char[][] mineField = fieldCreation(width, height);
        minesRandomizer(mineField,minesCount);
        addNumbers(mineField);

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
        int[] arrayOfIndexes = new int[fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            arrayOfIndexes[i] = i;
        }

        for (int i = 0; i < minesCount; i++) {
            int randomNumber = i + random.nextInt(fieldSize - i);
            int randomIndex = arrayOfIndexes[randomNumber];
            arrayOfIndexes[randomNumber] = arrayOfIndexes[i];

            int y = randomIndex / width;
            int x = randomIndex % width;

            newField[y][x] = MINE;
        }
    }

    /**
     * Метод для добавления на поле цифер (либо символа '█') в зависимости от количества мин в радиусе 1 клетки от текущей
     */
    private void addNumbers (char [][] newField){
        int height = newField.length;
        int width = newField[0].length;

        for (int y = 0; y < newField.length; y++) {
            for (int x = 0; x < newField[y].length; x++) {
                if (newField[y][x] != MinefieldGeneratorV2.MINE) {
                    int counter = counterOfMinesAround(newField, y, x, height, width);
                    newField[y][x] = (char) (counter + '0');
                    if (counter == 0) {
                        newField[y][x] = MinefieldGeneratorV2.EMPTY_CELL;
                    }
                }
                System.out.print(newField[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
    * Метод для подсчёта мин в радиусе 1 клетки от текущей
    * @return counter - возвращает количество мин вокруг клетки
    */
    private int counterOfMinesAround ( char[][] mineField, int currentRow, int currentColumn, int height, int width) {
        int counter = 0;

        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                int row = currentRow + m;
                int column = currentColumn + n;

                if (row >= 0 && row < height && column >= 0 && column < width && mineField[row][column] == '*') {
                    counter++;
                }
            }
        }
        return counter;
    }
}
