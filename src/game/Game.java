package game;

import display.GameDisplayPanel;
import display.GameWindow;
import input.MouseInputHandler;
import input.MouseInputState;

public class Game implements Runnable{
    public static int tickCount = 0;
    private final int FPS = 60;

    private Thread gameThread;
    private final MouseInputState mouseInputState;
    private final GameWindow window;
    private final GameDisplayPanel gameDisplayPanel;

    private final GameData data;


    public Game(int width, int height){
        this.mouseInputState = new MouseInputState();
        this.data = new GameData(mouseInputState, width, height);
        this.window = new GameWindow();
        this.gameDisplayPanel = new GameDisplayPanel(data, width, height);

        MouseInputHandler mouseInputHandler = new MouseInputHandler(mouseInputState);
        window.add(gameDisplayPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        this.gameDisplayPanel.addMouseListener(mouseInputHandler);
        this.gameDisplayPanel.addMouseMotionListener(mouseInputHandler);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
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
        gameDisplayPanel.repaint();
        tickCount++;
    }

    private void update() {
        data.update();
    }
}
