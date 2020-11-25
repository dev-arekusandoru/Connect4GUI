import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GUI {

    JLabel[][] grid = new JLabel[6][7];

    JButton[] buttons = new JButton[7];

    public GUI() {
        JFrame mainFrame = new JFrame("Connect 4");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocation(400, 150);
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();

        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(new EmptyBorder(0, 15, 0, 15));
        gridPanel.setLayout(new GridLayout(6, 7));
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new JLabel("-");
                gridPanel.add(grid[i][j]);
            }
        }
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 7));
         for(int i = 1; i <= buttons.length; i++) {
             buttons[i-1] = new JButton(String.valueOf(i));
             buttons[i-1].setSize(new Dimension(20,20));
             buttonsPanel.add(buttons[i-1]);
         }

         mainPanel.add(gridPanel);
         mainPanel.add(buttonsPanel);
         mainPanel.add(new JPanel());

         mainFrame.pack();
         mainFrame.setVisible(true);

    }

public static void main(String[] args) {
    new GUI();
}
}