package display;

import javax.swing.*;
import java.awt.*;

public class GameDisplayPanel extends JPanel{
    // Screen Settings
    private final int screenWidth = 1080;
    private final int screenHeight = 640;

    public GameDisplayPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.dispose();
    }

}
