import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    char gameVariable = 'x';
    JButton[] buttons = new JButton[10];
    Timer timer;

    public Game()
    {

        setLayout(new GridLayout(3,3));
        initializeButtons();

    }
    public void initializeButtons()
    {
        for(int i = 0; i<=8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setText(" ");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    if (buttonClicked.getText().charAt(0) == ' ') {
                        buttonClicked.setText(String.valueOf(gameVariable));
                        if (gameVariable == 'x')
                        {
                            buttonClicked.setBackground(Color.PINK);
                            gameVariable = '0';
                        }

                        else
                        {
                            buttonClicked.setBackground(Color.magenta);
                            gameVariable = 'x';

                        }
                    }
                    resetWindows();


                }

            });
            add(buttons[i]);

        }
    }
    public boolean checkColumns()
    {
        int i = 3;
        for(int j = 0 ; j < 3; j++)
            if (buttons[j].getText().equals(buttons[i+j].getText()) && buttons[i+j].getText().equals(buttons[j+2*i].getText()) && buttons[j].getText().charAt(0) != ' ' )
                return true;
        return false;

    }
    public boolean checkRows()
    {
        for (int i = 0; i <= 6; i+=3)
        {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i+1].getText().equals(buttons[i+2].getText()) && buttons[i].getText().charAt(0) != ' ')
                return true;
        }
        return false;
    }

    public boolean checkFull()
    {
        for (int i = 0; i<=8; i++)
            if(buttons[i].getText().charAt(0) == ' ')
                return false;
        return true;
    }

    public void resetButtons()
    {
        for(int i = 0; i<=8; i++) {
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.WHITE);
        }
    }

    public boolean winner()
    {
        if (checkDiagonals() || checkColumns() || checkRows()) {
            return true;
        }
        else return false;
    }
    public boolean checkDiagonals()
    {

        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText()) && buttons[0].getText().charAt(0) != ' ')
            return true;
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText()) && buttons[2].getText().charAt(0) != ' ')
            return true;
        return false;
    }
    public void resetWindows()
    {
        if (checkFull() || winner()) {
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DisplayWinner();
                    resetButtons();
                    timer.stop();
                }
            });
            timer.start();
        }

    }
    public void DisplayWinner()
    {
        String displayMessage = "No one wins.";
        if (winner())
          if (gameVariable == 'x')
                displayMessage = "0 Player won";
            else
                displayMessage = "X Player won";
        JFrame windows3 = new JFrame("Winner");
        windows3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows3.setBounds(500,500,700,700);

        JLabel label = new JLabel(displayMessage, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        windows3.getContentPane().add(label);
        windows3.setVisible(true);

        Timer closeTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windows3.dispose();
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();

    }


}