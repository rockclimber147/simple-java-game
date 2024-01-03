package display;

import game.GameData;
import gameEntities.EntityPlayerShip;
import linear2D.Vector;

import javax.swing.*;
import java.awt.*;

public class GameDisplayPanel extends JPanel{
    // Screen Settings
    private final int screenWidth;
    private final int screenHeight;
    private GameData data;
    public GameDisplayPanel(GameData data, int width, int height) {
        screenWidth = width;
        screenHeight = height;
        this.data = data;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        data.draw(g2);
//        paintDebugInfo(g2);
        g2.dispose();
    }

}
