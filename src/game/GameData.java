package game;

import gameEntities.Entity;
import gameEntities.EntityGeneric;
import gameEntities.EntityPlayerShip;
import input.MouseInputState;
import linear2D.Vector;
import linear2D.VectorMath;

import java.awt.*;
import java.util.ArrayList;

public class GameData implements Entity {

    private final int gameWidth;
    private final int gameHeight;
    private final EntityPlayerShip player;
    private final ArrayList<EntityGeneric> entities = new ArrayList<>();

    public GameData(MouseInputState input, int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.player = new EntityPlayerShip(input);
        entities.add(new EntityGeneric(new Vector(50,50), new Vector(.1f, .2f), 30, 20));
    }

    @Override
    public void draw(Graphics2D g2) {
        player.draw(g2);
        for (EntityGeneric entity: entities){
            entity.draw(g2);
        }
    }

    @Override
    public void update() {
        for (EntityGeneric entity: entities){
            if(player.checkCollision(entity)){
                VectorMath.handleCollision(player, entity);
            }
            entity.update();
        }
        player.update();
    }
}
