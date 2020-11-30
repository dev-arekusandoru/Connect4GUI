import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    int player = 1;
    boolean w = false;

    JButton[][] grid = new JButton[6][7];
    JButton[] buttons = new JButton[7];

    public GUI() {
        JFrame mainFrame = new JFrame("Connect 4");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(300, 270));
        mainFrame.setResizable(false);

        mainFrame.setLocation(400, 150);
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();

        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        gridPanel.setLayout(new GridLayout(6, 7));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new JButton("-");
                grid[i][j].setBorder(new EmptyBorder(0, 0, 0, 0));
                gridPanel.add(grid[i][j]);
            }
        }
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));
        buttonsPanel.setLayout(new GridLayout(1, 7));
        for (int i = 1; i <= buttons.length; i++) {
            buttons[i - 1] = new JButton(String.valueOf(i));
            buttons[i - 1].setSize(new Dimension(20, 20));
            buttons[i - 1].addActionListener(this);
            buttonsPanel.add(buttons[i - 1]);
        }

        mainPanel.add(gridPanel);
        mainPanel.add(buttonsPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        Object control = e.getSource();

        for (int i = 0; i < buttons.length; i++) {
            if (control == buttons[i] && (!w)) {
                placeTile(i);
                w = checkWin();
            }
        }
        if (w) {
            String[] options = {"Play again", "Cancel"};
            int input = JOptionPane.showOptionDialog(null,
                    "Player " + player + " won.",
                    "WIN", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(input == 0) {
                clear();
                w = false;
            }
        }

    }

    public void placeTile(int column) {
        String tile = "-";
        if (player == 1) {
            tile = "X";
        } else if (player == 2) {
            tile = "O";
        }
        for (int i = 5; i >= 0; i--) {
            if (grid[i][column].getText().equals("-")) {
                grid[i][column].setText(tile);
                if (player == 1) {
                    player = 2;
                } else if (player == 2) {
                    player = 1;
                }
                return;
            }
            else if(i == 0 && !(grid[i][column].getText().equals("-"))) {
                JOptionPane.showMessageDialog(null, "Column full.", "Invalid Column", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public boolean checkWin() {
        String toCheck = "X";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getText().equals(toCheck)) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (!(k == 0 && l == 0)) {
                                if ((i + k >= 0 && j + l >= 0) && (i + k <= 5 && j + l <= 6)) {
                                    if (grid[i + k][j + l].getText().equals(toCheck)) {
                                        if (k == -1 && l == -1) { // diag up right
                                            if (i + k - 2 >= 0 && j + l - 2 >= 0) {
                                                if (grid[i + k - 1][j + l - 1].getText().equals(toCheck)
                                                        && grid[i + k - 2][j + l - 2].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        } else if (k == 1 && l == -1) { // diag down left
                                            if (i + k + 2 <= 5 && j + l - 2 >= 0) {
                                                if (grid[i + k + 1][j + l - 1].getText().equals(toCheck)
                                                        && grid[i + k + 2][j + l - 2].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        } else if (k == -1 && l == 1) { // diag up right
                                            if (i + k - 2 >= 0 && j + l + 2 <= 5) {
                                                if (grid[i + k - 1][j + l + 1].getText().equals(toCheck)
                                                        && grid[i + k - 2][j + l + 2].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        } else if (k == 1 && l == 1) { // diag down right
                                            if (i + k + 2 <= 5 && j + l + 2 <= 5) {
                                                if (grid[i + k + 1][j + l + 1].getText().equals(toCheck)
                                                        && grid[i + k + 2][j + l + 2].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        }
                                        // no need for a second conditional
                                        else if (k == -1) { // straight up
                                            if (i + k - 2 >= 0) {
                                                if (grid[i + k - 1][j].getText().equals(toCheck)
                                                        && grid[i + k - 2][j].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        } else if (k == 1) { // straight down
                                            if (i + k + 2 <= 5) {
                                                if (grid[i + k + 1][j].getText().equals(toCheck)
                                                        && grid[i + k + 2][j].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }

                                        } else if (l == 1) { // straight right
                                            if (j + l + 2 <= 5) {
                                                if (grid[j][j + l + 1].getText().equals(toCheck)
                                                        && grid[i][j + l + 1].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        } else if (l == -1) { // straight left
                                            if (j + l - 2 >= 0) {
                                                if (grid[i][j + l - 1].getText().equals(toCheck)
                                                        && grid[i][j + l - 2].getText().equals(toCheck)) {
                                                    return true;
                                                }
                                            }
                                        }


                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        return false;
    }

    public void clear() {
        for (JButton[] jButtons : grid) {
            for (JButton jButton : jButtons) {
                jButton.setText("-");
            }
        }
    }

    public static void main(String[] args) {
        new GUI();

    }
}
