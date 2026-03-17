package minesweeper;

import java.util.Random;

public class minefieldGenerator {

    public char [][] generateMinefield (int width, int height, int minesCount){
        char [][] mineField = new char[height][width];
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Ширина и высота поля не могут быть меньше 1");
        } else if (minesCount >= width*height) {
            throw new IllegalArgumentException("Количество мин на поле не должно быть больше/равно количеству клеток поля");
        }

        char mine = '*';
        Random random = new Random();
        int i = 0;

        while (i < minesCount){
            int y = random.nextInt(mineField.length);
            int x = random.nextInt(mineField[0].length);

            if (mineField[y][x] != mine) {
                mineField[y][x] = mine;
                i++;
            }
        }

        for (int y = 0; y < mineField.length; y++) {
            for (int x = 0; x < mineField[y].length; x++) {
                if (mineField [y][x] != mine){
                    int counter = counterOfMinesAround(mineField, y, x, height, width);
                    mineField [y][x] = (char) (counter + '0');
                    if (counter == 0){
                        mineField [y][x] = '█';
                    }
                }
                System.out.print(mineField[y][x] + " ");
            }
            System.out.println();
        }
        return mineField;
    }

    /**
     * Метод для подсчёта мин в радиусе 1 клетки от текущей
     * @return counter - возвращает количество мин вокруг клетки
     */
    private int counterOfMinesAround (char [][] mineField, int currentRow, int currentColumn, int height, int width){
        int counter = 0;

        for (int m = -1; m <= 1; m++){
            for (int n = -1; n <= 1; n++){
                int row = currentRow + m;
                int column = currentColumn + n;

                if (row >= 0 && row < height && column >= 0 && column < width && mineField[row][column] == '*'){
                    counter++;
                }
            }
        }
        return counter;
    }
}
