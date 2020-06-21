package com.mechgames.game.tile;

import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;

public class GrassTile extends Tile{
    public GrassTile(Vector2d vector2d) {
        super(vector2d, Image.load("textures/tiles/grassTile.png"));
    }
}
