package com.mechgames.game.entity;

import com.mechgames.engine.Input;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.tile.Tile;

import java.awt.event.KeyEvent;


public class Player extends Entity{
    Input input;
    int speed = 1;


    public Player(Vector2d position, Image entityImage) {
        super(position, entityImage);

    }

    public void update() {
        input = MiningWithBombs.instance.getEngine().getInput();

        Tile tile;


        if (input.isKey(KeyEvent.VK_A)) {
            getPosition().setX(getPosition().getX() - speed);
            tile = collidingWorld();
            if (tile != null) {
                getPosition().setX(tile.getPosition().getX() + this.getEntityImage().getW());
            }
        }
        if (input.isKey(KeyEvent.VK_D)) {
            getPosition().setX(getPosition().getX() + speed);
            tile = collidingWorld();
            if (tile != null) {
                getPosition().setX(tile.getPosition().getX() - this.getEntityImage().getW());
            }
        }
        if (input.isKey(KeyEvent.VK_W)) {
            getPosition().setY(getPosition().getY() - speed);
            tile = collidingWorld();
            if (tile != null) {
                getPosition().setY(tile.getPosition().getY() + this.getEntityImage().getH());
            }
        }

        if (input.isKey(KeyEvent.VK_S)) {
            getPosition().setY(getPosition().getY() + speed);
            tile = collidingWorld();
            if (tile != null) {
                getPosition().setY(tile.getPosition().getY() - this.getEntityImage().getH());
            }
        }


        tile = collidingWorld();

        if (tile != null) {
            setOnGround(true);
            getPosition().setY(tile.getPosition().getY() - this.getEntityImage().getH());
        } else {
            setOnGround(false);
        }
    }
}
