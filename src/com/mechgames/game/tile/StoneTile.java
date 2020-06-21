package com.mechgames.game.tile;

import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;

public class StoneTile extends Tile {
    public StoneTile(Vector2d vector2d) {
        super(vector2d, Image.load("textures/tiles/stoneTile.png"));
    }
}
