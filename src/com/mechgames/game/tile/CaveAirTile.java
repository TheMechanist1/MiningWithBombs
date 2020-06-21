package com.mechgames.game.tile;

import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;

public class CaveAirTile extends Tile {

    public CaveAirTile(Vector2d vector2d) {
        super(vector2d, Image.load("textures/tiles/airTile.png"));
        this.setCollidable(false);
    }

}
