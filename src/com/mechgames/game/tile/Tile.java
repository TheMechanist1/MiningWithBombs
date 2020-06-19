package com.mechgames.game.tile;

import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.UI.Camera;

public class Tile {
    public static int size = 16;
    private Vector2d position;
    private Image tileImage;
    private boolean collidable = true;

    public Tile(Vector2d vector2d, Image image) {
        this.position = vector2d;
        this.tileImage = image;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public void draw(Renderer renderer) {
        renderer.drawImage(tileImage, (int)position.getX(), (int)position.getY());
    }

    public boolean isOnScreen(Camera cam, int width, int height) {
        return isIntersecting(new Vector2d(cam.getPosition().getX() - size, cam.getPosition().getY() - size), width + size * 2, height + size * 2);
    }

    public boolean isIntersecting(Vector2d v2d, int otherWidth, int otherHeight) {
        return this.position.getX() < v2d.getX() + otherWidth &&
                this.position.getX() + tileImage.getW() > v2d.getX() &&
                this.position.getY() < v2d.getY() + otherHeight &&
                this.position.getY() + tileImage.getH() > v2d.getY();
    }

    public boolean isIntersecting(int otherX, int otherY, int otherWidth, int otherHeight) {
        return this.position.getX() < otherX + otherWidth &&
                this.position.getX() + tileImage.getW() > otherX &&
                this.position.getY() < otherY + otherHeight &&
                this.position.getY() + tileImage.getH() > otherY;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
}
