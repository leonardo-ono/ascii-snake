package snakemodel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class Model {

    private char[][] screen;
    private int direction; // 6=cima 14=baixo 9=esq 11=dir
    private Point food;
    private List<Point> snake = new ArrayList<Point>();
    private boolean gameOver;

    public char[][] getScreen() {
        return screen;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    public int getScore() {
        return snake.size()-1;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void start(int columns, int rows) {
        screen = new char[columns][rows];
        for (int x = 0; x < columns; x++) {
            Arrays.fill(screen[x], '.'); // clear screen
        }
        direction = 14;
        gameOver = false;
        food = null;
        snake.clear();
        snake.add(new Point(screen.length / 2, screen[0].length / 2));
    }

    public void nextStep() {
        if (gameOver) {
            return;
        }
        updateFood();
        updateSnake();
    }

    private void updateFood() {
        if (food == null) {
            food = new Point();
            do {
                food.x = (int) (Math.random() * screen.length);
                food.y = (int) (Math.random() * screen[0].length);
            } while (screen[food.x][food.y] != '.');
            screen[food.x][food.y] = '*';
        }
    }

    private void updateSnake() {
        Point newPosition = (Point) snake.get(snake.size() - 1).clone();
        newPosition.x += (direction & 3) - 2;
        newPosition.y += ((direction & 12) >> 2) - 2;
        if (newPosition.equals(food)) {
            food = null;
        } else {
            Point lastPosition = snake.remove(0);
            screen[lastPosition.x][lastPosition.y] = '.';
        }
        if (snake.contains(newPosition)) {
            gameOver = true;
        }
        try {
            snake.add(newPosition);
            screen[newPosition.x][newPosition.y] = '#';
        } catch (ArrayIndexOutOfBoundsException ex) {
            gameOver = true;
        }
    }
    
}
