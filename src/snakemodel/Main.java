package snakemodel;

import java.util.Scanner;

/**
 *
 * @author leonardo
 */
public class Main {

    private static Model model=new Model();
    
    public static void main(String[] args) {
        model.start(80, 25);
        Scanner sc = new Scanner(System.in);
        while (true) {
            printScreen();
            int direction = sc.nextInt();
            model.setDirection(direction);
            model.nextStep();
            if (model.isGameOver()) {
                System.out.println("GAME OVER !");
                break;
            }
        }
    }

    public static void printScreen() {
        System.out.println("SCORE: "+model.getScore());
        char[][] screen=model.getScreen();
        for (int y=0; y<screen[0].length; y++) {
            for (int x=0; x<screen.length; x++) {
                System.out.print(screen[x][y]);
            }
            System.out.println("");
        }
    }
    
}
