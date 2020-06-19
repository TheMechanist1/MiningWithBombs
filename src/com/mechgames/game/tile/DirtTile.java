package com.mechgames.game.tile;

import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;

public class DirtTile extends Tile{
    public DirtTile(Vector2d vector2d) {
        super(vector2d, Image.load("sprites/dirtTile.png"));
    }
}
