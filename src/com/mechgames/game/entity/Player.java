package com.mechgames.game.entity;

import com.mechgames.engine.Input;
import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.gfx.Image;
import com.mechgames.game.MiningWithBombs;

import java.awt.event.KeyEvent;


public class Player extends Entity{
    Input input;
    int speed = 1;

    public Player(Vector2d position, Image entityImage) {
        super(position, entityImage);

    }

    public void update() {
        input = MiningWithBombs.instance.getEngine().getInput();

        if(input.isKey(KeyEvent.VK_A)) {
            this.getPosition().setX(this.getPosition().getX() + speed);
        }
        if(input.isKey(KeyEvent.VK_D)) {
            this.getPosition().setX(this.getPosition().getX() - speed);
        }
        if(input.isKey(KeyEvent.VK_W)) {
            this.getPosition().setY(this.getPosition().getY() + speed);
        }
        if(input.isKey(KeyEvent.VK_S)) {
            this.getPosition().setY(this.getPosition().getY() - speed);
        }
    }
}
