package com.mechgames.game.entity;

import com.mechgames.engine.Input;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.ImageTile;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.tile.Tile;

import java.awt.event.KeyEvent;


public class Player extends Entity {
    private Input input;
    private int speed = 1;
    private boolean onGroundLastFrame = false;
    private ImageTile imageMap = new ImageTile("textures/entities/playerImageMap.png", 7, 14);


    private boolean left = false, right = false, up = false, down = false;
    private double time = 0;

    public Player(Vector2d position) {
        super(position, null);
        this.setWidth(7);
        this.setHeight(14);
    }

    public void update() {


        input = MiningWithBombs.instance.getEngine().getInput();

        Tile tile;



        if (input.isKey(KeyEvent.VK_A)) {
            getVelocity().setX(getVelocity().getX() - 1.0);
            left = true;
        } else {
            left = false;
        }
        if (input.isKey(KeyEvent.VK_D)) {
            getVelocity().setX(getVelocity().getX() + 1.0);
            right = true;
        } else {
            right = false;
        }

        getVelocity().setX(getVelocity().getX() * 0.8);

        if (input.isKey(KeyEvent.VK_W)) {
            if (onGroundLastFrame) {
                getVelocity().setY(-7.0);
                up = true;
            } else {
                up = false;
            }
        }


        if (getVelocity().getY() < 1500) {
            getVelocity().setY(getVelocity().getY() + 0.3);
        }

        getPosition().setX(getPosition().getX() + getVelocity().getX());
        tile = collidingWorld();

        if (tile != null) {
            if (getVelocity().getX() < 0.0) getPosition().setX(tile.getPosition().getX() + 16); //left

            if (getVelocity().getX() > 0.0)
                getPosition().setX(tile.getPosition().getX() - this.getWidth()); //right

            getVelocity().setX(0);
        }

        getPosition().setY(getPosition().getY() + getVelocity().getY());
        tile = collidingWorld();

        if (tile != null) {
            if (getVelocity().getY() < 0.0) getPosition().setY(tile.getPosition().getY() + 16);

            if (getVelocity().getY() > 0.0) {
                getPosition().setY(tile.getPosition().getY() - this.getHeight());
                onGroundLastFrame = true;
            }
            getVelocity().setY(0);

        } else {
            onGroundLastFrame = false;
        }
    }

    @Override
    public void render(Renderer renderer) {
        time += 1.0 / 60.0;

        if (right) {
            if (time < 1) {
                renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 0, 1);
            } else if (time < 2) {
                renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 0, 2);
            } else {
                time = 0;
            }
        } else if (left) {
            if (time < 1) {
                renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 1, 1);
            } else if (time < 2) {
                renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 1, 2);
            } else {
                time = 0;
            }
        } else if (up) {
            renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 2, 0);
        } else if (down) {
            renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 3, 0);
        } else {
            renderer.drawTileImage(imageMap, (int) getPosition().getX(), (int) getPosition().getY(), 0, 0);
        }

    }
}
