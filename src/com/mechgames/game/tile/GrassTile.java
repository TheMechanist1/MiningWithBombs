package com.mechgames.game.tile;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.gfx.Image;

public class GrassTile extends Tile{
    public GrassTile(Vector2d vector2d) {
        super(vector2d, Image.load("sprites/grassTile.png"));
    }
}
