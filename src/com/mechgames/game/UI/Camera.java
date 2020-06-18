package com.mechgames.game.UI;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.game.MiningWithBombs;

public class Camera {
    private Vector2d position = new Vector2d(0, 0);

    public Camera() {}

    public void update(Vector2d position) {
        this.position.setX((position.getX() + this.position.getX() - (MiningWithBombs.width/2.0)) / 2);
        this.position.setY((position.getY() + this.position.getY() - (MiningWithBombs.height/2.0)) / 2);
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
