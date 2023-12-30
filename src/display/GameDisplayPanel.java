package display;

import gameEntities.EntityPlayer;
import linear2D.Vector;

import javax.swing.*;
import java.awt.*;

public class GameDisplayPanel extends JPanel{
    // Screen Settings
    private final int screenWidth = 1080;
    private final int screenHeight = 640;
    private EntityPlayer player;
    public GameDisplayPanel(EntityPlayer player) {
        this.player = player;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        player.draw(g2);
//        paintDebugInfo(g2);
        g2.dispose();
    }

    private void paintDebugInfo(Graphics2D g2){
        Vector playerPosition = player.getPosition();
        g2.drawString(player.toString(), playerPosition.getX(), playerPosition.getY());
    }

}
