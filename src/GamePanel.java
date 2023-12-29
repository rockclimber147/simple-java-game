import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    private final int screenWidth = 1080;
    private final int screenHeight = 640;
    private final double FPS = 60;
    private int tickCount = 0;

    private Thread gameThread;
    private final MouseInputState mouseInputState;

    GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.mouseInputState = new MouseInputState();
        MouseInputHandler mouseInputHandler = new MouseInputHandler(mouseInputState);

        this.addMouseListener(mouseInputHandler);
        this.addMouseMotionListener(mouseInputHandler);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(mouseInputState.mouseX, mouseInputState.mouseY, screenWidth / 10, screenHeight / 10);

        g2.dispose();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                tick();
                delta--;
            }
        }
    }

    private void tick(){
        update();
        repaint();
        tickCount++;
        System.out.println("tick:" + tickCount);
    }
}
