package com.mechgames.game.tile;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.gfx.ImageTile;
import com.mechgames.game.UI.Camera;

public class Tile {
    public static int size = 16;
    private Vector2d position;
    private Image tileImage;

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
        return isIntersecting(new Vector2d(cam.getPosition().getX() - size, cam.getPosition().getY() - size), width + size*2, height+size*2);
    }

    public boolean isIntersecting(Vector2d v2d, int otherWidth, int otherHeight) {
        return this.position.getX() < v2d.getX() + otherWidth &&
                this.position.getX() + tileImage.getW() > v2d.getX() &&
                this.position.getY() < v2d.getY() + otherHeight &&
                this.position.getY() + tileImage.getH() > v2d.getY();
    }

    public void translate(double x, double y) {
        this.position.add(new Vector2d(x, y));
    }
}
