package gameEntities;

import linear2D.Vector;

import java.util.ArrayList;

import java.lang.Math;

public class EntityAsteroid extends EntityGeneric {
    private final int generation;
    public EntityAsteroid(Vector position, Vector currentVelocity, float collisionRadius, float mass, float hitPoints, int generation) {
        super(position, currentVelocity, collisionRadius, mass, hitPoints);
        this.generation = generation;
    }

    public EntityAsteroid(EntityAsteroid parent){
        super(parent.position.clone(), parent.currentVelocity.clone(), parent.collisionRadius * .75f, parent.mass * .75f, parent.hitPoints*.75f);
        this.generation = parent.generation - 1;
    }

    @Override
    public ArrayList<EntityGeneric> onLethalDamage(){
        if (this.generation == 0) {
            return null;
        }

        ArrayList<EntityGeneric> children = new ArrayList<>();

        float randomChildXVelocity = (float) (2 * Math.random() - 1) / 2;
        float randomChildYVelocity = (float) (2 * Math.random() - 1) / 2;

        Vector randomChildVelocity = new Vector(randomChildXVelocity, randomChildYVelocity);
        EntityAsteroid firstChild = new EntityAsteroid(this);
        firstChild.getVelocity().addVector(randomChildVelocity);
        EntityAsteroid secondChild = new EntityAsteroid(this);
        secondChild.getVelocity().subtractVector(randomChildVelocity);

        children.add(firstChild);
        children.add(secondChild);
        return children;
    }
}
