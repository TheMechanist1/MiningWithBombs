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
    private Vector2d velocity;
    private boolean onGround = false;

    public Entity(Vector2d position, Image entityImage) {
        this.position = position;
        this.entityImage = entityImage;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void render(Renderer renderer) {
        renderer.drawImage(entityImage, (int) position.getX(), (int) position.getY());
    }

    public Tile collidingWorld() {
        for (Tile tile : MiningWithBombs.instance.generator.getTileList().getTiles()) {
            if (tile.isCollidable() && tile.isIntersecting((int) position.getX(), (int) position.getY(), getEntityImage().getW(), getEntityImage().getH())) {
                return tile;
            }
        }
        return null;
    }

    public boolean isOnScreen(Camera cam, int width, int height) {
        return isIntersecting(new Vector2d(cam.getPosition().getX() - entityImage.getW(), cam.getPosition().getY() - entityImage.getH()), width + entityImage.getW() * 2, height + entityImage.getH() * 2);
    }

    public boolean isIntersecting(Vector2d v2d, int otherWidth, int otherHeight) {
        return this.position.getX() < v2d.getX() + otherWidth &&
                this.position.getX() + entityImage.getW() > v2d.getX() &&
                this.position.getY() < v2d.getY() + otherHeight &&
                this.position.getY() + entityImage.getH() > v2d.getY();
    }

    public Image getEntityImage() {
        return entityImage;
    }
}
