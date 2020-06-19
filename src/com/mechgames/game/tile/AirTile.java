package com.mechgames.game.tile;

import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;

public class AirTile extends Tile {
    public AirTile(Vector2d vector2d) {
        super(vector2d, Image.load("sprites/airTile.png"));
        this.setCollidable(false);
    }
}
