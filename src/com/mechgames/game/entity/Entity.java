package com.mechgames.game.entity;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;

public class Entity {
    private Image entityImage;
    private Vector2d position;

    public Entity(Vector2d position, Image entityImage) {
        this.position = position;
        this.entityImage = entityImage;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void render(Renderer renderer) {
        renderer.drawImage(entityImage, (int)position.getX(), (int)position.getY());
    }

    public void translate(double x, double y) {
        this.position.add(new Vector2d(x, y));
    }
}
