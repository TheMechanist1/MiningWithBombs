package com.mechgames.game.tile;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.gfx.Image;

public class DirtTile extends Tile{
    public DirtTile(Vector2d vector2d) {
        super(vector2d, Image.load("sprites/dirtTile.png"));
    }
}
