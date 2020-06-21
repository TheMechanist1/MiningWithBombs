package com.mechgames.game.entity;

import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.tile.Tile;

public class Entity {
    private Image entityImage;
    private Vector2d position;
    private Vector2d velocity = new Vector2d(0, 0);
    private int width = 0;
    private int height = 0;

    public Entity(Vector2d position, Image entityImage) {
        this.position = position;
        this.entityImage = entityImage;

        if (entityImage != null) {
            width = entityImage.getW();
            height = entityImage.getH();
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void render(Renderer renderer) {
        renderer.drawImage(entityImage, (int) position.getX(), (int) position.getY());
    }

    public Tile collidingWorld() {
        for (Tile tile : MiningWithBombs.instance.generator.getTileList().getTiles()) {
            if (tile.isCollidable() && tile.isIntersecting(position.getX(), position.getY(), getWidth(), getHeight())) {
                return tile;
            }
        }
        return null;
    }

    public boolean isOnScreen(Camera cam, int width, int height) {
        return isIntersecting(new Vector2d(cam.getPosition().getX() - getWidth(), cam.getPosition().getY() - getHeight()), width + entityImage.getW() * 2, height + getHeight() * 2);
    }

    public boolean isIntersecting(Vector2d v2d, int otherWidth, int otherHeight) {
        return this.position.getX() < v2d.getX() + otherWidth &&
                this.position.getX() + getWidth() > v2d.getX() &&
                this.position.getY() < v2d.getY() + otherHeight &&
                this.position.getY() + getHeight() > v2d.getY();
    }

    public Image getEntityImage() {
        return entityImage;
    }

    public void setEntityImage(Image entityImage) {
        this.entityImage = entityImage;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
