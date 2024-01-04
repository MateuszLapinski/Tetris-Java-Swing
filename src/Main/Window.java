package Main;

import javax.swing.*;

public class Window extends JFrame  {
    private GamePanel gamePanel=new GamePanel();
    public Window(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(gamePanel);
        this.pack();
        gamePanel.launchGame();
    }


}
