package com.mechgames.game.world;

import com.mechgames.engine.Math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.tile.DirtTile;
import com.mechgames.game.tile.GrassTile;
import com.mechgames.game.tile.Tile;
import com.mechgames.game.tile.TileList;

public class WorldGen {
    public TileList tiles = new TileList();

    public WorldGen() {

    }

    public void update(Camera cam) {

        int camStartX = (((int)cam.getPosition().getX()-1) / Tile.size - 2);
        int camStartY = ((int)cam.getPosition().getY()) / Tile.size - 2;

        int camEndX = ((int)cam.getPosition().getX() / Tile.size) + MiningWithBombs.width / Tile.size + 2;
        int camEndY = ((int)cam.getPosition().getY() / Tile.size) + MiningWithBombs.height / Tile.size + 2;


        for (int x = camStartX; x < camEndX; x++) {
            for (int y = camStartY; y < camEndY; y++) {
                if(!new GrassTile(new Vector2d(x*16, y*16)).isOnScreen(cam, MiningWithBombs.width, MiningWithBombs.height)) continue;

                if(tiles.getTileAt(new Vector2d(x*16, y*16)) == null) {
                    tiles.add(new GrassTile(new Vector2d(x*16, y*16)));
                }
            }
        }
    }
}
