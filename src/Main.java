import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame game = new JFrame("Tic-tac-toe");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().add(new Game());
        game.setBounds(500,500,700,700);
        game.setVisible(true);






    }
}
