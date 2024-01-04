package game;

import gameEntities.Entity;
import gameEntities.EntityAsteroid;
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
        entities.add(new EntityAsteroid(new Vector(50,50), new Vector(.1f, .2f), 30, 20, 100, 3));
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
        updateEntities();
        handlePlayerCollisions();
        player.update();
    }
    private void updateEntities(){
        for (int i = 0; i < entities.size(); i++){
            EntityGeneric currentEntity = entities.get(i);
            if (!currentEntity.isActive()){
                handleEntityListAddition(currentEntity.onLethalDamage());
                entities.remove(i);
                i--;
            }
        }
    }

    private void handleEntityListAddition(ArrayList<EntityGeneric> entityList){
        if (entityList == null){
            return;
        }
        for (EntityGeneric entity: entityList){
            if (entity instanceof EntityAsteroid){
                entities.add(entity);
            }
        }
    }

    private void handlePlayerCollisions(){
        for (EntityGeneric entity: entities){
            if(player.checkCollision(entity)){
                VectorMath.handleCollision(player, entity);
                entity.takeDamage(100);
            }
            entity.update();
        }
    }
}
